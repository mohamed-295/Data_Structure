public class Main {
    public static void main(String[] args) {
        CustomerSupportSystem system = new CustomerSupportSystem();
        InputHandler inputHandler = new InputHandler(system);
        inputHandler.run();
    }
}
