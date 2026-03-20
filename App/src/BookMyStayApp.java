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
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, single, doubleRoom, suite);

        // UC5
        System.out.println("\n===== UC5: Booking Request Queue =====");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create requests
        Reservation r1 = new Reservation("Adhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process queue (FIFO)
        System.out.println("\nProcessing Booking Requests:");

        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();
            System.out.println("Guest: " + r.getGuestName() +
                    " | Room: " + r.getRoomType());
        }
    }
}