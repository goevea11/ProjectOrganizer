package database;

import bl.Arbeitsschritt;
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

            LinkedList<Arbeitsschritt> a = dba.getToDoList(1);

            for (Arbeitsschritt p : a) {
                System.out.println(p.getText());
            }

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
            String sqlString = "INSERT INTO \"mitarbeiter\"(\n"
                    + "            mitarbeiterid, name, firstname, birthdate, password)\n"
                    + "    VALUES ((SELECT MAX(mitarbeiterid) FROM \"mitarbeiter\")+1, '" + vn + "', '" + nn + "', TO_DATE('" + date + "','dd.MM.yyyy'), '" + pw + "');";
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
            String sqlString = "SELECT mitarbeiterid FROM \"mitarbeiter\" WHERE password='" + passwort + "' AND firstname='" + nachname + "';";

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
            String sqlString = "SElECT firstname FROM \"mitarbeiter\" WHERE password='" + passwort + "' AND firstname='" + nachname + "';";
            ResultSet rs = stat.executeQuery(sqlString);

            if (rs.next()) {
                returnname = rs.getString(1);
            }

            stat.close();

        } catch (SQLException ex) {

            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return "";

        }
        return returnname;
    }

    public void insertProjekt(Projekt p, int gründerid) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            Statement stat = db.getCon().createStatement();

            
            //Projekt einfügen
            String sqlString = "INSERT INTO \"projekt\""
                    + " VALUES ((SELECT MAX(projektid) FROM \"projekt\")+1, '" + p.getName() + "', TO_DATE('" + sdf.format(p.getAnfangsdatum()) + "','dd.MM.yyyy'),TO_DATE('" + sdf.format(p.getAnfangsdatum()) + "','dd.MM.yyyy'));";

           
              stat.executeUpdate(sqlString);
            //Verbindung zwischen Mitarbeiter und Projekt herstellen
         //1. Projektnummer herausholen
             p.setProjektid(this.getProjektId(p.getName()));
             
             //2. Arbeitsschritt zur Erstellung des Projektes einfügen
             String bezeichnung="Projekterstellung";
             sqlString="INSERT INTO arbeitsschritt "
                     + "VALUES ((SELECT MAX(arbeitsschrittid) FROM arbeitsschritt)+1,"+p.getProjektid()+", '"+bezeichnung+"', 'Erstellung von "+p.getName()+"', 2);";
          stat.executeUpdate(sqlString);
           int arbeitsschrittid=this.getArbeitsschrittId(bezeichnung,p.getProjektid());
           
           //3. Datensatz in Verwaltung einfügen
           
           sqlString="INSERT INTO verwaltung VALUES("+gründerid+","+arbeitsschrittid+","+p.getProjektid()+");";
           stat.executeUpdate(sqlString);
           stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getArbeitsschrittId(String bezeichnung, int projektid){
      String sqlString=" SELECT arbeitsschrittid "
               + "FROM \"arbeitsschritt\""
               + "WHERE bezeichnung='"+bezeichnung+"' AND projektid="+projektid+";";
       
        try {
             Statement stat = db.getCon().createStatement();    
       ResultSet rs = stat.executeQuery(sqlString);
            while (rs.next()) {
                return rs.getInt(1);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;  
        
    }
    
    public int getProjektId(String name){
       String sqlString=" SELECT projektid "
               + "FROM \"projekt\""
               + "WHERE name='"+name+"';";
       
        try {
             Statement stat = db.getCon().createStatement();    
       ResultSet rs = stat.executeQuery(sqlString);
            while (rs.next()) {
                return rs.getInt(1);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public LinkedList<Projekt> getProjekte(int id) {

        LinkedList<Projekt> projekte = new LinkedList<Projekt>();
        //Zuerst Mitarbeitet, MitarbeiterProject und Project joinen
        try {
            Statement stat = db.getCon().createStatement();
//            String sqlString = "SELECT projektid, name , begindate , enddate "
//                    + "FROM projekt "
//                    + "WHERE projektid = (SELECT projektid "
//                    + "FROM verwaltung "
//                    + "WHERE mitarbeiterid = " + id + ");";
            
            
            String sqlString="SELECT p.projektid, p.name , p.begindate , p.enddate "
                    + "FROM projekt p INNER JOIN verwaltung v ON(p.projektid=v.projektid) "
                    + "WHERE v.mitarbeiterid = "+id+";";
            System.out.println(sqlString);
            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
               java.sql.Date sqlbeginndate=rs.getDate(3);
                java.sql.Date sqlenddate=rs.getDate(4);
                Date beginndate=new Date(sqlbeginndate.getTime());
                 Date enddate=new Date(sqlenddate.getTime());
                projekte.add(new Projekt(rs.getInt(1), rs.getString(2), beginndate, enddate));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projekte;
    }

    public LinkedList<Arbeitsschritt> getToDoList(int id) {

        LinkedList<Arbeitsschritt> l = new LinkedList<Arbeitsschritt>();
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT arbeitsschrittid, progress , bezeichnung , text "
                    + "FROM arbeitsschritt"
                    + " WHERE projektid ='" + id + "' AND progress=0;";

            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                l.add(new Arbeitsschritt(rs.getInt(1), id, rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public LinkedList<Arbeitsschritt> getInWorkList(int id) {
        LinkedList<Arbeitsschritt> l = new LinkedList<Arbeitsschritt>();
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT arbeitsschrittid, progress , bezeichnung , text "
                    + "FROM arbeitsschritt"
                    + " WHERE projektid ='" + id + "' AND progress=1;";

            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                l.add(new Arbeitsschritt(rs.getInt(1), id, rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public LinkedList<Arbeitsschritt> getFinishedList(int id) {
        LinkedList<Arbeitsschritt> l = new LinkedList<Arbeitsschritt>();
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT arbeitsschrittid, progress , bezeichnung , text "
                    + "FROM arbeitsschritt"
                    + " WHERE projektid ='" + id + "' AND progress=2;";

            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                l.add(new Arbeitsschritt(rs.getInt(1), id, rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    

    public boolean updateArbeitsschritt(int projektid, String sf, int i) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }
}
