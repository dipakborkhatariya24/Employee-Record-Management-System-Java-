import controller.EmployeeController;
import database.EmployeeDB;
import view.EmployeeView;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDB database = new EmployeeDB();
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(database, view);
        controller.start();
    }
}
