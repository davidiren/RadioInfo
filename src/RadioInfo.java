import model.Channel;

import javax.swing.*;
import java.util.LinkedHashMap;

public class RadioInfo {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.setupModel();
        LinkedHashMap<String, Channel> channels;
        channels = c.getChannels();
        LinkedHashMap<String, Channel> finalChannels = channels;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                c.setupGui(finalChannels);
            }
        });
        System.out.println("Hejsan");
    }
}
