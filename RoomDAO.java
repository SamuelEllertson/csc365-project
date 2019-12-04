import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RoomDAO implements Dao<Room>{

   private Connection conn;

   public RoomDao(Connection conn){
      this.conn = conn;
   }
   
   @Override
   public Room getById(int id){

      Room room = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try{
         preparedStatement = this.conn.prepareStatement("");
         preparedStatement.setInt(1,id);
         resultSet = preparedStatement.executeQuery();
         Set<Room> rooms = unpackResultSet(resultSet); 
         room = (Room) rooms.toArray()[0];
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
      finally{
         try{
            if(resultSet!=null){
               resultSet.close();
            }
            catch(SQLException e){
               e.printStackTrace();
            }      
         }

         try{
            if(preparedStatement != null){
               preparedStatement.close();
            }
            catch(SQLException e){
               e.printStackTrace();
            }   

         }

         return room;

      }
   }
   @Override
   public Set<Room> getAll() {
      Set<Room> rooms = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      try {
         preparedStatement = this.conn.prepareStatement("SELECT * FROM Room");
         resultSet = preparedStatement.executeQuery();
         rooms = unpackResultSet(resultSet);
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
      return rooms;
   }

   @Override
   public Boolean insert(Room obj) {
      return null; // only reservation and rooms reserved
   }

   @Override
   public Boolean update(Room obj) {
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
         "UPDATE Room SET MaxOccupancy=?, RoomType=?, BedType=?, BedCount=?, 
            Decor=?, Price=? WHERE __RoomId__=?");
         preparedStatement.setInt(1, obj.getMaxOccupancy());
         preparedStatement.setString(2,obj.getRoomType());
         preparedStatement.setString(3,obj.getBedType());
         preparedStatement.setInt(4,obj.getBedCount());
         preparedStatement.setString(5,obj.getDecor());
         preparedStatement.setFloat(6,obj.getPrice());
         preparedStatement.setInt(7,obj.getRoomId());
         preparedStatement.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   @Override
   public Boolean delete(Room obj) {
      return null; // only reservation and rooms reserved
   }

   private Set<Room> unpackResultSet(ResultSet rs) throws SQLException {
      Set<Room> rooms = new HashSet<Room>();

      while(rs.next()) {
         Room room = new Room(
         rs.getInt("__RoomId__"),
         rs.getInt("MaxOccupancy"),
         rs.getString("RoomType"),
         rs.getString("BedType"),
         rs.getInt("BedCount"),
         rs.getString("Decor"),
         rs.getFloat("Price"));
         rooms.add(room);
      }
      return rooms;
   }
   //what is this?
   @Override
   protected void finalize() throws Throwable {
      super.finalize();
   }

 
}