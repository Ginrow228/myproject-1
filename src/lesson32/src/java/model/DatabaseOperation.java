package model;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseOperation<T> {
    T execute(Connection connection) throws SQLException;
}
