import javax.swing.*;
import java.awt.*;

public class Gui {

    private JFrame frame;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;

    public Gui(){
        frame = new JFrame("RadioInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,700);
        frame.setJMenuBar(buildMenuBar());

        //Build panels
        upperPanel = buildUpperPanel();
        middlePanel = buildMiddlePanel();
        lowerPanel = buildLowerPanel();

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
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(buildProgramPanel(), BorderLayout.CENTER);
        return middlePanel;
    }

    private JPanel buildProgramPanel() {
        JPanel programPanel = new JPanel();
        programPanel.setLayout((new GridLayout(2, 2, 30, 50)));
        int nrOfPrograms = 4;
        for (int i = 0; i < nrOfPrograms; i++){
            programPanel.add(new JButton("P"+i));
        }

        return programPanel;
    }

    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lowerPanel;
    }

}
