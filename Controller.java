
public class Controller{

    public ConnectionFactory connectionFactory;
    public View view;

    //TODO: Uncomment when the DAOS are implemented
    
    public RoomDAO roomDAO;
    public UserDAO userDAO;
    public CreditCardDAO creditCardDAO;
    //public ReservationDAO reservationDAO;

    public Controller(ConnectionFactory cf){
        this.connectionFactory = cf;

        this.roomDAO = new RoomDAO(cf.getConnection());
        this.userDAO = new UserDAO(cf.getConnection());
        this.creditCardDAO = new CreditCardDAO(cf.getConnection());
        //this.reservationDAO = new ReservationDAO(cf.getConnection());
    }

    //implements issue #5
    //returns true on success, false on failure
    public boolean CreateReservation(){
        return false;
    }

    //implements issue #6
    //returns true on success, false on failure
    public boolean CancelReservation(){
        return false;
    }

    //implements issue #7
    //returns true on success, false on failure
    public boolean RoomInformation(){
        return false;
    }

    //implements issue #10
    //returns true on success, false on failure
    public boolean RoomQuery(){
        return false;
    }

    //implements issue #11
    //returns true on success, false on failure
    public boolean RoomAvailability(){
        return false;
    }

    //implements issue #12
    //returns true on success, false on failure
    public boolean UpdateReservation(){
        return false;
    }

    //implements issue #13
    //returns true on success, false on failure
    public boolean DisplayReservations(){
        return false;
    }

    //implements issue #14
    //returns true on success, false on failure
    public boolean RevenueOverview(){
        return false;
    }


}