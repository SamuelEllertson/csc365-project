
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

}