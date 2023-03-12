import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FiletoJson {
    private File file;
    private List<User> users;


    public FiletoJson(File file){
        this.file = file;
        users = readFromFile();
    }
    private List <User> readFromFile(){
        List<User> users = new ArrayList<>();
        int i = 0;
        if(file.exists()){
            try(FileReader fileReader = new FileReader(file)){
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()){
                    String line = bufferedReader.readLine().strip();
                    if(i == 0){
                        i++;
                    }
                    else{
                        String [] res = line.split(" ");
                        users.add(new User(res[0],Integer.valueOf(res[1])));
                    }
                }
            }catch (FileNotFoundException e){
                throw new RuntimeException();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return users;
    }
    private String convertToJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        return json;
    }
    public void setJsonToFile()  {
        String json = convertToJson();
        OutputStream fos = null;
        try {
            fos = new FileOutputStream("users.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fos.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

