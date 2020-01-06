import gui.Gui;

import java.awt.event.ActionListener;

public class Controller {
    private Gui gui;


    public Controller(){
        gui = new Gui();
    }

    public void setupGui() {
        MakeActionListenerToExit();

        gui.show();
    }

    private void MakeActionListenerToExit(){
        ActionListener exitAction = e -> {
            gui.closeProgram();
        };
        gui.addActionListenerToExit(exitAction);
    }
}
