package app.stock;

import framework.core.db.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock implements Model<Stock> {
    private int id;
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

    public Stock(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toQuery() {
        return String.format("VALUES (null, '%s', '%d')", name, userId);
    }

    public String toWhere() {
        return "name = '" + name + "' AND user_id = '" + userId + "'";
    }

}
