import java.util.*;

public class CustomerSupportSystem {
    private Queue<Ticket> regularTickets;
    private PriorityQueue<UrgentTicket> urgentTickets;
    private int ticketCounter;

    public CustomerSupportSystem() {
        this.regularTickets = new LinkedList<>();
        this.urgentTickets = new PriorityQueue<>(Comparator.comparingInt(UrgentTicket::getPriority));
        this.ticketCounter = 0;
    }

    public int addUrgentTicket(String description, boolean urgent, int priority) {
        ticketCounter++;
        UrgentTicket ticket = new UrgentTicket(ticketCounter, description, priority);

            urgentTickets.add(ticket);

        return ticketCounter;
    }
    public int addRegularTicket(String description, boolean urgent) {
        ticketCounter++;
        Ticket ticket = new Ticket(ticketCounter, description);

        regularTickets.add(ticket);

        return ticketCounter;
    }

    public Ticket processNextTicket() {
        if (!urgentTickets.isEmpty()) {
            return urgentTickets.poll();
        } else if (!regularTickets.isEmpty()) {
            return regularTickets.poll();
        }
        return null;
    }

    public Ticket searchTicket(int id) {
        for (UrgentTicket ticket : urgentTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        for (Ticket ticket : regularTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void reprioritizeTicket(int ticketId, int newPriority) {
        Ticket ticket = searchTicket(ticketId);
        if (ticket instanceof UrgentTicket) {
            UrgentTicket urgentTicket = (UrgentTicket) ticket;
            if (urgentTickets.contains(urgentTicket)) {
                urgentTickets.remove(urgentTicket);
                urgentTicket.setPriority(newPriority);
                urgentTickets.add(urgentTicket);
                System.out.println("Ticket reprioritized successfully!");
            }
        } else {
            System.out.println("Ticket not found or is not an urgent ticket.");
        }
    }


    public void clearAllTickets() {
        urgentTickets.clear();
        regularTickets.clear();
        ticketCounter=0;
        System.out.println("All tickets cleared.");
    }

    public void displayTickets() {
        if(!urgentTickets.isEmpty()) {
            System.out.println("Urgent Tickets:");
            urgentTickets.forEach(x -> System.out.println(x));
        }
        if(!regularTickets.isEmpty()) {
            System.out.println("Regular Tickets:");
            regularTickets.forEach(x -> System.out.println(x));
        }
    }
}
