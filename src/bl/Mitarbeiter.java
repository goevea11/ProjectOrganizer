/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.Date;

/**
 *
 * @author Veronika
 */

// Beansklasse für den Mitarbeiter
public class Mitarbeiter {

    int id;
    String vorname;
    String nachname;
    Date gebdatum;
    String passwort;

    public Mitarbeiter(int id, String vorname, String nachname, Date gebdatum, String passwort) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.gebdatum = gebdatum;
        this.passwort = passwort;
    }

    public Mitarbeiter(String vorname, String nachname, Date gebdatum, String passwort) {

        this.vorname = vorname;
        this.nachname = nachname;
        this.gebdatum = gebdatum;
        this.passwort = passwort;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGebdatum() {
        return gebdatum;
    }

    public void setGebdatum(Date gebdatum) {
        this.gebdatum = gebdatum;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setid(int i) {
        this.id = i;
    }
}
