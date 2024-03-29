
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        String credFile = "credential.xml";
        Properties prop = new Properties();
        FileInputStream file = null;
        ConnectionFactory connectionFactory = null;

        //open credentials file
        try {
            file = new FileInputStream(credFile);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        //load credentials
        try{
            prop.loadFromXML(file);
            String driver = prop.getProperty("driver");
            String url    = prop.getProperty("url");
            String user   = prop.getProperty("user");
            String pass   = prop.getProperty("pass");

            connectionFactory = new ConnectionFactory(driver, url, user, pass);

        } catch(IOException e){
            e.printStackTrace();
        }

        //create our view and begin the UI loop
        Controller controller = new Controller(connectionFactory);
        ViewFactory viewFactory = new ViewFactory(controller);
        User user = null;
        boolean loggedIn = false;
        while(!loggedIn) {
            user = login(controller);
            if(user != null)
                loggedIn = true;
            else{
                System.out.println("Incorrect Username or password");
            }
        }

        View view = viewFactory.getView(user.type);
//        controller.view = view;

        
        //Testing stuff
        //User steve = new User(4, "Steve", "Lee", "stevo", "pass123", "CUSTOMER");
        //CreditCard cc = new CreditCard(3, 30.0, 500.0);

        //System.out.println(controller.userDAO.update(steve));
        //System.out.println(controller.creditCardDAO.insert(cc));

        //System.out.println(controller.userDAO.getAll());
//        System.out.println(controller.creditCardDAO.getAll());

        System.out.println(user.type.toString());
        System.out.println("");
        view.doUILoop(user);
    }

    public static User login(Controller controller){
        Scanner input = new Scanner(System.in);

        System.out.print("Username: ");
        String username = input.nextLine().trim();

        System.out.print("Password: ");
        String password = input.nextLine().trim();

        User user = controller.userDAO.getByUserNameAndPassword(username, password);
        return user;
    }
}