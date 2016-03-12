package com.recorder.ui.settings;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.recorder.Core;
import com.recorder.Settings;

public class SettingsFrame extends JFrame {

    public SettingsFrame() {
        initialize();
    }

    private void initialize() {
        setTitle("Settings");
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buildPanels();

        setVisible(true);
    }

    private void buildPanels() {
        // panel
        JPanel panel = new JPanel(new BorderLayout());
        JPanel optionsPanel = new JPanel();

        // components
        JLabel label = new JLabel("Java Gif Recorder settings.");
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        JCheckBox loop = new JCheckBox("Create continuously looping gif.", Settings.loop);
        JCheckBox stopWhenRec = new JCheckBox("Disable viewport when recording.", Settings.stopWhenRec);
        JCheckBox stopView = new JCheckBox("Disable viewport entirely.", Settings.stopView);

        JButton ok = new JButton("Apply");

        // add to panels
        optionsPanel.add(label);
        optionsPanel.add(loop);
        optionsPanel.add(stopWhenRec);
        optionsPanel.add(stopView);

        panel.add(optionsPanel, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);

        // listeners
        loop.addActionListener(e -> Settings.loop = loop.isSelected());
        stopWhenRec.addActionListener(e -> Settings.stopWhenRec = stopWhenRec.isSelected());
        stopView.addActionListener(e -> {
            Settings.stopView = stopView.isSelected();
            if(Settings.stopView)
                Core.frame.screen.stopViewTask();
            else
                Core.frame.screen.startViewTask();
        });
        ok.addActionListener(e -> { 
            Settings.saveSettings();
            dispose();
        });
        
        add(panel);
    }

}
