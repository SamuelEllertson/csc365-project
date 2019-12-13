public class ViewFactory {

    private Controller controller;

    public ViewFactory(Controller controller){
        this.controller = controller;
    }

    public View getView(User.userType type){
        if(type == User.userType.CUSTOMER){
            return new UserView(controller);
        }
        else{
            return new ManagerView(controller);
        }
    }
}
