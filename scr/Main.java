import java.io.*;

public class Main {
    public static void main(String[] args) {
/*
        File file1 = new File("test.txt");
        WordsCounter wordsCounter = new WordsCounter(file1);
        System.out.println(wordsCounter.getCountOfWords());



        File file2 = new File("numbers.txt");
        NumberValidator numberValidator = new NumberValidator(file2);
        System.out.println(numberValidator.getValidNumbers());
*/
        File file = new File("user.txt");
        FiletoJson filetoJson = new FiletoJson(file);

        filetoJson.setJsonToFile();



    }



}
