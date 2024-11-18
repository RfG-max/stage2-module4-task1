package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private Properties properties;

    public H2ConnectionFactory() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("h2database.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find h2database.properties");
                return;
            }
            // Load properties from the file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection createConnection() throws SQLException {
        String url = properties.getProperty("db_url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, user, password);
    }
    // Write your code here!
}

