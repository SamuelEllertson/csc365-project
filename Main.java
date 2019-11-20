
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.*;

import java.util.Properties;

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
        View view = new View(new Controller(connectionFactory));
        view.doUILoop();
    }
}