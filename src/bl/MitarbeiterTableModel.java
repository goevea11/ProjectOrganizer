package bl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Domi
 */
// Table Model für die Mitarbeitertabelle
public class MitarbeiterTableModel extends AbstractTableModel {

    private String[] colName = new String[]{"ID", "Name", "Geburtsdatum"};
    private LinkedList<Mitarbeiter> ll;

    public MitarbeiterTableModel(LinkedList<Mitarbeiter> mitarbeiterlist) {
        ll = mitarbeiterlist;
    }

    public MitarbeiterTableModel() {

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
        Mitarbeiter m = ll.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return m.getId();
            case 1:
                return m.getVorname() + " " + m.getNachname();
            case 2:
                Date d = m.getGebdatum();
                return sdf.format(d);

        }
        return "";
    }

    public void setlist(LinkedList<Mitarbeiter> mitarbeiterfromProjekt) {
        this.ll = mitarbeiterfromProjekt;
        this.fireTableDataChanged();
    }

}
