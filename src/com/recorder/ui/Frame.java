package com.recorder.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.recorder.Core;
import com.recorder.graphics.Recording;

public class Frame extends JFrame {

    /***
     * Construct the Frame class
     * 
     * @param name
     *            - title of the frame
     */
    public Frame(String name) {
        initialize(name);
    }

    private void initialize(String name) {
        // build frame
        setTitle(name);
        setSize(800, 600);

        setLayout(new BorderLayout());

        buildPanels();

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
        JButton record = new JButton("Record");
        record.addActionListener(e -> System.out.println("rec"));
        // labels
        JLabel viewPort = new JLabel();
        JLabel width = new JLabel("Width");
        JLabel height = new JLabel("Height");
        // fields
        JTextField widthField = new JTextField();
        widthField.setPreferredSize(new Dimension(75, 25));
        JTextField heightField = new JTextField();
        heightField.setPreferredSize(new Dimension(75, 25));

        // viewport
        Recording.displayViewport(viewPortPanel, viewPort, 700, 475, 25);

        // add components to panels
        controlsPanel.add(record);
        controlsPanel.add(width);
        controlsPanel.add(widthField);
        controlsPanel.add(height);
        controlsPanel.add(heightField);

        // add component panels to backpanel
        backPanel.add(viewPortPanel, BorderLayout.NORTH);
        backPanel.add(controlsPanel, BorderLayout.SOUTH);

        // finalize
        add(backPanel);
    }
}
