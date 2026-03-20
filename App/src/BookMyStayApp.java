public class BookMyStayApp {

    public static void main(String[] args) {

        // UC1
        System.out.println("===== UC1 =====");
        System.out.println("Welcome to Book My Stay App\n");

        // UC2
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // UC3
        RoomInventory inventory = new RoomInventory();

        // UC4
        System.out.println("\n===== UC4: Room Search =====");

        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(
                inventory,
                single,
                doubleRoom,
                suite
        );
    }
}