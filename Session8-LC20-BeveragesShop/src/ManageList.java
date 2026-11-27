import java.util.ArrayList;
import java.util.Scanner;

public class ManageList {
    private ArrayList<Coffee> coffees = new ArrayList<>();
    private ArrayList<Tea> teas = new ArrayList<>();
    private ArrayList<Juice> juices = new ArrayList<>();
    private int uniTrack = 0;

    // String Dictionary for Given Item Category
    private String[][] dictLabel = {
            {"Coffee", "Bean Type", "CFE"},
            {"Tea", "Tea Type", "TEA"},
            {"Juice", "Fruit Name", "JCE"}
    };

    private int askDID(int currentDID, Scanner inputter, String message) {
        while (currentDID < 1 || currentDID > 3) {
            System.out.print(generateMessage(message));
            currentDID = inputter.nextInt();

            if (currentDID < 1 || currentDID > 3) {
                System.out.println("Your Category ID must be 1-3.");
            }
        }

        return currentDID;
    }

    private String generateMessage(String message) {
        return """
                 There are 3 categories:
                 1. Coffee
                 2. Tea
                 3. Juice
                \s
                What category you want to %s the item from?\s""".formatted(message);
    }

    // did = Direct ID, this allows users to type Feature Followed by ID Number. If you don't have DID, it will ask for one.
    public void AddItem(int did, Scanner inputter) {
        String tempId = "";
        String tempName = "";
        int tempPrice = 0;
        String tempThirdField = "";

        if (did == 0) {
            did = askDID(did, inputter, "add");
            inputter.nextLine();
        }

        while (tempName.isEmpty()) {
            System.out.printf("Insert the %s Name: ", dictLabel[did - 1][0]);

            tempName = inputter.nextLine();

            if (tempName.isEmpty()) {
                System.out.println("The name of given product must be at least 1 character long.");
            }
        }

        while (tempPrice < 1) {
            System.out.print("Name your price (in IDR): ");
            tempPrice = inputter.nextInt();

            if (tempPrice < 1) {
                System.out.println("Invalid Price, Please price it at least 1 IDR or more. :3");
            }
        }

        inputter.nextLine();

        while (tempThirdField.isEmpty()) {
            System.out.printf("Finally... the %s of the %s: ", dictLabel[did - 1][1], dictLabel[did - 1][0]);
            tempThirdField = inputter.nextLine();

            if (tempThirdField.isEmpty()) {
                System.out.printf("The name of given %s must be at least 1 character long.\n", dictLabel[did - 1][1]);
            }
        }

        tempId = "%s-%04d".formatted(dictLabel[did - 1][2], ++uniTrack);

        switch (did) {
            case 1:
                coffees.add(new Coffee(tempId, tempName, tempPrice, tempThirdField));
                break;
            case 2:
                teas.add(new Tea(tempId, tempName, tempPrice, tempThirdField));
                break;
            case 3:
                juices.add(new Juice(tempId, tempName, tempPrice, tempThirdField));
                break;
        }

        System.out.println("\nOkay, so it's added. Thanks!");
    }

    public void ListItems(int did, Scanner inputter) {
        if (did == 0) {
            did = askDID(did, inputter, "list");
            inputter.nextLine();
        }

        switch (did) {
            case 1:
                if (coffees.isEmpty()) {
                    System.out.println("Data is Empty. Oops.");
                    return;
                }
                break;
            case 2:
                if (teas.isEmpty()) {
                    System.out.println("Data is Empty. Oops.");
                    return;
                }
                break;
            case 3:
                if (juices.isEmpty()) {
                    System.out.println("Data is Empty. Oops.");
                    return;
                }
                break;
        }

        System.out.printf("%-8s | %-25s | %-10s | %s\n", "ID", "Name", "Price", dictLabel[did - 1][1]);
        System.out.println("==========================================================================================");

        switch (did) {
            case 1:
                for (Coffee data : coffees) {
                    System.out.printf("%-8s | %-25s | Rp %-7d | %s\n", data.getId(), data.getName(), data.getPrice(), data.getBeanType());
                }
                break;
            case 2:
                for (Tea data : teas) {
                    System.out.printf("%-8s | %-25s | Rp %-7d | %s\n", data.getId(), data.getName(), data.getPrice(), data.getTeaType());
                }
                break;
            case 3:
                for (Juice data : juices) {
                    System.out.printf("%-8s | %-25s | Rp %-7d | %s\n", data.getId(), data.getName(), data.getPrice(), data.getFruit());
                }
                break;
        }

        System.out.println();
    }

    public void DeleteItem(int did, Scanner inputter) {
        String tempId = "";

        if (did == 0) {
            did = askDID(did, inputter, "remove");
            inputter.nextLine();
        }

        while (!tempId.startsWith("%s-".formatted(dictLabel[did - 1][2]))) {
            System.out.printf("In the table '%s', supply ID that you want to remove with format [%s-XXXX]: ", dictLabel[did - 1][0], dictLabel[did - 1][2]);
            tempId = inputter.nextLine();

            if (!tempId.startsWith("%s-".formatted(dictLabel[did - 1][2]))) {
                System.out.println("Umm, your ID format doesn't add up. Try again.\n");
            }
        }

        switch (did) {
            case 1:
                for (Coffee data : coffees) {
                    if (data.getId().equals(tempId)) {
                        coffees.remove(data);
                        System.out.println("Your data is deleted!\n");
                        return;
                    }
                }
                System.out.println("Data is not deleted, because it wasn't here. Oops.");
                break;
            case 2:
                for (Tea data : teas) {
                    if (data.getId().equals(tempId)) {
                        teas.remove(data);
                        System.out.println("Your data is deleted!\n");
                        return;
                    }
                }
                System.out.println("Data is not deleted, because it wasn't here. Oops.");
                break;
            case 3:
                for (Juice data : juices) {
                    if (data.getId().equals(tempId)) {
                        juices.remove(data);
                        System.out.println("Your data is deleted!\n");
                        return;
                    }
                }
                System.out.println("Data is not deleted, because it wasn't here. Oops.");
                break;
        }
    }
}
