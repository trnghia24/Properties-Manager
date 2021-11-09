package ui;


import model.ManagementList;
import model.Property;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class ManagerAppGUI {

//    // This class references code from this repo
//    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//    // Manager application
//
//    private static final String JSON_STORE = "./data/managementlist.json";
//    private Scanner input;
//    private ManagementList properties;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // Run the Manager application
//    public ManagerAppGUI() throws FileNotFoundException {
//        runManager();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user inputs
//    private void runManager() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("quit")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye!");
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: initialize a list of properties to manage
//    private void init() {
//        input = new Scanner(System.in);
//        properties = new ManagementList();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        input.useDelimiter("\n");
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tadd -> Add new property");
//        System.out.println("\tdelete -> Delete a property from management list");
//        System.out.println("\tview -> View details of a property");
//        System.out.println("\tupdate -> Update a property's information");
//        System.out.println("\tsave -> Save management list to file");
//        System.out.println("\tload -> Load management list from file");
//        System.out.println("\tquit -> Quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("add")) {
//            doAdd();
//        } else if (command.equals("delete")) {
//            doDelete();
//        } else if (command.equals("view")) {
//            doView();
//        } else if (command.equals("update")) {
//            doUpdate();
//        } else if (command.equals("save")) {
//            saveManagementList();
//        } else if (command.equals("load")) {
//            loadManagementList();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: enable users to add a new property to the management list
//    private void doAdd() {
//        Property h = new Property();
//        System.out.print("Enter the address of the property: ");
//        String address = input.next();
//        h.setAddress(address);
//
//        System.out.print("Enter the monthly rental price: $");
//        Double amount = input.nextDouble();
//        h.setPrice(amount);
//
//        System.out.print("Enter the number of tenants: ");
//        Integer tenants = input.nextInt();
//        h.setCapacity(tenants);
//
//        askRented(h);
//
//        h.setPaid(false);
//        properties.addProperty(h);
//
//        displayAllProperties();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: ask users if the current property to be added is rented or still available and record their answers
//    private void askRented(Property h) {
//        System.out.print("Was the property rented? ");
//        String status = input.nextLine();
//        Boolean validStatus = false;
//        while (!validStatus) {
//            status = input.nextLine();
//            if (status.equals("true")) {
//                validStatus = true;
//                h.setStatus(true);
//            } else if (status.equals("false")) {
//                validStatus = true;
//                h.setStatus(false);
//            } else {
//                System.out.println("Invalid. Enter one of true / false");
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: enable users to delete a property from management list
//    private void doDelete() {
//        System.out.print("Enter the address of the property: ");
//        String address = input.next();
//        properties.deleteProperty(properties.getPropertyByAddress(address));
//
//        displayAllProperties();
//    }
//
//    // EFFECTS: prints out the number and addresses of all properties being managed
//    private void displayAllProperties() {
//        if (properties.getProperties().isEmpty()) {
//            System.out.println("You have 0 properties");
//        } else {
//            System.out.println("** My list of properties: ");
//            for (Property p : properties.getProperties()) {
//                System.out.println(p.getAddress());
//            }
//            System.out.println("** You have " + properties.getProperties().size() + " properties");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: display a property in detail
//    private void doView() {
//        String address;
//        Boolean validStatus = false;
//        while (!validStatus) {
//            System.out.print("Enter the address of the property: ");
//            address = input.next();
//            if (properties.getProperties().isEmpty()) {
//                System.out.println("No results found. You have 0 properties");
//                break;
//            } else if (properties.getPropertyByAddress(address) == null) {
//                System.out.println("The entered address does not match any property");
//            } else {
//                validStatus = true;
//                displayProperty(address);
//            }
//        }
//    }
//
//    // EFFECTS: print out the information of a property
//    private void displayProperty(String address) {
//        Property h = properties.getPropertyByAddress(address);
//        System.out.println("Address: " + h.getAddress());
//        System.out.println("Monthly rental fee: " + "$" + h.getPrice());
//        System.out.println("Rented? " + h.getStatus());
//        System.out.println("Number of tenants: " + h.getCapacity());
//        System.out.println("Rent paid? " + h.getPaid());
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: enable users to update a property' attributes
//    private void doUpdate() {
//        String address;
//        Boolean validStatus = false;
//        while (!validStatus) {
//            System.out.print("Enter the address of the property: ");
//            address = input.next();
//            if (properties.getProperties().isEmpty()) {
//                System.out.println("You have 0 propeties");
//                break;
//            } else if (properties.getPropertyByAddress(address) == null) {
//                System.out.println("The entered address does not match any property");
//            } else {
//                validStatus = true;
//                makeUpdateChoices(address);
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: prompts users to choose which field of information to update
//    private void makeUpdateChoices(String address) {
//        String selection = "";  // force entry into loop
//
//        while (!(selection.equals("status") || selection.equals("price") || selection.equals("payment"))) {
//            System.out.println("enter 'status' for updating rental status");
//            System.out.println("enter 'price' for updating new rental price");
//            System.out.println("enter 'payment' for updating payment status");
//            selection = input.next();
//            selection = selection.toLowerCase();
//
//            if (selection.equals("status")) {
//                doUpdateRentStatus(address);
//            } else if (selection.equals("price")) {
//                doUpdatePrice(address);
//            } else if (selection.equals("payment")) {
//                doUpdatePaymentStatus(address);
//            } else {
//                System.out.println("Selection not valid...");
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: enable users to enter the payment status for a property(true if rent paid, false if rent not paid)
//    private void doUpdatePaymentStatus(String address) {
//        Property house = properties.getPropertyByAddress(address);
//        System.out.println("Enter true to mark as paid, enter false to mark as not paid");
//        Boolean status = input.nextBoolean();
//        house.setPaid(status);
//        displayProperty(address);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: enable users to change the rental price for a property
//    private void doUpdatePrice(String address) {
//        Property house = properties.getPropertyByAddress(address);
//        System.out.print("Enter new price: $");
//        Double amount = input.nextDouble();
//        house.setPrice(amount);
//        displayProperty(address);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: enable users to mark a property as rented or available for rent
//    private void doUpdateRentStatus(String address) {
//        Property house = properties.getPropertyByAddress(address);
//        System.out.println("Enter true to update that the property is rented, false that the property is available");
//        Boolean status = input.nextBoolean();
//        house.setStatus(status);
//        displayProperty(address);
//    }
//
//    // This method references code from this repo
//    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//    // EFFECTS: saves the management list to file
//    private void saveManagementList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(properties);
//            jsonWriter.close();
//            System.out.println("Saved to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // This method references code from this repo
//    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//    // MODIFIES: this
//    // EFFECTS: loads management list from file
//    private void loadManagementList() {
//        try {
//            properties = jsonReader.read();
//            System.out.println("Loaded from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }

//    private JList list;
//    private DefaultListModel listModel;
//
//    private static final String addString = "Add";
//    private static final String removeString = "Remove";
//    private JButton removeButton;
//    private JTextField address;
//
//    public ManagerAppGUI() {
//        super(new BorderLayout());
//
//        listModel = new DefaultListModel();
//        listModel.addElement("Jane Doe");
//        listModel.addElement("John Smith");
//        listModel.addElement("Kathy Green");
//
//        //Create the list and put it in a scroll pane.
//        list = new JList(listModel);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
//        list.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(list);
//
//        JButton addButton = new JButton(addString);
//        AddListener addListener = new AddListener(addButton);
//        addButton.setActionCommand(addString);
//        addButton.addActionListener(addListener);
//        addButton.setEnabled(false);
//
//        removeButton = new JButton(removeString);
//        removeButton.setActionCommand(removeString);
//        removeButton.addActionListener(new RemoveListener());
//
//        address = new JTextField(10);
//        address.addActionListener(addListener);
//        address.getDocument().addDocumentListener(addListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();
//
//        //Create a panel that uses BoxLayout.
//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new BoxLayout(buttonPane,
//                BoxLayout.LINE_AXIS));
//        buttonPane.add(removeButton);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(address);
//        buttonPane.add(addButton);
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//
//        add(listScrollPane, BorderLayout.CENTER);
//        add(buttonPane, BorderLayout.PAGE_END);
//    }
//
//    class RemoveListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            //This method can be called only if
//            //there's a valid selection
//            //so go ahead and remove whatever's selected.
//            int index = list.getSelectedIndex();
//            listModel.remove(index);
//
//            int size = listModel.getSize();
//
//            if (size == 0) { //Nobody's left, disable firing.
//                removeButton.setEnabled(false);
//
//            } else { //Select an index.
//                if (index == listModel.getSize()) {
//                    //removed item in last position
//                    index--;
//                }
//
//                list.setSelectedIndex(index);
//                list.ensureIndexIsVisible(index);
//            }
//        }
//    }
//
//    //This listener is shared by the text field and the hire button.
//    class AddListener implements ActionListener, DocumentListener {
//        private boolean alreadyEnabled = false;
//        private JButton button;
//
//        public AddListener(JButton button) {
//            this.button = button;
//        }
//
//        //Required by ActionListener.
//        public void actionPerformed(ActionEvent e) {
//            String name = address.getText();
//
//            //User didn't type in a unique name...
//            if (name.equals("") || alreadyInList(name)) {
//                Toolkit.getDefaultToolkit().beep();
//                address.requestFocusInWindow();
//                address.selectAll();
//                return;
//            }
//
//            int index = list.getSelectedIndex(); //get selected index
//            if (index == -1) { //no selection, so insert at beginning
//                index = 0;
//            } else {           //add after the selected item
//                index++;
//            }
//
//            listModel.insertElementAt(address.getText(), index);
//            //If we just wanted to add to the end, we'd do this:
//            //listModel.addElement(employeeName.getText());
//
//            //Reset the text field.
//            address.requestFocusInWindow();
//            address.setText("");
//
//            //Select the new item and make it visible.
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//
//        //This method tests for string equality. You could certainly
//        //get more sophisticated about the algorithm.  For example,
//        //you might want to ignore white space and capitalization.
//        protected boolean alreadyInList(String name) {
//            return listModel.contains(name);
//        }
//
//        //Required by DocumentListener.
//        public void insertUpdate(DocumentEvent e) {
//            enableButton();
//        }
//
//        //Required by DocumentListener.
//        public void removeUpdate(DocumentEvent e) {
//            handleEmptyTextField(e);
//        }
//
//        //Required by DocumentListener.
//        public void changedUpdate(DocumentEvent e) {
//            if (!handleEmptyTextField(e)) {
//                enableButton();
//            }
//        }
//
//        private void enableButton() {
//            if (!alreadyEnabled) {
//                button.setEnabled(true);
//            }
//        }
//
//        private boolean handleEmptyTextField(DocumentEvent e) {
//            if (e.getDocument().getLength() <= 0) {
//                button.setEnabled(false);
//                alreadyEnabled = false;
//                return true;
//            }
//            return false;
//        }
//    }
//
//    //This method is required by ListSelectionListener.
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                removeButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                removeButton.setEnabled(true);
//            }
//        }
//    }
//
//    /**
//     * Create the GUI and show it.  For thread safety,
//     * this method should be invoked from the
//     * event-dispatching thread.
//     */
//    public static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("Property Manager");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        JComponent newContentPane = new ManagerAppGUI();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
    public static final int SCREEN_HEIGHT = 500;
    public static final int SCREEN_WIDTH = 500;

    private ManagementList properties;
    Property propertyToView;
    
    private JFrame frame;
    
    private JButton addButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton showButton;
    
    private JButton searchButton;
    private JTextField searchField;
    private JPanel searchPanel;
    
    private JPanel buttonBar;
    
    private JPanel testPanel;

    public ManagerAppGUI() {
        init();
        createFrame();
    }


    //Initialize the buttons and text fields
    private void init() {
        properties = new ManagementList();

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
//      removeButton.addActionListener(new RemoveListener());

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
//        saveButton.addActionListener(new RemoveListener());

        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.setFocusable(false);
        loadButton.setOpaque(true);
        loadButton.setBackground(Color.LIGHT_GRAY);
//        loadButton.addActionListener(new RemoveListener());

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
        
        searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.setOpaque(true);
        searchButton.setBackground(Color.WHITE);
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));
        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(SCREEN_WIDTH / 10, SCREEN_HEIGHT / 10));
        searchPanel.setBackground(Color.LIGHT_GRAY);
        searchPanel.setOpaque(true);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setVisible(false);

