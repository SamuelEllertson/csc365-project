
public class Controller{

    public ConnectionFactory connectionFactory;
    public View view;

    //TODO: Uncomment when the DAOS are implemented
    
    public RoomDAO roomDAO;
    /*
    public UserDAO userDAO;
    public ReservationDAO reservationDAO;
    public CreditCardDAO creditCardDAO;
    */

    public Controller(ConnectionFactory cf){
        this.connectionFactory = cf;

        this.roomDAO = new RoomDAO(cf.getConnection());
        /*
        this.userDAO = new UserDAO(cf.getConnection());
        this.reservationDAO = new ReservationDAO(cf.getConnection());
        this.creditCardDAO = new CreditCardDAO(cf.getConnection());
        */
    }

    //implements issue #5
    //returns true on success, false on failure
    public bool CreateReservation(){
        return false;
    }

    //implements issue #6
    //returns true on success, false on failure
    public bool CancelReservation(){
        return false;
    }

    //implements issue #7
    //returns true on success, false on failure
    public bool RoomInformation(){
        return false;
    }

    //implements issue #10
    //returns true on success, false on failure
    public bool RoomQuery(){
        return false;
    }

    //implements issue #11
    //returns true on success, false on failure
    public bool RoomAvailability(){
        return false;
    }

    //implements issue #12
    //returns true on success, false on failure
    public bool UpdateReservation(){
        return false;
    }

    //implements issue #13
    //returns true on success, false on failure
    public bool DisplayReservations(){
        return false;
    }

    //implements issue #14
    //returns true on success, false on failure
    public bool RevenueOverview(){
        return false;
    }


}