package gui;

import model.ManagementList;
import model.Property;
import ui.ManagerAppGUI2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.time.format.DateTimeFormatter;

import static ui.ManagerAppGUI2.SCREEN_HEIGHT;
import static ui.ManagerAppGUI2.SCREEN_WIDTH;

public class DisplayWindow extends JFrame {
    private JPanel mainPanel;
    private JList listOfProperties;
    private JTextField textAddress;
    private JTextField textPrice;
    private JTextField textCapacity;
    private JTextField textStatus;
    private JTextField textPaid;
    private JPanel titlePanel;
    private ManagementList properties = ManagerAppGUI2.getManagementList();



    public DisplayWindow() {
        setUpFrame();
        displayProperties();

        listOfProperties.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int personNumber = listOfProperties.getSelectedIndex();
                if (personNumber >= 0) {
                    Property p = properties.getProperties().get(personNumber);
                    textAddress.setText(p.getAddress());
                    textPrice.setText(Double.toString(p.getCapacity()));
                    textCapacity.setText(Integer.toString(p.getCapacity()));
                    textStatus.setText(Boolean.toString(p.getStatus()));
                    textPaid.setText(Boolean.toString(p.getPaid()));
                }

            }
        });
    }

    public void displayProperties() {
        DefaultListModel<String> list = new DefaultListModel();
        for (Property p: properties.getProperties()) {
            list.addElement(p.getAddress());
        }

        listOfProperties.setModel(list);
    }

    private void setUpFrame() {
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(mainPanel);


        this.setVisible(true);
    }
}
