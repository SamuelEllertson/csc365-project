import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ReservationDAO implements Dao<Reservation>{

   private Connection conn;

   public ReservationDAO(Connection conn){
      this.conn = conn;
   }
   
   @Override
   public Reservation getById(int reservationId){

      Reservation reservation = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try{
         preparedStatement = this.conn.prepareStatement("SELECT * FROM Reservation WHERE ReservationId=?");
         preparedStatement.setInt(1, reservationId);
         resultSet = preparedStatement.executeQuery();

         Set<Reservation> reservations = unpackResultSet(resultSet); 

         reservation = (Reservation) reservations.toArray()[0];
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
      finally{

         try{
            if(resultSet!=null){
               resultSet.close();
            }
         } 
         catch(SQLException e){
            e.printStackTrace();
         }      

         try{
            if(preparedStatement != null){
               preparedStatement.close();
            }
         }
         catch(SQLException e){
            e.printStackTrace();
         }
      }

      return reservation;
   }

   @Override
   public Set<Reservation> getAll() {
      Set<Reservation> reservations = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      try {
         preparedStatement = this.conn.prepareStatement("SELECT * FROM Reservation");
         resultSet = preparedStatement.executeQuery();
         reservations = unpackResultSet(resultSet);
      } 
      catch (SQLException e) {
         e.printStackTrace();
      } 
      finally {
         try {
         if (resultSet != null)
            resultSet.close();
         } 
         catch (SQLException e) {
            e.printStackTrace();
         }
         try {
         if (preparedStatement != null)
            preparedStatement.close();
         } 
         catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return reservations;
   }

   @Override
   public Boolean insert(Reservation obj) {
      return null;
   }

   @Override
   public Boolean update(Reservation obj) {
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
            "UPDATE Reservation SET UserId=?, CardId=?, CheckIn=?, CheckOut=? WHERE ReservationId=?"
         );
         preparedStatement.setInt(1, obj.userId);
         preparedStatement.setLong(2,obj.cardId);
         preparedStatement.setDate(3,obj.checkIn);
         preparedStatement.setDate(4,obj.checkOut);
         preparedStatement.setInt(5,obj.reservationId);
         
         preparedStatement.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   @Override
   public Boolean delete(Reservation obj) {
      return null;
   }

   private Set<Reservation> unpackResultSet(ResultSet rs) throws SQLException {
      Set<Reservation> reservations = new HashSet<Reservation>();

      while(rs.next()) {
         Reservation reservation = new Reservation(
         rs.getInt("ReservationId"),
         rs.getInt("UserId"),
         rs.getLong("CardId"),
         rs.getDate("CheckIn"),
         rs.getDate("CheckOut"));
         reservations.add(reservation);
      }
      return reservations;
   }
}