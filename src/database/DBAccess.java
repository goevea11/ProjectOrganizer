package database;

import bl.Mitarbeiter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Veronika
 */
public class DBAccess
{
   private Database db;
   public static void main(String[] args) {
       try {
           DBAccess dba = new DBAccess("proorg");
           dba.insertMitarbeiter(new Mitarbeiter("admin","admin",new Date(),"1234"));
       } catch (IOException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
  /** Creates a new instance of DBAccess */
   public DBAccess(String dbname)
            throws IOException, FileNotFoundException, 
            ClassNotFoundException, SQLException {
        db = Database.getInstance();
        db.connect(dbname);
    }

    public void insertMitarbeiter(Mitarbeiter m)
    {
       try {
           int maid = m.getId();
           String vn = m.getVorname();
           String nn = m.getNachname();
           Date d = m.getGebdatum();
           String pw = m.getPasswort();
           SimpleDateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
           String date = sdf.format(d);
           Statement stat = db.getCon().createStatement();
           String sqlString="INSERT INTO \"Mitarbeiter\"(\n" +
"            mitarbeiterid, vorname, nachname, geburtsdatum, passwort)\n" +
"    VALUES ((SELECT MAX(maid) FROM \"mitarbeiter\")+1, '"+vn+"', '"+nn+"', TO_DATE('"+date+"','dd.MM.yyyy'), '"+pw+"');";
           stat.executeUpdate(sqlString);
           stat.close();
       } catch (SQLException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public LinkedList<String> getMitarbeiter() {
        LinkedList<String> ma = new LinkedList<String>();
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SElECT nachname FROM mitarbeiter;";
            ResultSet rs = stat.executeQuery(sqlString);

            while (rs.next()) {
                String s = rs.getString(1);
                ma.add(s);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }
}