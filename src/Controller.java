import gui.Gui;
import model.SverigesRadio;

import java.awt.event.ActionListener;

public class Controller {
    private Gui gui;
    private SverigesRadio sverigesRadio;


    public Controller(){
        gui = new Gui();
    }

    public void setupGui() {
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
