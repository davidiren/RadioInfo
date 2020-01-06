package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Gui {

    private JFrame frame;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;


    //Menu items
    JMenuItem archiveItem1;
    JMenuItem archiveItem2;
    JMenuItem exit;

    /**
     * Constructor
     *
     * puts all the gui components together
     */
    public Gui(){
        frame = new JFrame("RadioInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(buildMenuBar());

        //Build panels
        upperPanel = buildSRPanel();
        middlePanel = buildMiddlePanel();
        lowerPanel = buildLowerPanel();

        //add to frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(600,700));
        frame.pack();

    }

    public void show(){
        frame.setVisible(true);
    }

    /**
     * Builds the Menu Bar
     * @return - JMenuBar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //make menu options
        JMenu archive = new JMenu("Arkiv");
        archiveItem1 = new JMenuItem("Val ett");
        archiveItem2 = new JMenuItem("Val två");
        exit = new JMenuItem("Exit");
        archive.add(archiveItem1);
        archive.add(archiveItem2);
        archive.add(exit);
        //Second menu option
        JMenu help = new JMenu("Hjälp");
        menuBar.add(archive);
        menuBar.add(help);
        return menuBar;
    }

    /**
     * Builds the upper panel
     * @return - JPanel
     */
    private JPanel buildSRPanel() {
        JPanel srPanel = new JPanel();
        srPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Image img;
        img = ImageLoader.getImageLoader().getScaledImage(
                "images/sverigesradio.jpg", 400, 100);
        JLabel pic = new JLabel(new ImageIcon(img));
        srPanel.add(pic);

        return srPanel;
    }

    /**
     * Builds the middle panel
     * @return - JPanel
     */
    private JPanel buildMiddlePanel() {
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(buildProgramPanel(), BorderLayout.CENTER);
        return middlePanel;
    }

    /**
     * Builds the programs panel
     * @return - JPanel
     */
    private JPanel buildProgramPanel() {
        JPanel programPanel = new JPanel();
        int nrOfPrograms = 4;
        programPanel.setLayout((new GridLayout(nrOfPrograms/2, 2, 30, 30)));
        for (int i = 0; i < nrOfPrograms; i++){

            JButton temp = new JButton("P"+(i +1));
            Image im = ImageLoader.getImageLoader().getScaledImage(
                    "images/p"+(i +1)+".jpg", 300, 180);
            temp.setIcon(new ImageIcon(im));
            programPanel.add(temp);
        }

        return programPanel;
    }

    /**
     * Builds the lower panel
     * @return - JPanel
     */
    private JPanel buildLowerPanel() {
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return lowerPanel;
    }

    /**
     * Adds an ActionListener to exit in the JMenuBar
     * @param actionListener - ActionListener to add
     */
    public void addActionListenerToExit(ActionListener actionListener){
        SwingUtilities.invokeLater(() -> exit.addActionListener(actionListener));
    }

    /**
     * Closes the program
     */
    public void closeProgram() {
        SwingUtilities.invokeLater(() -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
    }
}
