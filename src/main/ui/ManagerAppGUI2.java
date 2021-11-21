package ui;



import model.Event;
import model.EventLog;
import model.ManagementList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;


// Manager application
public class ManagerAppGUI2 {
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 600;

    private static ManagementList properties;

    private JFrame frame;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton showButton;
    private JButton quitButton;

    private JPanel buttonBar;
    private JLabel imageLabel;

    private static final String JSON_STORE = "./data/managementlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ManagerAppGUI2() throws FileNotFoundException {
        init();
        createFrame();
    }


    // EFFECTS: Initialize necessary components for the app
    private void init() {
        properties = new ManagementList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setUpAddButton();

        setUpRemoveButton();

        setUpSaveButton();

        setUpLoadButton();

        setUpShowButton();

        setUpQuitButton();

        setUpButtonBar();

        setUpHomeScreenImage();


    }


    // EFFECTS: create a button for adding properties
    private void setUpAddButton() {
        addButton = new JButton();
        addButton.setText("Add");
        addButton.setFocusable(false);
        addButton.setOpaque(true);
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(new AddListener());
    }

    // EFFECTS: create a button for removing properties
    private void setUpRemoveButton() {
        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setFocusable(false);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.LIGHT_GRAY);
        removeButton.addActionListener(new RemoveListener());
    }

    // EFFECTS: create a button for saving
    private void setUpSaveButton() {
        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setOpaque(true);
        saveButton.setBackground(Color.LIGHT_GRAY);
        saveButton.addActionListener(new SaveListener());
    }

    // EFFECTS: create a button for loading
    private void setUpLoadButton() {
        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setOpaque(true);
        loadButton.setBackground(Color.LIGHT_GRAY);
        loadButton.addActionListener(new LoadListener());
    }

    // EFFECTS: create a button for showing and viewing properties
    private void setUpShowButton() {
        showButton = new JButton();
        showButton.setText("Show & View");
        showButton.setFocusable(false);
        showButton.setOpaque(true);
        showButton.setBackground(Color.LIGHT_GRAY);
        showButton.addActionListener(new ShowListener());
    }

    private void setUpQuitButton() {
        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setFocusable(false);
        quitButton.setOpaque(true);
        quitButton.setBackground(Color.LIGHT_GRAY);
        quitButton.addActionListener(new QuitListener());
    }

    // EFFECTS: set up a bar to contain all of this screen's buttons
    private void setUpButtonBar() {
        buttonBar = new JPanel();
        buttonBar.setPreferredSize(new Dimension(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 10));
        buttonBar.setBackground(Color.LIGHT_GRAY);
        buttonBar.setOpaque(true);
        buttonBar.setLayout(new FlowLayout());
        buttonBar.add(addButton);
        buttonBar.add(removeButton);
        buttonBar.add(showButton);
        buttonBar.add(saveButton);
        buttonBar.add(loadButton);
        buttonBar.add(quitButton);

    }

    // EFFECTS: set up a home screen image
    private void setUpHomeScreenImage() {
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - SCREEN_HEIGHT / 10));
        ImageIcon image = new ImageIcon(
                "/Users/trnghia240/IdeaProjects/personal project/project_m7p3q/src/main/ui/screen logo.png");
        imageLabel.setIcon(image);
    }

    // Represent an ActionListener for 'Add' button
    class AddListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: prompts user to a new window for adding properties when 'Add' button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                new WindowForAdd();
            }

        }
    }

    // Represent an ActionListener for 'Remove' button
    class RemoveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: prompts user to a new window for removing properties when 'Remove' button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                new RemoveWindow();
            }
        }
    }

    // Represent an ActionListener for 'Show & View' button
    class ShowListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: prompts user to a new window for viewing properties when 'Show & View' button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showButton) {
                new ShowWindow();

            }

        }
    }

    // Represent an ActionListener for 'Save' button
    class SaveListener implements ActionListener {
        // EFFECTS: save all the changes made
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

    // Represent an ActionListener for 'Load' button
    class LoadListener implements ActionListener {
        // EFFECTS: load the latest saved data
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

    // Represent an ActionListener for "Quit" button
    class QuitListener implements ActionListener {
        @Override
        // EFFECTS: print all the events to the console and quit the application
        public void actionPerformed(ActionEvent e) {
            EventLog el = EventLog.getInstance();
            for (Event event: el) {
                System.out.println(event.toString());
            }
            frame.dispose();
            System.exit(0);

        }
    }

    // MODIFIES: this
    // EFFECTS: create the frame and display all components
    private void createFrame() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new BorderLayout());

        frame.add(buttonBar, BorderLayout.SOUTH);
        frame.add(imageLabel, BorderLayout.NORTH);


        frame.setVisible(true);
    }

    // EFFECTS: return the management list
    public static ManagementList getManagementList() {
        return properties;
    }
}




