import java.util.*;

public class MUDGame {
    private static Map<String, Room> rooms = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    private static Player player;

    public static void main(String[] args) {
        setupGame();
        System.out.println("Добро пожаловать в игру!");
        System.out.println("Введите 'помощь', чтобы увидеть список команд.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            handleCommand(input);
        }
    }

    private static void setupGame() {
        Room startRoom = new Room("Пещера", "Темное место с влажными стенами.");
        Room hallway = new Room("Коридор", "Факелы освещают стены. Видно старый шкаф.");
        Room storeroom = new Room("Кладовка", "Полки забиты артефактами и архивами.");

        startRoom.setExit("север", hallway);
        hallway.setExit("юг", startRoom);
        hallway.setExit("восток", storeroom);
        storeroom.setExit("запад", hallway);

        startRoom.addItem(new Item("ключ", "Старый ржавый ключ."));
        storeroom.addItem(new Item("меч", "Тяжелый железный меч."));

        rooms.put("пещера", startRoom);
        rooms.put("коридор", hallway);
        rooms.put("кладовка", storeroom);

        player = new Player("Герой", startRoom);
    }

    private static void handleCommand(String command) {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "осмотреться":
                player.lookAround();
                break;
            case "идти":
                if (parts.length > 1) {
                    player.move(parts[1]);
                } else {
                    System.out.println("Куда идти?");
                }
                break;
            case "инвентарь":
                player.showInventory();
                break;
            case "взять":
                if (parts.length > 1) {
                    player.pickUp(parts[1]);
                } else {
                    System.out.println("Что взять?");
                }
                break;
            case "помощь":
                showHelp();
                break;
            case "выход":
                System.out.println("Игра завершена. До свидания!");
                System.exit(0);
                break;
            default:
                System.out.println("Неизвестная команда. Введите 'помощь' для списка команд.");
        }
    }

    private static void showHelp() {
        System.out.println("Доступные команды:");
        System.out.println("- осмотреться : Описать текущее местоположение.");
        System.out.println("- идти <направление>: север, юг, восток, запад.");
        System.out.println("- инвентарь : Проверить, что у вас есть.");
        System.out.println("- взять <предмет>: Подобрать предмет.");
        System.out.println("- помощь : Показать список доступных команд.");
        System.out.println("- выход : Выйти из игры.");
    }
}
