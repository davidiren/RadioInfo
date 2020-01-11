package gui;

import model.Channel;
import model.SverigesRadio;

import javax.swing.*;
import java.util.LinkedHashMap;

/**
 * Class: Updater
 *
 * @author David Irén
 *
 * A swingworker that is  used to update information
 * about all channels
 */
public class Updater extends SwingWorker {

    private LinkedHashMap<String, Channel> channels;
    private Gui gui;

    /**
     * Constructor
     * @param gui - Gui
     */
    public Updater(Gui gui){
        this.gui = gui;
    }

    /**
     * Will fetch all information needed
     * @return - null wont be used
     */
    @Override
    protected Object doInBackground() {
        SverigesRadio sr = new SverigesRadio();
        sr.setUp();
        channels = sr.getChannelsList();
        return null;
    }

    /**
     * Updates information inside the gui and enables the JButton
     */
    @Override
    protected void done() {
        gui.update(channels);
        gui.enableUpdateButton();

    }
}
