import java.util.Scanner;

class Main {
    private static int displayMenu() {
        System.out.println("=== PAYROLL SYSTEM 3000 ===");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice < 1 || choice > 3) {
            System.out.println("1. Add Employee");
            System.out.println("2. List Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
        }

        return choice;
    }
    public static void main(String [] args) {
        EmployeeManage employeeManage = new EmployeeManage();

        int choice;
        do {
            choice = displayMenu();
            switch (choice) {
                case 1:
                    employeeManage.addEmployee();
                    break;
                case 2:
                    employeeManage.listEmployees();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}