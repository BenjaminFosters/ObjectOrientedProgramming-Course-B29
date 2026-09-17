import java.util.Scanner;

public class App {
    private static String categorize(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private static float calculateBMI(int w, int h) {
        float h_m2 = (float)h / 100f;
        h_m2 = h_m2 * h_m2;
        return (float)w / h_m2;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int weight, height;

        System.out.println("BMI Checker");

        System.out.print("Enter your weight in kg: ");
        weight = input.nextInt();

        System.out.print("Enter your height in centimeters: ");
        height = input.nextInt();
        float bmi = calculateBMI(weight, height);
        System.out.println(String.format("So given your weight and height (which is %d kg and %d m), your BMI is %.2f (a.k.a. %s)", weight, height, bmi, categorize(bmi)));
    }
}