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

import com.recorder.Core;

public class Screen {

    public JPanel panel;
    public JLabel viewPort;
    public int x;
    public int y;
    public int width;
    public int height;
    public int frameRate;
    public Robot robot;
    public Rectangle captureRect;

    public Timer timer;
    public TimerTask viewTask;

    /**
     * Construct screen
     * 
     * @param panel
     *            jpanel to draw on
     * @param viewPort
     *            jlabel to set the image onto
     * @param x
     *            screen x position
     * @param y
     *            screen y position
     * @param width
     *            screen width
     * @param height
     *            screen height
     * @param frameRate
     *            display frame rate
     */
    public Screen(JPanel panel, JLabel viewPort, int x, int y, int width, int height,
            int frameRate) {
        this.panel = panel;
        this.viewPort = viewPort;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        captureRect = new Rectangle(x, y, width, height);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the screen region
     * 
     * @param x
     *            screen x position
     * @param y
     *            screen y position
     * @param width
     *            screen width
     * @param height
     *            screen height
     */
    public void updateScreenRegion(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        captureRect = new Rectangle(x, y, width, height);
        viewPort.setPreferredSize(new Dimension(width, height));

        viewPort.revalidate();
        viewPort.repaint();
        Core.frame.pack();
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

        startViewTask();
    }

    public void startViewTask() {
        timer = new Timer();
        viewTask = new TimerTask() {
            public void run() {
                viewPort.setIcon(new ImageIcon(robot.createScreenCapture(captureRect)));
            }
        };
        timer.schedule(viewTask, 1, 1000 / frameRate);
    }

    public void stopViewTask() {
        viewTask.cancel();
        timer.purge();
    }

}
