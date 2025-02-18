import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void describe() {
        System.out.println(name + ": " + description);
        if (!items.isEmpty()) {
            System.out.println("Items here:");
            for (String itemName : items.keySet()) {
                System.out.println("- " + itemName);
            }
        }
        if (!exits.isEmpty()) {
            System.out.print("Exits: ");
            for (String dir : exits.keySet()) {
                System.out.print(dir + " ");
            }
            System.out.println();
        }
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public Item takeItem(String itemName) {
        return items.remove(itemName);
    }
}
