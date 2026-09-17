import java.util.regex.Pattern;
import java.util.Scanner;

public class App {
    // DETAILS ARE ON README.MD (https://github.com/BenjaminFosters/ObjectOrientedProgramming-Course-B29/tree/main/Session2-LC20-SimpleEmployeeSystem)

    private static Boolean validateEmail(String email) {
        /*
         * Regex Template: https://regexbox.com/regex-templates/email
         * Regex Tutorial:
         * https://www.geeksforgeeks.org/java/regular-expressions-in-java/
         */
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email);
    }

    // Source
    // https://stackoverflow.com/questions/6100712/simple-way-to-count-character-occurrences-in-a-string
    private static Integer countChar(String text, char character) {
        Integer count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == character) {
                count++;
            }
        }

        return count;
    }

    private static Integer displayMenu(Scanner scan) {
        Integer choice = 0;

        // Multiline String: https://www.baeldung.com/java-multiline-string
        String guiText = """
                ==================================================
                | Ridiculously Simple Employee Management System |
                ==================================================
                1. Insert Employee Data
                2. View All Employee
                3. Exit
                Option:""" + ' ';

        while ((choice < 1) || (choice > 3)) {
            System.out.print(guiText);

            // Try Catch (https://www.w3schools.com/java/java_try_catch.asp)
            try {
                choice = scan.nextInt();
                if ((choice < 1) || (choice > 3)) {
                    throw new IllegalArgumentException("Only option 1 - 3 are valid.");
                }
            } catch (Exception e) { // Non-Int Input
                System.err.println("Only 1 through 3 inputs are valid.");
                // Thanks for the Suggestion, Evendy Hartono! (Read more about Scanner -
                // nextLine here:
                // https://www.geeksforgeeks.org/java/scanner-nextline-method-in-java-with-examples/)
                scan.nextLine();
            }

            System.out.println();
        }

        scan.nextLine();
        return choice;
    }

    private static int addData(Scanner scan, String[] data, Integer dataTrac) {
        if (data.length <= dataTrac) {
            System.err.println("Sorry, array is full.");
            return dataTrac;
        }

        String biodata = null;
        String[] biodataSplit = new String[2];

        while (true) {
            System.out.print("Enter employee (name;email): ");
            biodata = scan.nextLine();

            if ((biodata.contains(";")) && (countChar(biodata, ';') == 1)) {
                biodataSplit = biodata.split(";");
                if (validateEmail(biodataSplit[1])) {
                    data[dataTrac] = biodata;
                    System.out.println("Data is in!");
                    break;
                } else {
                    System.err.println("Your Email isn't valid though... Please check it again.");
                }
            } else {
                System.err.println("Please put just one ';'.");
            }
        }

        return dataTrac + 1;
    }

    private static void viewData(String[] data, Integer dataTrac) {
        if (dataTrac == 0) {
            System.out.println("No data found, please add it :3\n");
            return;
        }

        Integer LargestPos = 0;
        Integer LengthChar = 0;
        String[] biodataSplit = new String[2];

        System.out.println("List of Employees:");
        for (int i = 0; i < dataTrac; i++) {
            biodataSplit = data[i].split(";");

            if (biodataSplit[0].length() > LengthChar) {
                LengthChar = biodataSplit[0].length();
                LargestPos = i;
            }

            // String Format (https://www.w3schools.com/java/ref_string_format.asp)
            System.out.println(String.format("%3d. %s - %s", i + 1, biodataSplit[0], biodataSplit[1]));
        }
        System.out.println("Total employees: " + dataTrac);
        System.out.println(
                String.format("Longest name: %s (%d character(s))", data[LargestPos].split(";")[0], LengthChar));
    }

    public static void main(String[] args) throws Exception {
        // Scanner (https://www.w3schools.com/java/java_user_input.asp)
        // Reusability is Inspired by CommandBeep at BenjaminFosters/CommandBeep (yes
        // it's C#, but it too an OOP)
        Scanner RightHandOfDoom = new Scanner(System.in);
        Integer choice = 0;
        Integer dataTrac = 0;
        String[] data = new String[100];
        while (choice != 3) {
            choice = displayMenu(RightHandOfDoom);

            switch (choice) {
                case 1:
                    dataTrac = addData(RightHandOfDoom, data, dataTrac);
                    break;

                case 2:
                    viewData(data, dataTrac);
                    break;

                case 3:
                    System.out.println("Goodbye");
                    break;
            }
        }
    }
}
