package gui;

import model.ManagementList;
import model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static gui.ManagerAppGUI2.SCREEN_HEIGHT;
import static gui.ManagerAppGUI2.SCREEN_WIDTH;

// Represents a user's window for adding properties
public class WindowForAdd {
    private JFrame frame;
    private JPanel addressPanel;
    private JPanel pricePanel;
    private JPanel capacityPanel;
    private JPanel statusPanel;
    private JPanel payPanel;
    private JPanel buttonPanel;

    private JTextField addressField;
    private JTextField priceField;
    private JTextField capacityField;
    private JTextField statusField;
    private JTextField payField;

    private JButton addButton;
    private JButton backButton;

    private ManagementList properties;

    public WindowForAdd() {
        setUpFrame();
    }

    //EFFECTS: set up this screen's field
    private void setUpPanels() {
        createAddressPanel();

        createPricePanel();

        createCapacityPanel();

        createStatusPanel();

        createPayPanel();

        createButtonPanel();
    }

    // EFFECTS: creates a panel to contain 'Add' and 'Back' buttons
    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        addButton = new JButton("Add");
        addButton.addActionListener(new AddListener());
        backButton = new JButton("Back");
        backButton.addActionListener(new BackListener());

        buttonPanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setOpaque(true);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
    }

    // EFFECTS: create the field for inserting payment status
    private void createPayPanel() {
        payField = new JTextField();
        payField.setPreferredSize(new Dimension(SCREEN_WIDTH / 10,
                SCREEN_HEIGHT / 10 - 10));

        JLabel payLabel = new JLabel("Enter if rent was paid (true/false):");
        payPanel = new JPanel();
        payPanel = new JPanel();
        payPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        payPanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        payPanel.setBackground(Color.LIGHT_GRAY);
        payPanel.setOpaque(true);
        payPanel.add(payLabel);
        payPanel.add(payField);
    }

    // EFFECTS: create the field for inserting rental status
    private void createStatusPanel() {
        statusField = new JTextField();
        statusField.setPreferredSize(new Dimension(SCREEN_WIDTH / 10,
                SCREEN_HEIGHT / 10 - 10));

        JLabel statusLabel = new JLabel("Enter if property was rented (true/false):");

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        statusPanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        statusPanel.setBackground(Color.LIGHT_GRAY);
        statusPanel.setOpaque(true);
        statusPanel.add(statusLabel);
        statusPanel.add(statusField);
    }

    // EFFECTS: create the field for inserting capacity
    private void createCapacityPanel() {
        capacityField = new JTextField();
        capacityField.setPreferredSize(new Dimension(SCREEN_WIDTH / 10,
                SCREEN_HEIGHT / 10 - 10));

        JLabel capacityLabel = new JLabel("Enter capacity:");

        capacityPanel = new JPanel();
        capacityPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        capacityPanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        capacityPanel.setBackground(Color.LIGHT_GRAY);
        capacityPanel.setOpaque(true);
        capacityPanel.add(capacityLabel);
        capacityPanel.add(capacityField);
    }

    // EFFECTS: create the field for inserting rental price
    private void createPricePanel() {
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 10 - 10));

        JLabel priceLabel = new JLabel("Enter price:");

        pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        pricePanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        pricePanel.setBackground(Color.LIGHT_GRAY);
        pricePanel.setOpaque(true);
        pricePanel.add(priceLabel);
        pricePanel.add(priceField);
    }

    // EFFECTS: create the field for inserting address
    private void createAddressPanel() {
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 10 - 10));


        JLabel addressLabel = new JLabel("Enter address:");

        addressPanel = new JPanel();
        addressPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        addressPanel.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        addressPanel.setBackground(Color.LIGHT_GRAY);
        addressPanel.setOpaque(true);
        addressPanel.add(addressLabel);
        addressPanel.add(addressField);
    }

    // MODIFIES: this
    // EFFECTS: set up frame and display all components
    private void setUpFrame() {
        properties = ManagerAppGUI2.getManagementList();

        frame = new JFrame("Adding property");
        
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new GridLayout(6,1));
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        setUpPanels();


        frame.add(addressPanel);
        frame.add(pricePanel);
        frame.add(capacityPanel);
        frame.add(statusPanel);
        frame.add(payPanel);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }

    // Represents an ActionListener for 'Add' button
    class AddListener implements ActionListener {
        // EFFECTS: add property to the management list
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {

                Property p = new Property();
                p.setAddress(addressField.getText());

                Double price = Double.valueOf(priceField.getText());
                p.setPrice(price);

                Integer capacity = Integer.valueOf(capacityField.getText());
                p.setCapacity(capacity);

                Boolean status = Boolean.valueOf(statusField.getText());
                p.setStatus(status);

                Boolean paid = Boolean.valueOf(payField.getText());
                p.setPaid(paid);

                properties.addProperty(p);
            }

        }
    }

    // Represents an ActionListener for 'Back' button
    class BackListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: prompts user back to the home screen
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                frame.dispose();
            }
        }
    }

}
