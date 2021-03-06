import gui.Gui;
import gui.Updater;
import model.Channel;
import model.SverigesRadio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class: Controller
 *
 * @author - David Irén
 *
 * the Controller that communicates between the model and gui
 */
public class Controller {
    private Gui gui;
    private SverigesRadio sverigesRadio = new SverigesRadio();

    /**
     * Constructor
     */
    public Controller(){
    }

    /**
     * setups the 1 h interval that updates and does the
     * initial api calls from Sveriges Radio
     */
    public void setupModel(){
        ScheduledExecutorService ses =
                Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }, 1, 1, TimeUnit.HOURS);

        sverigesRadio.setUp();
    }

    /**
     * getter for the channels
     * @return - LinkedHashMap with the channels
     */
    public LinkedHashMap<String, Channel> getChannels(){
        return sverigesRadio.getChannelsList();
    }

    /**
     * Setups the gui and its listeners
     * @param channels - LinkedHashMap
     */
    public void setupGui(LinkedHashMap<String, Channel> channels) {

        gui = new Gui(channels);
        setupListeners();
        gui.showStartFrame();
    }

    /**
     * Call all methods that creates listeners in the gui
     */
    private void setupListeners() {
        makeActionListenerToExit();
        makeMouseListenerForChannels();
        makeButtonListenerForUpdate();
        makeActionListenerToHelpTextMenuOption();
    }

    /**
     * Create ActionListenerToExit
     */
    private void makeActionListenerToExit(){
        ActionListener exitAction = e -> {
            gui.closeProgram();
        };
        gui.addActionListenerToExit(exitAction);
    }

    /**
     * Create ActionListener to the HelpText menu option
     */
    private void makeActionListenerToHelpTextMenuOption(){
        ActionListener help = e -> {
            gui.showPopupHelper();
        };
        gui.addActionListenerToHelp(help);
    }

    /**
     * Create MouseListener for channels
     */
    private void makeMouseListenerForChannels() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.displayEpisodes(e);
            }
        };
        makeMouseListenerForEpisodes();
        gui.addMouseListenerToChannelTable(mouseAdapter);
    }

    /**
     * Create MouseListener for episodes
     */
    private void makeMouseListenerForEpisodes() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.displayEpisodeDescription(e);
            }
        };
        gui.addMouseListenerToEpisodeTable(mouseAdapter);
    }

    /**
     * Create ActionListener to update-button
     */
    private void makeButtonListenerForUpdate(){
        ActionListener update = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            gui.disableUpdateButton();
            Updater worker = new Updater(gui);
            worker.execute();
            }
        };
        gui.addButtonListenerToUpdate(update);
    }

    /**
     * Called once every hour to update information
     */
    private void update(){
        gui.disableUpdateButton();
        Updater worker = new Updater(gui);
        worker.execute();
    }
}
