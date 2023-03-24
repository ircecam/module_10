package module_10;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        File file1 = new File("src/main/java/files/test.txt");
        System.out.println(WordsCounter.getCountOfWords(file1));
        
        File file2 = new File("src/main/java/files/numbers.txt");
        System.out.println(NumberValidator.getValidNumbers(file2));

        File file3 = new File("src/main/java/files/user.txt");
        FiletoJson.setUsersToFile(file3);







    }



}
