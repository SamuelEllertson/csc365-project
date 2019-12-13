import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class ReservationsView {

    public Controller controller;
    public Scanner input;

    public ReservationsView(Controller controller, Scanner input) {
        this.controller = controller;
        this.input = input;
    }

    /* 2) The system shall display the list of reservations made by a user, including active
     * and past reservations.
     */
    public void viewReservations(int userId) {
        Set<Reservation> reservations = controller.reservationDAO.getByUserId(userId);
        System.out.println("-- View Reservations --");
        for (Reservation res: reservations) {
            System.out.println(res.toString());
        }
        System.out.println("");
    }

    private void editDate(Reservation res, Object[] roomsRes) {
        String checkIn, checkOut;
        boolean correctInput;
        Reservation resCpy = new Reservation(res.reservationId, res.userId, res.cardId, res.checkIn, res.checkOut);
        do {
            try {
                correctInput = true;
                System.out.print("New check-in date (Format: YYYY-MM-DD): ");
                checkIn = input.nextLine();
                resCpy.checkIn = Date.valueOf(checkIn);
            }catch (IllegalArgumentException e) {
                System.out.println("Invalid Date format");
                correctInput = false;
            }
        }while(!correctInput);

        do {
            try {
                correctInput = true;
                System.out.print("New check-out date (Format: YYYY-MM-DD): ");
                checkOut = input.nextLine();
                resCpy.checkOut = Date.valueOf(checkOut);
            }catch (IllegalArgumentException e) {
                System.out.println("Invalid Date format");
                correctInput = false;
            }
        }while(!correctInput);

        // Check rooms are available for new dates
        Set<Room> occupiedRooms = controller.roomDAO.getOccupiedRoomsOnDateRange(resCpy.checkIn, resCpy.checkOut);
        if (occupiedRooms != null) {
            for (Object rr : roomsRes) {
                for (Room r : occupiedRooms) {
                    if (((RoomsReserved) rr).roomId == r.roomId) {
                        System.out.printf("Room(s) already occupied within new date range");
                        return;
                    }
                }
            }
        }

        if (occupiedRooms == null || resCpy.checkIn.after(resCpy.checkOut) || !controller.reservationDAO.update(resCpy)) {
            System.out.println("FAILED: Could not update dates\n");
            return;
        }

        res = resCpy;
        System.out.println("Successfully updated: New dates are " + res.checkIn + " to " + res.checkOut + "\n");
    }

    private void editNumOccupants(RoomsReserved roomRes) {
        boolean correctInput;
        int newOccupants = 0;
        RoomsReserved rrCpy = new RoomsReserved(roomRes.reservationId, roomRes.roomId, roomRes.occupants);
        Room room = controller.roomDAO.getById(roomRes.roomId);
        System.out.println("Max occupancy for room: " + room.maxOccupancy);
        System.out.println("Current number of occupants: " + roomRes.occupants);
        System.out.print("Enter new number of occupants: ");
        do {
            try {
                correctInput = true;
                newOccupants = Integer.parseInt(input.nextLine());
                if (newOccupants <= 0 || newOccupants > room.maxOccupancy) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.print("Invalid input: Enter a number between 1 and " + room.maxOccupancy + ": ");
                correctInput = false;
            }
        }while(!correctInput);

        rrCpy.occupants = newOccupants;
        if (!controller.roomsReservedDAO.update(rrCpy)) {
            System.out.println("FAILED: Could not update number of occupants");
            return;
        }
        roomRes = rrCpy;
        System.out.println("Successfully updated number of occupants to " + roomRes.occupants + "\n");
    }

    private void replaceRoom(Reservation res, RoomsReserved roomRes) {
        boolean correctInput;
        int roomNum = 0;
        Set<Room> availableRooms = controller.roomDAO.getAvailableRoomsOnDateRange(res.checkIn, res.checkOut, roomRes.occupants);
        System.out.println("Available Rooms for replacement:");
        if (availableRooms.size() == 0) {
            System.out.println("No other available rooms in date range " + res.checkIn + " to " + res.checkOut);
            return;
        }
        int num = 1;
        Object[] rooms = availableRooms.toArray();
        // Display available rooms
        for (Object r:rooms) {
            System.out.println(num + ") " + ((Room)r).toString());
            num++;
        }
        System.out.print("Select room to replace with: ");
        do {
            try {
                correctInput = true;
                roomNum = Integer.parseInt(input.nextLine());
                if (roomNum <= 0 || roomNum > rooms.length) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.print("Invalid input: Enter room number between 1 and " + rooms.length + ": ");
                correctInput = false;
            }
        }while(!correctInput);
        Room r = (Room)rooms[roomNum - 1];

        RoomsReserved rrCpy = new RoomsReserved(roomRes.reservationId, r.roomId, roomRes.occupants);
        if (!controller.roomsReservedDAO.update(rrCpy)) {
            System.out.println("FAILED: Could not change room");
            return;
        }
        roomRes = rrCpy;
        System.out.println("Successfully changed reserved room to " + roomRes.roomId + "\n");

    }

    private void editRoom(Reservation res, Object[] roomsRes) {
        boolean correctInput;
        int roomNum = 0;
        System.out.print("Enter room number: ");
        do {
            try {
                correctInput = true;
                roomNum = Integer.parseInt(input.nextLine());
                if (roomNum <= 0 || roomNum > roomsRes.length) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.print("Invalid input: Enter room number between 1 and " + roomsRes.length + ": ");
                correctInput = false;
            }
        }while(!correctInput);
        RoomsReserved roomRes = (RoomsReserved)roomsRes[roomNum - 1];

        System.out.println("Room with id " + roomRes.roomId + " selected:\n1) Edit number of occupants\n"
                + "2) Replace Room\n3) Cancel");

        String in = input.nextLine();
        correctInput = false;
        while (!correctInput) {
            correctInput = true;
            switch(in) {
                case("1"): editNumOccupants(roomRes); break;
                case("2"): replaceRoom(res, roomRes); break;
                case("3"): System.out.println(""); return;
                default:
                    System.out.print("Invalid input: Enter number between 1 and 3: ");
                    correctInput = false;
            }
        }
    }

    /* 3) The system shall allow a user to change reservations, such as changing dates
     * and changing rooms. (Upon the change of a reservation, the system shall display
     * the details of the changed reservation on the screen.)
     */
    public void editReservation(int userId) {
        boolean correctInput;
        Set<Reservation> reservations = controller.reservationDAO.getByUserId(userId);
        System.out.println("-- Edit Reservation --");
        Object[] reservs = reservations.toArray();
        int num = 1;
        System.out.println("All reservations with dates: ");
        for (Object res:reservs) {
            System.out.println("Reservation " + num + ") " + ((Reservation) res).checkIn + " to " + ((Reservation) res).checkOut);
            num++;
        }
        int resNum = 0;
        System.out.print("\nEnter reservation number: ");
        do {
            try {
                correctInput = true;
                resNum = Integer.parseInt(input.nextLine());
                if (resNum <= 0 || resNum > reservs.length) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.print("Invalid input: Enter reservation number between 1 and " + reservs.length + ": ");
                correctInput = false;
            }
        }while(!correctInput);
        Reservation editRes = (Reservation)reservs[resNum - 1];

        // Display dates, rooms, & occupants for that reservation
        System.out.println("\nReservation selected:\nreservationId: " + editRes.reservationId + "\ncardId: "
                + editRes.cardId + "\nCheckIn: " + editRes.checkIn + "\nCheckOut: " + editRes.checkOut + "\n");

        Set<RoomsReserved> roomsReserved = controller.roomsReservedDAO.getByReservationId(editRes.reservationId);
        Object[] roomsRes = roomsReserved.toArray();
        num = 1;
        for (Object rr:roomsRes) {
            System.out.println("Room " + num + ":\nRoomId: " + ((RoomsReserved)rr).roomId + "\nnumber of occupants: "
                    + ((RoomsReserved)rr).occupants + "\n");
            num++;
        }

        System.out.println("1) Edit Date\n2) Edit Room\n3) Cancel");
        String in = input.nextLine();
        correctInput = false;
        while (!correctInput) {
            correctInput = true;
            switch(in) {
                case("1"): editDate(editRes, roomsRes); break;
                case("2"): editRoom(editRes, roomsRes); break;
                case("3"): return;
                default:
                    System.out.print("Invalid input: Enter number between 1 and 3: ");
                    correctInput = false;
            }
        }
    }


    /* 4) The system shall allow users to cancel bookings. (Upon the cancellation of a
     * reservation, the system shall display the details of the cancelled reservation
     * on the screen.)
     */
    public void cancelReservation(int userId) {
        boolean correctInput;
        Set<Reservation> reservations = controller.reservationDAO.getByUserId(userId);
        System.out.println("-- Cancel Reservation --");
        Object[] reservs = reservations.toArray();
        int num = 1;
        System.out.println("All reservations with dates: ");
        for (Object res:reservs) {
            System.out.println("Reservation " + num + ") " + ((Reservation) res).checkIn + " to " + ((Reservation) res).checkOut);
            num++;
        }
        int resNum = 0;
        System.out.print("\nEnter reservation number: ");
        do {
            try {
                correctInput = true;
                resNum = Integer.parseInt(input.nextLine());
                if (resNum <= 0 || resNum > reservs.length) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.print("Invalid input: Enter reservation number between 1 and " + reservs.length + ": ");
                correctInput = false;
            }
        }while(!correctInput);

        Reservation cancelRes = (Reservation)reservs[resNum - 1];
        Set<RoomsReserved> roomsReserved = controller.roomsReservedDAO.getByReservationId(cancelRes.reservationId);
        Object[] roomsRes = roomsReserved.toArray();

        // Transaction
        if (!controller.cancelReservation(roomsReserved, cancelRes)) {
            return;
        }

        // Display reservation info after deleted
        System.out.println("\nSuccessfully cancelled reservation:\nreservationId: " + cancelRes.reservationId + "\ncardId: "
                + cancelRes.cardId + "\nCheckIn: " + cancelRes.checkIn + "\nCheckOut: " + cancelRes.checkOut);
        num = 1;
        System.out.println("Rooms: ");
        for (Object rr:roomsRes) {
            System.out.println(num + ") RoomId: " + ((RoomsReserved)rr).roomId + ", occupants: "
                    + ((RoomsReserved)rr).occupants + "\n");
            num++;
        }

    }

}

