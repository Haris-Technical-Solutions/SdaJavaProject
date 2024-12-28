package org.example.bookstore.util;

import java.sql.*;
import java.util.*;
import java.io.InputStream; // Add this import for InputStream
import java.io.IOException;  // Add this import for IOException
import java.util.Properties; // Add this import for Properties
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.example.bookstore.util.Config;


public class DB {
    private Connection connection;
    private static DB instance;

    // private String tableName;
    // private List<String> columns;
    // private List<String> joins;
    // private String whereClause;
    // private String orWhereClause;
    // private String limitClause;
    // private String orderByClause;
    
    public DB() {
        // Config config = Config.getInstance();
        Properties config = Config.getConfig();
        try {
            // Load and register the JDBC driver dynamically
            // String driverPath = config.getProperty("jdbc.driver.path");
            // loadDriver(driverPath);

            // Establish the database connection
            this.connection = DriverManager.getConnection(
                config.getProperty("mysql.host"),
                config.getProperty("mysql.username"),
                config.getProperty("mysql.password")
            );

            // System.out.println("Connected to the database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() {
        if (instance == null) {
            instance = new DB();
        }
        return instance.getConnertionObj();
    }

    public Connection getConnertionObj(){
        return this.connection;
    }
}