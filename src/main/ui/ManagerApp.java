package ui;

import model.*;

import java.util.Scanner;

public class ManagerApp {
    private ManagementList properties;
    private Scanner input;

    public ManagerApp() {
        runManager();
    }

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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void init() {
        properties = new ManagementList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> Add new property");
        System.out.println("\tdelete -> Delete a property from management list");
        System.out.println("\tview -> View details of a property");
        System.out.println("\tupdate -> Update a property's information");
        System.out.println("\tquit -> Quit");
    }

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

        System.out.println("My list of properties: ");
        for (Property p: properties.getProperties()) {
            System.out.println(p.getAddress());
        }
        System.out.println("I have " + properties.getProperties().size() + " properties");
    }

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
                System.out.println("invalid. enter one of true / false");
            }
        }
    }

    private void doDelete() {
        System.out.print("Enter the address of the property: ");
        String address = input.next();
        properties.deleteProperty(properties.getPropertyByAddress(address));

        System.out.println("My list of properties: ");
        for (Property p: properties.getProperties()) {
            System.out.println(p.getAddress());
        }
        System.out.println("I have " + properties.getProperties().size() + " properties");
    }

    private void doView() {
        System.out.print("Enter the address of the property: ");
        String address = input.next();

        Property h = properties.getPropertyByAddress(address);
        System.out.println("Address: " + h.getAddress());
        System.out.println("Monthly rental fee: " + "$" + h.getPrice());
        System.out.println("Rented? " + h.getStatus());
        System.out.println("Number of tenants: " + h.getCapacity());
        System.out.println("Rent paid? " + h.getPaid());
    }

    private void doUpdate() {
        System.out.print("Enter the address of the property: ");
        String address = input.next();
        String selection = "";  // force entry into loop

        while (!(selection.equals("status") || selection.equals("price") || selection.equals("pay"))) {
            System.out.println("status for updating rental status");
            System.out.println("price for updating new rental price");
            System.out.println("pay for updating payment status");
            selection = input.next();
            selection = selection.toLowerCase();

            if (selection.equals("status")) {
                doUpdateRentStatus(address);
            } else if (selection.equals("price")) {
                doUpdatePrice(address);
            } else {
                doUpdatePaymentStatus(address);
            }
        }
    }

    private void doUpdatePaymentStatus(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.println("Enter true to mark as paid, enter false to mark as not paid");
        Boolean status = input.nextBoolean();
        house.setPaid(status);


    }

    private void doUpdatePrice(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.print("Enter new price: $");
        Double amount = input.nextDouble();
        house.setPrice(amount);
    }

    private void doUpdateRentStatus(String address) {
        Property house = properties.getPropertyByAddress(address);
        System.out.println("Enter true to update that the property is rented, false that the property is available");
        Boolean status = input.nextBoolean();
        house.setStatus(status);

    }


}

