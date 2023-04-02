import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipeAdder {

    // MySQL database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jommasak";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Knfe210024!!";

    // SQL statement to insert data into "recipe" table
    private static final String INSERT_SQL = "INSERT INTO recipe (recipe_id, recipe_name) VALUES (?, ?)";

    public static void addRecipe(int recipeId, String name) {
        try {
            // Connect to MySQL database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Create prepared statement with SQL statement and parameters
            PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
            stmt.setInt(1, recipeId);
            stmt.setString(2, name);

            // Execute SQL statement to insert data
            int numRowsAffected = stmt.executeUpdate();

            // Check if data was inserted successfully
            if (numRowsAffected == 1) {
                System.out.println("Recipe added successfully.");
            } else {
                System.out.println("Error: recipe not added.");
            }

            // Close database connection and statement
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage: add a recipe with recipe_id 1 and name "Pasta with Tomato Sauce"
        addRecipe(7, "Ayam Masak Merah");
        addRecipe(8, "Karipap Kentang");
        addRecipe(9, "Goreng Pisang Cheese");
    }
}
