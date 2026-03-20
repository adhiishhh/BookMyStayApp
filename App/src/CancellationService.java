import java.util.*;

public class CancellationService {

    private Stack<String> rollbackStack;
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        rollbackStack = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    // Register booking (store reservation data)
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation. Reservation not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push to stack (rollback tracking)
        rollbackStack.push(reservationId);

        // Restore inventory
        int current = inventory.getRoomAvailability().get(roomType);
        inventory.updateAvailability(roomType, current + 1);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    // Show rollback history
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (int i = rollbackStack.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + rollbackStack.get(i));
        }
    }
}