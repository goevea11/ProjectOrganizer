package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    public Connection getCon() {
        return con;
    }
    private String DB_Driver = "org.postgresql.Driver";
    private String DB_Username = "postgres";
    private String DB_Password = "postgres";
    private String DB_Url = "jdbc:postgresql://localhost/";
    Connection con;

    private final String fileName = System.getProperty("user.dir")
            + File.separator + "src" + File.separator + "database"
            + File.separator + "dbproperties.properties";
    // connect
    // dissconnect
    private static Database theInstance = null;

    public static Database getInstance()
            throws FileNotFoundException, IOException, ClassNotFoundException {
        if (theInstance == null) {
            theInstance = new Database();
        }
        return theInstance;
    }

    private Database()
            throws FileNotFoundException, IOException, ClassNotFoundException {
        loadProperties();
        Class.forName(this.DB_Driver);
    }

    public void connect(String dbname) throws SQLException {
        con = DriverManager.getConnection(DB_Url + dbname, DB_Username, DB_Password);
    }

    public void disconnect() throws SQLException {
        con.close();
    }

    private void loadProperties() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(fileName);
        props.load(in);
        DB_Driver = props.getProperty("driver");
        DB_Username = props.getProperty("username");
        DB_Password = props.getProperty("password");
        DB_Url = props.getProperty("url");
    }
}
