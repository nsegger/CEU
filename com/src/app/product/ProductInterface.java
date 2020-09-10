package app.product;

import framework.Logger;
import framework.core.db.DatabaseColumn;
import framework.core.db.DatabaseInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductInterface extends DatabaseInterface<Product> {
    public ProductInterface(Connection connection) {
        super(connection, Product.class);
        table = "stocks";
    }

    public List<Product> index(int stockId) {
        Logger.info("Indexando produtos");

        try {
            return findBy(new DatabaseColumn("stock_id", stockId));
        } catch (SQLException throwables) {
            Logger.error("Erro ao indexar produtos:");
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean store(String name, int stockId) {
        Logger.info("Criando produto " + name + " pertencente ao estoque de id " + stockId);

        try {
            return create(new Product(name, 1, stockId));
        } catch (SQLException throwables) {
            Logger.error("Erro ao criar produto:");
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean remove(Product entry) {
        Logger.db("Removendo produto " + entry.getName());

        try {
            return delete(entry);
        } catch (SQLException throwables) {
            Logger.error("Erro ao remover produto:");
            throwables.printStackTrace();
        }

        return false;
    }

    private boolean delete(Product entry) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s", table, entry.toWhere());

        int rowsAffected = connection.prepareStatement(query).executeUpdate();
        Logger.db("Executado update \"" + query + "\" com " + rowsAffected + " linhas afetadas");

        return rowsAffected > 0;
    }
}
