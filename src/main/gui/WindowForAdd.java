//package gui;
//
//import ui.ManagerApp;
//import ui.ManagerAppGUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class WindowForAdd {
//    private ManagerAppGUI app;
//    private JFrame frame;
//
//    private JPanel addressPanel;
//    private JPanel pricePanel;
//    private JPanel capacityPanel;
//    private JPanel statusPanel;
//    private JPanel payPanel;
//    private JPanel buttonPanel;
//
//
//    private JTextField addressField;
//    private JTextField priceField;
//    private JTextField capacityField;
//    private JTextField statusField;
//    private JTextField payField;
//
//    private JButton addButton;
//    private JButton backButton;
//
//    public WindowForAdd() {
//        setUpFrame();
//    }
//
//    private void setUpPanels() {
//        //
//        createAddressPanel();
//
//        //
//        createPricePanel();
//
//
//        //
//        createCapacityPanel();
//
//        //
//        createStatusPanel();
//
//
//        //
//        createPayPanel();
//
//        createButtonPanel();
//
//    }
//
//    private void createButtonPanel() {
//        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//
//        addButton = new JButton("Add");
////        addButton.addActionListener(new AddListner());
//        backButton = new JButton("Back");
//        backButton.addActionListener(new BackListener());
//
//        buttonPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        buttonPanel.setBackground(Color.LIGHT_GRAY);
//        buttonPanel.setOpaque(true);
//        buttonPanel.add(addButton);
//        buttonPanel.add(backButton);
//    }
//
//    private void createPayPanel() {
//        payField = new JTextField();
//        payField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//        JLabel payLabel = new JLabel("Enter if rent was paid (true/false):");
//        payPanel = new JPanel();
//
//        payPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        payPanel.setBackground(Color.LIGHT_GRAY);
//        payPanel.setOpaque(true);
//        payPanel.add(payLabel);
//        payPanel.add(payField);
//    }
//
//    private void createStatusPanel() {
//        statusField = new JTextField();
//        statusField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//        JLabel statusLabel = new JLabel("Enter status:");
//        statusPanel = new JPanel();
//
//        statusPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        statusPanel.setBackground(Color.LIGHT_GRAY);
//        statusPanel.setOpaque(true);
//        statusPanel.add(statusLabel);
//        statusPanel.add(statusField);
//    }
//
//    private void createCapacityPanel() {
//        capacityField = new JTextField();
//        capacityField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//        JLabel capacityLabel = new JLabel("Enter capacity:");
//        capacityPanel = new JPanel();
//
//        capacityPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        capacityPanel.setBackground(Color.LIGHT_GRAY);
//        capacityPanel.setOpaque(true);
//        capacityPanel.add(capacityLabel);
//        capacityPanel.add(capacityField);
//    }
//
//    private void createPricePanel() {
//        priceField = new JTextField();
//        priceField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//        JLabel priceLabel = new JLabel("Enter price:");
//        pricePanel = new JPanel();
//
//        pricePanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        pricePanel.setBackground(Color.LIGHT_GRAY);
//        pricePanel.setOpaque(true);
//        pricePanel.add(priceLabel);
//        pricePanel.add(priceField);
//    }
//
//    private void createAddressPanel() {
//        addressField = new JTextField();
//        addressField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
//                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
//
//        JLabel addressLabel = new JLabel("Enter address:");
//        addressPanel = new JPanel();
//
//        addressPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
//        addressPanel.setBackground(Color.LIGHT_GRAY);
//        addressPanel.setOpaque(true);
//        addressPanel.add(addressLabel);
//        addressPanel.add(addressField);
//    }
//
//    private void setUpFrame() {
//        frame = new JFrame("Adding Property");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout((new FlowLayout(FlowLayout.LEADING)));
//        frame.setSize(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT);
//
//        setUpPanels();
//
//
//        frame.add(addressPanel);
//        frame.add(pricePanel);
//        frame.add(capacityPanel);
//        frame.add(statusPanel);
//        frame.add(payPanel);
//        frame.add(buttonPanel);
//
//        frame.setVisible(true);
//
//    }
//
//    class AddListener implements  ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == addButton) {
//
//            }
//
//        }
//    }
//
//    class BackListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == backButton) {
//                frame.dispose();
//                new ManagerAppGUI();
//            }
//        }
//    }
//
//
//
//}
