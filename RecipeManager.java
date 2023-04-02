import java.sql.*;
import java.util.*;

public class RecipeManager {
    public static List<Recipe> searchRecipes(String[] ingredients) throws SQLException {

        String sql = "SELECT r.recipe_name, (SELECT GROUP_CONCAT(i.ingredient_name SEPARATOR ', ') FROM recipe_ingredients ri JOIN ingredients i ON ri.ingredient_id = i.ingredient_id WHERE ri.recipe_id = r.recipe_id) AS ingredients, " +
                "GROUP_CONCAT(CONCAT(instr.step_number, '. ', instr.instruction_text) ORDER BY instr.step_number SEPARATOR '\n') AS instructions " +
                "FROM recipes r " +
                "JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id " +
                "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                "JOIN recipe_instructions instr ON r.recipe_id = instr.recipe_id " +
                "WHERE i.ingredient_name IN (" + String.join(",", Collections.nCopies(ingredients.length, "?")) + ") " +
                "GROUP BY r.recipe_id";

        try(Connection conn = DatabaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for(int i = 0; i < ingredients.length; i++) {

                pstmt.setString(i+1, ingredients[i].trim());
            }

            List<Recipe> recipes = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

                String recipeName = rs.getString("recipe_name");
                String recipeIngredients = rs.getString("ingredients");
                String recipeInstructions = rs.getString("instructions");

                Recipe recipe = new Recipe(recipeName, recipeIngredients, recipeInstructions);
                recipes.add(recipe);
            }

            return recipes;
        }
    }
}
