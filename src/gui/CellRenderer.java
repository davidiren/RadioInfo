package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class: CellRenderer
 *
 * @author David Irén
 *
 * Class is used to color rows
 */
class CellRenderer extends DefaultTableCellRenderer {

    /**
     * Method will compare endtime of an episode
     * with the current time to determine if that
     * episode has already aired or not
     * @param table - JTable
     * @param value - Object
     * @param isSelected - boolean
     * @param hasFocus - boolean
     * @param row - int
     * @param column - int
     * @return Component
     */
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        EpisodeTableModel episodeTableModel =
                (EpisodeTableModel) table.getModel();

        try {
            Date today = new Date();
            String strDateFormat = "HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            String formatedToday = sdf.format(today);

            Date now = sdf.parse(formatedToday);

            String endTime =
                    episodeTableModel.getEndTime(row);
            Date ep;
            SimpleDateFormat another = new SimpleDateFormat(strDateFormat);
            ep = another.parse(endTime);

            if (now.compareTo(ep) > 0 || row < 3) {
                setBackground(Color.RED);
            } else if (now.compareTo(ep) < 0) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.RED);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}