package framework.core.db;

import framework.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public MySQL() {
        this("remotemysql.com:3306/EAjg4En4xo", "EAjg4En4xo", "3G1vIIUbDF");
    }

    public MySQL(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://" + url, user, password).load();
        flyway.migrate();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.db("Driver not found. Not able to connect to DB.");
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + url, user, password);
        } catch (SQLException throwables) {
            Logger.db("Error while trying to get connection to DB: ");
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
