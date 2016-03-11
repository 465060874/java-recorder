package com.recorder.graphics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen {

    private boolean recording;
    
    private JPanel panel;
    private JLabel viewPort;
    private int width;
    private int height;
    private int frameRate;
    private Robot robot;
    private Rectangle captureRect;
    
    private GifWriter writer;
    private ImageOutputStream output;
    private Timer timer;
    private TimerTask viewTask;
    private TimerTask recordTask;
    
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
    
    public void recordClick() {
        if(isRecording())
            endRecording();
        else
            beginRecording();
    }
    
    /**
     * Begins the recording process.
     */
    public void beginRecording() {
        try {
            //set recording to true and update gui
            viewPort.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            setRecording(true);
            
            // grab the output image type from the first image in the sequence
            BufferedImage firstImage = robot.createScreenCapture(captureRect);

            // create a new BufferedOutputStream with the last argument
            output = new FileImageOutputStream(new File("C:\\gif.gif"));

            // create a gif writer
            writer = new GifWriter(output, firstImage.getType(), 1000 / frameRate, false);

            // write out the first image to our sequence...
            writer.writeToSequence(firstImage);
            
            recordTask = new TimerTask() {
                public void run() {
                    try {
                        BufferedImage nextImage = robot.createScreenCapture(captureRect);
                        if(isRecording() && nextImage != null)
                            writer.writeToSequence(nextImage);
                        else {
                            recordTask.cancel();
                            timer.purge();
                            writer.close();
                            output.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(recordTask, 0, 1000 / frameRate);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ends a recording session
     */
    public void endRecording() {
        setRecording(false);

        // update gui
        viewPort.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    
    public boolean isRecording() {
        return recording;
    }
    
    public void setRecording(boolean value) {
        recording = value;
    }
    
}
