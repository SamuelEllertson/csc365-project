
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionFactory{
    private String driver;
    private String url;
    private String user;
    private String pass;

    public ConnectionFactory(String driver, String url, String user, String pass){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public Connection getConnection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch(SQLException e){
            throw new RuntimeException("Error connection to the database", e);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}