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

        addButton = new JButton();
        addButton.setText("Add");
        addButton.setFocusable(false);
        addButton.setOpaque(true);
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(new AddListener());

        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setFocusable(false);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.LIGHT_GRAY);
        removeButton.addActionListener(new RemoveListener());

        viewButton = new JButton();
        viewButton.setText("View");
        viewButton.setFocusable(false);
        viewButton.setOpaque(true);
        viewButton.setBackground(Color.LIGHT_GRAY);
//        viewButton.addActionListener(new ViewListener());

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setOpaque(true);
        saveButton.setBackground(Color.LIGHT_GRAY);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setOpaque(true);
        loadButton.setBackground(Color.LIGHT_GRAY);
        loadButton.addActionListener(new LoadListener());

        showButton = new JButton();
        showButton.setText("Show");
        showButton.setFocusable(false);
        showButton.setOpaque(true);
        showButton.setBackground(Color.LIGHT_GRAY);
        showButton.addActionListener(new ShowListener());


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
                new AddWindow();
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
                new DisplayWindow();

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

//    class RemoveWindow {
//        private JFrame frame;
//
//        private JButton removeButton;
//        private JTextField removeField;
//        private JPanel removePanel;
//
//        RemoveWindow() {
//            setUpFrame();
//        }
//
//        private void setUpRemovePanel() {
//            removeButton = new JButton("Remove");
//            removeButton.setFocusable(false);
//            removeButton.setOpaque(true);
//            removeButton.setBackground(Color.LIGHT_GRAY);
//            removeButton.addActionListener(new DoRemoveListener());
//            removeField = new JTextField();
//            removeField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//            removePanel = new JPanel();
//            removePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
//            removePanel.setBackground(Color.LIGHT_GRAY);
//            removePanel.setOpaque(true);
//            removePanel.setLayout(new FlowLayout());
//            removePanel.add(removeField);
//            removePanel.add(removeButton);
//        }
//
//        class DoRemoveListener implements ActionListener {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == removeButton) {
//                    String inputText = removeField.getText();
//                    for (Property p: properties.getProperties()) {
//                        if (inputText.equals(p.getAddress())) {
//                            properties.deleteProperty(p);
//                        }
//                    }
//                }
//            }
//        }
//
//        private void setUpFrame() {
//            frame = new JFrame("Removing property");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLayout((new FlowLayout(FlowLayout.CENTER)));
//            frame.setSize(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT);
//
//            setUpRemovePanel();
//
//            frame.add(removePanel);
//            frame.setVisible(true);
//        }
//    }

