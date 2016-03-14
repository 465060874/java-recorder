package com.recorder.gui.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {

    /**
     * Gets version number
     * 
     * @return version number
     */
    public static double getVersion() {
        return 1.0;
    }

    public static boolean loop;
    public static boolean stopWhenRec;
    public static boolean stopView;

    /**
     * Gets the directory of the settings data
     * 
     * @return path to file with trailing slash included
     */
    public static String getDataDir() {
        return System.getProperty("user.home") + File.separator + ".jgifrecorder" + File.separator + getVersion() + File.separator;
    }

    /**
     * Initialize the settings, create the file if it dosn't exist.
     */
    public static void initialize() {
        Path pathToFile = Paths.get(getDataDir() + "settings.properties");
        // attempt to create directory/file, if it already exists read
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
            // set default values
            loop = false;
            stopWhenRec = false;
            stopView = false;
            saveSettings();
            return;
        } catch (IOException ex) {
            try {
                // load properties
                Properties properties = new Properties();
                FileInputStream inputStream = new FileInputStream(pathToFile.toFile());
                properties.load(inputStream);
                inputStream.close();

                // read properties
                loop = properties.get("loop").equals("true") ? true : false;
                stopWhenRec = properties.get("stopWhenRec").equals("true") ? true : false;
                stopView = properties.get("stopView").equals("true") ? true : false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Save the settings file, create if it dosn't exist
     */
    public static void saveSettings() {
        Path pathToFile = Paths.get(getDataDir() + "settings.properties");
        // attempt to create directory/file if it exist
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
        } catch (IOException ex) {
        }

        try {
            // set properties
            Properties props = new Properties();
            FileWriter writer = new FileWriter(pathToFile.toFile());
            props.setProperty("loop", loop ? "true" : "false");
            props.setProperty("stopWhenRec", stopWhenRec ? "true" : "false");
            props.setProperty("stopView", stopView ? "true" : "false");
            props.store(writer, "settings");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
