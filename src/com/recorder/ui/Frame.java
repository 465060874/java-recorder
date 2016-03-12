package com.recorder.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.recorder.graphics.Recorder;
import com.recorder.graphics.Screen;
import com.recorder.graphics.selection.Selector;
import com.recorder.ui.settings.SettingsFrame;

public class Frame extends JFrame {

    public Screen screen;
    public Recorder recorder;
    
    /***
     * Construct the Frame class
     * 
     * @param name
     *            title of the frame
     */
    public Frame(String name) {     
        initialize(name);
    }

    /**
     * Initializes and builds the frame
     * 
     * @param name
     *            title of the frame
     */
    private void initialize(String name) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle(name);
        setSize(800, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildPanels();

        pack();
        setVisible(true);
    }

    private void buildPanels() {
        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem about = new JMenuItem("About");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(settings);
        file.add(about);
        file.add(exit);
        menuBar.add(file);
        
        // background default panel
        JPanel backPanel = new JPanel(new BorderLayout());
        // component panels
        JPanel viewPortPanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel();

        // buttons
        JButton record = new JButton("Begin Recording");
        JButton selectRegion = new JButton("Select Region");
        // labels
        JLabel viewPort = new JLabel();
        viewPort.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // initialize screen
        screen = new Screen(viewPortPanel, viewPort, 0, 0, 700, 475, 60);
        screen.displayViewport();
        //initialize recorder
        recorder = new Recorder(screen);

        // add components to panels
        viewPortPanel.add(menuBar, BorderLayout.NORTH);
        controlsPanel.add(record);
        controlsPanel.add(selectRegion);

        // add component panels to backpanel
        backPanel.add(viewPortPanel, BorderLayout.NORTH);
        backPanel.add(controlsPanel, BorderLayout.SOUTH);

        // listeners
        settings.addActionListener(e -> new SettingsFrame());
        exit.addActionListener(e -> System.exit(0));
        record.addActionListener(e -> {
            recorder.recordClick();
            record.setText(recorder.isRecording() ? "End Recording" : "Begin Recording");
        });
        selectRegion.addActionListener(e -> new Selector());
        
        // finalize
        add(backPanel);
    }
}
