package ui;

import model.ManagementList;
import model.Property;

import javax.swing.*;
import java.awt.*;

import static ui.ManagerAppGUI2.SCREEN_HEIGHT;
import static ui.ManagerAppGUI2.SCREEN_WIDTH;

// Represent a window to display all properties
public class ShowWindow {
    private JList<String> listToShow;
    private JPanel showPanel;
    private JFrame frame;
    private ManagementList properties;

    public ShowWindow() {
        setUpFrame();
    }

    // MODIFIES: this
    // EFFECTS: set up frame and display all components
    private void setUpFrame() {
        properties = ManagerAppGUI2.getManagementList();
        frame = new JFrame();
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new FlowLayout());

        setUpShowPanel();


        frame.add(showPanel);

        frame.setVisible(true);


    }

    // EFFECTS: create an area to show properties
    private void setUpShowPanel() {
        showPanel = new JPanel();
        showPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        showPanel.setBackground(Color.LIGHT_GRAY);
        showPanel.setOpaque(true);
        showPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        showProperties();

        showPanel.add(listToShow);
    }

    // MODIFIES: this
    // EFFECTS: display all properties in the management list and their associated info
    private void showProperties() {
        DefaultListModel<String> list = new DefaultListModel();
        for (Property p: properties.getProperties()) {
            list.addElement(p.displayInfo());
        }

        listToShow = new JList(list);

    }

}