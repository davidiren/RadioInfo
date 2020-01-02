import javax.swing.*;
import java.awt.*;

public class Gui {

    private JFrame frame;

    public Gui(){
        frame = new JFrame("RadioInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,700);
        frame.setJMenuBar(buildMenuBar());

        //Build panels
        JPanel upperPanel = buildUpperPanel();
        JPanel middlePanel = buildMiddlePanel();
        JPanel lowerPanel = buildLowerPanel();

        //add to frame
        frame.add(upperPanel);
        frame.add(middlePanel);
        frame.add(lowerPanel);

    }

    public void show(){
        frame.setVisible(true);
    }

    private JMenuBar buildMenuBar() {
        return null;
    }

    private JPanel buildUpperPanel() {
        return null;
    }

    private JPanel buildMiddlePanel() {
        return null;
    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lowerPanel;
    }

}
