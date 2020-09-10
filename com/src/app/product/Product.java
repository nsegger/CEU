package app.product;

import framework.core.db.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Product implements Model<Product> {
    private int id;
    private final String name;
    private final int amount;
    private final int stockId;
    private final Timestamp lastUpdate;

    public int getStockId() {
        return stockId;
    }

    public Product(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        amount = rs.getInt("amount");
        stockId = rs.getInt("stock_id");
        lastUpdate = rs.getTimestamp("last_update");
    }

    public Product(int id, String name, int amount, int stockId, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.stockId = stockId;
        this.lastUpdate = lastUpdate;
    }

    public Product(String name, int amount, int stockId, Timestamp lastUpdate) {
        this.name = name;
        this.amount = amount;
        this.stockId = stockId;
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toQuery() {
        return String.format("VALUES (null, '%s', '%d', '%d', '%s')", name, amount, stockId, lastUpdate);
    }

    public String toWhere() {
        return "id = '" + id + "'";
    }
}
