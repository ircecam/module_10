package module_10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FiletoJson {

    public static void setUsersToFile(File file)  {
        List<User> users = new ArrayList<>();
        int i = 0;

        if(file.exists()){
            try(FileReader fileReader = new FileReader(file)){
                try(OutputStream fos = new FileOutputStream("src/main/java/files/users.json")){
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

                    String json = convertToJson(users);
                    fos.write(json.getBytes(StandardCharsets.UTF_8));
                    
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            }
    }
    private static String convertToJson(List<User> users){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        return json;
    }

}

