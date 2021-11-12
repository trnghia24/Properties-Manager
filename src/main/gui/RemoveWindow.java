package gui;

import model.ManagementList;
import model.Property;
import ui.ManagerAppGUI;
import ui.ManagerAppGUI2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.ManagerAppGUI2.SCREEN_HEIGHT;
import static ui.ManagerAppGUI2.SCREEN_WIDTH;

public class RemoveWindow {
    private JFrame frame;

    private JButton removeButton;
    private JTextField removeField;
    private JPanel removePanel;
    private ManagementList properties;

    public RemoveWindow() {
        setUpFrame();
    }


    private void setUpRemovePanel() {
        removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.LIGHT_GRAY);
        removeButton.addActionListener(new DoRemoveListener());
        removeField = new JTextField();
        removeField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
        removePanel = new JPanel();
        removePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT / 10));
        removePanel.setBackground(Color.LIGHT_GRAY);
        removePanel.setOpaque(true);
        removePanel.setLayout(new FlowLayout());
        removePanel.add(removeField);
        removePanel.add(removeButton);
    }

    class DoRemoveListener implements ActionListener {

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

    private void setUpFrame() {
        properties = ManagerAppGUI2.getManagementList();

        frame = new JFrame("Removing property");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout((new FlowLayout(FlowLayout.CENTER)));
        frame.setSize(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT);

        setUpRemovePanel();

        frame.add(removePanel);
        frame.setVisible(true);
    }
}
