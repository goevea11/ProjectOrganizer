/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author Veronika
 */
public class Arbeitsschritt {
    private int arbeitsschrittid;
    private int projectid;
    private int progressid;
    private String bezeichnung;
    private String text;

    public Arbeitsschritt(int arbeitsschrittid, int projectid, int progressid, String bezeichnung, String text) {
        this.arbeitsschrittid = arbeitsschrittid;
        this.projectid = projectid;
        this.progressid = progressid;
        this.bezeichnung = bezeichnung;
        this.text = text;
    }

    public int getArbeitsschrittid() {
        return arbeitsschrittid;
    }

    public void setArbeitsschrittid(int arbeitsschrittid) {
        this.arbeitsschrittid = arbeitsschrittid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public int getProgressid() {
        return progressid;
    }

    public void setProgressid(int progressid) {
        this.progressid = progressid;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
