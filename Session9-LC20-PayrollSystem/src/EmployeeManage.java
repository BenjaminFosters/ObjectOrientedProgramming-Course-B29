import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManage {
    private Integer employeeCounter = 0;
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void addEmployee() {
        int type = 0;
        String tempName = "";
        int tempBaseSalary = 0;

        while (type < 1 || type > 2) {
            System.out.print("Enter employee type (1 for Permanent, 2 for Contract): ");
            type = scanner.nextInt();

            if (type < 1 || type > 2) {
                System.out.println("Invalid type. Please enter 1 or 2.");
            }
        }

        scanner.nextLine();

        while (tempName.isEmpty()) {
            System.out.print("Enter employee name: ");
            tempName = scanner.nextLine();

            if (tempName.isEmpty()) {
                System.out.println("Name shouldn't be empty.");
            }
        }

        switch (type) {
            case 1:
                while (tempBaseSalary < 1) {
                    System.out.print("Enter base salary: ");
                    tempBaseSalary = scanner.nextInt();

                    if (tempBaseSalary < 1) {
                        System.out.println("Base salary should be 1 IDR or more.");
                    }
                }
                scanner.nextLine();
                employees.add(new PermanentEmployee("PRMNT-%04d".formatted(++employeeCounter), tempName, tempBaseSalary));
                break;
            case 2:
                int tempWorkingDays = -1;
                int tempDailyRate = -1;

                while (tempWorkingDays < 0) {
                    System.out.print("Enter working days : ");
                    tempWorkingDays = scanner.nextInt();

                    if (tempWorkingDays < 0) {
                        System.out.println("Overtime pay should be 0 IDR or more.");
                    }
                }

                scanner.nextLine();


                while (tempDailyRate < 1) {
                    System.out.print("Enter daily rate: ");
                    tempDailyRate = scanner.nextInt();

                    if (tempDailyRate < 1) {
                        System.out.println("Daily rate should be 1 IDR or more.");
                    }
                }

                scanner.nextLine();

                employees.add(new ContractEmployee("CNTRC-%04d".formatted(++employeeCounter), tempName, 1, tempWorkingDays, tempDailyRate)); // 1 is Workaround since Calc is Day*Rate
                break;
        }
    }

    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee.displayInfo());
                System.out.println("\n================================\n");
            }
        }
    }
}