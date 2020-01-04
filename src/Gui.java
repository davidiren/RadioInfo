import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

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
        JMenuBar menuBar = new JMenuBar();
        //make menu options
        JMenu archive = new JMenu("Arkiv");
        JMenuItem archiveItem1 = new JMenuItem("Val ett");
        JMenuItem archiveItem2 = new JMenuItem("Val två");
        JMenuItem archiveItem3 = new JMenuItem("Val tre");
        archive.add(archiveItem1);
        archive.add(archiveItem2);
        archive.add(archiveItem3);
        //Second menu option
        JMenu help = new JMenu("Hjälp");
        menuBar.add(archive);
        menuBar.add(help);
        return menuBar;
    }

    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        return upperPanel;
    }

    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout());
        return middlePanel;
    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lowerPanel;
    }

}
