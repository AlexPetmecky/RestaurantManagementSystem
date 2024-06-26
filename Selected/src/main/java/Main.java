import java.time.LocalTime;

public class Main {
	Blackboard blackboard;
	EmployeeDB employeedb;
	DateDB datedb;

    public mainfront mf;

	public static void main(String[] args){
        Main m = new Main();
	}
	
	public Main(){
		blackboard = new Blackboard();
		employeedb = new EmployeeDB(blackboard);
		datedb = new DateDB(blackboard);
        mf = new mainfront();
        mf.setup(this);
        ButtonHandler bh = new ButtonHandler(this);
	}
	
	public void terminate() {
		blackboard.terminate();
		// add code to close the GUI
		
	}
	
	public String formatJsonString(String json) {
		StringBuilder formattedJson = new StringBuilder();
        int indentLevel = 0;
        boolean inQuotes = false;
        json = "<html>" + json + "</html>";
		for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                    formattedJson.append(c);
                    if (!inQuotes) {
                        indentLevel++;
                        appendIndent(formattedJson, indentLevel);
                    }
                    break;
                case '}':
                case ']':
                    if (!inQuotes) {
                        indentLevel--;
                        appendIndent(formattedJson, indentLevel);
                    }
                    formattedJson.append(c);
                    break;
                case '"':
                    formattedJson.append(c);
                    inQuotes = !inQuotes;
                    break;
                case ',':
                    formattedJson.append(c);
                    if (!inQuotes) {
                        formattedJson.append("<br/>");
                        appendIndent(formattedJson, indentLevel);
                    }
                    break;
                default:
                    formattedJson.append(c);
            }
        }

        return formattedJson.toString();
	}
	
	private static void appendIndent(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            stringBuilder.append("    "); // 4 spaces per indent level
        }
    }
	
	// methods for GUI to call components
	public boolean clockIn(int id, String date) {
        //current seconds since midnight
        int seconds = LocalTime.now().toSecondOfDay();
		return employeedb.clockIn(id, date, "" + seconds);
	}
	public boolean clockOut(int id, String date) {
        //current seconds since midnight
        int seconds = LocalTime.now().toSecondOfDay();
        return employeedb.clockOut(id, date, "" + seconds);
	}
	public String getEmployeeStringData(int id) {
		return employeedb.getEmployeeStringData(id);
	}
	public String getAllEmployeesString() {
		return employeedb.getAllEmployeesString();
	}
	public String getDateString(String date) {
		return datedb.getDateString(date);
	}
	public String getAllDateStrings() {
		return datedb.getAllDateStrings();
	}
    
}


// for handling GUI inputs
class ButtonHandler implements SubmitHandler {
    private Main main;

    public ButtonHandler(Main m) {
        main = m;
        main.mf.setSubmitIn(this);
    }
    public void submitIn(String idNum) {
        System.out.println("submitIn: " + idNum);
        main.clockIn(Integer.parseInt(idNum), "1");

    }
    public void submitOut(String idNum) {
        System.out.println("submitOut: " + idNum);
        main.clockOut(Integer.parseInt(idNum), "1");
    }

}