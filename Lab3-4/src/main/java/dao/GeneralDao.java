package dao;

import java.sql.Connection;

public class GeneralDao {
    private Connection connection;      //todo protected

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection releaseConnection() { //todo closeconnection
        Connection connection = this.connection;
        this.connection = null;
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
