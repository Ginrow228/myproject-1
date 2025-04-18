package jdbc;

import model.DatabaseOperation;
import model.Device;
import model.Network;
import model.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworksDao {

    public NetworksDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Network save(Network networkToSave) throws SQLException {
        return getInfoFromDb(conn -> {
            try(var statement = conn.prepareStatement("insert into networks (name, description) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, networkToSave.getName());
                statement.setString(2, networkToSave.getDescription());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    networkToSave.setId(rs.getLong(1));
                    try (var timestampQuery = conn.prepareStatement(
                            "SELECT created_at FROM networks WHERE id = ?")) {
                        timestampQuery.setLong(1, networkToSave.getId());
                        ResultSet tsRs = timestampQuery.executeQuery();
                        if (tsRs.next()) {
                            networkToSave.setCreated_at(tsRs.getDate("created_at"));
                        }
                    }
                } else {
                    throw new RuntimeException("Failed to get ID for new network");
                }
                return networkToSave;
            } catch (Exception e) {
                throw new RuntimeException("Error saving network: " + e.getMessage());
            }
        });
    }

    public List<Network> getNetworks() throws SQLException {
        return getInfoFromDb(conn -> {
            try(var statement = conn.createStatement()) {
                var rs = statement.executeQuery("Select * from networks");
                var list = new ArrayList<Network>();
                while (rs.next()) {
                    var id = rs.getLong("id");
                    var name = rs.getString("name");
                    var description = rs.getString("description");
                    var createdAt = rs.getDate("created_at");
                    list.add(new Network(id, name, description, createdAt));
                }
                return list;
            } catch (Exception e) {
                throw new RuntimeException("Error getting list of networks: " + e.getMessage());
            }
        });
    }

    public List<Network> findNetworksByName(String name) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT * from networks WHERE name like ?";
                try (var statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + name + "%");
                    var rs = statement.executeQuery();

                    List<Network> networks = new ArrayList<>();
                    while (rs.next()) {
                        Long networkId = rs.getLong("id");
                        String networkName = rs.getString("name");
                        String description = rs.getString("description");
                        Date createdAt = rs.getDate("created_at");

                        networks.add(new Network(networkId, networkName, description, createdAt));
                    }
                    return networks;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error searching for networks: " + e.getMessage());
            }
        });
    }

    public List<Network> getNetworksWithDevices() throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT n.id as network_id, n.name as network_name, n.description, n.created_at as network_created_at, " +
                        "d.id as device_id, d.name as device_name, d.ip_address, d.mac_address, d.type, d.status, d.created_at as device_created_at " +
                        "FROM networks n " +
                        "LEFT JOIN devices d ON n.id = d.network_id " +
                        "ORDER BY n.id";

                try(var statement = connection.createStatement();
                    var rs = statement.executeQuery(query)) {
                    List<Network> networks = new ArrayList<>();
                    Network currNetwork = null;
                    Long currNetworkId = null;

                    while (rs.next()) {
                        Long networkId = rs.getLong("network_id");
                        if(currNetworkId == null || !currNetworkId.equals(networkId)) {
                            String networkName = rs.getString("network_name");
                            String description = rs.getString("description");
                            Date networkCreatedAt = rs.getDate("network_created_at");

                            currNetwork = new Network(networkId, networkName, description, networkCreatedAt);
                            networks.add(currNetwork);
                            currNetworkId = networkId;
                        }
                        Long deviceId = rs.getLong("device_id");
                        if(!rs.wasNull()) {
                            String deviceName = rs.getString("device_name");
                            String ipAddress = rs.getString("ip_address");
                            String macAddress = rs.getString("mac_address");
                            String type = rs.getString("type");
                            String status = rs.getString("status");
                            Date deviceCreatedAt = rs.getDate("device_created_at");

                            model.Device device = new model.Device(deviceId, networkId, deviceName, ipAddress, macAddress, type, status, deviceCreatedAt);
                            currNetwork.addDevice(device);
                        }
                    }
                    return networks;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errors when receiving networks with the device: " + e.getMessage());
            }
        });
    }

    public int countActiveDevicesInNetwork(Long networkId) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT COUNT(*) as count FROM devices WHERE network_id = ? AND status = 'active'";
                try (var statement = connection.prepareStatement(query)) {
                    statement.setLong(1, networkId);
                    var rs = statement.executeQuery();

                    if (rs.next()) {
                        return rs.getInt("count");
                    }

                    return 0;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error counting active devices: " + e.getMessage(), e);
            }
        });
    }

    public <T> T getInfoFromDb(DatabaseOperation<T> operation) throws SQLException {
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/network_db", "admin", "admin")) {
            return operation.execute(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

