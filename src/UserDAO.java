import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements Dao<User>{

   private Connection conn;
   private Controller controller;

   public UserDAO(Connection conn, Controller controller){
      this.conn = conn;
      this.controller = controller;
   }
   
   @Override
   public User getById(int userId){

      User user = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try{
         preparedStatement = this.conn.prepareStatement("SELECT * FROM User WHERE UserId=?");
         preparedStatement.setInt(1, userId);
         resultSet = preparedStatement.executeQuery();

         Set<User> users = unpackResultSet(resultSet); 

         user = (User) users.toArray()[0];
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
      
    return user;
   }

   public User getByUserNameAndPassword(String userName, String password){
       User user = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

       try{
           preparedStatement = this.conn.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
           preparedStatement.setString(1, userName);
           preparedStatement.setString(2, password);
           resultSet = preparedStatement.executeQuery();

           Set<User> users = unpackResultSet(resultSet);

           Object[] userArr = users.toArray();
           if(userArr.length != 0)
               user = (User) userArr[0];

           return user;
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

       return user;
   }

   @Override
   public Set<User> getAll() {
      Set<User> users = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      try {
         preparedStatement = this.conn.prepareStatement("SELECT * FROM User");
         resultSet = preparedStatement.executeQuery();
         users = unpackResultSet(resultSet);
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
      return users;
   }

    @Override
    public Boolean insert(User obj) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                "INSERT INTO User (FirstName, LastName, username, password, type) VALUES (?, ?, ?, ?, ?);"
            );
            preparedStatement.setString(1, obj.firstName);
            preparedStatement.setString(2, obj.lastName);
            preparedStatement.setString(3, obj.username);
            preparedStatement.setString(4, obj.password);
            preparedStatement.setString(5, obj.type.name());

            preparedStatement.execute();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean update(User obj) {
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                "UPDATE User SET FirstName=?, LastName=?, username=?, password=?, type=? WHERE UserId=?"
            );
            preparedStatement.setString(1, obj.firstName);
            preparedStatement.setString(2, obj.lastName);
            preparedStatement.setString(3, obj.username);
            preparedStatement.setString(4, obj.password);
            preparedStatement.setString(5, obj.type.name());
            preparedStatement.setInt(6, obj.userId);

            preparedStatement.execute();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

   //Never used since we are never deleting users.
   @Override
   public Boolean delete(User obj) {
      return null;
   }

   private Set<User> unpackResultSet(ResultSet rs) throws SQLException {
      Set<User> users = new HashSet<User>();

      while(rs.next()) {
        User user = new User(
            rs.getInt("UserId"),
            rs.getString("FirstName"),
            rs.getString("LastName"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("type")
        );

        users.add(user);
      }

      return users;
   }
}