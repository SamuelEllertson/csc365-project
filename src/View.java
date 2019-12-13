import java.util.Scanner;

public abstract class View {

    public Controller controller;
    public Scanner input;

    public View(Controller controller){
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public User login(){
        System.out.print("Username: ");
        String username = input.nextLine().trim();

        System.out.print("Password: ");
        String password = input.nextLine().trim();

        User user = controller.userDAO.getByUserNameAndPassword(username, password);
        return user;
    }

    abstract public void  PrintMenuItems();

    abstract public void doUILoop(User user);

}
