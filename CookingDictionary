import java.sql.*;
import java.util.*;
import java.util.Scanner;

public class CookingDictionary {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jommasak";
    private static final String USER = "root";
    private static final String PASS = "Iman@1207";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connected to database.");

            // Prompt the user to enter the ingredients they have
            System.out.print("Enter the ingredients you have (separated by commas): ");
            String[] ingredients = new Scanner(System.in).nextLine().split(",");

            // Retrieve the recipes that use any of the entered ingredients
            String sql = "SELECT r.recipe_name, (SELECT GROUP_CONCAT(i.ingredient_name SEPARATOR ', ') FROM recipe_ingredients ri JOIN ingredients i ON ri.ingredient_id = i.ingredient_id WHERE ri.recipe_id = r.recipe_id) AS ingredients, " +
                    "GROUP_CONCAT(CONCAT(instr.step_number, '. ', instr.instruction_text) ORDER BY instr.step_number SEPARATOR '\n') AS instructions " +
                    "FROM recipes r " +
                    "JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id " +
                    "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                    "JOIN recipe_instructions instr ON r.recipe_id = instr.recipe_id " +
                    "WHERE i.ingredient_name IN (" + String.join(",", Collections.nCopies(ingredients.length, "?")) + ") " +
                    "GROUP BY r.recipe_id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for(int i = 0; i < ingredients.length; i++) {
                pstmt.setString(i+1, ingredients[i].trim());
            }
            ResultSet rs = pstmt.executeQuery();

            // Display the retrieved recipes to the user
            if(rs.next()) {
                System.out.println("You can make these recipes:");
                do {
                    String recipeName = rs.getString("recipe_name");
                    String recipeIngredients = rs.getString("ingredients");
                    String recipeInstructions = rs.getString("instructions");
                    System.out.println(recipeName + " - " + recipeIngredients);
                    System.out.println("Instructions:\n" + recipeInstructions);
                } while(rs.next());
            } else {
                System.out.println("No recipes found.");
            }

        } catch(SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
