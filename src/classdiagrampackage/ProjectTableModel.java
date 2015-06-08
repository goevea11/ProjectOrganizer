/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classdiagrampackage;

import bl.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Domi
 */
// Table Model für die Tabelle in der ProjektGUI, wo die Projekte angezeigt werden, zu denen ein Mitarbeiter zugeteilt ist.
public class ProjectTableModel extends AbstractTableModel {

    private String[] colName = new String[]{"Number", "Name", "Beginn", "Ende"};
    private LinkedList<Projekt> ll;

    public ProjectTableModel(LinkedList<Projekt> prlist) {
        ll = prlist;
    }

    @Override
    public int getRowCount() {
        return ll.size();
    }

    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public String getColumnName(int column) {
        return colName[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projekt s = ll.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return s.getProjektid();
            case 1:
                return s.getName();
            case 2:
                Date d = s.getAnfangsdatum();

                return sdf.format(d);
            case 3:
                Date da = s.getEnddatum();

                return sdf.format(da);
        }
        return "";
    }
}
