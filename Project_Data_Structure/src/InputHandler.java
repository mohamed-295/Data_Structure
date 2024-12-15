import java.util.Scanner;

public class InputHandler {
    private CustomerSupportSystem system ;
    private Scanner scanner;

    public InputHandler(CustomerSupportSystem system) {
        this.system = system;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMenu();
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "T":
                    handleAddTicket();
                    break;
                case "P":
                    handleProcessNextTicket();
                    break;
                case "S":
                    handleSearchTicket();
                    break;
                case "R":
                    handleReprioritizeTicket();
                    break;
                case "D":
                    system.displayTickets();
                    break;
                case "C":
                    system.clearAllTickets();
                    break;
                case "X":
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("T - Add Ticket");
        System.out.println("P - Process Next Ticket");
        System.out.println("S - Search for Ticket by ID");
        System.out.println("R - Reprioritize a Ticket");
        System.out.println("D - Display All Tickets");
        System.out.println("C - Clear All Tickets");
        System.out.println("X - Exit Program");
        System.out.print("Enter your choice: ");
    }

    private void handleAddTicket() {
        System.out.print("Enter the ticket description: ");
        String description = scanner.nextLine();

        boolean urgent = false;
        while (true) {
            System.out.print("Is this ticket urgent? (Y/N): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) {
                urgent = true;
                break;
            } else if (input.equalsIgnoreCase("N")) {
                urgent = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }

        int priority = 0;
        if (urgent) {
            System.out.print("Enter the ticket priority from 1 to 10 (lower number = higher priority): ");
            while (true) {
                try {
                    int x = Integer.parseInt(scanner.nextLine().trim());
                    if (x >= 1 && x <= 10) {
                        priority = x;
                        break;

                    } else {
                        System.out.print("Invalid input. Enter a valid priority number: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input. Enter a valid priority number: ");
                }
            }
        }
        int ticketId=0;
        if(urgent) {
             ticketId = system.addUrgentTicket(description, urgent, priority);
        }
        else {
             ticketId = system.addRegularTicket(description, urgent);
        }
        System.out.println("Ticket added successfully! Ticket ID: " + ticketId);
    }

    private void handleProcessNextTicket() {
        Ticket nextTicket = system.processNextTicket();
        if (nextTicket != null) {
            System.out.println("Processing Ticket: " + nextTicket);
        } else {
            System.out.println("No tickets available to process.");
        }
    }

    private void handleSearchTicket() {
        System.out.print("Enter the ticket ID to search: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Ticket ticket = system.searchTicket(id);
            if (ticket != null) {
                System.out.println("Found Ticket: " + ticket);
            } else {
                System.out.println("Ticket not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket ID.");
        }
    }

    private void handleReprioritizeTicket() {
        System.out.print("Enter the ticket ID to reprioritize: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter the ticket priority from 1 to 10 (lower number = higher priority): ");
            int newPriority = Integer.parseInt(scanner.nextLine().trim());
            system.reprioritizeTicket(id, newPriority);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
