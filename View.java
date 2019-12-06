import java.util.Scanner;

public class View{

   public Controller controller;
   public Scanner input;

   public View(Controller controller){
      this.controller = controller;
      this.input = new Scanner(System.in);
   }

   public void print(String str){
      System.out.println(str);
   }


   public void PrintMenuItems(){

      String[] options = {
         "1) ...",
         "2) ...",
         "3) ...",
      };

      for(String option : options){
         print(option);
      }
   }


   //This function should continuously loop, reading in commands, executing them, and displaying the output. 
   //Should return when the user wants to quit.
   public void doUILoop(){
      
      while(true){
         PrintMenuItems();
         
         break;
         //GetInput();
      }

   }

}
