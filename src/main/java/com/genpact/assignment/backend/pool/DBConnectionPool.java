package com.genpact.assignment.backend.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

public class DBConnectionPool {
	List<Connection> availableConnections = new ArrayList<Connection>();
	private List<Connection> usedConnections = new ArrayList<Connection>();
    private final int MAX_DB_CONNECTIONS = 10;

    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    public DBConnectionPool(String Url, String UserId, String password)
            throws SQLException {
        this.databaseUrl = Url;
        this.databaseUsername = UserId;
        this.databasePassword = password;

        for (int count = 0; count <MAX_DB_CONNECTIONS; count++) {
            availableConnections.add(this.createDBConnection());
        }

    }
    private Connection createDBConnection() throws SQLException {
        return DriverManager
                .getConnection(this.databaseUrl, this.databaseUsername, this.databasePassword);
    }
    
    public Connection getDBConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con = 
            availableConnections.remove(
                availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }

    public boolean releaseDBConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }
    
    public int getFreeDBConnectionCount() {
        return availableConnections.size();
    }

}
