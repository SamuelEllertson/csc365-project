import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoomDAO implements Dao<Room>{

   private Connection conn;
   private Controller controller;

   public RoomDAO(Connection conn, Controller controller){
      this.conn = conn;
      this.controller = controller;
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
      }
      
      return room;
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

   public Set<Room> getAvailableRooms(Date checkIn, Date checkOut, int numOccupants, float minPrice, float maxPrice,
                                      String rType, String bType, String decor){

      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      Set<Room>  rooms ;
      int arr [] = new int[8];
      Object args[] = {(Integer)numOccupants, checkIn, checkOut, (Float)minPrice, (Float)maxPrice, rType, bType, decor};
      int i = 1;
      StringBuilder queryString = new StringBuilder(
              "SELECT distinct room.RoomId as RoomId, room.MaxOccupancy as MaxOccupancy, room.RoomType as RoomType, "
      );
      queryString.append(
              "room.BedType as BedType,  room.BedCount as BedCount, room.Decor as Decor, room.Price as Price "
      );
      queryString.append(
              "FROM Room room LEFT JOIN RoomsReserved rr on room.RoomId = rr.RoomId LEFT JOIN Reservation res on res.ReservationId = rr.ReservationId "
      );
      queryString.append(
              "WHERE (room.Price > 0)"
      );
      if(numOccupants>0){
         queryString.append(
                 "AND (room.MaxOccupancy >= ?) "
         );
         arr[0] = i;
         i++;
      }
      if(checkIn!=null && checkOut!=null){
         queryString.append(
                 "AND (res.CheckIn >= ? OR res.Checkout <= ? OR res.CheckIn is null OR res.CheckOut is null)"
         );
         arr[1] = i;
         i++;
         arr[2] = i;
         i++;
      }

      if(minPrice!=0 && maxPrice!=0 ){
         queryString.append(" AND ( room.Price BETWEEN ? AND ?)");
         arr[3] = i;
         i++;
         arr[4] = i;
         i++;
      }

      if(rType.length()>0){
         queryString.append(" AND (room.RoomType = ?)");
         arr[5] = i;
         i++;
      }

      if(bType.length()>0){
         queryString.append(" AND (room.BedType = ?)");
         arr[6] = i;
         i++;
      }

      if(decor.length()>0){
         queryString.append(" AND (room.Decor = ?)");
         arr[7] = i;
         i++;
      }

      System.out.println(queryString.toString());
      try {
         preparedStatement = conn.prepareStatement(
                 queryString.toString()
         );

         for(int j = 0; j < args.length; j++){
            if(arr[j]!=0){
               preparedStatement.setString(arr[j],args[j].toString());
            }
         }

         resultSet = preparedStatement.executeQuery();
         rooms = unpackResultSet(resultSet);

      }
      catch (SQLException e) {
         e.printStackTrace();
         return null;
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
}