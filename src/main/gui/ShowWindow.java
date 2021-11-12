package gui;

import model.ManagementList;
import model.Property;
import ui.ManagerAppGUI2;

import javax.swing.*;
import java.awt.*;

import static ui.ManagerAppGUI2.SCREEN_HEIGHT;
import static ui.ManagerAppGUI2.SCREEN_WIDTH;

public class ShowWindow {
    private JList<String> listToShow;
    private JPanel showPanel;
    private JFrame frame;
    private ManagementList properties;

    public ShowWindow() {
        setUpFrame();
    }

    private void setUpFrame() {
        properties = ManagerAppGUI2.getManagementList();
        frame = new JFrame();
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new FlowLayout());

        setUpShowPanel();


        frame.add(showPanel);

        frame.setVisible(true);


    }

    private void setUpShowPanel() {
        showPanel = new JPanel();
        showPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        showPanel.setBackground(Color.LIGHT_GRAY);
        showPanel.setOpaque(true);
        showPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        showProperties();

        showPanel.add(listToShow);
    }

    private void showProperties() {
        DefaultListModel<String> list = new DefaultListModel();
        for (Property p: properties.getProperties()) {
            list.addElement(p.displayInfo());
        }

        listToShow = new JList(list);

    }

}