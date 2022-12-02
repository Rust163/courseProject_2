public class Messages {
    public static final String RESET_TEXT = "\u001B[0m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static void mainMenu(int incompleteTaskCount, int completedTaskCount) {
        System.out.println("\nМеню");
        System.out.println("===========\n");
        System.out.println("У тебя есть " + Messages.RED_TEXT
                + incompleteTaskCount + " Задача "
                + Messages.RESET_TEXT + " и " + Messages.GREEN_TEXT
                + completedTaskCount + " выполненная задача\n" + Messages.RESET_TEXT);
        System.out.println("Выберите вариант:\n");
        System.out.println("1. Показать список задач (по дате или проекту)");
        System.out.println("2. Добавить новую задачу");
        System.out.println("3. Редактировать задачу (обновить, отметить как выполненную, удалить)");
        System.out.println("4. Сохранить и выйти");
        System.out.println("5. Период задачи ");
        System.out.print("Пожалуйста, укажите свой выбор 1-5: ");
    }
    public static void listAllTasksMenu() {
        System.out.println("\nОтображать все задачи");
        System.out.println("===================\n");
        System.out.println("Выберите вариант:");
        System.out.println("1. Список задач по дате" +
                Messages.RED_TEXT + " [выбор по умолчанию, просто нажмите клавишу ENTER]" + Messages.RESET_TEXT);
        System.out.println("2. Показать список задач по проектам");
        System.out.print("\nПожалуйста, укажите свой выбор 1-2: ");
    }
    public static void editTaskSelection() {
        System.out.println(GREEN_TEXT);
        System.out.print(">>> Введите номер задачи для редактирования и нажмите клавишу ENTER: ");
        System.out.print(RESET_TEXT);
    }
    public static void editTaskMenu() {
        System.out.println("\nПараметры редактирования задачи");
        System.out.println("======================\n");
        System.out.println("Выберите вариант:");
        System.out.println("1. Изменить выбранную задачу");
        System.out.println("2. Пометить выбранную задачу как выполненную");
        System.out.println("3. Удалить выбранную задачу");
        System.out.println("4. Вернуться в главное меню "
                + Messages.RED_TEXT + " [выбор по умолчанию, просто нажмите ENTER]" + Messages.RESET_TEXT);
        System.out.print("\nПожалуйста, укажите свой выбор " + "1-4: ");
    }
    public static void byeMessage() {
        System.out.println(GREEN_TEXT);
        System.out.println(">>> Задачи сохраняются");
        System.out.println(">>> До свидания!");
        System.out.println(RESET_TEXT);
    }
    public static void unknownMessage() {
        System.out.println(RED_TEXT);
        System.out.println(">>> Неправильный выбор: Пожалуйста, введите номер из предложенных вариантов ");
        System.out.print(RESET_TEXT);
    }
    public static void showMessage(String message, boolean warning) {
        System.out.println(warning ? RED_TEXT : GREEN_TEXT);
        System.out.println(">>> " + message);
        System.out.println(RESET_TEXT);
    }
    public static void separator(char charToPrint, int times) {
        for (int index = 0; index < times; index++) System.out.print(charToPrint);
        System.out.println("");
    }
}
