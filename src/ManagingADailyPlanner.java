import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class ManagingADailyPlanner {
    private ArrayList<Tasks> taskList;
         private enum FrequencyTask {

            ONETIME(LocalDate.of(2022, 12, 1)),
            DAILY(LocalDate.of(2022, 12, 1)),
            WEEKLY(LocalDate.of(2022, 12, 1)),
            MONTHLY(LocalDate.of(2022, 12, 1)),
            ANNUAL(LocalDate.of(2022, 12, 1));
            private LocalDate localDate;

            public void setLocalDate(LocalDate localDate) {
                this.localDate = localDate;
            }
        FrequencyTask(LocalDate ld) {

                Objects.requireNonNull(ld);
                this.localDate = ld;
            }
            public LocalDate getLocalDate() {
                return this.localDate;
            }

            LocalDate ld = getLocalDate();
        }

        public ManagingADailyPlanner() {
            taskList = new ArrayList<>();
        }

        public void addTask(String title, String project, String taskDescription, LocalDate ld, LocalDate dueDate) {
            this.taskList.add(new Tasks(title, project, taskDescription, ld, dueDate));
        }

        public boolean readTaskFromUser() {
            Scanner scan = new Scanner(System.in);

            try {
                System.out.println(Messages.GREEN_TEXT + "Пожалуйста, введите следующие данные, чтобы добавить задачу:" + Messages.RESET_TEXT);
                System.out.print(">>> Название задачи  : ");
                String title = scan.nextLine();
                System.out.print(">>> Название проекта: ");
                String project = scan.nextLine();
                System.out.print(">>> Описание задачи: ");
                String taskDescription = scan.nextLine();
                System.out.print("Дата назначения задачи [пример: 2022-12-27] : ");
                LocalDate ld = LocalDate.parse(scan.nextLine());
                System.out.print(">>> Крайний срок решения задачи [пример: 2022-12-27] : ");
                LocalDate dueDate = LocalDate.parse(scan.nextLine());

                this.taskList.add(new Tasks(title, project, taskDescription, ld, dueDate));
                Messages.showMessage("Задача успешно добавлена", false);

                return true;
            } catch (Exception e) {
                Messages.showMessage(e.getMessage(), true);
                return false;
            }

        }

        public boolean readTaskFromUserToUpdate(Tasks tasks) {
            Scanner scan = new Scanner(System.in);
            boolean isTaskUpdated = false;

            try {
                System.out.println(Messages.GREEN_TEXT + "Пожалуйста, введите следующие данные, чтобы обновить задачу:"
                        + "\nЕсли вы не хотите изменять какое-либо поле, просто нажмите клавишу ENTER!" + Messages.RESET_TEXT);
                System.out.print(">>> Название задачи  : ");
                String title = scan.nextLine();
                if (!(title.trim().equals("") || title == null)) {
                    tasks.setTitle(title);
                    isTaskUpdated = true;
                }

                System.out.print(">>> Название проекта: ");
                String project = scan.nextLine();
                if (!(project.trim().equals("") || project == null)) {
                    tasks.setProject(project);
                    isTaskUpdated = true;
                }
                System.out.print(">>> Описание задачи: ");
                String taskDescription = scan.nextLine();
                if (!(project.trim().equals("") || project == null)) {
                    tasks.setTaskDescription(taskDescription);
                    isTaskUpdated = true;
                }
                System.out.print(">>> Дата назначения задачи [пример: 2022-12-27] : ");
                String ld = scan.nextLine();
                if (!(ld.trim().equals("") || ld == null)) {
                    tasks.setLd(LocalDate.parse(ld));
                    isTaskUpdated = true;
                }

                System.out.print(">>> Крайний срок решения задачи [пример: 2022-12-27] : ");
                String dueDate = scan.nextLine();
                if (!(dueDate.trim().equals("") || dueDate == null)) {
                    tasks.setDueDate(LocalDate.parse(dueDate));
                    isTaskUpdated = true;
                }

                Messages.showMessage("Задача заключается в " + (isTaskUpdated ? "обновлено успешно" : "Нет изменений") + ": Возврат в главное меню", false);

                return true;
            } catch (Exception e) {
                Messages.showMessage(e.getMessage(), true);
                return false;
            }
        }

        public void listAllTasksWithIndex() {
            String displayFormat = "%-4s %-15s %-15s %-15s %-10s %-10s";
            ;

            if (taskList.size() > 0) {
                System.out.println(String.format(displayFormat, "NUM", "TITLE", "PROJECT", "DESCRIPTION", "TypesOfTasks", "DUE DATE", "COMPLETED"));
                System.out.println(String.format(displayFormat, "===", "=====", "=======", "===============", "========", "============", "========="));
            } else {
                System.out.println(Messages.RED_TEXT + "Задачи отсутствуют" + Messages.RESET_TEXT);
            }

            taskList.stream()
                    .forEach(task -> System.out.println(String.format(displayFormat,
                            taskList.indexOf(task) + 1,
                            task.getTitle(),
                            task.getProject(),
                            task.getTaskDescription(),
                            task.getLd(),
                            task.getDueDate(),
                            (task.isComplete() ? "Да" : "Нет")
                    )));
        }

        public void listAllTasks(String sortBy) {
            Messages.separator('=', 75);
            System.out.println(
                    "Общее количество задач = " + taskList.size() +
                            "\t\t (Выполнено = " + completedCount() + "\t\t" +
                            Messages.RED_TEXT + " Не Выполнено = " + notCompletedCount() + Messages.RESET_TEXT +
                            " )");
            Messages.separator('=', 75);

            if (sortBy.equals("2")) {
                String displayFormat = "%-15s %-15s %-10s %-10s %-25s %-10s";

                if (taskList.size() > 0) {
                    System.out.println(String.format(displayFormat, "PROJECT", "TITLE", "DESCRIPTION", "TypesOfTasks", "DUE DATE", "COMPLETED"));
                    System.out.println(String.format(displayFormat, "=======", "=====", "========", "===============", "========", "========="));
                } else {
                    System.out.println(Messages.RED_TEXT + "No tasks to show" + Messages.RESET_TEXT);
                }

                taskList.stream()
                        .sorted(Comparator.comparing(Tasks::getProject))
                        .forEach(task -> System.out.println(String.format(displayFormat, task.getProject(),
                                task.getTitle(),
                                task.getTaskDescription(),
                                task.getLd(),
                                task.getDueDate(),
                                (task.isComplete() ? "Да" : "Нет")
                        )));
            } else {
                String displayFormat = "%-15s %-15s %-10s %-10s %-25s %-10s";

                if (taskList.size() > 0) {
                    System.out.println(String.format(displayFormat, "DUE DATE", "TypesOfTasks", "TITLE", "PROJECT", "DESCRIPTION", "COMPLETED"));
                    System.out.println(String.format(displayFormat, "========", "=========", "============", "=======", "=========", "========="));
                } else {
                    System.out.println(Messages.RED_TEXT + "Задачи отсутствуют" + Messages.RESET_TEXT);
                }

                taskList.stream()
                        .sorted(Comparator.comparing(Tasks::getDueDate))
                        .forEach(task -> System.out.println(String.format(displayFormat, task.getDueDate(),
                                task.getLd(),
                                task.getTitle(),
                                task.getProject(),
                                task.getTaskDescription(),
                                (task.isComplete() ? "Да" : "Нет")
                        )));
            }
        }

        public void editTask(String selectedTask) throws NullPointerException {
            try {
                if (selectedTask.trim().equals("") || selectedTask == null) {
                    throw new NullPointerException("ОТСУТСТВУЕТ НОМЕР ЗАДАЧИ: Возврат в главное меню");
                }
                int taskIndex = Integer.parseInt(selectedTask) - 1;
                if (taskIndex < 0 || taskIndex > taskList.size()) {
                    throw new ArrayIndexOutOfBoundsException("НОМЕР ЗАДАЧИ НЕ УКАЗАН: Возврат в главное меню");
                }

                Tasks task = taskList.get(taskIndex);

                Messages.showMessage("Номер задачи " + selectedTask + "  выбирается:" + task.formattedStringOfTasks(), false);

                Messages.editTaskMenu();
                Scanner scan = new Scanner(System.in);
                String editChoice = scan.nextLine();
                switch (editChoice) {
                    case "1":
                        readTaskFromUserToUpdate(task);
                        break;
                    case "2":
                        task.markCompleted();
                        Messages.showMessage("Номер задачи" + selectedTask + " помечено как завершенное: Возврат к Main Menu", false);
                        break;
                    case "3":
                        taskList.remove(task);
                        Messages.showMessage("Номер задачи " + selectedTask + " удалено: Возвращение к Main Menu", true);
                        break;
                    default:
                        Messages.showMessage("Возвращаясь к Main Menu", true);
                }
            } catch (Exception e) {
                Messages.showMessage(e.getMessage(), true);
            }
        }

        public int completedCount() {
            return (int) taskList.stream()
                    .filter(Tasks::isComplete)
                    .count();
        }

        public int notCompletedCount() {
            return (int) taskList.stream()
                    .filter(task -> !task.isComplete())
                    .count();
        }

        public boolean readFromFile(String filename) {
            boolean status = false;

            try {
                if (!Files.isReadable(Paths.get(filename))) {
                    Messages.showMessage("Файл данных, " + filename + " не существует", true);
                    return false;
                }

                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                taskList = (ArrayList<Tasks>) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
                return true;

            } catch (Exception e) {
                Messages.showMessage(e.getMessage(), true);
                return false;
            }
        }

        public boolean saveToFile(String filename) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(taskList);

                objectOutputStream.close();
                fileOutputStream.close();
                return true;

            } catch (Exception e) {
                Messages.showMessage(e.getMessage(), true);
                return false;
            }
        }

        public void runTask() {

            Calendar calendar = Calendar.getInstance();
            calendar.set(
                    Calendar.DAY_OF_WEEK,
                    Calendar.MONDAY
            );
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 40);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Timer time = new Timer();
            TimeUnit.HOURS.toMillis(24);
        }
}
