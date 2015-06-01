package database;

import bl.Arbeitsschritt;
import bl.Mitarbeiter;
import bl.Projekt;
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
 * @author Veronika und Dominik
 */
public class DBAccess {

    private Database db;

    public static void main(String[] args) {
        DBAccess dba = new DBAccess("proorg");
        LinkedList<Arbeitsschritt> a = dba.getToDoList(1);
        for (Arbeitsschritt p : a) {
            System.out.println(p.getText());
        }
    }

    /**
     * Hier werden alle Datenbankzugriffe durchgeführt
     */
    public DBAccess(String dbname) {

        try {
            db = Database.getInstance();
            db.connect(dbname);
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertMitarbeiter(Mitarbeiter m) {
        // Fügt einen neuen Mitarbeiter in die Datenbank ein
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
        //Holt die Mitarbeiterid mittels namen und passwort aus der Datenbank
        int ma = 0;
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT mitarbeiterid FROM \"mitarbeiter\" WHERE password='" + passwort + "' AND LOWER(firstname)='" + nachname + "';";

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
        //Überprüft, ob das Passwort zum Mitarbeiter richtig ist
        String returnname = "";

        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SElECT LOWER(firstname) FROM \"mitarbeiter\" WHERE password='" + passwort + "' AND LOWER(firstname)='" + nachname + "';";
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

    public LinkedList<Mitarbeiter> getMitarbeiterfromProjekt(int projektid) throws SQLException {
        //Gibt alle Mitarbeiter zurück, die an einem Projekt beteiligt sind.
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        LinkedList<Mitarbeiter> mitarbeiterlist = new LinkedList<Mitarbeiter>();
        Statement stat = db.getCon().createStatement();
        String sqlString = "SELECT m.mitarbeiterid, m.name,m.firstname,  m.birthdate, m.password"
                + " FROM mitarbeiter m INNER JOIN Verwaltung v ON(m.mitarbeiterid=v.mitarbeiterid)"
                + " WHERE v.projektid=" + projektid + ";";

        ResultSet rs = stat.executeQuery(sqlString);
        while (rs.next()) {
            java.sql.Date sqlbirthdate = rs.getDate(4);
            Date birthdate = new Date(sqlbirthdate.getTime());

            //int id, String vorname, String nachname, Date gebdatum, String passwort
            mitarbeiterlist.add(new Mitarbeiter(rs.getInt(1), rs.getString(2), rs.getString(3), birthdate, rs.getString(5)));
        }

        return mitarbeiterlist;

    }

    public void insertProjekt(Projekt p, int gründerid) {
        //Fügt ein neues Projekt mit der Gründerid in die Datenbank ein
        //Auch der zugehörige Arbeitsschritt wird in die Verwaltung eingefügt,
        //damit der Gründer als dem Projekt zugehörig angesehen wird
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
            String bezeichnung = "Projekterstellung";
            sqlString = "INSERT INTO arbeitsschritt "
                    + "VALUES ((SELECT MAX(arbeitsschrittid) FROM arbeitsschritt)+1," + p.getProjektid() + ", '" + bezeichnung + "', 'Erstellung von " + p.getName() + "', 2);";
            stat.executeUpdate(sqlString);
            int arbeitsschrittid = this.getArbeitsschrittId(bezeichnung, p.getProjektid());

            //3. Datensatz in Verwaltung einfügen
            sqlString = "INSERT INTO verwaltung VALUES(" + gründerid + "," + arbeitsschrittid + "," + p.getProjektid() + ");";
            stat.executeUpdate(sqlString);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertArbeitsschritt(Projekt p, Mitarbeiter m, Arbeitsschritt a) {
        //Fügt einen neuen Arbeitsschritt zu einem bestimmten Projekt ein,
        //welcher durch einen bestimmten Mitarbeiter betreut wird.
        //Der zugehörige Datensatz, der Projekt, Mitarbeiter und Arbeitsschritt verbindet,
        //wird in die Verwaltung eingefügt.
        try {
            Statement stat = db.getCon().createStatement();

            String sqlString = "INSERT INTO arbeitsschritt "
                    + "VALUES ((SELECT MAX(arbeitsschrittid) FROM arbeitsschritt)+1," + p.getProjektid() + ", '" + a.getBezeichnung() + "', '" + a.getText() + "',0);";
            stat.executeUpdate(sqlString);
            int arbeitsschrittid = this.getArbeitsschrittId(a.getBezeichnung(), p.getProjektid());

            sqlString = "INSERT INTO verwaltung VALUES(" + m.getId() + "," + arbeitsschrittid + "," + p.getProjektid() + ");";
            stat.executeUpdate(sqlString);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getArbeitsschrittId(String bezeichnung, int projektid) {
        //Gibt für einen Arbeitsschritt eines Projekts die ID zurück
        String sqlString = " SELECT arbeitsschrittid "
                + "FROM \"arbeitsschritt\""
                + "WHERE bezeichnung='" + bezeichnung + "' AND projektid=" + projektid + ";";

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

    public int getProjektId(String name) {
        //Gibt die ProjektID für einen Projektnamen zurück
        String sqlString = " SELECT projektid "
                + "FROM \"projekt\""
                + "WHERE name='" + name + "';";

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
        //Holt alle Projekte heraus, bei denen ein bestimmter Mitarbeiter beteiligt ist 
        //(=Es gibt einen Arbeitsschritt in diesem Projekt, für den der Mitarbeiter zuständig ist)

        LinkedList<Projekt> projekte = new LinkedList<Projekt>();
        //Zuerst Mitarbeitet, MitarbeiterProject und Project joinen
        try {
            Statement stat = db.getCon().createStatement();

            String sqlString = "SELECT p.projektid, p.name , p.begindate , p.enddate "
                    + "FROM projekt p INNER JOIN verwaltung v ON(p.projektid=v.projektid) "
                    + "WHERE v.mitarbeiterid = " + id + ";";
            System.out.println(sqlString);
            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                java.sql.Date sqlbeginndate = rs.getDate(3);
                java.sql.Date sqlenddate = rs.getDate(4);
                Date beginndate = new Date(sqlbeginndate.getTime());
                Date enddate = new Date(sqlenddate.getTime());
                projekte.add(new Projekt(rs.getInt(1), rs.getString(2), beginndate, enddate));
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projekte;
    }

    public LinkedList<Arbeitsschritt> getToDoList(int id) {

        // Gibt anhand der ProjektID alle Arbeitsschritte zrück, die noch zu erledigen sind
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
        // Gibt anhand der ProjektID alle Arbeitsschritte zrück, die in bearbeitung sind
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
        // Gibt anhand der ProjektID alle Arbeitsschritte zrück, die fertig erledigt sind
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
        // Verändert die Progressid anhand der ProjektID und der ArbeitsschrittID
        // Die ProgressID ist der Fortschritt des Arbeitsschrittes der entweder 0(todo), 1(in work) oder 2 (finished) sein kann
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "UPDATE arbeitsschritt "
                    + "SET progress=" + i + ""
                    + " WHERE projektid='" + projektid + "' AND arbeitsschrittid='" + sf + "';";
            stat.executeUpdate(sqlString);
        } catch (SQLException ex) {

            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public LinkedList<Mitarbeiter> getAllMitarbeiter() {
        //Holt alle vorhandenen Mitarbeiter aus der Datenbank
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        LinkedList<Mitarbeiter> mitarbeiterlist = new LinkedList<Mitarbeiter>();
        Statement stat;
        try {
            stat = db.getCon().createStatement();
            String sqlString = "SELECT m.mitarbeiterid, m.name,m.firstname,  m.birthdate, m.password"
                    + " FROM mitarbeiter m;";
            ResultSet rs = stat.executeQuery(sqlString);
            while (rs.next()) {
                java.sql.Date sqlbirthdate = rs.getDate(4);
                Date birthdate = new Date(sqlbirthdate.getTime());

                //int id, String vorname, String nachname, Date gebdatum, String passwort
                mitarbeiterlist.add(new Mitarbeiter(rs.getInt(1), rs.getString(2), rs.getString(3), birthdate, rs.getString(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mitarbeiterlist;

    }

    public String getMitarbeiterFromArbeitsschritt(int projektid, int arbeitsid) {
        //Holt den zuständigen Mitarbeiter für den Arbeitsschritt eines Projekts heraus
        String name = "";
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECT m.firstname, m.name "
                    + "FROM verwaltung v INNER JOIN Mitarbeiter m ON(m.mitarbeiterid=v.mitarbeiterid)"
                    + " WHERE v.projektid ='" + projektid + "' AND v.arbeitsschrittid="+arbeitsid+";";

            ResultSet rs = stat.executeQuery(sqlString);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            while (rs.next()) {
                name=rs.getString(1);
                name+=" "+rs.getString(2);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public String getBezeichnung(int projektid, int arbeitsid) {
        //Gibt anhand der ArbeitsschrittID und der ProjektID die Bezeichnung eines Arbeitsschrittes zurück
       String bez = "";
        try {
            Statement stat = db.getCon().createStatement();
            String sqlString = "SELECt text FROM arbeitsschritt WHERE arbeitsschrittid="+arbeitsid+" AND "
                    + "projektid="+projektid+";";
            ResultSet rs = stat.executeQuery(sqlString);
            
            while (rs.next()) {
                bez=rs.getString(1);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bez;
    }

    
    
    public void removeArbeitsschritt(int projektid, int arbeitsschrittid){
            //Diese Methode löscht anhand von projektid und arbeitsschrittid einen vorher ausgewählten Arbeitsschritt aus der DB
        try {
             Statement stat = db.getCon().createStatement();
            String sqlString = "DELETE FROM verwaltung"
                             + "WHERE projektid = "+projektid+" AND arbeitsschrittid= "+arbeitsschrittid+" ;";
            stat.executeUpdate(sqlString);
              sqlString = "DELETE FROM arbeitsschritt"
                             + "WHERE projektid = "+projektid+" AND arbeitsschrittid= "+arbeitsschrittid+" ;";
            stat.executeUpdate(sqlString);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
