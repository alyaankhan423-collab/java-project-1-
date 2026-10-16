import java.util.Scanner;

class Room {
    int roomNumber;
    String roomType;
    double price;
    boolean occupied;
    String customerName;

    Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.occupied = false;
        this.customerName = "";
    }

    void displayRoom() {
        String status;

        if (occupied) {
            status = "Occupied";
        } else {
            status = "Available";
        }

        System.out.println(
            "Room " + roomNumber +
            " | " + roomType +
            " | Rs." + price +
            " | " + status
        );
    }
}

public class HotelBookingManager {

    static Scanner sc = new Scanner(System.in);

    static Room[] rooms = {
        new Room(101, "Single", 1500),
        new Room(102, "Single", 1500),
        new Room(103, "Double", 2500),
        new Room(104, "Double", 2500),
        new Room(105, "Deluxe", 3500),
        new Room(106, "Deluxe", 3500),
        new Room(107, "Suite", 5000),
        new Room(108, "Suite", 5000)
    };

    // Display all rooms
    static void viewAllRooms() {

        System.out.println("\n========== ALL ROOMS ==========");

        for (Room room : rooms) {
            room.displayRoom();
        }

        System.out.println("===============================");
    }

    // Display only available rooms
    static void viewAvailableRooms() {

        System.out.println("\n======= AVAILABLE ROOMS =======");

        boolean found = false;

        for (Room room : rooms) {

            if (!room.occupied) {
                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms are currently available.");
        }

        System.out.println("===============================");
    }

    // Book a room
    static void bookRoom() {

        System.out.println("\n========== BOOK A ROOM ==========");

        viewAvailableRooms();

        System.out.print("Enter room number to book: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number.");
            return;
        }

        if (selectedRoom.occupied) {
            System.out.println("Sorry! This room is already occupied.");
            return;
        }

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        if (customerName.trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        selectedRoom.customerName = customerName;
        selectedRoom.occupied = true;

        System.out.println("\n***** BOOKING SUCCESSFUL *****");
        System.out.println("Customer Name : " + selectedRoom.customerName);
        System.out.println("Room Number   : " + selectedRoom.roomNumber);
        System.out.println("Room Type     : " + selectedRoom.roomType);
        System.out.println("Room Price    : Rs." + selectedRoom.price);
        System.out.println("Status        : Occupied");
        System.out.println("*******************************");
    }

    // Cancel a booking
    static void cancelBooking() {

        System.out.println("\n======== CANCEL BOOKING ========");

        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number.");
            return;
        }

        if (!selectedRoom.occupied) {
            System.out.println("This room is not currently booked.");
            return;
        }

        System.out.println("Customer Name: " + selectedRoom.customerName);

        System.out.print("Are you sure you want to cancel? (Y/N): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Y")) {

            selectedRoom.occupied = false;
            selectedRoom.customerName = "";

            System.out.println("Booking cancelled successfully.");
            System.out.println("Room " + roomNumber + " is now available.");

        } else {

            System.out.println("Booking cancellation stopped.");
        }
    }

    // View booking details
    static void viewBookings() {

        System.out.println("\n======= CURRENT BOOKINGS =======");

        boolean found = false;

        for (Room room : rooms) {

            if (room.occupied) {

                System.out.println(
                    "Room " + room.roomNumber +
                    " | Customer: " + room.customerName +
                    " | Type: " + room.roomType +
                    " | Price: Rs." + room.price
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found.");
        }

        System.out.println("===============================");
    }

    // Calculate occupancy
    static void occupancyReport() {

        int totalRooms = rooms.length;
        int occupiedRooms = 0;

        for (Room room : rooms) {

            if (room.occupied) {
                occupiedRooms++;
            }
        }

        int availableRooms = totalRooms - occupiedRooms;

        double occupancyRate =
            ((double) occupiedRooms / totalRooms) * 100;

        System.out.println("\n======= OCCUPANCY REPORT =======");

        System.out.println("Total Rooms     : " + totalRooms);
        System.out.println("Occupied Rooms  : " + occupiedRooms);
        System.out.println("Available Rooms : " + availableRooms);
        System.out.printf("Occupancy Rate  : %.2f%%\n", occupancyRate);

        System.out.println("===============================");
    }

    // Main menu
    static void displayMenu() {

        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("     HOTEL ROOM BOOKING & OCCUPANCY");
        System.out.println("               MANAGER");
        System.out.println("========================================");
        System.out.println("1. View All Rooms");
        System.out.println("2. View Available Rooms");
        System.out.println("3. Book a Room");
        System.out.println("4. Cancel Booking");
        System.out.println("5. View Current Bookings");
        System.out.println("6. Occupancy Report");
        System.out.println("7. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {

        int choice;

        System.out.println("Welcome to Hotel Room Booking and Occupancy Manager!");

        do {

            displayMenu();

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewAllRooms();
                    break;

                case 2:
                    viewAvailableRooms();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    cancelBooking();
                    break;

                case 5:
                    viewBookings();
                    break;

                case 6:
                    occupancyReport();
                    break;

                case 7:
                    System.out.println("\nThank you for using the system!");
                    System.out.println("Program exited successfully.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-7.");
            }

        } while (choice != 7);

        sc.close();
    }
}