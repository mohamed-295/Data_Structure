public class UrgentTicket extends Ticket {

    private int priority;




    public UrgentTicket(int id, String description, int priority) {
        super(id,description);
        this.priority=priority;
    }




    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
