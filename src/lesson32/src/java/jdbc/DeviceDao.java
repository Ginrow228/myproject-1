package jdbc;

import model.DatabaseOperation;
import model.Device;
import model.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceDao {
    public DeviceDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Device save(Device deviceToSave) throws SQLException {
        return getInfoFromDb(conn -> {
            try (var statement = conn.prepareStatement(
                    "insert into devices (network_id, name, ip_address, mac_address, type, status) values(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setLong(1, deviceToSave.getNetworkId());
                statement.setString(2, deviceToSave.getName());
                statement.setString(3, deviceToSave.getIpAddress());
                statement.setString(4, deviceToSave.getMacAddress());
                statement.setString(5, deviceToSave.getType());
                statement.setString(6, deviceToSave.getStatus());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    deviceToSave.setId(rs.getLong(1));
                    try (var timestampQuery = conn.prepareStatement(
                            "SELECT created_at FROM devices WHERE id = ?")) {
                        timestampQuery.setLong(1, deviceToSave.getId());
                        ResultSet tsRs = timestampQuery.executeQuery();
                        if (tsRs.next()) {
                            deviceToSave.setCreated_at(tsRs.getDate("created_at"));
                        }
                    }
                } else {
                    throw new RuntimeException("Failed to get ID for new device");
                }
                return deviceToSave;
            } catch (Exception e) {
                throw new RuntimeException("Error saving device: " + e.getMessage());
            }
        });
    }

    public List<Device> getDevicesByNetworkId(Long networkId) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT * FROM devices WHERE network_id = ?";
                List<Device> devices = new ArrayList<>();

                try (var statement = connection.prepareStatement(query)) {
                    statement.setLong(1, networkId);
                    var rs = statement.executeQuery();

                    while (rs.next()) {
                        Long deviceId = rs.getLong("id");
                        Long deviceNetworkId = rs.getLong("network_id");
                        String name = rs.getString("name");
                        String ipAddress = rs.getString("ip_address");
                        String macAddress = rs.getString("mac_address");
                        String type = rs.getString("type");
                        String status = rs.getString("status");
                        Date createdAt = rs.getDate("created_at");

                        Device device = new Device(deviceId, deviceNetworkId, name, ipAddress, macAddress, type, status, createdAt);
                        devices.add(device);
                    }
                }
                return devices;
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при получении устройств сети: " + e.getMessage());
            }
        });
    }

    public List<Device> findDevicesByName(String name) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT * from devices WHERE name like ?";
                try(var statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + name + "%");
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

    public List<Device> getDevicesWithConnectionsByNetworkId(Long networkId) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var devicesQuery = "SELECT * from devices WHERE network_id = ?";

                List<Device> devices = new ArrayList<>();
                try (var devicesStatement = connection.prepareStatement(devicesQuery)) {
                    devicesStatement.setLong(1, networkId);
                    var devicesRs = devicesStatement.executeQuery();

                    while (devicesRs.next()) {
                        Long deviceId = devicesRs.getLong("id");
                        Long deviceNetworkId = devicesRs.getLong("network_id");
                        String name = devicesRs.getString("name");
                        String ipAddress = devicesRs.getString("ip_address");
                        String macAddress = devicesRs.getString("mac_address");
                        String type = devicesRs.getString("type");
                        String status = devicesRs.getString("status");
                        Date createdAt = devicesRs.getDate("created_at");

                        Device device = new Device(deviceId, deviceNetworkId, name, ipAddress, macAddress, type, status, createdAt);
                        devices.add(device);
                    }
                }
                if (devices.isEmpty()) {
                    return devices;
                }
                var connectionsQuery = "SELECT * FROM connections WHERE device_from_id IN (SELECT id FROM devices WHERE network_id = ?) " +
                        "OR device_to_id IN (SELECT id FROM devices WHERE network_id = ?)";

                try (var connectionStatement = connection.prepareStatement(connectionsQuery)) {
                    connectionStatement.setLong(1, networkId);
                    connectionStatement.setLong(2, networkId);
                    var connectionsRs = connectionStatement.executeQuery();

                    while (connectionsRs.next()) {
                        Long connectionId = connectionsRs.getLong("id");
                        Long deviceFromId = connectionsRs.getLong("device_from_id");
                        Long deviceToId = connectionsRs.getLong("device_to_id");
                        String connectionType = connectionsRs.getString("connection_type");
                        String status = connectionsRs.getString("status");
                        Date createdAt = connectionsRs.getDate("created_at");

                        Connection connectionObject = new Connection(connectionId, deviceFromId, deviceToId, connectionType, status, createdAt);
                        for (Device device : devices) {
                            if (device.getId().equals(deviceFromId) || device.getId().equals(deviceToId)) {
                                device.addConnection(connectionObject);
                            }
                        }
                    }
                }
                return devices;
            } catch (SQLException e) {
                throw new RuntimeException("Error while getting devices with connections: " + e.getMessage());
            }
        });
    }

    public Map<String, Integer> getDeviceTypeStatisticsByNetworkId(Long networkId) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT type, COUNT(*) as count FROM devices WHERE network_id = ? GROUP BY type";
                try (var statement = connection.prepareStatement(query)) {
                    statement.setLong(1, networkId);
                    var rs = statement.executeQuery();

                    Map<String, Integer> typeStats = new HashMap<>();
                    while (rs.next()) {
                        String type = rs.getString("type");
                        int count = rs.getInt("count");
                        typeStats.put(type, count);
                    }

                    return typeStats;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error when retrieving statistics by device type: " + e.getMessage(), e);
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

