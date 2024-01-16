import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import org.json.XML;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.JSONException;
import org.json.JSONPointerException;
public class Test{

    public static JSONObject convertXML(String path){
        try{
            StringBuilder str = new StringBuilder();
            File xml = new File(path);
            Scanner sc = new Scanner(xml);
            while(sc.hasNextLine()){
                str.append(sc.nextLine());
            }
            JSONObject json = XML.toJSONObject(str.toString());
            return json;
        }catch(IOException e){
            e.getStackTrace();
        }
        return null;
    }

    /* this function convert xml file into json file and write the json file as
     * Output.json in same dir.
     */
    public static void writeFile(String content, String path){
        try{
            FileWriter out = new FileWriter(path);
            out.write(content);
            out.close();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }

    public static Object queryJSON(String queryPath, JSONObject json){
        try{
            JSONPointer pointer = new JSONPointer(queryPath);
            Object result = pointer.queryFrom(json);
            return result;
        }catch(JSONPointerException e){
            System.out.println(e);
        }
        return null;
    }   

    public static JSONObject addPrefix(JSONObject json){
        Set<String> keys = json.keySet();
        List<String> key_arrs = new ArrayList<>(keys);
        for(int i = 0; i < key_arrs.size(); i++){
            Object value = json.get(key_arrs.get(i));
            json.remove(key_arrs.get(i));
            String newKey = "swe262_" + key_arrs.get(i);
            if (value instanceof JSONObject){
                JSONObject subObject = JSONObject.class.cast(value);
                subObject = addPrefix(subObject);
                json.put(newKey, subObject);
            }else if(value instanceof JSONArray){
                JSONArray subArr = JSONArray.class.cast(value);
                subArr = addPrefix(subArr);
                json.put(newKey, subArr);
            }
            else{
                json.put(newKey, value);
            }
        }
        return json;
    }

    public static JSONArray addPrefix(JSONArray arr){
        int length = arr.length();
        for(int i = 0; i < length; i++){
            JSONObject element = arr.getJSONObject(0);
            element = addPrefix(element);
            arr.remove(0);
            arr.put(element);
        }
        return arr;
    }

    public static void main(String[] args){
        // task1
        // JSONObject json = convertXML(args[0]);
        // writeFile(json.toString(), args[0].replace(".xml",".json"));

        //task2
        // String queryPath = "/catalog/book/3";
        // Object result = queryJSON(queryPath, json);
        // if(result == null){
        //     System.out.println("task2 failed");
        // }else{
        //     writeFile(result.toString(), "./subObject.json");
        // }

        //task3
        // JSONObject json = convertXML(args[0]);
        // writeFile(json.toString(), args[0].replace(".xml",".json"));
        // String queryPath = args[1];
        // Object result = queryJSON(queryPath, json);
        // if(result == null){
        //     System.out.println("Qeury Failed, Discarded!");
        // }else{
        //     writeFile(result.toString(), "./subObject.json");
        // }
        
        //task4
        JSONObject json = convertXML(args[0]);
        json = addPrefix(json);
        writeFile(json.toString(), "./addPrefix.json");


    }
}