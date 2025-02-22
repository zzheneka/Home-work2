

import java.util.Scanner;
public class MUDController {
    private boolean isRunning;
    private final Scanner scanner;


    public MUDController() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Game!!!!!");
        System.out.println("Type 'help' for a list of commands.");

        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            processCommand(input);
        }
    }

    public void processCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";

        switch (command) {
            case "explore":
                explore();
                break;
            case "go":
                if (!argument.isEmpty()) {
                    moveTo(argument);
                } else {
                    System.out.println("Go where? (north, south, east, west)");
                }
                break;
            case "bag":
                checkBag();
                break;
            case "grab":
                if (!argument.isEmpty()) {
                    collectItem(argument);
                } else {
                    System.out.println("Grab what?");
                }
                break;
            case "help":
                displayHelp();
                break;
            case "quit":
                System.out.println("Exiting the game. See you next time!");
                isRunning = false;
                break;
            default:
                System.out.println("Invalid command. Type 'help' for a list of commands.");
        }
    }

    private void explore() {
        System.out.println("You take a moment to observe your surroundings...");
    }

    private void moveTo(String direction) {
        System.out.println("You move " + direction + ".");
    }

    private void collectItem(String item) {
        System.out.println("You pick up a " + item + ".");
    }

    private void checkBag() {
        System.out.println("Checking your bag... It's currently empty.");
    }

    private void displayHelp() {
        System.out.println("Commands available:");
        System.out.println("- explore : Observe your current surroundings.");
        System.out.println("- go <direction> :north, south, east, or west.");
        System.out.println("- bag : Check your inventory.");
        System.out.println("- grab <item> : Pick up an item.");
        System.out.println("- help : Show available commands.");
        System.out.println("- quit : Exit the game.");
    }

    public static void main(String[] args) {
        MUDController controller = new MUDController();
        controller.startGame();
    }
}