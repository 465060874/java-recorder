package com.recorder.graphics.selection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.recorder.Core;

public class SelectionPane extends JPanel {

    private JButton select;
    private JLabel label;

    public SelectionPane() {
        select = new JButton("Select");
        setOpaque(false);

        label = new JLabel("Rectangle");
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(4, 4, 4, 4));
        label.setBackground(Color.GRAY);
        label.setForeground(Color.WHITE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.gridy++;
        add(select, gbc);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.frame.screen.updateScreenRegion(getX(), getY(), getWidth(), getHeight());
                SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                label.setText("Selected: " + getWidth() + "x" + getHeight());
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // draws the line around selection
        float dash1[] = { 10.0f };
        BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10.0f, dash1, 0.0f);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(dashed);
        g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
        g2d.dispose();
    }
}