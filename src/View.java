import java.util.Scanner;

public abstract class View {

    public Controller controller;
    public Scanner input;

    public View(Controller controller){
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void print(String str){
        System.out.println(str);
    }

    abstract public void  PrintMenuItems();

    abstract public void doUILoop(User user);

}
