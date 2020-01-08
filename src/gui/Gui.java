package gui;

import model.Channel;
import model.Episode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Gui {

    private CardLayout cardLayout;
    private String currentFrame;
    private JFrame frame;
    private JPanel startFrame;
    private JPanel srPanel;
    private JPanel programPanel;
    private JPanel lowerPanel;
    private JScrollPane channelScroll;

    private DefaultTableModel channelModel;
    private JTable channelTable;

    private DefaultTableModel programModel;
    private JTable programTable;
    private JScrollPane programScroll;

    private JButton update;
    //HashMap<String,JButton> programButtons = new HashMap<>();
    private ArrayList<JButton> programButtons = new ArrayList<>();
    private LinkedHashMap<String, Channel> channels;

    //Menu items
    private JMenuItem archiveItem1;
    private JMenuItem archiveItem2;
    private JMenuItem exit;

    /**
     * Constructor
     *
     * puts all the gui components together
     */
    public Gui(LinkedHashMap<String, Channel> channels){
        this.channels = channels;

        cardLayout = new CardLayout();

        frame = new JFrame("RadioInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(cardLayout);
        frame.setJMenuBar(buildMenuBar());

        //Build frames
        startFrame = buildStartFrame();

        //add to frame
        frame.add((startFrame), "start");
        //frame.add(lowerPanel,"start");

        frame.setPreferredSize(new Dimension(800,900));
        frame.pack();

    }

    private JPanel buildStartFrame() {
        JPanel temp = new JPanel();
        temp.setLayout(new BorderLayout());
        srPanel = buildSRPanel();
        //programPanel = buildProgramPanel();
        channelScroll = buildChannelTable();
        programScroll = buildProgramsTableForAChannel("P3");

        temp.add(srPanel, BorderLayout.NORTH);
        temp.add(channelScroll, BorderLayout.WEST);
        temp.add(programScroll, BorderLayout.CENTER);


        return temp;
    }

    public void showStartFrame(){
        SwingUtilities.invokeLater(() -> {
            cardLayout.show(frame.getContentPane(), "start");
            frame.setVisible(true);
            currentFrame = "start";
        });
    }

    public JScrollPane buildChannelTable(){
        channelModel = new DefaultTableModel();
        channelTable = new JTable(channelModel);
        channelModel.addColumn("Radio Kanal");
        int i = 0;
        for(HashMap.Entry<String, Channel> entry: channels.entrySet()){
            channelModel.insertRow(i, new Object[] {entry.getValue().getName()});
            i++;
        }
        //TODO: Make channelTable less wide
        //channelTable.getColumn("Radio Kanal").setPreferredWidth(100);
        channelTable.addMouseListener(createMouseListner());
        channelTable.setRowHeight(25);
        return new JScrollPane(channelTable);
    }

    private MouseListener createMouseListner() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = channelTable.rowAtPoint(e.getPoint());
                int col = channelTable.columnAtPoint(e.getPoint());
                displayEpisodesFrom(channelTable.getValueAt(row, col).toString());
            }
        };
    }

    public JScrollPane buildProgramsTableForAChannel(String nameOfChannel){
        programModel = new DefaultTableModel();
        programTable = new JTable(programModel);
        programModel.addColumn("Program");
        programModel.addColumn("Start");
        programModel.addColumn("Slut");

        return new JScrollPane(programTable);
    }

    private void displayEpisodesFrom(String channel){
        programModel.setRowCount(0);
        System.out.println(channel);

        /*for (Episode e:channels.indexOf()) {

        }*/
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
     * Builds the SR picture panel, and update button
     * @return - JPanel
     */
    private JPanel buildSRPanel() {
        JPanel srPanel = new JPanel();
        update = new JButton("Uppdatera");
        srPanel.setLayout(new BorderLayout());
        Image img;
        img = ImageLoader.getImageLoader().getScaledImage(
                "images/sverigesradio.jpg", 400, 100);
        JLabel pic = new JLabel(new ImageIcon(img));
        srPanel.add(pic, BorderLayout.CENTER);
        srPanel.add(update, BorderLayout.EAST);

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

        int nrOfPrograms = channels.size();
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
