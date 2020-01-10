package gui;

import model.Channel;
import model.SverigesRadio;

import javax.swing.*;
import java.util.LinkedHashMap;

public class Updater extends SwingWorker {

    private LinkedHashMap<String, Channel> channels;
    private Gui gui;


    public Updater(Gui gui){
        this.gui = gui;
    }

    @Override
    protected Object doInBackground() throws Exception {
        SverigesRadio sr = new SverigesRadio();
        sr.setUp();
        channels = sr.getChannelsList();
        return null;
    }

    @Override
    protected void done() {
        gui.update(channels);
        gui.enableUpdateButton();

    }
}
