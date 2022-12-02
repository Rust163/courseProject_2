import java.time.LocalDate;
public class CustomTask extends Tasks {
    public CustomTask(String title, String project, String taskDescription, LocalDate localDate, LocalDate dueDate) {
        super(title, project, taskDescription, localDate, dueDate);
    }

    public void run() {
        try {
            System.out.println("");
        } catch (Exception ex) {
            System.out.println("error running thread " + ex.getMessage());
        }
    }
}
