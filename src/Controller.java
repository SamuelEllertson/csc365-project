import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;
public class Controller{

    public ConnectionFactory connectionFactory;

    //TODO: Uncomment when the DAOS are implemented
    
    public RoomDAO roomDAO;
    public UserDAO userDAO;
    public CreditCardDAO creditCardDAO;
    public ReservationDAO reservationDAO;
    public RoomsReservedDAO roomsReservedDAO;

    public Controller(ConnectionFactory cf){
        this.connectionFactory = cf;
        this.roomDAO = new RoomDAO(cf.getConnection(), this);
        this.userDAO = new UserDAO(cf.getConnection(), this);
        this.creditCardDAO = new CreditCardDAO(cf.getConnection(), this);
        this.reservationDAO = new ReservationDAO(cf.getConnection(), this);
        this.roomsReservedDAO = new RoomsReservedDAO(cf.getConnection(), this);
    }

    public boolean addReservations(Reservation res, HashMap<Room,Integer> rooms, float cost ){
        //start transaction
        Connection conn = connectionFactory.getConnection();
        try{

            conn.setAutoCommit(false);
            reservationDAO.insert(res);

            //first we add all the reservations, then we add all the rooms reserved, then we pay
            Reservation reservation = reservationDAO.getByEverythingElse(res.userId, res.cardId, res.checkIn, res.checkOut);
            Object roomsArr[] = rooms.keySet().toArray();

            for (Object room : roomsArr) {

                Room r = ((Room)room);
                RoomsReserved roomsReserved = new RoomsReserved(res.reservationId,r.roomId, rooms.get(r));
                roomsReservedDAO.insert(roomsReserved);

            }

            CreditCard cred = creditCardDAO.getByCardId(res.cardId);
            if(cred.balance + cost < cred.climit){
                cred.balance = cred.balance+cost;
                creditCardDAO.update(cred);
            }
            else{
                System.out.println("Not enough money");
                throw new SQLException();
            }


            try {
                conn.commit();
            }catch(SQLException e){
                try {
                    System.out.println("Transaction Error");
                    conn.rollback();
                    return false;
                } catch(SQLException c){
                    c.printStackTrace();
                    return false;
                }

            }

        }catch(SQLException e){
            try {
                System.out.println("Transaction Error");
                conn.rollback();
                return false;
            } catch(SQLException c){
                c.printStackTrace();
                return false;
            }

        }

        //end transaction
        return true;
    }

    //implements issue #5
    //returns true on success, false on failure
    public boolean CreateReservation(){
        return false;
    }

    //implements issue #6
    //returns true on success, false on failure
    public boolean cancelReservation(Set<RoomsReserved> roomsReserved, Reservation res){
        // start transaction
        Connection conn = connectionFactory.getConnection();

        try{
            conn.setAutoCommit(false);
            // Remove from RoomsReserved
            for (RoomsReserved rRes : roomsReserved) {
                if (!roomsReservedDAO.delete(rRes)) {
                    System.out.println("FAILED: Could not cancel reservation");
                    return false;
                }
            }
            // Remove from Reservation
            if (!reservationDAO.delete(res)) {
                System.out.println("FAILED: Could not cancel reservation");
                return false;
            }
            // Refund !!

            try {
                conn.commit();
            }catch(SQLException e){
                try {
                    System.out.println("Transaction Error");
                    conn.rollback();
                    return false;
                } catch(SQLException c){
                    c.printStackTrace();
                    return false;
                }

            }
        }catch(SQLException e){
            try {
                System.out.println("Transaction Error");
                conn.rollback();
                return false;
            } catch(SQLException c){
                c.printStackTrace();
                return false;
            }

        }

        //end transaction
        return true;
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

/*      while(rs.next()) {
         Room room = new Room(
         rs.getInt("RoomId"),
         rs.getInt("MaxOccupancy"),
         rs.getString("RoomType"),
         rs.getString("BedType"),
         rs.getInt("BedCount"),
         rs.getString("Decor"),
         rs.getFloat("Price"));
         rooms.add(room);
      }
      return rooms;*/