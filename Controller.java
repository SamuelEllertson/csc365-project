
public class Controller{

    private ConnectionFactory connectionFactory;

    //TODO: Uncomment when the DAOS are implemented
    /*
    private RoomDAO roomDAO;
    private UserDAO userDAO;
    private ReservationDAO reservationDAO;
    private CreditCardDAO creditCardDAO;
    */

    public Controller(ConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;

        /*
        this.roomDAO = new RoomDAO;
        this.userDAO = new UserDAO;
        this.reservationDAO = new ReservationDAO;
        this.creditCardDAO = new CreditCardDAO;
        */
    }

}