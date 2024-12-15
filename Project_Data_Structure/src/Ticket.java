public class Ticket {
    protected int id;
    protected String description;




    public Ticket(int id, String description) {
        this.id = id;
        this.description = description;
    }



    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' + '}';
    }
}
