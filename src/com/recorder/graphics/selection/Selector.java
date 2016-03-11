package com.recorder.graphics.selection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Selector {
    
    public Selector() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Selecting");
                frame.setUndecorated(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new BackgroundPane());
                
                frame.setResizable(false);
                frame.setBounds(getScreenViewableBounds());

                frame.setVisible(true);
            }

        });
    }

    public static Rectangle getScreenViewableBounds() {
        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getScreenDevices();
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        int maxy = Integer.MIN_VALUE;
        for (GraphicsDevice device : devices) {
            for (GraphicsConfiguration config : device.getConfigurations()) {
                Rectangle bounds = config.getBounds();
                minx = Math.min(minx, bounds.x);
                miny = Math.min(miny, bounds.y);
                maxx = Math.max(maxx, bounds.x + bounds.width);
                maxy = Math.max(maxy, bounds.y + bounds.height);
            }
        }
        return new Rectangle(new Point(minx, miny), new Dimension(maxx - minx, maxy - miny));
    }

    public static Rectangle getScreenViewableBounds(GraphicsDevice gd) {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);
        if (gd != null) {
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            bounds = gc.getBounds();

            Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

            bounds.x += insets.left;
            bounds.y += insets.top;
            bounds.width -= (insets.left + insets.right);
            bounds.height -= (insets.top + insets.bottom);
        }
        return bounds;
    }
}
