import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ReservationDAO implements Dao<Reservation>{

   private Connection conn;
   private Controller controller;

   public ReservationDAO(Connection conn, Controller controller){
      this.conn = conn;
      this.controller = controller;
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
      PreparedStatement preparedStatement = null;

      boolean success;
      try {
         preparedStatement = this.conn.prepareStatement(
                 "INSERT INTO Reservation(UserId, CardId, CheckIn, CheckOut) VALUES(?, ?, ?, ?)"
         );
         preparedStatement.setInt(1, obj.userId);
         preparedStatement.setLong(2,obj.cardId);
         preparedStatement.setDate(3,obj.checkIn);
         preparedStatement.setDate(4,obj.checkOut);

         success = preparedStatement.execute();
      }
      catch (SQLException e) {
         e.printStackTrace();
         success = false;
      }
      finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         }
         catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return success;
   }

   @Override
   public Boolean update(Reservation obj) {
      boolean success;
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
            "UPDATE Reservation SET UserId=?, CardId=?, CheckIn=?, CheckOut=? WHERE ReservationId=?"
         );
         preparedStatement.setInt(1, obj.userId);
         preparedStatement.setLong(2,obj.cardId);
         preparedStatement.setDate(3,obj.checkIn);
         preparedStatement.setDate(4,obj.checkOut);
         preparedStatement.setInt(5,obj.reservationId);

         success = preparedStatement.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
         success = false;
      }
      return success;
   }

   @Override
   public Boolean delete(Reservation obj) {
      Boolean success;
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
                 "DELETE FROM Reservation WHERE ReservationId=?"
         );
         preparedStatement.setInt(1, obj.userId);

         success = preparedStatement.execute();
      }
      catch (SQLException e) {
         e.printStackTrace();
         success = false;
      }
      return success;
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