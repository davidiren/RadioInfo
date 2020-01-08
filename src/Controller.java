import gui.Gui;
import model.Channel;
import model.SverigesRadio;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Controller {
    private Gui gui;
    private SverigesRadio sverigesRadio = new SverigesRadio();


    public Controller(){
    }

    public void setupModel(){
        sverigesRadio.setUp();
    }

    public LinkedHashMap<String, Channel> getChannels(){
        return sverigesRadio.getChannelsList();
    }

    public void setupGui(LinkedHashMap<String, Channel> channels) {
        gui = new Gui(channels);
        makeActionListenerToExit();
        makeActionListenerToPrograms();
        gui.showStartFrame();
    }

    private void makeActionListenerToExit(){
        ActionListener exitAction = e -> {
            gui.closeProgram();
        };
        gui.addActionListenerToExit(exitAction);
    }
    private void makeActionListenerToPrograms(){
        ActionListener actionListener = e ->{
            gui.changeToTableOfPrograms(e.getSource());

        };
        gui.addActionListenerToPrograms(actionListener);
    }
}
