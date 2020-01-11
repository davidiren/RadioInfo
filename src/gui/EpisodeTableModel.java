package gui;

import javax.swing.table.DefaultTableModel;


/**
 * Class: EpisodeTableModel
 *
 * @author David Irén
 *
 * A tablemodel that makes the table non editable
 * and enables to get endtimes for episodes so
 * that it is possible to know if they have aired
 */
public class EpisodeTableModel extends DefaultTableModel{

    /**
     * makes cells un-editable
     * @param row -int
     * @param col - int
     * @return - false
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * gets the endtime of a episode in the table
     * @param row - int
     * @return - String of episode endtime
     */
    public String getEndTime(int row){

        return super.dataVector.get(row).elementAt(2).toString();
    }
}
