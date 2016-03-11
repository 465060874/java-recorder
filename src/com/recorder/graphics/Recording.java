package com.recorder.graphics;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.recorder.Core;

public class Recording {

    public static void displayViewport(JPanel panel, JLabel viewPort, int width, int height, int frameRate) {
        Timer timer = new Timer();

        try {
            Robot robot = new Robot();

            Dimension size = new Dimension(width, height);
            Rectangle captureRect = new Rectangle(0, 0, width, height);
            BufferedImage image = robot.createScreenCapture(captureRect);

            viewPort.setPreferredSize(size);
            viewPort.setIcon(new ImageIcon(image));
            panel.add(viewPort);
            panel.repaint();

            timer.schedule(new TimerTask() {

                public void run() {
                    viewPort.setIcon(new ImageIcon(robot.createScreenCapture(captureRect)));
                    panel.repaint();
                }
            }, 0, 1000 / frameRate);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
