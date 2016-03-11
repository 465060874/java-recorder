package com.recorder.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.BorderFactory;

public class Recorder {

    private Screen screen;
    
    public boolean recording;
    
    public Recorder(Screen screen) {
        this.screen = screen;
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
            screen.viewPort.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            setRecording(true);
            
            // grab the output image type from the first image in the sequence
            BufferedImage firstImage = screen.robot.createScreenCapture(screen.captureRect);

            // create a new BufferedOutputStream with the last argument
            FileImageOutputStream output = new FileImageOutputStream(new File("C:\\gif.gif"));

            // create a gif writer
            GifWriter writer = new GifWriter(output, firstImage.getType(), 1000 / screen.frameRate, false);

            // write out the first image to our sequence...
            writer.writeToSequence(firstImage);
            
            //screen.stopViewTask();
            
            TimerTask recordTask = new TimerTask() {
                public void run() {
                    try {
                        BufferedImage nextImage = screen.robot.createScreenCapture(screen.captureRect);
                        if(isRecording() && nextImage != null)
                            writer.writeToSequence(nextImage);
                        else {
                            this.cancel();
                            screen.timer.purge();
                            writer.close();
                            output.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            screen.timer.schedule(recordTask, 0, 1000 / screen.frameRate);
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
        screen.viewPort.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //screen.startViewTask();
    }
    
    public boolean isRecording() {
        return recording;
    }
    
    public void setRecording(boolean value) {
        recording = value;
    }
}
