import gui.Gui;

import java.awt.event.ActionListener;

public class Controller {
    private Gui gui;


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
