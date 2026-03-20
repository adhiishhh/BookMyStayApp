public class BookMyStayApp {

    public static void main(String[] args) {

        // UC1
        System.out.println("===== UC1 =====");
        System.out.println("Welcome to Book My Stay App\n");

        // UC2
        System.out.println("===== UC2 =====");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // UC3
        System.out.println("\n===== UC3: Inventory Management =====");

        RoomInventory inventory = new RoomInventory();

        System.out.println("\nSingle Room:");
        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Single"));

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Double"));

        System.out.println("\nSuite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Suite"));
    }
}