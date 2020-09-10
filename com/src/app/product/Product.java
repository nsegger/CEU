package app.product;

import framework.core.db.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Product implements Model<Product> {
    private int id;
    private final String name;
    private final int amount;
    private final int stockId;
    private Date lastUpdate;

    public Product(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        amount = rs.getInt("amount");
        stockId = rs.getInt("stock_id");
        lastUpdate = rs.getDate("last_update");
    }

    public Product(int id, String name, int amount, int stockId, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.stockId = stockId;
        this.lastUpdate = lastUpdate;
    }

    public Product(String name, int amount, int stockId) {
        this.name = name;
        this.amount = amount;
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toQuery() {
        return String.format("VALUES (null, '%s', '%d', '%d')", name, amount, stockId);
    }

    public String toWhere() {
        return "id = '" + id + "'";
    }
}
