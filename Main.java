import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter search term: ");
            String userInput = scanner.nextLine();
            String[] searchWords = userInput.split("\\s+"); // split input string into words

            String[] fileNames = {".idea/file1.txt", ".idea/file2.txt", ".idea/file3.txt", ".idea/file4.txt"};
            FileSearch.searchFiles(fileNames, searchWords);

            scanner.close();
        }
}

