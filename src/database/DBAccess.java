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
public class DBAccess {

    private Database db;

    public static void main(String[] args) {
        try {
            DBAccess dba = new DBAccess("proorg");
            dba.insertMitarbeiter(new Mitarbeiter("admin", "admin", new Date(), "1234"));
        } catch (IOException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new instance of DBAccess
     */
    public DBAccess(String dbname)
            throws IOException, FileNotFoundException,
            ClassNotFoundException, SQLException {
        db = Database.getInstance();
        db.connect(dbname);
    }

    public void insertMitarbeiter(Mitarbeiter m) {
        try {
            int maid = m.getId();
            String vn = m.getVorname();
            String nn = m.getNachname();
            Date d = m.getGebdatum();
            String pw = m.getPasswort();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String date = sdf.format(d);
            Statement stat = db.getCon().createStatement();
            String sqlString = "INSERT INTO \"Mitarbeiter\"(\n"
                    + "            mitarbeiterid, vorname, nachname, geburtsdatum, passwort)\n"
                    + "    VALUES ((SELECT MAX(mitarbeiterid) FROM \"Mitarbeiter\")+1, '" + vn + "', '" + nn + "', TO_DATE('" + date + "','dd.MM.yyyy'), '" + pw + "');";
            stat.executeUpdate(sqlString);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMitarbeiter(String passwort, String nachname) {
        int ma = 0;
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT mitarbeiterid FROM \"Mitarbeiter\" WHERE passwort='" + passwort + "' AND nachname='" + nachname + "';";

            ResultSet rs = stat.executeQuery(sqlString);

            while (rs.next()) {
                ma = rs.getInt(1);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }

    public String checkMitarbeiter(String nachname, String passwort) {
        String returnname = "";

        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SElECT nachname FROM \"Mitarbeiter\" WHERE passwort='" + passwort + "' AND nachname='" + nachname + "';";
            ResultSet rs = stat.executeQuery(sqlString);

            rs.next();
            returnname = rs.getString(1);
            stat.close();

        } catch (SQLException ex) {
            
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return "";
            
        }
        return returnname;
    }
}

    
