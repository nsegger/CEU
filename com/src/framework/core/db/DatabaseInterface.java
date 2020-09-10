package framework.core.db;

import framework.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseInterface<T extends Model<T> > implements SimpleOperations<T> {
    protected String table;
    protected Connection connection;
    protected Class<T> clazz;

    public DatabaseInterface(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }

    @Override
    public List<T> findAll() throws SQLException {
        String query = String.format("SELECT * FROM %s", table);

        ResultSet rs = executeQuery(query);


        ArrayList<T> results = new ArrayList<>();
        while (rs.next()) {
            try {
                results.add(clazz.getDeclaredConstructor(ResultSet.class).newInstance(rs));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    @Override
    public List<T> findBy(DatabaseColumn column) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = '" + column.getKey() + "'", table, column.getName());

        ResultSet rs = executeQuery(query);

        ArrayList<T> results = new ArrayList<>();
        while (rs.next()) {
            try {
                results.add(clazz.getDeclaredConstructor(ResultSet.class).newInstance(rs));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    @Override
    public T findByAnd(Map<String, Object> column) throws SQLException {
        StringBuilder query = new StringBuilder(String.format("SELECT * FROM %s WHERE", table));

        column.forEach((name, key) -> query.append(String.format(" %s = '" + key + "' AND", name)));

        query.setLength(query.length() - 4);

        ResultSet rs = executeQuery(query.toString());
        if (rs.next()) {
            try {
                return clazz.getDeclaredConstructor(ResultSet.class).newInstance(rs);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public T findById(int id) throws SQLException {
        return findBy(new DatabaseColumn("id", id)).get(0);
    }

    @Override
    public boolean create(T entry) throws SQLException {
        String query = String.format("INSERT INTO %s %s", table, entry.toQuery());

        Logger.db("Executando update \"" + query + "\"");
        int rowsAffected = connection.prepareStatement(query).executeUpdate();
        Logger.db("Executado update com " + rowsAffected + " linhas afetadas");

        return rowsAffected > 0;
    }

    private ResultSet executeQuery(String query) {
        Logger.db("Executando query \"" + query + "\"");
        ResultSet rs = null;

        try {
            rs = connection.prepareStatement(query).executeQuery();
        } catch (SQLException throwables) {
            Logger.db("Error while executing query:");
            throwables.printStackTrace();
        }

        return rs;
    }
}
