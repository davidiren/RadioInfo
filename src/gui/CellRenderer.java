package gui;

import model.Episode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class CellRenderer extends DefaultTableCellRenderer {

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