package gui;

import model.Channel;
import model.Episode;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class: Gui
 *
 * @author - David Irén
 *
 * Does everything related to the gui
 */

public class Gui {

    private CardLayout cardLayout;
    private String currentFrame;
    private String currentChannelShown;
    private JFrame frame;
    private JPanel startFrame;
    private JPanel srPanel;

    private JPanel episodeDescriptionPanel;
    private JLabel episodeImage;
    private JTextArea episodeDescription;

    private JScrollPane channelScroll;

    private DefaultTableModel channelModel;
    private JTable channelTable;

    private EpisodeTableModel programModel;
    private JTable programTable;
    private JScrollPane programScroll;

    private JButton update;
    private LinkedHashMap<String, Channel> channels;

    //Menu items
    private JMenuItem helptext;
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
        //Build frame
        startFrame = buildStartFrame();
        //add to frame
        frame.add((startFrame), "start");
        frame.setPreferredSize(new Dimension(1400,900));
        frame.pack();

    }

    /**
     * build gui panel
     * @return - JPanel to show
     */
    private JPanel buildStartFrame() {
        JPanel temp = new JPanel();
        temp.setLayout(new BorderLayout());
        srPanel = buildSRPanel();
        channelScroll = buildChannelTable();
        programScroll = buildProgramsTableForAChannel();
        buildEpisodeDescriptionPanel();

        temp.add(srPanel, BorderLayout.NORTH);
        temp.add(channelScroll, BorderLayout.WEST);
        temp.add(programScroll, BorderLayout.CENTER);
        temp.add(episodeDescriptionPanel, BorderLayout.SOUTH);


        return temp;
    }

    /**
     * build Episode description panel
     */
    private void buildEpisodeDescriptionPanel() {
        episodeDescriptionPanel = new JPanel();
        episodeDescriptionPanel.setLayout(new BorderLayout());
        Image im = ImageLoader.getImageLoader().getScaledImage(
                "images/nopic.png",200, 200);
        episodeImage = new JLabel(new ImageIcon(im));
        episodeDescriptionPanel.add(episodeImage, BorderLayout.WEST);
        episodeDescription = new JTextArea();
        episodeDescription.append("\nTryck på ett program sedan så kommer informationen här");
        episodeDescription.setEditable(false);
        episodeDescriptionPanel.add(episodeDescription, BorderLayout.CENTER);

    }

    /**
     * Make the gui visible
     */
    public void showStartFrame(){
        SwingUtilities.invokeLater(() -> {
            cardLayout.show(frame.getContentPane(), "start");
            frame.setVisible(true);
            currentFrame = "start";
        });
    }

    /**
     * Build channel-table and display all channels
     * @return - JScrollPane with channel-table
     */
    public JScrollPane buildChannelTable(){
        channelModel = new DefaultTableModel();
        channelTable = new JTable(channelModel);
        channelModel.addColumn("Radio Kanal");
        int i = 0;
        for(HashMap.Entry<String, Channel> entry: channels.entrySet()){
            //do not show channels that do not have episodes
            if (entry.getValue().getEpisodeList().size() <= 0){
                continue;
            }
            channelModel.insertRow(i, new Object[] {entry.getValue().getName()});
            i++;
        }
        channelTable.setRowHeight(25);
        return new JScrollPane(channelTable);
    }

    /**
     * Build episode-table columns and add renderer
     * @return - JScrollPane with episode table
     */
    public JScrollPane buildProgramsTableForAChannel(){
        programModel = new EpisodeTableModel();
        programTable = new JTable(programModel);
        programModel.addColumn("Program");
        programModel.addColumn("Start");
        programModel.addColumn("Slut");
        programTable.setRowHeight(25);

        //add renderer that can color cells
        for (int i =0; i<programModel.getColumnCount();i++) {
            programTable.setDefaultRenderer(programTable.getColumnClass(i),
                    new CellRenderer());
        }

        return new JScrollPane(programTable);
    }

    /**
     * Display episodes from a channel
     * @param channel - channel to show episodes from
     */
    private synchronized void displayEpisodesFrom(String channel){
        programModel.setRowCount(0);
        System.out.println(channel);

        int i = 0;
        //This will insert a row with name of episode,
        //start and endtime for that episode, into the table
        for (Episode e:channels.get(channel).getEpisodeList()) {
            programModel.insertRow(i, new Object[]{e.getProgramName(),
                    e.getStartTime().substring(e.getStartTime().indexOf("T") + 1,
                            e.getStartTime().indexOf("Z")),
                    e.getEndTime().substring(e.getEndTime().indexOf("T") + 1,
                            e.getEndTime().indexOf("Z"))});

            i++;
        }

    }

    /**
     * Builds the Menu Bar
     * @return - JMenuBar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //make menu options
        JMenu archive = new JMenu("Arkiv");
        helptext = new JMenuItem("Instruktioner");
        exit = new JMenuItem("Avsluta");
        archive.add(exit);

        //Second menu option
        JMenu help = new JMenu("Hjälp");
        help.add(helptext);
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
     * Display information from an episode
     * @param episode - episode to display info from
     */
    private void updateEpisodeDescriptionPanel(Episode episode) {
        Image img;
        img = ImageLoader.getImageLoader().getScaledImageFromUrl(
                episode.getImageURL(), 200, 200);
        episodeImage.setIcon(new ImageIcon(img));
        episodeDescription.selectAll();
        episodeDescription.replaceSelection(episode.getTitle()+": \n\n");
        episodeDescription.append(episode.getDescription());

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

    /**
     * Add listener to help button in the menu-bar
     * @param actionListener - listener to add
     */
    public void addActionListenerToHelp(ActionListener actionListener){
        SwingUtilities.invokeLater(() ->
                helptext.addActionListener(actionListener));
    }

    /**
     * Add mouse listener to channel table
     * @param mouseAdapter - mouse adapter to add
     */
    public void addMouseListenerToChannelTable(MouseAdapter mouseAdapter) {
        SwingUtilities.invokeLater(() ->
            channelTable.addMouseListener(mouseAdapter));
    }

    /**
     * Display episodes from a channel
     * @param event - MouseEvent
     */
    public synchronized void displayEpisodes(MouseEvent event) {
        SwingUtilities.invokeLater(() -> {
            int row = channelTable.rowAtPoint(event.getPoint());
            int col = 0; //first column has name of ep
            displayEpisodesFrom(channelTable.getValueAt(row, col).toString());
            currentChannelShown =
                    channelTable.getValueAt(row, col).toString();
        });

    }

    /**
     * Add a mouse listener to the episode table
     * @param mouseAdapter - mouse adapter to add
     */
    public void addMouseListenerToEpisodeTable(MouseAdapter mouseAdapter) {
        SwingUtilities.invokeLater(() ->
        programTable.addMouseListener(mouseAdapter));
    }

    /**
     * Display the description for a clicked episode
     * @param event - MouseEvent
     */
    public synchronized void displayEpisodeDescription(MouseEvent event) {
        SwingUtilities.invokeLater(() -> {
            int row = channelTable.rowAtPoint(event.getPoint());
            System.out.println("Current channel: "+currentChannelShown+" " +
                    "rowNr: "+row+ " Episodes in current channel: "+channels.get(currentChannelShown).getEpisodeList().size()+" Number of Rows in table: "+ programTable.getRowCount());
            //this will always give me the name of the episode
            //will not need column
            updateEpisodeDescriptionPanel(channels.get(
                    currentChannelShown)
                    .getEpisodeList().get(row));
        });
    }

    /**
     * Update the hashmap that has the channels
     * @param lhm
     */
    public void update(LinkedHashMap<String, Channel> lhm){
        channels = lhm;
        updateChannelsShown();


    }

    /**
     * Update all channels in the gui
     */
    private void updateChannelsShown(){
        int i = 0;
        channelModel.setRowCount(0);
        for(HashMap.Entry<String, Channel> entry: channels.entrySet()){
            //do not show channels that do not have episodes
            if (entry.getValue().getEpisodeList().size() <= 0){
                continue;
            }
            channelModel.insertRow(i, new Object[] {entry.getValue().getName()});
            i++;
        }
    }

    /**
     * Add listener to update-button
     * @param listener - ActionListener to add
     */
    public void addButtonListenerToUpdate(ActionListener listener) {
        update.addActionListener(listener);
    }

    /**
     * disable update-button
     */
    public void disableUpdateButton() {
        update.setEnabled(false);
    }

    /**
     * Enable update-button
     */
    public void enableUpdateButton() {
        update.setEnabled(true);
    }

    /**
     * This will show a popup when user press "help" button
     */
    public void showPopupHelper() {
        String s = "Detta program hämtar alla radiokanaler från Sveriges " +
                "Radio och visar dem till vänster." +
                "\n Trycker du på en radiokanal så" +
                "kommer tablån för den kanalen att visas.\n Det som är " +
                "rödmarkerat i tablån är de program som redan har redan " +
                "har gått.\n Trycker du på ett program så kommer " +
                "programmets beskrivning presenteras i rutan längst ner \n" +
                "Det finns en uppdatera-knapp som kommer att " +
                "uppdatera om alla kanaler.\n Utöver det så kommer allt att " +
                "automatiskt uppdateras en gång varje timme.";
        JOptionPane.showMessageDialog(frame, s);
    }
}
