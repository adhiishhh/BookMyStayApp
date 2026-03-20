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

        // UC6
        System.out.println("\n===== UC6: Room Allocation =====");

        RoomAllocationService allocationService = new RoomAllocationService();

// recreate queue
        BookingRequestQueue bookingQueue2 = new BookingRequestQueue();

        bookingQueue2.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue2.addRequest(new Reservation("Subha", "Single"));
        bookingQueue2.addRequest(new Reservation("Vanmathi", "Suite"));

// process queue
        while (bookingQueue2.hasPendingRequests()) {
            Reservation r = bookingQueue2.getNextRequest();
            allocationService.allocateRoom(r, inventory);
        }
        // UC7
        System.out.println("\n===== UC7: Add-On Services =====");

        AddOnServiceManager serviceManager = new AddOnServiceManager();

// Example reservation ID (from UC6)
        String reservationId = "Single-1";

// Add services
        serviceManager.addService(reservationId, new AddOnService("Breakfast", 500));
        serviceManager.addService(reservationId, new AddOnService("Spa", 1000));

// Calculate total cost
        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("\nAdd-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: ₹" + totalCost);

        // UC8
        System.out.println("\n===== UC8: Booking History & Reporting =====");

        BookingHistory history = new BookingHistory();

// Add confirmed bookings (example)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

// Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);

        // UC9
        System.out.println("\n===== UC9: Error Handling & Validation =====");

        ReservationValidator validator = new ReservationValidator();

        try {

            // Valid case
            validator.validate("Adhi", "Single", inventory);
            System.out.println("Valid booking for Adhi");

            // Invalid case (wrong room type)
            validator.validate("Subha", "Luxury", inventory);

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());
        }
        // UC10
        System.out.println("\n===== UC10: Booking Cancellation =====");

        CancellationService cancellationService = new CancellationService();

// Register booking (example)
        cancellationService.registerBooking("Single-1", "Single");

// Cancel booking
        cancellationService.cancelBooking("Single-1", inventory);

// Show rollback history
        cancellationService.showRollbackHistory();

// Show updated inventory
        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single"));

        // UC11
        System.out.println("\n===== UC11: Concurrent Booking Simulation =====");

// reuse bookingQueue (already declared before)
        bookingQueue = new BookingRequestQueue();

// Add requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

// Create threads
        Thread t1 = new Thread(new ConcurrentBookingProcessor(
                bookingQueue, inventory, allocationService));

        Thread t2 = new Thread(new ConcurrentBookingProcessor(
                bookingQueue, inventory, allocationService));

// Start threads
        t1.start();
        t2.start();

// Wait for threads
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

// Show inventory
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.getRoomAvailability().get("Single"));
        System.out.println("Double: " + inventory.getRoomAvailability().get("Double"));
        System.out.println("Suite: " + inventory.getRoomAvailability().get("Suite"));

        // UC12
        System.out.println("\n===== UC12: Data Persistence & Recovery =====");

        FilePersistenceService persistenceService = new FilePersistenceService();

        String filePath = "inventory.txt";

// Load existing data
        persistenceService.loadInventory(inventory, filePath);

// Show current inventory
        System.out.println("\nCurrent Inventory:");
        System.out.println("Single: " + inventory.getRoomAvailability().get("Single"));
        System.out.println("Double: " + inventory.getRoomAvailability().get("Double"));
        System.out.println("Suite: " + inventory.getRoomAvailability().get("Suite"));

// Save data
        persistenceService.saveInventory(inventory, filePath);
    }

}

