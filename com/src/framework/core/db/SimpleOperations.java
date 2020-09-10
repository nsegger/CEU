package framework.core.db;

import java.sql.SQLException;
import java.util.List;

public interface SimpleOperations<T extends Model<T> > {
    List<T> findAll() throws SQLException;
    T findBy(DatabaseColumn column) throws SQLException;
    T findById(int id) throws SQLException;
    boolean create(T entry) throws SQLException;
}