package com.company;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class GameConfiguration {

    private Dialogs dialogs;
    private Scanner R_file;
    private int numberOfLevels;
    private String [] paths;


    public GameConfiguration(){
        this.dialogs = new Dialogs();
        this.numberOfLevels = 0;
        this.paths = new String[100];
    }

/* ------------------------------------------------ */
    public void openReadFile(){
        try{
            R_file = new Scanner(new File("configuration.txt"));
        } catch(Exception e) {
            dialogs.showCannotFindFileFrame();
        }
    }

    public void readConfiguration(String[] paths){
        int i = 0;
        numberOfLevels = Integer.parseInt(R_file.next());
        while(R_file.hasNext()){
            if (i < numberOfLevels){
                paths[i] = R_file.next();
                i = i + 1;
            } else {
                break;
            }
        }
    }

    public void closeReadFile(){ R_file.close(); }

    public void getConfiguration(){
        openReadFile();
        readConfiguration(paths);
        closeReadFile();


    }
    /* ------------------------------------------------ */

    public int getNumberOfLevels() {return this.numberOfLevels; }

    public void setNumberOfLevels(int numberOfLevels) { this.numberOfLevels = numberOfLevels; }

    public String[] getPaths() {return this.paths; }

    public void setPaths(String[] paths) { this.paths = paths; }

}
