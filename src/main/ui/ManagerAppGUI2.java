package ui;


import gui.*;
import model.ManagementList;
import model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerAppGUI2 {
    public static final int SCREEN_HEIGHT = 500;
    public static final int SCREEN_WIDTH = 500;

    private static ManagementList properties;
    Property propertyToView;

    private JFrame frame;

    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton showButton;

    private JPanel buttonBar;
    private JLabel imageLabel;

    private static final String JSON_STORE = "./data/managementlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ManagerAppGUI2() throws FileNotFoundException {
        init();
        createFrame();
    }


    //Initialize the buttons and text fields
    private void init() {
        properties = new ManagementList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setUpAddButton();

        setUpRemoveButton();

        setUpViewButton();

        setUpSaveButton();

        setUpLoadButton();

        setUpShowButton();


        setUpButtonBar();

        setUpHomeScreenImage();


    }

    private void setUpAddButton() {
        addButton = new JButton();
        addButton.setText("Add");
        addButton.setFocusable(false);
        addButton.setOpaque(true);
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(new AddListener());
    }

    private void setUpRemoveButton() {
        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setFocusable(false);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.LIGHT_GRAY);
        removeButton.addActionListener(new RemoveListener());
    }

    private void setUpViewButton() {
        viewButton = new JButton();
        viewButton.setText("View");
        viewButton.setFocusable(false);
        viewButton.setOpaque(true);
        viewButton.setBackground(Color.LIGHT_GRAY);
//        viewButton.addActionListener(new ViewListener());
    }

    private void setUpSaveButton() {
        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setOpaque(true);
        saveButton.setBackground(Color.LIGHT_GRAY);
        saveButton.addActionListener(new SaveListener());
    }

    private void setUpLoadButton() {
        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setOpaque(true);
        loadButton.setBackground(Color.LIGHT_GRAY);
        loadButton.addActionListener(new LoadListener());
    }

    private void setUpShowButton() {
        showButton = new JButton();
        showButton.setText("Show");
        showButton.setFocusable(false);
        showButton.setOpaque(true);
        showButton.setBackground(Color.LIGHT_GRAY);
        showButton.addActionListener(new ShowListener());
    }

    private void setUpButtonBar() {
        buttonBar = new JPanel();
        buttonBar.setPreferredSize(new Dimension(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 10));
        buttonBar.setBackground(Color.LIGHT_GRAY);
        buttonBar.setOpaque(true);
        buttonBar.setLayout(new FlowLayout());
        buttonBar.add(addButton);
        buttonBar.add(removeButton);
        buttonBar.add(viewButton);
        buttonBar.add(saveButton);
        buttonBar.add(loadButton);
        buttonBar.add(showButton);
    }

    private void setUpHomeScreenImage() {
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - SCREEN_HEIGHT / 10));
        ImageIcon image = new ImageIcon(
                "/Users/trnghia240/IdeaProjects/personal project/project_m7p3q/src/main/gui/screen logo.png");
        imageLabel.setIcon(image);
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                new WindowForAdd();
            }

        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                new RemoveWindow();
            }
        }
    }


//    class ViewListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == viewButton) {
//                searchPanel.setVisible(true);
//                String inputText = searchField.getText();
//
//
//                for (Property p : properties.getProperties()) {
//                    if (inputText.equals(p.getAddress())) {
//                        propertyToView = p;
//                    }
//
//                }
//            }
//
//        }
//    }

    class ShowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showButton) {
                new ShowWindow();

            }

        }
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(properties);
                jsonWriter.close();
                System.out.println("Saved to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                properties = jsonReader.read();
                System.out.println("Loaded from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }


    private void createFrame() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new BorderLayout());

        frame.add(buttonBar, BorderLayout.SOUTH);
        frame.add(imageLabel, BorderLayout.NORTH);


        frame.setVisible(true);
    }

    public static ManagementList getManagementList() {
        return properties;
    }
}




