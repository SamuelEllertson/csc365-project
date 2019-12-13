import java.util.ArrayList;
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
        ArrayList<ArrayList<String>> tableArray;
        Map<String, Map<String, Float>> roomMonthlyRevenue = controller.roomsReservedDAO.getRoomsMonthlyRevenue();

        tableArray = getTableArrayList(roomMonthlyRevenue);
        int maxLen = findMaxLength(tableArray);

        StringBuilder rowFormatter = new StringBuilder();
        for(int i = 0; i < tableArray.get(0).size(); i++) {
            rowFormatter.append("%");
            rowFormatter.append(maxLen);
            rowFormatter.append("s");
        }
        rowFormatter.append("\n");

        final Object[][] table = getTableFormat(tableArray);

        for(final Object[] row : table){
            System.out.format(rowFormatter.toString(), row);
            System.out.println("");
        }
    }

    private ArrayList<ArrayList<String>> getTableArrayList(Map<String, Map<String, Float>> roomMonthlyRevenue){
        ArrayList<ArrayList<String>> table = new ArrayList<>();

        String tempRoomId = (String)roomMonthlyRevenue.keySet().toArray()[0];
        Object[] monthsObjArr = roomMonthlyRevenue.get(tempRoomId).keySet().toArray();

        ArrayList<String> months = new ArrayList<>();
        months.add("RoomId");
        for(Object month: monthsObjArr){
            months.add((String) month);
        }
        table.add(months);

        for (String roomId: roomMonthlyRevenue.keySet()){
            Map<String, Float> monthly = roomMonthlyRevenue.get(roomId);
            ArrayList<String> row = new ArrayList<>();
            table.add(row);
            row.add(roomId);
            for (String month: monthly.keySet()) {
                row.add(monthly.get(month).toString());
            }
        }

        return table;
    }

    private Object[][] getTableFormat(ArrayList<ArrayList<String>> tableArray){
        int rows = tableArray.size();
        int cols = tableArray.get(0).size();

        Object[][] table = new Object[rows][cols];
        int i = 0;
        for(ArrayList<String> rowArr : tableArray){
            int j = 0;
            for(String value : rowArr){
                table[i][j] = value;
                j++;
            }
            i++;
        }
        return table;
    }

    private int findMaxLength(ArrayList<ArrayList<String>> table){
        int max = 0;
        for(ArrayList<String> row : table){
            for(String value : row){
                if(value.length() > max)
                    max = value.length();
            }
        }
        return max + 2;
    }

    public void doUILoop(User user){
        PrintMenuItems();
        while(true) {
            String in = input.nextLine();
            switch (in){
                case("1"): revenueDisplay(); break;
                case("2"):
                    input.close();
                    System.exit(0);
                    break;
                default: print("Invalid input: Must be integer between 1 and 2");

            }
        }
    }
}
