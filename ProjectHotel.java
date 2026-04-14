package Project;



    interface Service {
        double calculateBill(int days, double rate);
    }


    class Room {
        int roomNumber;
        String roomType;
        double pricePerDay;
        boolean isAvailable;


        Room(int roomNumber, String roomType, double pricePerDay) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerDay = pricePerDay;
            this.isAvailable = true;
        }
    }


    class Hotel implements Service {
        Room[][] rooms;


        Hotel(int floors, int roomsPerFloor) {
            rooms = new Room[floors][roomsPerFloor];

            int roomNo = 101;
            for (int i = 0; i < floors; i++) {
                for (int j = 0; j < roomsPerFloor; j++) {
                    if (j == 0)
                        rooms[i][j] = new Room(roomNo++, "Single", 1000);
                    else if (j == 1)
                        rooms[i][j] = new Room(roomNo++, "Double", 1500);
                    else
                        rooms[i][j] = new Room(roomNo++, "Deluxe", 2000);
                }
            }
        }


        void bookRoom(int floor, int roomIndex) {
            if (rooms[floor][roomIndex].isAvailable) {
                rooms[floor][roomIndex].isAvailable = false;
                System.out.println("Room booked successfully");
            } else {
                System.out.println("Room already occupied");
            }
        }


        void cancelBooking(int floor, int roomIndex) {
            rooms[floor][roomIndex].isAvailable = true;
            System.out.println("Booking cancelled successfully");
        }


        public double calculateBill(int days, double rate) {
            double total = days * rate;
            double tax = total * 0.1; 
            return total + tax;
        }


        void displayRooms() {
            System.out.println("\nRoom Status:");
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[i].length; j++) {
                    Room r = rooms[i][j];
                    System.out.println("Room " + r.roomNumber + " - " +
                            (r.isAvailable ? "Available" : "Occupied"));
                }
            }
        }
    }

    
    public class ProjectHotel {
        public static void main(String[] args) {

            Hotel hotel = new Hotel(2, 3);

            // Booking
            hotel.bookRoom(0, 1);
            hotel.bookRoom(0, 1); // try again

            // Cancel
            hotel.cancelBooking(0, 1);

            // Billing
            double bill = hotel.calculateBill(3, 1500);
            System.out.println("\nTotal Bill for 3 days: " + bill);

            // Display
            hotel.displayRooms();
        }
    }

