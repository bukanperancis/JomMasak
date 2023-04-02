import java.util.Scanner;

public class AddItem {
    public static String[] promptForIngredients() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ingredients you have (separated by commas): ");
        String input = scanner.nextLine();

        return input.split(",");
    }
}
