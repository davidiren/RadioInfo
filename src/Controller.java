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

public class Controller {
    private Gui gui;
    private SverigesRadio sverigesRadio = new SverigesRadio();


    public Controller(){
    }

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

    public LinkedHashMap<String, Channel> getChannels(){
        return sverigesRadio.getChannelsList();
    }

    public void setupGui(LinkedHashMap<String, Channel> channels) {

        gui = new Gui(channels);

        setupListeners();

        gui.showStartFrame();
    }

    private void setupListeners() {
        makeActionListenerToExit();
        makeMouseListenerForChannels();
        makeButtonListenerForUpdate();
        makeActionListenerToHelpTextMenuOption();
    }

    private void makeActionListenerToExit(){
        ActionListener exitAction = e -> {
            gui.closeProgram();
        };
        gui.addActionListenerToExit(exitAction);
    }

    private void makeActionListenerToHelpTextMenuOption(){
        ActionListener help = e -> {
            gui.showPopupHelper();
        };
        gui.addActionListenerToHelp(help);
    }

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

    private void makeMouseListenerForEpisodes() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.displayEpisodeDescription(e);
            }
        };
        gui.addMouseListenerToEpisodeTable(mouseAdapter);
    }

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
    private void update(){
        gui.disableUpdateButton();
        Updater worker = new Updater(gui);
        worker.execute();
    }
}
