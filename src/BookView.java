import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

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
        boolean exitCode = false;
        while(!exitCode) {
            getOccupants();
            getPriceRange();
            getType();
            getBedType();
            getDecor();
            dispAvail();
            exitCode = endOfLoop();
        }//if pay exit to menu even if fail, if cancel exit to menu, if exit
    }
    public void getOccupants(){

        boolean correctInput = true;
        do {
            try {
                correctInput = true;
                System.out.print("Enter the number of occupants for this room: ");
                numOccupants = Integer.parseInt(scanner.nextLine());
                if(numOccupants<1){
                    throw new NumberFormatException();
                }

            }catch (NumberFormatException e){
                System.out.println("Not a valid number for occupants ");
                correctInput = false;
            }
        }while(!correctInput);
    }

    public void getPriceRange(){

        boolean validInput;

        do {
            validInput =true;
            System.out.println("Would you like to specify price range of this room? (Y/N)");
            String pRange = scanner.nextLine();
            if (pRange.equals("Y")) {

                boolean correctInput = true;
                do {
                    try {
                        correctInput = true;
                        System.out.print("Enter the minimum price: ");
                        minPrice = Float.parseFloat(scanner.nextLine());
                        if (minPrice < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid price. Make sure it is a valid number greater than 0.");
                        correctInput = false;
                    }
                } while (!correctInput);

                do {
                    try {
                        correctInput = true;
                        System.out.print("Enter the maximum price: ");
                        maxPrice = Float.parseFloat(scanner.nextLine());
                        if (maxPrice < minPrice) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid price. Make sure it is a valid number greater than the minimum price.");
                        correctInput = false;
                    }
                } while (!correctInput);

            } else if (pRange.equals("N")) {
                return;
            } else {
                validInput = false;
                System.out.println("Invalid Input");
            }
        }while(!validInput);


    }

    public void getType(){

        boolean validInput;

        do {
            validInput =true;
            System.out.println("Would you like to specify the room type? (Y/N)");
            String pRange = scanner.nextLine();
            if (pRange.equals("Y")) {

                boolean correctInput = true;
                do {
                    try {
                        correctInput = true;
                        Room.roomTypeEnum enumVal []= Room.roomTypeEnum.values();
                        System.out.println("Select room type (Enter a number from the list): ");
                        int i = 0;
                        for( Room.roomTypeEnum option : enumVal){
                            i++;
                            System.out.println(i+") "+ option);
                        }
                        int option = Integer.parseInt(scanner.nextLine());

                        if (option < 0 || option > i) {
                            throw new NumberFormatException();
                        }
                        else{
                            rType = enumVal[i-1].name();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid option");
                        correctInput = false;
                    }
                } while (!correctInput);


            } else if (pRange.equals("N")) {
                return;
            } else {
                validInput = false;
                System.out.println("Invalid Input");
            }
        }while(!validInput);

    }

    public void getBedType(){

        boolean validInput;

        do {
            validInput =true;
            System.out.println("Would you like to specify the bed type? (Y/N)");
            String pRange = scanner.nextLine();
            if (pRange.equals("Y")) {

                boolean correctInput = true;
                do {
                    try {
                        correctInput = true;
                        Room.bedTypeEnum enumVal []= Room.bedTypeEnum.values();
                        System.out.println("Select bed type (Enter a number from the list): ");
                        int i = 0;
                        for( Room.bedTypeEnum option : enumVal){
                            i++;
                            System.out.println(i+") "+ option);
                        }
                        int option = Integer.parseInt(scanner.nextLine());

                        if (option < 0 || option > i) {
                            throw new NumberFormatException();
                        }
                        else{
                            bType = enumVal[i-1].name();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid option");
                        correctInput = false;
                    }
                } while (!correctInput);


            } else if (pRange.equals("N")) {
                return;
            } else {
                validInput = false;
                System.out.println("Invalid Input");
            }
        }while(!validInput);

    }

    public void getDecor(){
        boolean validInput;

        do {
            validInput =true;
            System.out.println("Would you like to specify the decor type? (Y/N)");
            String pRange = scanner.nextLine();
            if (pRange.equals("Y")) {

                boolean correctInput = true;
                do {
                    try {
                        correctInput = true;
                        Room.decorEnum enumVal []= Room.decorEnum.values();
                        System.out.println("Select decor type (Enter a number from the list): ");
                        int i = 0;
                        for( Room.decorEnum option : enumVal){
                            i++;
                            System.out.println(i+") "+ option);
                        }
                        int option = Integer.parseInt(scanner.nextLine());

                        if (option < 0 || option > i) {
                            throw new NumberFormatException();
                        }
                        else{
                            decor = enumVal[i-1].name();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid option");
                        correctInput = false;
                    }
                } while (!correctInput);


            } else if (pRange.equals("N")) {
                return;
            } else {
                validInput = false;
                System.out.println("Invalid Input");
            }
        }while(!validInput);
    }

    public void dispAvail() {

        Set<Room> availableRooms = controller.roomDAO.getAvailableRooms(res.checkIn,
                res.checkOut, numOccupants, minPrice, maxPrice, rType, bType, decor);


        if (availableRooms.isEmpty()) {
            System.out.println("No Rooms Found");
        } else {
            boolean correctInput;
            do {
                try {
                    correctInput = true;
                    Object roomsArr[] = availableRooms.toArray();
                    int i = 0;
                    System.out.println("Select a Room: ");
                    for (Object room : roomsArr) {
                        i++;
                        System.out.println(i + ")");
                        ((Room) room).printRoomInfo();
                    }
                    int option = Integer.parseInt(scanner.nextLine());

                    if (option < 0 || option > i) {
                        throw new NumberFormatException();
                    } else if (rooms.contains((Room) roomsArr[i - 1])) {
                        throw new IllegalStateException();
                    } else {
                        totalCost += ((Room) roomsArr[i - 1]).price;
                        totalOccupants += numOccupants;
                        totalRooms++;
                        rooms.add((Room) roomsArr[i - 1]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid option");
                    correctInput = false;
                } catch (IllegalStateException e) {
                    System.out.println("Room Already Selected, Pick Another");
                    correctInput = false;
                }
            } while (!correctInput);
        }
    }

    public boolean endOfLoop(){
        boolean correctInput;
        do {
            correctInput = true;
            System.out.println("Current Options: ");
            System.out.println("1) Find Another Room");
            System.out.println("2) Cancel Reservation (Will lose everything reserved)");
            if (totalRooms > 0) {
                System.out.println("3) Reserve as is");
            }
            String option = scanner.nextLine();
            if (option.equals("1")) {
                return false;
            } else if (option.equals("2")) {
                return true;
            } else if (option.equals("3") && totalRooms > 0) {
                boolean thisCheck;
                do {
                    try {
                        thisCheck = true;
                        System.out.print("Enter Card Number: ");
                        res.cardId = Long.parseLong(scanner.nextLine());
                        controller.addReservations(res,rooms);


                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid card number");
                        thisCheck = false;
                    }
                }while(!thisCheck);
                //payment transaction
                return true;

            } else {
                correctInput = false;
            }
        } while (!correctInput);
        return true;
    }


}



