package com.recorder.graphics;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen {

    
    public JPanel panel;
    public JLabel viewPort;
    public int width;
    public int height;
    public int frameRate;
    public Robot robot;
    public Rectangle captureRect;
    
    public Timer timer;
    public TimerTask viewTask;
    
    public Screen(JPanel panel, JLabel viewPort, int width, int height, int frameRate) {
        this.panel = panel;
        this.viewPort = viewPort;
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        captureRect = new Rectangle(0, 0, width, height);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to display the viewport as a label icon on the panel.
     */
    public void displayViewport() {
        Dimension size = new Dimension(width, height);
        BufferedImage image = robot.createScreenCapture(captureRect);

        viewPort.setPreferredSize(size);
        viewPort.setIcon(new ImageIcon(image));
        panel.add(viewPort);
        panel.repaint();

        timer = new Timer();
        viewTask = new TimerTask() {
            public void run() {
                viewPort.setIcon(new ImageIcon(robot.createScreenCapture(captureRect)));
            }
        };
        timer.schedule(viewTask, 1, 1000 / frameRate);
    }
    
    
    
}
