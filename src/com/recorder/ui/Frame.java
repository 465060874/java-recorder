package com.recorder.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.recorder.graphics.Recorder;
import com.recorder.graphics.Screen;

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
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void buildPanels() {
        // background default panel
        JPanel backPanel = new JPanel(new BorderLayout());
        // component panels
        JPanel viewPortPanel = new JPanel();
        JPanel controlsPanel = new JPanel();

        // buttons
        JButton record = new JButton("Begin Recording");
        // labels
        JLabel viewPort = new JLabel();
        viewPort.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel width = new JLabel("Width");
        JLabel height = new JLabel("Height");
        // fields
        JTextField widthField = new JTextField();
        widthField.setPreferredSize(new Dimension(75, 25));
        widthField.setText("700");
        JTextField heightField = new JTextField();
        heightField.setPreferredSize(new Dimension(75, 25));
        heightField.setText("475");

        // initialize screen
        screen = new Screen(viewPortPanel, viewPort, 700, 475, 60);
        screen.displayViewport();
        //initialize recorder
        recorder = new Recorder(screen);

        // add components to panels
        controlsPanel.add(record);
        controlsPanel.add(width);
        controlsPanel.add(widthField);
        controlsPanel.add(height);
        controlsPanel.add(heightField);

        // add component panels to backpanel
        backPanel.add(viewPortPanel, BorderLayout.NORTH);
        backPanel.add(controlsPanel, BorderLayout.SOUTH);

        // listeners
        record.addActionListener(e -> {
            recorder.recordClick();
            record.setText(recorder.isRecording() ? "End Recording" : "Begin Recording");
        });
        
        // finalize
        add(backPanel);
    }
}
