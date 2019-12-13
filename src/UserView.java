import java.util.Scanner;
import java.util.Set;

public class UserView extends View{

   public UserView(Controller controller) {
       super(controller);
   }

   public void print(String str){
      System.out.println(str);
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

    /* FOR MANAGER: The system shall provide a user interface for the hotel manager and shall provide
     * a month-by-month overview of revenue for an entire year. For the purpose of this
     * assignment, all revenue from the reservation is assigned to the month and year when
     * the reservation ended. Your system shall display a list of rooms, and, for each room,
     * 13 columns: 12 columns showing dollar revenue for each month and a 13th column to
     * display total year revenue for the room. There shall also be a ”totals” row in the
     * table, which contains column totals. All amounts should be rounded to the nearest
     * whole dollar.
     */
    private void revenueOverview(User.userType type) {

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
        int in = input.nextInt();

        switch(in) {
            case(1): /* Book Reservation */ break;
            case(2): /* View Reservation */ break;
            case(3): /* Edit Reservation */ break;
            case(4): /* Cancel Reservation */ break;
            case(5): /* View Rooms */ break;
            case(6): /* Logout */
                input.close();
                System.exit(0);
                break;
            default: print("Invalid input: Must be integer between 1 and 9");
         }
      }
   }

}
