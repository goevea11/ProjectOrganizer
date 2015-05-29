package bl;

import java.util.Date;

public class Projekt {

    private int projektid;
    private String name;
    private Date anfangsdatum, enddatum;

    public Projekt(String name, Date anfangsdatum, Date enddatum) {

        this.name = name;
        this.anfangsdatum = anfangsdatum;
        this.enddatum = enddatum;
    }

    public Projekt(int projektid, String name, Date anfangsdatum, Date enddatum) {
        this.projektid = projektid;
        this.name = name;
        this.anfangsdatum = anfangsdatum;
        this.enddatum = enddatum;
    }

    public int getProjektid() {
        return projektid;
    }

    public void setProjektid(int projektid) {
        this.projektid = projektid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAnfangsdatum() {
        return anfangsdatum;
    }

    public void setAnfangsdatum(Date anfangsdatum) {
        this.anfangsdatum = anfangsdatum;
    }

    public Date getEnddatum() {
        return enddatum;
    }

    public void setEnddatum(Date enddatum) {
        this.enddatum = enddatum;
    }

}
