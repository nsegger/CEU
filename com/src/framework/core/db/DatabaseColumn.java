package framework.core.db;

public class DatabaseColumn {
    private String name;
    private Object key;

    public DatabaseColumn(String name, Object key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public Object getKey() {
        return key;
    }
}
