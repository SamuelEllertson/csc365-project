import java.util.Map;

public class ManagerView extends View {

    public ManagerView(Controller controller){
        super(controller);
    }

    public void PrintMenuItems() {
        String[] options = {
                "1) view monthly revenue",
                "2) log out"
        };
        for(String option: options){
            print(option);
        }
    }

    public void revenueDisplay(){
        Map<String, Map<String, Integer>> roomMonthlyRevenue = controller.roomsReservedDAO.getRoomsMonthlyRevenue();
        System.out.println(roomMonthlyRevenue.toString());
    }

    public void doUILoop(User user){
        revenueDisplay();
    }
}
