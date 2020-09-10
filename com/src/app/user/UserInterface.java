package app.user;

import framework.Logger;
import framework.core.db.DatabaseInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class UserInterface extends DatabaseInterface<User> {

    public UserInterface(Connection connection) {
        super(connection, User.class);
        table = "user";
    }

    public User login(String username, String password) {
        Logger.info("Iniciando sessão de " + username);

        HashMap<String, Object> columns = new HashMap<>();

        columns.put("username", username);
        columns.put("password", password);

        try {
            return findByAnd(columns);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean register(String username, String password) {
        Logger.info("Criando usuário " + username);

        try {
            return create(new User(username, password));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
