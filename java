import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Connection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search ingredients (separate by space): ");
        String userInput = scanner.nextLine();
        String[] searchTerms = userInput.split("\\s+"); // split input into separate words
        String[] fileNames = {".idea/NasiAyam.txt", ".idea/NasiLemak.txt"};

        for (String fileName : fileNames) {

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                StringBuilder fileContent = new StringBuilder();
                String line;

                    while ((line = br.readLine()) != null) {
                        fileContent.append(line).append("\n"); // add newline character to preserve file formatting
                }
            boolean allMatch = true;

                for (String term : searchTerms) {
                if (!fileContent.toString().contains(term)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                System.out.println("ingredient found in " + fileName + ":");
                System.out.println(fileContent);
            }
        }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}
