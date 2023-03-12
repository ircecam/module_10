import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordsCounter {
    private File file = null;
    private String str = null;
    private Map<String,Integer> countWord;

    public WordsCounter(String pathname){
        file = new File(pathname);
        str = readFromFile(file);
        countWord = countWords(str);

    }
    public WordsCounter(File file){
        this.file = file;
        str = readFromFile(this.file);
        countWord = countWords(str);

    }

    private String readFromFile(File file) {
        StringBuilder s = new StringBuilder("");
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine().strip();
                    s.append(line + " ");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s.toString();
    }
    private  String [] strToArrayOfString(String s){
        String [] words = s.split("\\s+");
        return words;
    }
    private  Map<String, Integer> countWords(String s){
        String [] words = strToArrayOfString(s);

        Map<String,Integer> countWord = new HashMap<>();
        Integer count = 1;
        for(String word:words){
            if(countWord.containsKey(word)){
                count = countWord.get(word);
                countWord.replace(word,count+1);
                count = 1;
            }
            else{
                countWord.put(word,count);
            }
        }
        return countWord;
    }

    private String[] getKeys(Map<String,Integer> map,Integer value){
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        ArrayList<String>  newKeys = new ArrayList<>();
        for (String key:keys){
            if (map.get(key).equals(value)){
                newKeys.add(key);
            }
        }
        return newKeys.toArray(new String[0]);
    }

    private Integer [] sortedValues(Map<String,Integer> countWord){
        Integer [] values = countWord.values().toArray(new Integer[0]);
        boolean isSorted = false;
        int temp;
        while(!isSorted){
            isSorted = true;
            for(int i = 0; i < values.length - 1; i++){
                if(values[i] < values[i+1]){
                    temp = values[i];
                    values[i] = values[i+1];
                    values[i+1] = temp;

                    isSorted = false;
                }
            }
        }
        return values;
    }

    private String sortedMap(Map<String,Integer> countWord){
        StringBuilder res = new StringBuilder("");

        Integer [] sortedValues = sortedValues(countWord);
        ArrayList<String> str = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();

        for(int i = 0; i < sortedValues.length; ){
            String [] sortedKeys = getKeys(countWord,sortedValues[i]);
            for(String key:sortedKeys){
                str.add(key);
                integers.add(sortedValues[i]);
            }
            i += sortedKeys.length;
        }

        for(int i = 0; i < str.size();i++){
            res.append(str.get(i) + ": " + integers.get(i).toString() + "\n");
        }


        return res.toString();
    }
     public String getCountOfWords(){
        return sortedMap(countWord);
     }




}
