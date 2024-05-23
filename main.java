
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) { 
            // Display
            System.out.println("To Do List");
            System.out.println("1. Add a task");
            System.out.println("2. Edit a task");
            System.out.println("3. View all tasks");
            System.out.println("4. Delete a task");
            System.out.println("5. Mark task as Complete");
            System.out.println("0. Exit");

            System.out.println("Enter what to do? : ");
            // Get user input
            int choice = scanner.nextInt();
            scanner.nextLine(); // newline char
            
            switch (choice){
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1 -> {
                    System.out.println("Enter the task title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter the task description: ");
                    String description = scanner.nextLine();

                    LocalDate dueDate = null;
                    while (dueDate == null){
                        System.out.println("Enter task date (YYYY-MM-DD): ");
                        String dueDateString  = scanner.nextLine();

                        try{
                            dueDate = LocalDate.parse(dueDateString);
                        }catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    }
                    taskManager.addTask(title, description, dueDate);
                    System.out.println("task added Successfully");
                }
                case 2 -> {
                    //Edit
                    System.out.println("Enter the task number to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine(); // new line
                    
                    if (editIndex <= 0 || editIndex > taskManager.getTasks().size()){
                        System.out.println("Invalid task number. Please try again.");
                        break;
                    }
                    System.out.println("Enter the new task title: ");
                    String newTitle = scanner.nextLine();
                    System.out.println("Enter task description: ");
                    String newDescription = scanner.nextLine();
                    LocalDate newDueDate = null;
                    while (newDueDate == null) {
                        System.out.println("Enter the new task due date (YYYY-MM-DD): ");
                        String newDueDateString = scanner.nextLine();

                        try {
                            newDueDate = LocalDate.parse(newDueDateString);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please try again.");
                        }
                    }
                    taskManager.editTask(editIndex-1, newTitle, newDescription, newDueDate);
                    System.out.println("Task edited successfully.");
                }
                case 3 -> // view all tasks
                     taskManager.viewTasks();
                case 4 -> {
                    //delete
                    System.out.println("Enter the task number to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine(); // new line
                    
                    if (deleteIndex <= 0 || deleteIndex > taskManager.getTasks().size()){
                        System.out.println("Invalid task number. Please try again.");
                        break;
                    }
                    taskManager.deleteTask(deleteIndex-1);
                    System.out.println("Task deleted successfully.");
                }
                case 5 -> {
                    // mark as complete
                    System.out.println("Enter the task number to mark complete: ");
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine(); // new line

                    if (completeIndex <= 0 || completeIndex > taskManager.getTasks().size()){
                        System.out.println("Invalid task number. Please try again.");
                        break;
                    }
                    taskManager.markTaskAsCompleted(completeIndex-1);
                    System.out.println("Task marked as completed successfully.");
                }
                default -> System.out.println("Invalid Choice.");
                
            }
            System.out.println();
        }
    }
}