import java.util.Scanner;
import java.util.Set;

public class UserView extends View{
   ReservationsView reservationsView;

   public UserView(Controller controller) {
       super(controller);
       reservationsView = new ReservationsView(controller, input);
   }

   public void PrintMenuItems(){

      String[] options = {
         "1) Book Reservation",
         "2) View Reservations",
         "3) Edit Reservation",
         "4) Cancel Reservation",
         "5) View Rooms",
         "6) Logout"
      };

      for(String option : options){
         print(option);
      }
   }

    /* ViewRooms 'Find available rooms' option:
     * The system shall allow users to search for availabilities of rooms specifying day
     * (checkout and checkin dates), the type of room (single, double, twin, etc), the
     * decor, the price range, the number of rooms, and the number of occupants.
     */
    private void findAvailRoom() { }

    /* ViewRooms 'All rooms on date' option:
     * The system shall display availability of rooms on each day. For each room, display
     * its popularity score (number of days the room has been occupied during the previous
     * 180 days divided by 180 (round to two decimal places)), price, available or if not,
     * next available date, length, bed type, the number of beds, the maximum number of
     * occupancy allowed.
     */
    private void allRoomsOnDate() {

    }

   /* This function should continuously loop, reading in commands, executing them, and
    * displaying the output. Should return when the user wants to quit.
    */
    public void doUILoop(User user){
      
        while(true){
        PrintMenuItems();
        String in = input.nextLine();

        switch(in) {
            case("1"): /* Book Reservation */ break;
            case("2"): reservationsView.viewReservations(user.userId); break;
            case("3"): reservationsView.editReservation(user.userId); break;
            case("4"): reservationsView.cancelReservation(user.userId); break;
            case("5"): /* View Rooms */ break;
            case("6"): /* Logout */
                input.close();
                System.exit(0);
                break;
            default: print("Invalid input: Must be integer between 1 and 9");
         }
      }
   }

}
