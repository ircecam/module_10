import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class NumberValidator {
    private File file;
    private String [] validNumbers;


    public NumberValidator(File file){
        this.file = file;
        validNumbers = readFromFile();
    }

    private String [] readFromFile(){
        Collection <String> res = new ArrayList<>();
        if(file.exists()){
            try(FileReader fileReader = new FileReader(file)){
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()){
                    String phoneNumber = bufferedReader.readLine().strip();
                    if(isNumberValid(phoneNumber)){
                        res.add(phoneNumber);
                    }
                }
            }catch (FileNotFoundException e){
                throw new RuntimeException();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return res.toArray(new String[0]);
    }

    public boolean isNumberValid(String number){
        String regex1 = "\\d{3}\\-\\d{3}\\-\\d{4}";
        String regex2 = "\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}";
        boolean res = Pattern.matches(regex1,number) || Pattern.matches(regex2,number);
        return res;
    }

    public String getValidNumbers(){
        StringBuilder res = new StringBuilder("");
        for(String number:validNumbers){
            res.append(number).append("\n");
        }
        return res.toString();
    }
}
