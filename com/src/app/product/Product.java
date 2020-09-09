package app.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Product {
    private final int id;
    private final String name;
    private final int amount;
    private final int stockId;
    private final Date lastUpdate;

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

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
