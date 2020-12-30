package dao;

import java.sql.Connection;

public class GeneralDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection closeConnection() {
        Connection connection = this.connection;
        this.connection = null;
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
