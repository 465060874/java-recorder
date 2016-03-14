package com.recorder.gui.about;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.recorder.Settings;

public class AboutFrame extends JFrame {

    public AboutFrame(String name) {
        initialize(name);
    }

    private void initialize(String name) {
        setTitle(name);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buildPanels();

        setVisible(true);
    }

    private void buildPanels() {
        // panels
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        // labels
        JLabel titleVersion = new JLabel("Java Gif Recorder - Version " + Settings.getVersion(),
                SwingConstants.CENTER);
        titleVersion.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel dev = new JLabel("Developer:", SwingConstants.CENTER);
        dev.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel fullName = new JLabel("Nick Mckloski", SwingConstants.CENTER);
        fullName.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel web = new JLabel("Website:", SwingConstants.CENTER);
        web.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel website = new JLabel("www.NickMckloski.com", SwingConstants.CENTER);
        website.setFont(new Font("Arial", Font.PLAIN, 13));

        // add components to panels
        cons.gridx = 0;
        cons.gridy = 0;
        cons.ipady = 40;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(-50, 0, 0, 0);
        panel.add(titleVersion, cons);
        cons.gridy = 1;
        cons.ipady = 5;
        cons.insets = new Insets(0, 0, 0, 0);
        panel.add(dev, cons);
        cons.gridy = 2;
        panel.add(fullName, cons);
        cons.gridy = 3;
        panel.add(web, cons);
        cons.gridy = 4;
        panel.add(website, cons);

        // add panel to frame
        add(panel);
    }
}