//        testPanel = new JPanel();
//
//        testPanel.setSize(new Dimension(100,100));
//        testPanel.setBackground(Color.RED);
//        testPanel.setOpaque(true);
//        testPanel.setVisible(false);


    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                new WindowForAdd();
            }

        }
    }
    
//    class RemoveListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == removeButton) {
//                searchPanel.setVisible(true);
//
//                if (e.getSource() == searchButton) {
//                    String inputText = searchField.getText();
//                    for (Property p : properties.getProperties()) {
//                        if (inputText.equals(p.getAddress())) {
//                            properties.deleteProperty(p);
//                        }
//
//                    }
//                    System.out.println(properties.getProperties().size()); //for testing purpose
//
//                }
//
//            }
//        }
//    }

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
                for (Property p: properties.getProperties()) {
                    System.out.println(p.getAddress());
                    System.out.println(p.getPrice());
                    System.out.println(p.getCapacity());
                    System.out.println(p.getStatus());
                    System.out.println(p.getPaid());
                }
            }

        }
    }

    class WindowForAdd {
        private ManagerAppGUI app;
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

        public WindowForAdd() {
            setUpFrame();
        }

        private void setUpPanels() {
            //
            createAddressPanel();

            //
            createPricePanel();


            //
            createCapacityPanel();

            //
            createStatusPanel();


            //
            createPayPanel();

            createButtonPanel();
           

        }

        private void createButtonPanel() {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            addButton = new JButton("Add");
            addButton.addActionListener(new AddListener());
            backButton = new JButton("Back");
            backButton.addActionListener(new BackListener());

            buttonPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            buttonPanel.setOpaque(true);
            buttonPanel.add(addButton);
            buttonPanel.add(backButton);
        }

        private void createPayPanel() {
            payField = new JTextField();
            payField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));

            JLabel payLabel = new JLabel("Enter if rent was paid (true/false):");
            payPanel = new JPanel();

            payPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            payPanel.setBackground(Color.LIGHT_GRAY);
            payPanel.setOpaque(true);
            payPanel.add(payLabel);
            payPanel.add(payField);
        }

        private void createStatusPanel() {
            statusField = new JTextField();
            statusField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));

            JLabel statusLabel = new JLabel("Enter status:");
            statusPanel = new JPanel();

            statusPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            statusPanel.setBackground(Color.LIGHT_GRAY);
            statusPanel.setOpaque(true);
            statusPanel.add(statusLabel);
            statusPanel.add(statusField);
        }

        private void createCapacityPanel() {
            capacityField = new JTextField();
            capacityField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));

            JLabel capacityLabel = new JLabel("Enter capacity:");
            capacityPanel = new JPanel();

            capacityPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            capacityPanel.setBackground(Color.LIGHT_GRAY);
            capacityPanel.setOpaque(true);
            capacityPanel.add(capacityLabel);
            capacityPanel.add(capacityField);
        }

        private void createPricePanel() {
            priceField = new JTextField();
            priceField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));

            JLabel priceLabel = new JLabel("Enter price:");
            pricePanel = new JPanel();

            pricePanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            pricePanel.setBackground(Color.LIGHT_GRAY);
            pricePanel.setOpaque(true);
            pricePanel.add(priceLabel);
            pricePanel.add(priceField);
        }

        private void createAddressPanel() {
            addressField = new JTextField();
            addressField.setPreferredSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH / 2,
                    ManagerAppGUI.SCREEN_HEIGHT / 10 - 10));

            JLabel addressLabel = new JLabel("Enter address:");
            addressPanel = new JPanel();

            addressPanel.setSize(new Dimension(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT / 10));
            addressPanel.setBackground(Color.LIGHT_GRAY);
            addressPanel.setOpaque(true);
            addressPanel.add(addressLabel);
            addressPanel.add(addressField);
        }

        private void setUpFrame() {
            frame = new JFrame("Adding Property");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout((new FlowLayout(FlowLayout.LEADING)));
            frame.setSize(ManagerAppGUI.SCREEN_WIDTH, ManagerAppGUI.SCREEN_HEIGHT);

            setUpPanels();


            frame.add(addressPanel);
            frame.add(pricePanel);
            frame.add(capacityPanel);
            frame.add(statusPanel);
            frame.add(payPanel);
            frame.add(buttonPanel);

            frame.setVisible(true);

        }

        class AddListener implements ActionListener {
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

                    Boolean paid = Boolean.valueOf(capacityField.getText());
                    p.setPaid(paid);

                    properties.addProperty(p);

                    System.out.println(properties.getProperties().size()); // for testing purpose
                    

                }

            }
        }

        class BackListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    frame.dispose();
                }
            }
        }



    }




    private void createFrame() {
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new BorderLayout());

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(buttonBar, BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}



