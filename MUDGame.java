import java.util.*;

public class MUDGame {
    static Scanner scanner = new Scanner(System.in);
    private static Player player;
    private static Map<String, Room> rooms = new HashMap<>();
    public static void main(String[] args) {
        setupGame();
        System.out.println(" Welcome to the  Adventure! ");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            handleCommand(input);
        }
    }

    private static void setupGame() {
        // Создаём комнаты
        Room startRoom = new Room("Dungeon Entrance", "A dark cave entrance covered with moss.");
        Room hallway = new Room("Ancient Hallway", "Faint echoes of dripping water fill the air.");
        Room armory = new Room("Old Armory", "Rusty weapons and broken shields lie scattered around.");

        // Связываем комнаты
        startRoom.setExit("north", hallway);
        hallway.setExit("south", startRoom);
        hallway.setExit("east", armory);
        armory.setExit("west", hallway);

        // Добавляем предметы
        startRoom.addItem(new Item("Rusty Key", "A key that looks very old."));
        armory.addItem(new Item("Iron Sword", "A sturdy, yet slightly rusted sword."));

        // Заносим в карту комнат
        rooms.put("Dungeon Entrance", startRoom);
        rooms.put("Ancient Hallway", hallway);
        rooms.put("Old Armory", armory);

        // Создаём игрока
        player = new Player("Hero", startRoom);
    }

    private static void handleCommand(String command) {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "look":
                player.lookAround();
                break;
            case "go":
                if (parts.length > 1) {
                    player.move(parts[1]);
                } else {
                    System.out.println("Go where?");
                }
                break;
            case "inventory":
                player.showInventory();
                break;
            case "pick":
                if (parts.length > 1) {
                    player.pickUp(parts[1]);
                } else {
                    System.out.println("Pick up what?");
                }
                break;
            case "help":
                showHelp();
                break;
            case "exit":
                System.out.println("The adventure ends here... Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }

    private static void showHelp() {
        System.out.println("Available commands:");
        System.out.println("- look : Describe your surroundings.");
        System.out.println("- go <direction>: Move in a specified direction (north, south, east, west).");
        System.out.println("- inventory : Check your carried items.");
        System.out.println("- pick <item>: Pick up an item.");
        System.out.println("- help : Display this help message.");
        System.out.println("- exit : Quit the game.");
    }
}
