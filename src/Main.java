import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String filename = "Задача";
        ManagingADailyPlanner todoList = new ManagingADailyPlanner();
        String menuChoice = "-17";
        try {
            Scanner input = new Scanner(System.in);
            todoList.readFromFile(filename);
            Messages.showMessage("Ежедневник", false);
            while (!menuChoice.equals("4")) {
                Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
                menuChoice = input.nextLine();
                switch (menuChoice) {
                    case "1":
                        Messages.listAllTasksMenu();
                        todoList.listAllTasks(input.nextLine());
                        break;
                    case "2":
                        todoList.readTaskFromUser();
                        break;
                    case "3":
                        todoList.listAllTasksWithIndex();
                        Messages.editTaskSelection();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;
                    case "5":
                        todoList.runTask();

                    default:
                        Messages.unknownMessage();
                }
            }

            todoList.saveToFile(filename);
            Messages.byeMessage();

        } catch (Exception e) {
            Messages.showMessage("UNCAUGHT EXCEPTION THROWN", true);
            System.out.println("Сохраните данные перед записью в файл данных!");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}