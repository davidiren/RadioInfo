package gui;

import model.Channel;

import javax.swing.*;
import java.util.ArrayList;

public class ChannelNameGetter extends SwingWorker {

    ArrayList<Channel> channels = new ArrayList<>();
    Gui gui;

    public ChannelNameGetter(ArrayList<Channel> c, Gui gui){
        this.channels = c;
        this.gui = gui;
    }

    @Override
    protected Object doInBackground() throws Exception {
        Object[][] data;
        return null;
    }

    @Override
    protected void done() {
        super.done();
    }
}
