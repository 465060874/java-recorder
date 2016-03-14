package com.recorder;

import com.recorder.gui.Frame;
import com.recorder.gui.settings.Settings;

public class Core {

    public static Frame frame;

    // initialize recorder
    public static void main(String[] args) {
        frame = new Frame("Java Gif Recorder");
        Settings.initialize();
    }

}
