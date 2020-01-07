package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Gui {

    private CardLayout cardLayout;
    private String currentFrame;
    private JFrame frame;
    private JPanel startFrame;
    private JPanel srPanel;
    private JPanel programPanel;
    private JPanel lowerPanel;
    //HashMap<String,JButton> programButtons = new HashMap<>();
    ArrayList<JButton> programButtons = new ArrayList<>();

    //Menu items
    private JMenuItem archiveItem1;
    private JMenuItem archiveItem2;
    private JMenuItem exit;

    /**
     * Constructor
     *
     * puts all the gui components together
     */
    public Gui(){
        cardLayout = new CardLayout();

        frame = new JFrame("RadioInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(cardLayout);
        frame.setJMenuBar(buildMenuBar());

        //Build frames
        startFrame = buildStartFrame();


        //add to frame
        frame.add(startFrame, "start");
        //frame.add(lowerPanel,"start");

        frame.setPreferredSize(new Dimension(600,700));
        frame.pack();

    }

    private JPanel buildStartFrame() {
        JPanel temp = new JPanel();
        srPanel = buildSRPanel();
        programPanel = buildProgramPanel();
        lowerPanel = buildLowerPanel();

        temp.add(srPanel, BorderLayout.NORTH);
        temp.add(programPanel, BorderLayout.CENTER);

        return temp;
    }

    public void showStartFrame(){
        SwingUtilities.invokeLater(() -> {
            cardLayout.show(frame.getContentPane(), "start");
            frame.setVisible(true);
            currentFrame = "start";
        });
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
        exit = new JMenuItem("Avsluta");
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
        for (int i = 0; i < nrOfPrograms+8; i++){
            JButton temp = new JButton(Integer.toString(i));
            programButtons.add(temp);
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
        SwingUtilities.invokeLater(() ->
                exit.addActionListener(actionListener));
    }

    /**
     * Closes the program
     */
    public void closeProgram() {
        SwingUtilities.invokeLater(() -> frame.dispatchEvent(
                new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
    }

    public void addActionListenerToPrograms(ActionListener actionListener) {
        SwingUtilities.invokeLater(() -> {
            for(int i = 0; i < programButtons.size(); i++){
                programButtons.get(i).addActionListener(actionListener);
            }
        });

    }

    public void changeToTableOfPrograms(Object id) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JButton button = (JButton) id;

                System.out.println(button.getText());
                //TODO: make a switch that changes ui to the correct table depending on which button was pressed

            }
        });


        //System.out.println(id);
    }
}
