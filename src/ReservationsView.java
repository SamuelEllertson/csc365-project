import java.util.Set;

public class ReservationsView {



    /* 1) The system shall allow users to book rooms in a hotel. (Upon reservation,
     * the system shall display the details of the reservation on the screen.)
     */
    private void bookReservation() { }

    /* 3) The system shall allow a user to change reservations, such as changing dates
     * and changing rooms. (Upon the change of a reservation, the system shall display
     * the details of the changed reservation on the screen.)
     */
    private void updateReservation() { }

    /* 4) The system shall allow users to cancel bookings. (Upon the cancellation of a
     * reservation, the system shall display the details of the cancelled reservation
     * on the screen.)
     */
    private void cancelReservation() { }

    /* 8) The system shall display the list of reservations made by a user, including active
     * and past reservations.
     */
    private void displayUserReserv(int userId) {
        Set<Reservation> reservations = controller.reservationDAO.getByUserId(userId);
        for (Reservation res: reservations) {
            System.out.println(res.toString());
        }
    }
    /*For viewing, editing, and canceling reservations (2-4)*/
}

