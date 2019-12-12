import java.util.Scanner;

public class View{

   public Controller controller;
   public Scanner input;

   // Call DAO's like controller.UserDAO.function()

   public View(Controller controller){
      this.controller = controller;
      this.input = new Scanner(System.in);
   }

   public void print(String str){
      System.out.println(str);
   }

   public void PrintMenuItems(){

      String[] options = {
         "1) Add new user",
         "2) Add new credit card",
         "3) Add new reservation",
         "4) Update reservation",
         "5) Cancel reservation",
         "6) Search room availabilities",
         "7) Display room availabilities",
         "8) Display user reservations",
         "9) Revenue overview"
      };

      for(String option : options){
         print(option);
      }
   }

   private void addUser() {
      // Need String firstName, lastName, username, password;
      // userType type;
      String firstName, lastName, username, password;
      User.userType type;

      // Get input from command line

      // User u = new User();
   }

   private void addCreditCard() { }

   /* 3) The system shall allow users to book rooms in a hotel. (Upon reservation,
    * the system shall display the details of the reservation on the screen.)
    */
   private void addReservation() { }

   /* 4) The system shall allow a user to change reservations, such as changing dates
    * and changing rooms. (Upon the change of a reservation, the system shall display
    * the details of the changed reservation on the screen.)
    */
   private void updateReservation() { }

    /* 5) The system shall allow users to cancel bookings. (Upon the cancellation of a
     * reservation, the system shall display the details of the cancelled reservation
     * on the screen.)
     */
    private void cancelReservation() { }

    /* 6) The system shall allow users to search for availabilities of rooms specifying day
     * (checkout and checkin dates), the type of room (single, double, twin, etc), the
     * decor, the price range, the number of rooms, and the number of occupants.
     */
    private void searchRoomAvail() { }

    /* 7) The system shall display availability of rooms on each day. For each room, display
     * its popularity score (number of days the room has been occupied during the previous
     * 180 days divided by 180 (round to two decimal places)), price, available or if not,
     * next available date, length, bed type, the number of beds, the maximum number of
     * occupancy allowed.
     */
    private void displayRoomAvail() { }

    /* 8) The system shall display the list of reservations made by a user, including active
     * and past reservations.
     */
    private void displayUserReserv() { }

    /* 9) The system shall provide a user interface for the hotel manager and shall provide
     * a month-by-month overview of revenue for an entire year. For the purpose of this
     * assignment, all revenue from the reservation is assigned to the month and year when
     * the reservation ended. Your system shall display a list of rooms, and, for each room,
     * 13 columns: 12 columns showing dollar revenue for each month and a 13th column to
     * display total year revenue for the room. There shall also be a ”totals” row in the
     * table, which contains column totals. All amounts should be rounded to the nearest
     * whole dollar.
     */
    private void revenueOverview() { }


   /* This function should continuously loop, reading in commands, executing them, and
    * displaying the output. Should return when the user wants to quit.
    */
   public void doUILoop(){
      
      while(true){
         PrintMenuItems();
         //GetInput();
         int in = input.nextInt();

         switch(in) {
            case(1): addUser(); break;
            case(2): addCreditCard(); break;
            case(3): addReservation(); break;
            case(4): updateReservation(); break;
            case(5): cancelReservation(); break;
            case(6): searchRoomAvail(); break;
            case(7): displayRoomAvail(); break;
            case(8): displayUserReserv(); break;
            case(9): revenueOverview(); break;
            default: print("Invalid input: Must be integer between 1 and 9");
         }
         break;
      }
      input.close();
   }

}