//    class WindowForAdd {
//        private JFrame frame;
//
//        private JPanel addressPanel;
//        private JPanel pricePanel;
//        private JPanel capacityPanel;
//        private JPanel statusPanel;
//        private JPanel payPanel;
//        private JPanel buttonPanel;
//
//
//        private JTextField addressField;
//        private JTextField priceField;
//        private JTextField capacityField;
//        private JTextField statusField;
//        private JTextField payField;
//
//        private JButton addButton;
//        private JButton backButton;
//
//        public WindowForAdd() {
//            setUpFrame();
//        }
//
//        private void setUpPanels() {
//            //
//            createAddressPanel();
//
//            //
//            createPricePanel();
//
//
//            //
//            createCapacityPanel();
//
//            //
//            createStatusPanel();
//
//
//            //
//            createPayPanel();
//
//            createButtonPanel();
//
//
//        }
//
//        private void createButtonPanel() {
//            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//
//            addButton = new JButton("Add");
//            addButton.addActionListener(new AddListener());
//            backButton = new JButton("Back");
//            backButton.addActionListener(new BackListener());
//
//            buttonPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            buttonPanel.setBackground(Color.LIGHT_GRAY);
//            buttonPanel.setOpaque(true);
//            buttonPanel.add(addButton);
//            buttonPanel.add(backButton);
//        }
//
//        private void createPayPanel() {
//            payField = new JTextField();
//            payField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//            JLabel payLabel = new JLabel("Enter if rent was paid (true/false):");
//            payPanel = new JPanel();
//
//            payPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            payPanel.setBackground(Color.LIGHT_GRAY);
//            payPanel.setOpaque(true);
//            payPanel.add(payLabel);
//            payPanel.add(payField);
//        }
//
//        private void createStatusPanel() {
//            statusField = new JTextField();
//            statusField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//            JLabel statusLabel = new JLabel("Enter status:");
//            statusPanel = new JPanel();
//
//            statusPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            statusPanel.setBackground(Color.LIGHT_GRAY);
//            statusPanel.setOpaque(true);
//            statusPanel.add(statusLabel);
//            statusPanel.add(statusField);
//        }
//
//        private void createCapacityPanel() {
//            capacityField = new JTextField();
//            capacityField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//            JLabel capacityLabel = new JLabel("Enter capacity:");
//            capacityPanel = new JPanel();
//
//            capacityPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            capacityPanel.setBackground(Color.LIGHT_GRAY);
//            capacityPanel.setOpaque(true);
//            capacityPanel.add(capacityLabel);
//            capacityPanel.add(capacityField);
//        }
//
//        private void createPricePanel() {
//            priceField = new JTextField();
//            priceField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//            JLabel priceLabel = new JLabel("Enter price:");
//            pricePanel = new JPanel();
//
//            pricePanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            pricePanel.setBackground(Color.LIGHT_GRAY);
//            pricePanel.setOpaque(true);
//            pricePanel.add(priceLabel);
//            pricePanel.add(priceField);
//        }
//
//        private void createAddressPanel() {
//            addressField = new JTextField();
//            addressField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//            JLabel addressLabel = new JLabel("Enter address:");
//            addressPanel = new JPanel();
//
//            addressPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//            addressPanel.setBackground(Color.LIGHT_GRAY);
//            addressPanel.setOpaque(true);
//            addressPanel.add(addressLabel);
//            addressPanel.add(addressField);
//        }
//
//        private void setUpFrame() {
//            frame = new JFrame("Adding Property");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLayout((new FlowLayout(FlowLayout.LEADING)));
//            frame.setSize(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT);
//
//            setUpPanels();
//
//
//            frame.add(addressPanel);
//            frame.add(pricePanel);
//            frame.add(capacityPanel);
//            frame.add(statusPanel);
//            frame.add(payPanel);
//            frame.add(buttonPanel);
//
//            frame.setVisible(true);
//
//        }
//
//        class AddListener implements ActionListener {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == addButton) {
//
//                    Property p = new Property();
//                    p.setAddress(addressField.getText());
//
//                    Double price = Double.valueOf(priceField.getText());
//                    p.setPrice(price);
//
//                    Integer capacity = Integer.valueOf(capacityField.getText());
//                    p.setCapacity(capacity);
//
//                    Boolean status = Boolean.valueOf(statusField.getText());
//                    p.setStatus(status);
//
//                    Boolean paid = Boolean.valueOf(payField.getText());
//                    p.setPaid(paid);
//
//                    properties.addProperty(p);
//
//                    System.out.println(properties.getProperties().size()); // for testing purpose
//
//
//                }
//
//            }
//        }
//
//        class BackListener implements ActionListener {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == backButton) {
//                    frame.dispose();
//                }
//            }
//        }
//
//
//
//    }

//    class ShowWindow {
//        private JList<String> listToShow;
//        private JPanel showPanel;
//        private JFrame frame;
//
//        ShowWindow() {
//            setUpFrame();
//        }
//
//        private void setUpFrame() {
//            frame = new JFrame();
//            frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
//            frame.setLayout(new FlowLayout());
//
//            setUpShowPanel();
//
//
//            frame.add(showPanel);
//
//            frame.setVisible(true);
//
//
//        }
//
//        private void setUpShowPanel() {
//            showPanel = new JPanel();
//            showPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
//            showPanel.setBackground(Color.LIGHT_GRAY);
//            showPanel.setOpaque(true);
//            showPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//            showProperties();
//
//            showPanel.add(listToShow);
//        }
//
//        private void showProperties() {
//            DefaultListModel<String> list = new DefaultListModel();
//            for (Property p: properties.getProperties()) {
//                list.addElement(p.displayInfo());
//            }
//
//            listToShow = new JList(list);
//
//        }
//
//    }


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




