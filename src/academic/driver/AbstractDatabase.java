package academic.driver;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public abstract class AbstractDatabase {
    protected String url = "";
    protected String username = "";
    protected String password = "";
    protected Connection connection = null;

    public AbstractDatabase(String url, String username, String  password) throws SQLException{
        this.url = url;
        this.username = username;
        this.password = password;
        this.prepareTables();
    }

    protected Connection getConnection() throws SQLException{
        if (this.connection == null){
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        }
        return this.connection;
    }

    public void shutdown() throws SQLException{
        if (this.connection != null){
            this.connection.close();
        }
    }

    protected void prepareTables() throws SQLException{
        this.createTable();
        this.seedTable();
    }

    protected void createTable() throws SQLException{
        // Implementasi pembuatan tabel
    }

    protected void seedTable () throws SQLException{
        // Implementasi pengisian data awal
    }
}
