package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Veronika
 */
public class Database {
    //Connection zur Datenbank aufbauen
    //SINGLETON
    //DB_Url+dbname, DB_Username, DB_Password

    private String DB_Driver = "";
    private String DB_Username = "";
    private String DB_Password = "";
    private String DB_Url = "";
    private Connection con;
    private final String filename = System.getProperty("user.dir") + File.separator + "src" + File.separator + "database" + File.separator + "dbproperties.properties";
    public static Database instance;

    public Connection getCon() {
        return con;
    }

    ;

  public static Database getInstance() throws FileNotFoundException, IOException, ClassNotFoundException {

        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Database() throws FileNotFoundException, IOException, ClassNotFoundException {
        loadProperties();
        Class.forName(this.DB_Driver);

    }

    public void connect(String dbname) throws SQLException {
        con = DriverManager.getConnection(DB_Url + dbname, this.DB_Username, this.DB_Password);
    }

    public void disconnect() throws SQLException {
        con.close();
    }

    private void loadProperties() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream in = new FileInputStream(filename);
        prop.load(in);
        DB_Driver = prop.getProperty("driver");
        DB_Username = prop.getProperty("username");
        DB_Password = prop.getProperty("password");
        DB_Url = prop.getProperty("url");


    }
}
