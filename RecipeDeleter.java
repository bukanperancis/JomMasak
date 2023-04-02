import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipeDeleter {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/jommasak";
    private String user = "root";
    private String password = "Knfe210024!!";

    public RecipeDeleter() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void deleteRecipe(int recipeId, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM recipe WHERE recipe_id = ? AND recipe_name = ?");
        statement.setInt(1, recipeId);
        statement.setString(2, name);
        statement.executeUpdate();
        System.out.println("Recipe with recipe_id " + recipeId + " and name " + name + " has been deleted.");
    }

    public static void main(String[] args) {
        try {
            RecipeDeleter deleter = new RecipeDeleter();
            deleter.deleteRecipe(6, "Whiskas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
