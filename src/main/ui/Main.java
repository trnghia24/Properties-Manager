package ui;

import java.io.FileNotFoundException;

public class Main {
//    public static void main(String[] args) {
//        try {
//            new ManagerAppGUI();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
//
//    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ManagerAppGUI app = new ManagerAppGUI();
                app.createAndShowGUI();
            }
        });
    }
}
