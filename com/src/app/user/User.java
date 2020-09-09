package app.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private final int id;
    private final String username;
    private final String password;

    public User(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        username = rs.getString("username");
        password = rs.getString("password");
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
