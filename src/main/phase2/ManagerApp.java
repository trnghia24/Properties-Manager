package phase2;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This class references code from this repo
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// Manager application
public class ManagerApp {
    private static final String JSON_STORE = "./data/managementlist.json";
    private Scanner input;
    private ManagementList properties;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Run the Manager application
    public ManagerApp() throws FileNotFoundException {
        runManager();
    }


    // MODIFIES: this
    // EFFECTS: processes user inputs
    private void runManager() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: initialize a list of properties to manage
    private void init() {
        input = new Scanner(System.in);
        properties = new ManagementList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add new property");
        System.out.println("\tdelete -> Delete a property from management list");
        System.out.println("\tview -> View details of a property");
        System.out.println("\tupdate -> Update a property's information");
        System.out.println("\tsave -> Save management list to file");
        System.out.println("\tload -> Load management list from file");
        System.out.println("\tquit -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAdd();
        } else if (command.equals("delete")) {
            doDelete();
        } else if (command.equals("view")) {
            doView();
        } else if (command.equals("update")) {
            doUpdate();
        } else if (command.equals("save")) {
            saveManagementList();
        } else if (command.equals("load")) {
            loadManagementList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: enable users to add a new property to the management list
    private void doAdd() {
        Property h = new Property();
        System.out.print("Enter the address of the property: ");
        String address = input.next();
        h.setAddress(address);

        System.out.print("Enter the monthly rental price: $");
        Double amount = input.nextDouble();
        h.setPrice(amount);

        System.out.print("Enter the number of tenants: ");
        Integer tenants = input.nextInt();
        h.setCapacity(tenants);

        askRented(h);

        h.setPaid(false);
        properties.addProperty(h);

        displayAllProperties();
    }

    // MODIFIES: this
    // EFFECTS: ask users if the current property to be added is rented or still available and record their answers
    private void askRented(Property h) {
        System.out.print("Was the property rented? ");
        String status = input.nextLine();
        Boolean validStatus = false;
        while (!validStatus) {
            status = input.nextLine();
            if (status.equals("true")) {
                validStatus = true;
                h.setStatus(true);
            } else if (status.equals("false")) {
                validStatus = true;
                h.setStatus(false);
            } else {
                System.out.println("Invalid. Enter one of true / false");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: enable users to delete a property from management list
    private void doDelete() {
        System.out.print("Enter the address of the property: ");
        String address = input.next();
        properties.deleteProperty(properties.getPropertyByAddress(address));

        displayAllProperties();
    }

    // EFFECTS: prints out the number and addresses of all properties being managed
    private void displayAllProperties() {
        if (properties.getProperties().isEmpty()) {
            System.out.println("You have 0 properties");
        } else {
            System.out.println("** My list of properties: ");
            for (Property p : properties.getProperties()) {
                System.out.println(p.getAddress());
            }
            System.out.println("** You have " + properties.getProperties().size() + " properties");
        }
    }

    // MODIFIES: this
    // EFFECTS: display a property in detail
    private void doView() {
        String address;
        Boolean validStatus = false;
        while (!validStatus) {
            System.out.print("Enter the address of the property: ");
            address = input.next();
            if (properties.getProperties().isEmpty()) {
                System.out.println("No results found. You have 0 properties");
                break;
            } else if (properties.getPropertyByAddress(address) == null) {
                System.out.println("The entered address does not match any property");
            } else {
                validStatus = true;
                displayProperty(address);
            }
        }
    }

    // EFFECTS: print out the information of a property
    private void displayProperty(String address) {
        Property h = properties.getPropertyByAddress(address);
        System.out.println("Address: " + h.getAddress());
        System.out.println("Monthly rental fee: " + "$" + h.getPrice());
        System.out.println("Rented? " + h.getStatus());
        System.out.println("Number of tenants: " + h.getCapacity());
        System.out.println("Rent paid? " + h.getPaid());
    }


    // MODIFIES: this
    // EFFECTS: enable users to update a property' attributes
    private void doUpdate() {
        String address;
        Boolean validStatus = false;
        while (!validStatus) {
            System.out.print("Enter the address of the property: ");
            address = input.next();
            if (properties.getProperties().isEmpty()) {
                System.out.println("You have 0 propeties");
                break;
            } else if (properties.getPropertyByAddress(address) == null) {
                System.out.println("The entered address does not match any property");
            } else {
                validStatus = true;
                makeUpdateChoices(address);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts users to choose which field of information to update
    private void makeUpdateChoices(String address) {
        String selection = "";  // force entry into loop

        while (!(selection.equals("status") || selection.equals("price") || selection.equals("payment"))) {
            System.out.println("enter 'status' for updating rental status");
            System.out.println("enter 'price' for updating new rental price");
            System.out.println("enter 'payment' for updating payment status");
            selection = input.next();
            selection = selection.toLowerCase();

            if (selection.equals("status")) {
                doUpdateRentStatus(address);
            } else if (selection.equals("price")) {
                doUpdatePrice(address);
            } else if (selection.equals("payment")) {
                doUpdatePaymentStatus(address);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: enable users to enter the payment status for a property(true if rent paid, false if rent not paid)
    private void doUpdatePaymentStatus(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.println("Enter true to mark as paid, enter false to mark as not paid");
        Boolean status = input.nextBoolean();
        house.setPaid(status);
        displayProperty(address);
    }

    // MODIFIES: this
    // EFFECTS: enable users to change the rental price for a property
    private void doUpdatePrice(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.print("Enter new price: $");
        Double amount = input.nextDouble();
        house.setPrice(amount);
        displayProperty(address);
    }

    // MODIFIES: this
    // EFFECTS: enable users to mark a property as rented or available for rent
    private void doUpdateRentStatus(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.println("Enter true to update that the property is rented, false that the property is available");
        Boolean status = input.nextBoolean();
        house.setStatus(status);
        displayProperty(address);
    }

    // This method references code from this repo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the management list to file
    private void saveManagementList() {
        try {
            jsonWriter.open();
            jsonWriter.write(properties);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // This method references code from this repo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads management list from file
    private void loadManagementList() {
        try {
            properties = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

