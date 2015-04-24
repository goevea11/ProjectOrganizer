package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Veronika
 */
public class DBAccess
{
   private Database db;
   public static void main(String[] args) {
       
   }
  /** Creates a new instance of DBAccess */
   public DBAccess(String dbname)
            throws IOException, FileNotFoundException, 
            ClassNotFoundException, SQLException {
        db = Database.getInstance();
        db.connect(dbname);
    }
}