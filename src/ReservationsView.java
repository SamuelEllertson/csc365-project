import java.sql.Date;
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
        for (Reservation res: reservations) {
            System.out.println(res.toString());
        }
    }

    private void editDate(Reservation res) {
        String checkIn, checkOut;
        boolean correctInput;
        Reservation resCpy = new Reservation(res.reservationId, res.userId, res.cardId, res.checkIn, res.checkOut);
        System.out.println("Current dates: " + resCpy.checkIn + " to " + resCpy.checkOut);
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

        // Check if checkIn > checkOut? !!

        if (!controller.reservationDAO.update(resCpy)) {
            return;
            // Maybe print error? !!
        }
        res = resCpy;
        System.out.println("New dates: " + res.checkIn + " to " + res.checkOut);
    }

    private void editNumOccupants(RoomsReserved roomRes) {
        boolean correctInput;
        int newOccupants = 0;
        RoomsReserved rrCpy = new RoomsReserved(roomRes.reservationId, roomRes.roomId, roomRes.occupants);
        System.out.println("\nCurrent number of occupants: " + roomRes.occupants);
        do {
            try {
                correctInput = true;
                System.out.print("Enter room number: ");
                newOccupants = Integer.parseInt(input.nextLine());
                if (newOccupants <= 0) {
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e) {
                System.out.println("Not a valid number for occupants");
                correctInput = false;
            }
        }while(!correctInput);

        if (!controller.roomsReservedDAO.update(rrCpy)) {
            return;
            // Maybe print error? !!
        }
        roomRes = rrCpy;
        System.out.println("New number of occupants: " + roomRes.occupants);
    }

    private void replaceRoom(RoomsReserved roomRes) {

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

        System.out.println("Room with id " + roomRes.roomId + " selected\n\n1) Edit number of occupants\n"
                + "2)Replace Room\n3)Cancel");

        String in = input.nextLine();
        correctInput = false;
        while (!correctInput) {
            correctInput = true;
            switch(in) {
                case("1"): editNumOccupants(roomRes); break;
                case("2"): replaceRoom(roomRes); break;
                case("3"): return;
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
        Object[] reservs = reservations.toArray();
        int num = 1;
        for (Object res:reservs) {
            System.out.println(num + ") " + ((Reservation) res).checkIn + " to " + ((Reservation) res).checkOut);
            num++;
        }
        int resNum = 0;
        System.out.print("Enter reservation number: ");
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
        System.out.println("Reservation selected:\nreservationId: " + editRes.reservationId + "\ncardId: "
                + editRes.cardId + "\nCheckIn: " + editRes.checkIn + "\tCheckOut: " + editRes.checkOut);
        // Display rooms, & occupants for that reservation !!

        Set<RoomsReserved> roomsReserved = controller.roomsReservedDAO.getByReservationId(editRes.reservationId);
        Object[] roomsRes = roomsReserved.toArray();
        num = 1;
        for (Object rr:roomsRes) {
            System.out.println("Room " + num + ":\nRoomId: " + ((RoomsReserved)rr).roomId + "\nnumber of occupants: "
                    + ((RoomsReserved)rr).occupants + "\n");
            num++;
        }

        System.out.println("\n1) Edit Date\n2) Edit Room\n3) Cancel");
        String in = input.nextLine();
        correctInput = false;
        while (!correctInput) {
            correctInput = true;
            switch(in) {
                case("1"): editDate(editRes); break;
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
    public void cancelReservation(int userId) { }

}

