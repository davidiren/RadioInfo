import javax.swing.*;

public class RadioInfo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller c = new Controller();
                c.setupGui();
            }
        });
    }
}
