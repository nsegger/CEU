package app.product;

import framework.Logger;
import framework.core.db.DatabaseColumn;
import framework.core.db.DatabaseInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductInterface extends DatabaseInterface<Product> {
    public ProductInterface(Connection connection) {
        super(connection, Product.class);
        table = "products";
    }

    public List<Product> index(int stockId) {
        Logger.info("Indexando produtos");

        try {
            return findBy(new DatabaseColumn("stock_id", stockId));
        } catch (SQLException throwables) {
            Logger.error("Erro ao indexar produtos:");
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public boolean store(Product product) {
        Logger.info("Criando produto " + product.getName() + " pertencente ao estoque de id " + product.getStockId());

        try {
            return create(product);
        } catch (SQLException throwables) {
            Logger.error("Erro ao criar produto:");
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean patch(Product product) {
        Logger.info("Editando produto " + product.getName());

        try {
            return update(product);
        } catch (SQLException throwables) {
            Logger.info("Erro ao editar produto:");
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean update(Product product) throws SQLException {
        String name = product.getName();
        int amount = product.getAmount();

        Logger.db("Atualizando produto [" + product.getId() + "] " + name);

        String query = String.format("UPDATE %s SET %s WHERE %s", table, "name = '" + name + "', amount = '" + amount + "'", product.toWhere());

        int rowsAffected = connection.prepareStatement(query).executeUpdate();
        Logger.db("Executado update \"" + query + "\" com " + rowsAffected + " linhas afetadas");

        return rowsAffected > 0;
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
