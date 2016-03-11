package com.recorder.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.recorder.graphics.Recorder;
import com.recorder.graphics.Screen;
import com.recorder.graphics.selection.Selector;

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

        setLayout(new BorderLayout());

        buildPanels();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buildPanels() {
        // background default panel
        JPanel backPanel = new JPanel(new BorderLayout());
        // component panels
        JPanel viewPortPanel = new JPanel();
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
        controlsPanel.add(record);
        controlsPanel.add(selectRegion);

        // add component panels to backpanel
        backPanel.add(viewPortPanel, BorderLayout.NORTH);
        backPanel.add(controlsPanel, BorderLayout.SOUTH);

        // listeners
        record.addActionListener(e -> {
            recorder.recordClick();
            record.setText(recorder.isRecording() ? "End Recording" : "Begin Recording");
        });
        selectRegion.addActionListener(e -> new Selector());
        
        // finalize
        add(backPanel);
    }
}
