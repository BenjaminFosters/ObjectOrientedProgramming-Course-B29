import java.util.Scanner;

public class Main {
    private static int getMainOption(String draftOption) {
        draftOption = draftOption.trim();
        if (draftOption.length() == 1 || draftOption.length() == 2) {
            return Character.getNumericValue(draftOption.charAt(0));
        }
        return -1;
    }

    private static int getSubOption(String draftOption) {
        draftOption = draftOption.trim();
        if (draftOption.length() == 2) {
            int draftNum = Character.getNumericValue(draftOption.charAt(1));
            if (draftNum > 0 && draftNum < 4) {
                return Character.getNumericValue(draftOption.charAt(1));
            }
        }
        return 0;
    }

    private static void easterExit(int subOption) {
        if (subOption == 0) {
            System.out.println("See you later!");
        } else {
            System.out.println("See you later! Apparently you left an option hanging there :3");
        }
    }

    private static String printScreen(Scanner inputter) {
        String Option;
        System.out.println("""
                ===========================
                ||     The Watermill     ||
                ===========================""");

        System.out.println("""
                1. Add Beverages
                2. List All Beverages
                3. Delete Beverages
                0. Exit
                
                Protip! Use 1, 2, or 3 (Coffee, Tea, Juice) after the option if you need to skip to specific category.
                For example, Adding Beverages for Tea, type: 12 directly.
                """);

        System.out.print("Enter Your Number: ");
        Option = inputter.nextLine();
        return Option;
    }

    public static void main(String[] args) {
        String draftOption;
        int mainOption = -1;
        int subOption = 0;
        Scanner inputter = new Scanner(System.in);
        ManageList mnLs = new ManageList();

        while (mainOption != 0) {
            draftOption = printScreen(inputter);
            mainOption = getMainOption(draftOption);
            subOption = getSubOption(draftOption);

            switch (mainOption) {
                case 1:
                    mnLs.AddItem(subOption, inputter);
                    break;

                case 2:
                    mnLs.ListItems(subOption, inputter);
                    break;

                case 3:
                    mnLs.DeleteItem(subOption, inputter);
                    break;

                case 0:
                    easterExit(subOption);
                    break;

                default:
                    System.out.println("Invalid Option. Please Try Again :3");
            }
        }
    }
}