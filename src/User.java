public class User{

   public enum userType{
      MANAGER, CUSTOMER;
   }

   public int userId;
   public String firstName;
   public String lastName;
   public String username;
   public String password;
   public userType type;

   public User(int userId, String firstName, String lastName, String username, String password, String type){
      this.userId = userId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.type = userType.valueOf(type);
   }

   @Override
   public String toString() {
    return "User(userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
      + ", username=" + username + ", password=" + password + ", type=" + type.name() + ')';
  }

}
