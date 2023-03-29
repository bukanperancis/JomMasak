import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileSearch {
    private static String[] fileNames;
    private static String[] searchWords;

    public static void searchFiles(String[] fileNames, String[] searchWords) {
        FileSearch.fileNames = fileNames;
        FileSearch.searchWords = searchWords;
        for (String fileName : fileNames) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String fileContent = "";
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent += line + "\n"; // add newline character to preserve file formatting
                }

                boolean allWordsFound = true;
                for (String word : searchWords) {
                    if (!fileContent.contains(word)) {
                        allWordsFound = false;
                        break;
                    }
                }

                if (allWordsFound) {
                    System.out.println("Match found in " + fileName + ":");
                    System.out.println(fileContent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
