import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RoomDAO implements Dao<Room>{

   private Connection conn;

   public RoomDAO(Connection conn){
      this.conn = conn;
   }
   
   @Override
   public Room getById(int roomId){

      Room room = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try{
         preparedStatement = this.conn.prepareStatement("SELECT * FROM Room WHERE RoomId=?");
         preparedStatement.setInt(1, roomId);
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

   //Never used since we are not creating new rooms.
   @Override
   public Boolean insert(Room obj) {
      return null;
   }

   //Never used since we are not updating old rooms.
   @Override
   public Boolean update(Room obj) {
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
            "UPDATE Room SET MaxOccupancy=?, RoomType=?, BedType=?, BedCount=?, Decor=?, Price=? WHERE RoomId=?"
         );
         preparedStatement.setInt(1, obj.maxOccupancy);
         preparedStatement.setString(2,obj.roomType.name());
         preparedStatement.setString(3,obj.bedType.name());
         preparedStatement.setInt(4,obj.bedCount);
         preparedStatement.setString(5,obj.decor.name());
         preparedStatement.setFloat(6,obj.price);
         preparedStatement.setInt(7,obj.roomId);
         preparedStatement.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   //Never used since we are never deleting rooms.
   @Override
   public Boolean delete(Room obj) {
      return null;
   }

   private Set<Room> unpackResultSet(ResultSet rs) throws SQLException {
      Set<Room> rooms = new HashSet<Room>();

      while(rs.next()) {
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
      return rooms;
   }
}