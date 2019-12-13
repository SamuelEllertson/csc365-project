import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoomsReservedDAO implements Dao<RoomsReserved>{

    private Connection conn;
    private Controller controller;

    public RoomsReservedDAO(Connection conn, Controller controller){
        this.conn = conn;
        this.controller = controller;
    }

    public Set<RoomsReserved> getByReservationId(int reservationId){
        Set<RoomsReserved> res = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = this.conn.prepareStatement("SELECT * FROM RoomsReserved WHERE ReservationId=?");
            preparedStatement.setInt(1, reservationId);
            resultSet = preparedStatement.executeQuery();
            res = unpackResultSet(resultSet);
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
        return res;
    }

    public Map<String, Integer> getMonthlyRevenue(){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, Integer> monthlyRevenue = new HashMap<>();
        try {

            preparedStatement = this.conn.prepareStatement(
                    "SELECT MONTHNAME(CONCAT('2011-', month, '-01')) AS month, revenue FROM(\n" +
                            "    SELECT\n" +
                            "        DATE_FORMAT(re.CheckOut, \"%m\") AS month,\n" +
                            "        SUM(DATEDIFF(re.CheckOut, re.CheckIn)*ro.Price) AS revenue\n" +
                            "    FROM Reservation re\n" +
                            "    JOIN RoomsReserved rr ON re.ReservationId = rr.ReservationId\n" +
                            "    JOIN Room ro ON rr.RoomId = ro.RoomId\n" +
                            "    GROUP BY month\n" +
                            "    ORDER BY month\n" +
                            ") AS M"
            );
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String month = resultSet.getString("month");
                int revenue = resultSet.getInt("revenue");
                monthlyRevenue.put(month, revenue);
            }
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
        return monthlyRevenue;
    }

    public Set<RoomsReserved> getByRoomId(int roomId){
        Set<RoomsReserved> res = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = this.conn.prepareStatement("SELECT * FROM RoomsReserved WHERE RoomId=?");
            preparedStatement.setInt(1, roomId);
            resultSet = preparedStatement.executeQuery();
            res = unpackResultSet(resultSet);
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
        return res;
    }

    @Override
    public Boolean insert(RoomsReserved obj) {
        try{
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "INSERT INTO RoomsReserved(ReservationId, RoomId, Occupants) VALUES(?, ?, ?)"
            );
            preparedStatement.setInt(1,obj.reservationId);
            preparedStatement.setInt(2,obj.roomId);
            preparedStatement.setInt(3,obj.occupants);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public Boolean update(RoomsReserved obj) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "UPDATE RoomsReserved SET Occupants=? WHERE ReservationId=? AND RoomId = ?"
            );
            preparedStatement.setInt(1, obj.occupants);
            preparedStatement.setInt(2,obj.reservationId);
            preparedStatement.setInt(3,obj.roomId);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(RoomsReserved obj) {
        try{
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                    "DELETE FROM RoomsReserved WHERE ReservationId=? AND RoomId=?"
            );
            preparedStatement.setInt(1,obj.reservationId);
            preparedStatement.setInt(2,obj.roomId);
            preparedStatement.setInt(3,obj.occupants);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private Set<RoomsReserved> unpackResultSet(ResultSet rs) throws SQLException {
        Set<RoomsReserved> res = new HashSet<RoomsReserved>();

        while(rs.next()) {
            RoomsReserved resRoom = new RoomsReserved(
                    rs.getInt("ReservationId"),
                    rs.getInt("RoomId"),
                    rs.getInt("Occupants"));
            res.add(resRoom);
        }
        return res;
    }

    //don't use
    @Override
    public RoomsReserved getById(int id) {
        return null;
    }

    //don't use
    @Override
    public Set<RoomsReserved> getAll() {
        Set<RoomsReserved> res = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = this.conn.prepareStatement("SELECT * FROM RoomsReserved");
            resultSet = preparedStatement.executeQuery();
            res = unpackResultSet(resultSet);
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
        return res;
    }
}
