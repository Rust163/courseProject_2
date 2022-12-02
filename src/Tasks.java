import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tasks implements Serializable {
    private String title;
    private String project;
    private String taskDescription;
    private boolean complete;
    private LocalDate ld;
    private LocalDate dueDate;

    public LocalDate getLd() {
        return ld;
    }
    public void setLd(LocalDate ld) {
        this.ld = ld;
    }

    public Tasks(String title, String project, String taskDescription, LocalDate ld, LocalDate dueDate) {
        this.setTitle(title);
        this.setProject(project);
        this.setTaskDescription(taskDescription);
        this.complete = false;
        this.setLd(ld);
        this.setDueDate(dueDate);
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) throws NullPointerException {
        if (title.trim().equals("") || title == null) {
            throw new NullPointerException("Заголовок не может быть пустым.");
        }
        this.title = title.trim();
    }
    public String getProject() {
        return this.project;
    }
    public void setProject(String project) {
        this.project = project.trim();
    }
    public boolean isComplete() {
        return this.complete;
    }
    public boolean markInComplete() {
        this.complete = false;
        return this.complete;
    }
    public boolean markCompleted() {
        this.complete = true;
        return this.complete;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) throws DateTimeException {
        // Выдает исключение DateTimeException, если задана прошедшая дата
        if (dueDate.compareTo(LocalDate.now()) < 0) {
            throw new DateTimeException("Прошедшая дата не допускается");
        }

        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate.format(formattedDate));
    }

    public String formattedStringOfTasks() {
        return (
                        "\nЗадача     : " + title +
                        "\nПроект   : " + project +
                        "\nОписание : " + taskDescription +
                        "\nСтатус   : " + (complete ? "Завершено" : "НЕ ЗАВЕРШЕНО") +
                        "\nДата  : " + dueDate +
                        "\n");
    }
}
