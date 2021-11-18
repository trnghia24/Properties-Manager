package gui;

import gui.ManagerAppGUI2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ManagerAppGUI2();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }

}
