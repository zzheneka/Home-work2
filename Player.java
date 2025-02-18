
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Room currentRoom;
    private List<Item> inventory = new ArrayList<>();

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    public void lookAround() {
        currentRoom.describe();
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You walk " + direction + "...");
            lookAround();
        } else {
            System.out.println("There's no path in that direction.");
        }
    }

    public void pickUp(String itemName) {
        Item item = currentRoom.takeItem(itemName);
        if (item != null) {
            inventory.add(item);
            System.out.println("You picked up: " + item.getName());
        } else {
            System.out.println("There is no '" + itemName + "' here.");
        }
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You have:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }
}
