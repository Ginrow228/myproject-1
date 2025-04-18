package jdbc;

import model.Connection;
import model.DatabaseOperation;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDao {

    public ConnectionDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Connection save(Connection connectionToSave) throws SQLException {
        return getInfoFromDb(conn -> {
            try(var statement = conn.prepareStatement(
                    "INSERT INTO connections (device_from_id, device_to_id, connection_type, status) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                statement.setLong(1, connectionToSave.getDeviceFromId());
                statement.setLong(2, connectionToSave.getDeviceToId());
                statement.setString(3, connectionToSave.getConnectionType());
                statement.setString(4, connectionToSave.getStatus());

                statement.executeUpdate();
                var rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    connectionToSave.setId(rs.getLong(1));
                    try (var timestampQuery = conn.prepareStatement(
                            "SELECT created_at FROM connections WHERE id = ?")) {
                        timestampQuery.setLong(1, connectionToSave.getId());
                        var tsRs = timestampQuery.executeQuery();
                        if (tsRs.next()) {
                            connectionToSave.setCreatedAt(tsRs.getDate("created_at"));
                        }
                    }
                }
                return connectionToSave;
            } catch (Exception e) {
                throw new RuntimeException("Error saving connection: " + e.getMessage());
            }
        });
    }

    public Map<String, Integer> getConnectionTypeStatisticsByNetworkId(Long networkId) throws SQLException {
        return getInfoFromDb(connection -> {
            try {
                var query = "SELECT c.connection_type, COUNT(*) as count " +
                        "FROM connections c " +
                        "JOIN devices d1 ON c.device_from_id = d1.id " +
                        "JOIN devices d2 ON c.device_to_id = d2.id " +
                        "WHERE d1.network_id = ? AND d2.network_id = ? " +
                        "GROUP BY c.connection_type";
                try (var statement = connection.prepareStatement(query)) {
                    statement.setLong(1, networkId);
                    statement.setLong(2, networkId);
                    var rs = statement.executeQuery();

                    Map<String, Integer> typeStats = new HashMap<>();
                    while (rs.next()) {
                        String type = rs.getString("connection_type");
                        int count = rs.getInt("count");
                        typeStats.put(type, count);
                    }

                    return typeStats;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error when getting statistics on connection types: " + e.getMessage(), e);
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