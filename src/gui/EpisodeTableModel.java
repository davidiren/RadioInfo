package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class EpisodeTableModel extends DefaultTableModel{

    public boolean isCellEditable(int row, int col) {
        return false;
    }


    @Override
    public void insertRow(int row, Vector<?> rowData) {
        super.insertRow(row, rowData);
    }

    public String getEndTime(int row){

        return super.dataVector.get(row).elementAt(2).toString();
    }
}
