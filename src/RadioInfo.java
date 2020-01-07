import model.Channel;

import javax.swing.*;
import java.util.ArrayList;

public class RadioInfo {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.setupModel();
        ArrayList<Channel> channels = new ArrayList<>();
        channels = c.getChannels();
        ArrayList<Channel> finalChannels = channels;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                c.setupGui(finalChannels);
            }
        });

    }
}
