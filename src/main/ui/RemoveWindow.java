package ui;

import model.ManagementList;
import model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.ManagerAppGUI2.SCREEN_HEIGHT;
import static ui.ManagerAppGUI2.SCREEN_WIDTH;

// Represents a window for removing properties
public class RemoveWindow {
    private JFrame frame;

    private JButton removeButton;
    private JTextField removeField;
    private JPanel removePanel;
    private JLabel instructionLabel;
    private ManagementList properties;

    public RemoveWindow() {
        setUpFrame();
    }

    // EFFECTS: create a remove field for this window
    private void setUpRemovePanel() {
        removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.LIGHT_GRAY);
        removeButton.addActionListener(new DoRemoveListener());

        instructionLabel = new JLabel("Enter address:");

        removeField = new JTextField();
        removeField.setPreferredSize(new Dimension(SCREEN_WIDTH / 2,
                SCREEN_HEIGHT / 10 - 10));
        removePanel = new JPanel();
        removePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        removePanel.setBackground(Color.LIGHT_GRAY);
        removePanel.setOpaque(true);
        removePanel.setLayout(new FlowLayout());
        removePanel.add(instructionLabel);
        removePanel.add(removeField);
        removePanel.add(removeButton);
    }

    // Represents an ActionListener for 'Remove' button
    class DoRemoveListener implements ActionListener {
        // EFFECTS: remove the properties in the management list whose address matches user's input
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                String inputText = removeField.getText();
                for (Property p: properties.getProperties()) {
                    if (inputText.equals(p.getAddress())) {
                        properties.deleteProperty(p);
                    }
                }
            }
        }
    }

    // EFFECTS: set up frame and display all components
    private void setUpFrame() {
        properties = ManagerAppGUI2.getManagementList();

        frame = new JFrame("Removing property");
        frame.setLayout((new FlowLayout(FlowLayout.CENTER)));
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        setUpRemovePanel();

        frame.add(removePanel);
        frame.setVisible(true);
    }
}
