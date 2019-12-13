import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.sql.Date;

public class BookView {

    private Reservation res = new Reservation();
    private Set<Room> rooms = new HashSet<Room>();

    private int totalRooms = 0;
    private int totalOccupants = 0;
    private float totalCost =0;

    private int numOccupants = 0;
    private float minPrice = 0;
    private float maxPrice = 0;
    private String rType = "";
    private String bType = "";
    private String decor = "";



    Scanner scanner;
    Controller controller;

    public BookView(Scanner input, Controller controller){
        this.scanner = input;
        this.controller = controller;

    }

    public void showBookView(int userId){

        res.userId = userId;
        getResDay();
        getRoom();

    }


    public void getResDay(){

        boolean correctInput = true;
        String date;

        do {
            try {
                correctInput = true;
                System.out.print("Enter the check-in date: (Format: YYYY-MM-DD): ");
                date = scanner.nextLine();
                res.checkIn = Date.valueOf(date);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Date format");
                correctInput = false;
            }
        } while(!correctInput);


        do {
            try {
                correctInput = true;
                System.out.print("Enter the check-out date: (Format: YYYY-MM-DD): ");
                date = scanner.nextLine();
                res.checkOut = Date.valueOf(date);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Date format");
                correctInput = false;
            }
        }while(!correctInput);

    }


    public void getRoom(){
        while(true) {
            getOccupants();
            getPriceRange();
            getType();
            getBedType();
            getDecor();
            dispAvail();
        }//if pay exit to menu even if fail, if cancel exit to menu, if exit
    }
    public void getOccupants(){

        boolean correctInput = true;
        do {
            try {
                correctInput = true;
                System.out.print("Enter the number of occupants for this room: ");
                numOccupants = Integer.parseInt(scanner.nextLine());
                if(numOccupants<0){
                    throw new NumberFormatException();
                }

            }catch (NumberFormatException e){
                System.out.println("Not a valid number for occupants ");
                correctInput = false;
            }
        }while(!correctInput);
    }

    public void getPriceRange(){

        System.out.println("Would you like to specify price range of this room? (Y/N)");
        String pRange = scanner.nextLine();
        if(pRange.equals("Y")){

            boolean correctInput = true;
            do {
                try {
                    correctInput = true;
                    System.out.print("Enter the minimum price: ");
                    minPrice = Float.parseFloat(scanner.nextLine());
                    if(minPrice<0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Not a valid price. Make sure it is a valid number greater than 0.");
                    correctInput = false;
                }
            }while(!correctInput);

            do {
                try {
                    correctInput = true;
                    System.out.print("Enter the maximum price: ");
                    maxPrice = Float.parseFloat(scanner.nextLine());
                    if(maxPrice<minPrice){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException e){
                    System.out.println("Not a valid price. Make sure it is a valid number greater than the minimum price.");
                    correctInput = false;
                }
            }while(!correctInput);

        }
        else if(pRange.equals("N")){
            return;
        }



    }

    public void getType(){

        /* Select room type:
        1) SINGLE
        2) DOUBLE
        3) TWIN
        4) ANY*/

    }

    public void getBedType(){
        /*
        Select bed type:
        1) TWIN
        2) TWIN_XL
        3) DOUBLE
        4) QUEEN
        5) KING
        6) CA_KING
        7) ANY */

    }

    public void getDecor(){
        /*    Select decor type:
    1) MODERN
    2) INDUSTRIAL
    3) NAUTICAL
    4) SCANDINAVIAN
    5) BOHEMIAN
    6) RUSTIC
    7) ANY*/
    }

    public void dispAvail(){
        /*    Display availability
    No rooms available:
    Find another room
    Cancel

    Rooms available: List Rooms
    Select Room
    Cancel
*/
    }

    public void payOrSelect(){
        /*transaction or other option
        Display # of Rooms, total # occupants, total price, Room Info (for each)
        Pay (-> Transaction)
        Select another room (back to List Rooms)
        Cancel
        */
    }



}



