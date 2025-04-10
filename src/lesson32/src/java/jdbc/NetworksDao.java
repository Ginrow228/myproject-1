package jdbc;

import model.DatabaseOperation;
import model.Device;
import model.Network;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworksDao {

    public NetworksDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Network save(Network networkToSave) throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/network_db", "admin", "admin")) {
            try(var statement = conn.prepareStatement("insert into networks (name, description) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, networkToSave.getName());
                statement.setString(2, networkToSave.getDescription());
                statement.executeQuery();
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                networkToSave.setId(rs.getLong("id"));
                networkToSave.setCreated_at(rs.getDate("created_at"));
            }
        } catch (Exception e) {

        }
        return networkToSave;
    }

    public Device save(Device deviceToSave) throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/network_db", "admin", "admin")) {
            try(var statement = conn.prepareStatement("insert into networks (network_id, name, ip_address, mac_address, type, status) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, deviceToSave.getNetworkId());
                statement.setString(2, deviceToSave.getName());
                statement.setString(3, deviceToSave.getIpAddress());
                statement.setString(4, deviceToSave.getMacAddress());
                statement.setString(5, deviceToSave.getType());
                statement.setString(6, deviceToSave.getStatus());
                statement.executeQuery();
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                deviceToSave.setId(rs.getLong("id"));
                deviceToSave.setCreated_at(rs.getDate("created_at"));
            }
        } catch (Exception e) {

        }
        return deviceToSave;
    }

    public List<Network> getNetworks() throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/network_db", "admin", "admin")) {
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
            }
        }
    }

    public List<Network> findNetworksByName(String name) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT * from networks WHERE name like ?";
                try (var statement = connection.prepareStatement(query)) {
                    statement.setString(1, "name");
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

    public List<Device> findDevicesByName(String name) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT * from devices WHERE name like ?";
                try(var statement = connection.prepareStatement(query)) {
                    statement.setString(1, "name");
                    var rs = statement.executeQuery();

                    List<Device> devices = new ArrayList<>();
                    while (rs.next()) {
                        Long deviceId = rs.getLong("id");
                        Long networkId = rs.getLong("network_id");
                        String deviceName = rs.getString("name");
                        String ipAddress = rs.getString("ip_address");
                        String macAddress = rs.getString("mac_address");
                        String type = rs.getString("type");
                        String status = rs.getString("status");
                        Date createdAt = rs.getDate("created_at");

                        devices.add(new Device(deviceId, networkId, deviceName, ipAddress, macAddress, type, status, createdAt));
                    }
                    return devices;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error searching for devices: " + e.getMessage());
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
