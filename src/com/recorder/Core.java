package com.recorder;

import com.recorder.gui.Frame;

public class Core {

    public static Frame frame;

    // initialize recorder
    public static void main(String[] args) {
        frame = new Frame("JavaRecorder");
        Settings.initialize();
    }

}
