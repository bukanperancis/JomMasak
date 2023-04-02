import java.sql.SQLException;
import java.util.*;

public class UserInterface {
    public static void main(String[] args) {

        try {

            String[] ingredients = AddItem.promptForIngredients();

            List<Recipe> recipes = RecipeManager.searchRecipes(ingredients);

            if (!recipes.isEmpty()) {

                System.out.println("You can make these recipes:");

                for (Recipe recipe : recipes) {
                    System.out.println("\n");
                    System.out.println(recipe.getName() + " - " + recipe.getIngredients());
                    System.out.println("Instructions:\n" + recipe.getInstructions());

                }
            }
            else {
                System.out.println("No recipes found.");
            }
        }
        catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
