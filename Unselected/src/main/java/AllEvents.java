public class AllEvents implements EVENTHANDLER{

    private GUI gui;
    private Employees employees;
    private Dates dates;

    @Override
    public void submitIn(String id) {
        employees.printEmployees();
    }

    @Override
    public void submitOut(String id) {

    }

    @Override
    public void showEmployees() {

    }

    //constructor
    public AllEvents(){
        gui = new GUI();
        gui.setSubmitIn(this);
        gui.setSubmitOut(this);
        gui.setShowEmployees(this);

        employees = new Employees();
        dates = new Dates();

        gui.setup();
    }
}
