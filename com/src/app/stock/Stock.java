package app.stock;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock {
    private final int id;
    private final String name;
    private final int userId;

    public Stock(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        userId = rs.getInt("user_id");
    }

    public Stock(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
