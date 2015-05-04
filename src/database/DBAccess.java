package database;

import bl.Mitarbeiter;
import bl.Projekt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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

            if(rs.next()){
                returnname = rs.getString(1); 
            }
           
            stat.close();

        } catch (SQLException ex) {
            
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return "";
            
        }
        return returnname;
    }


public void insertProjekt(Projekt p, Mitarbeiter gründer){
    
     try {
           
           SimpleDateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
          
           Statement stat = db.getCon().createStatement();
           
           
           //Projekt einfügen
           String sqlString="INSERT INTO \"Projekt\"(\n" +
"            Projectid, Name, anfangsdatum, enddatum)\n" +
"    VALUES ((SELECT MAX(Projectid) FROM \"Projekt\")+1, '"+p.getName()+"', TO_DATE('"+sdf.format(p.getAnfangsdatum())+"','dd.MM.yyyy'),TO_DATE('"+sdf.format(p.getAnfangsdatum())+"','dd.MM.yyyy'));";
           stat.executeUpdate(sqlString);
           
           //Verbindung zwischen Mitarbeiter und Projektersteller hersteller
           sqlString="INSERT INTO Mitarbeiter";
           stat.close();
       } catch (SQLException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       }
}


public LinkedList<Projekt> getProjekte(int mitarbeiterid){
    
    LinkedList<Projekt> projekte=new LinkedList<Projekt>();
    //Zuerst Mitarbeitet, MitarbeiterProject und Project joinen
  try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT Projectid, Name, anfangsdatum, enddatum "
                    + "FROM \"Mitarbeiter\" m INNER JOIN \"MitarbeiterProject\" mp ON(m.mitarbeiterid==mp.mitarbeiterid)"
                    + " INNER JOIN Project p ON (p.Projectid==mp.projectid)"
            + "WHERE m.mitarbeiterid='"+mitarbeiterid+"';";
            ResultSet rs = stat.executeQuery(sqlString);
 SimpleDateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
              projekte.add(new Projekt(rs.getInt(1),rs.getString(2),sdf.parse(rs.getDate(3).toString()),sdf.parse(rs.getDate(4).toString())));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
           Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
       }
     return projekte;
}
}

    
