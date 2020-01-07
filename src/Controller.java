import gui.Gui;
import model.Channel;
import model.SverigesRadio;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private Gui gui;
    private SverigesRadio sverigesRadio = new SverigesRadio();


    public Controller(){
    }

    public void setupModel(){
        sverigesRadio.setUp();
    }

    public ArrayList<Channel> getChannels(){
        return sverigesRadio.getChannelList();
    }

    public void setupGui(ArrayList<Channel> channels) {
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
