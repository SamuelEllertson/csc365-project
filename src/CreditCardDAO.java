import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CreditCardDAO implements Dao<CreditCard>{

   private Connection conn;
   private Controller controller;

   public CreditCardDAO(Connection conn, Controller controller){
      this.conn = conn;
      this.controller = controller;
   }

   @Override
   public CreditCard getById(int id) {
      return null;
   }


   public CreditCard getByCardId(long cardId){

      CreditCard credCard = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try{
         preparedStatement = this.conn.prepareStatement("SELECT * FROM CreditCard WHERE CardId=?");
         preparedStatement.setLong(1, cardId);
         resultSet = preparedStatement.executeQuery();

         Set<CreditCard> credCards = unpackResultSet(resultSet); 

         credCard = (CreditCard) credCards.toArray()[0];
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
      
      return credCard;
   }
   @Override
   public Set<CreditCard> getAll() {
      Set<CreditCard> credCards = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      try {
         preparedStatement = this.conn.prepareStatement("SELECT * FROM CreditCard");
         resultSet = preparedStatement.executeQuery();
         credCards = unpackResultSet(resultSet);
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
      return credCards;
   }

   @Override
   public Boolean insert(CreditCard obj) {
      try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(
                "INSERT INTO CreditCard (CardId, balance, climit) VALUES (?, ?, ?);"
            );
            preparedStatement.setLong(1, obj.cardId);
            preparedStatement.setFloat(2, obj.balance);
            preparedStatement.setFloat(3, obj.climit);

            preparedStatement.execute();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
   }

   @Override
   public Boolean update(CreditCard obj) {
      try {
         PreparedStatement preparedStatement = this.conn.prepareStatement(
            "UPDATE CreditCard SET balance=?, climit=? WHERE CardId=?"
         );
         preparedStatement.setFloat(1, obj.balance);
         preparedStatement.setFloat(2,obj.climit);
         preparedStatement.setLong(3, obj.cardId);

         preparedStatement.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }

   @Override
   public Boolean delete(CreditCard obj) {
      return null;
   }

   private Set<CreditCard> unpackResultSet(ResultSet rs) throws SQLException {
      Set<CreditCard> credCards = new HashSet<CreditCard>();

      while(rs.next()) {
         CreditCard credCard = new CreditCard(
            rs.getLong("CardId"),
            rs.getFloat("balance"),
            rs.getFloat("climit")
         );

         credCards.add(credCard);
      }
      
      return credCards;
   }
}