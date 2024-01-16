import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Set;
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
    public static void toJSONFile(String file, String path){
        try{
            FileWriter out = new FileWriter(path);
            out.write(file);
            out.close();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }

    public static Object queryJSON(String JSONPointer, JSONObject json){
        try{
            JSONPointer pointer = new JSONPointer(JSONPointer);
            Object result = pointer.queryFrom(json);
            return result;
        }catch(JSONPointerException e){
            System.out.println(e);
        }
        return null;
    }   

    public static JSONObject addPrefix(JSONObject json){
        Set<String> keys = json.keySet();
        for(String key: keys){
            Object value = json.get(key);
            json.remove(key);
            String newKey = "swe262_" + key;
            if (value instanceof JSONObject){
                JSONObject subObject = JSONObject.class.cast(value);
                subObject = addPrefix(subObject);
                json.put(newKey, subObject);
            }else if(value instanceof JSONArray){
                JSONArray subArray = JSONArray.class.cast(value);
                int length = subArray.length();
                for(int i = 0; i < length; i++){
                    JSONObject element = subArray.getJSONObject(i);
                    addPrefix(element);
                }
            }
            else{
                json.put(newKey, value);
            }
        }
        return json;
    }

    public static void main(String[] args){
        // task1
        // JSONObject json = getJSONObject(args[0]);
        // toJSONFile(json.toString(), args[0].replace(".xml",".json"));

        //task2
        // String JSONPointer = "/catalog/book/3";
        // Object result = queryJSON(JSONPointer, json);
        // if(result == null){
        //     System.out.println("task2 failed");
        // }else{
        //     toJSONFile(result.toString(), "./subObject.json");
        // }

        //task3
        // JSONObject json = getJSONObject(args[0]);
        // toJSONFile(json.toString(), args[0].replace(".xml",".json"));
        // String JSONPointer = args[1];
        // Object result = queryJSON(JSONPointer, json);
        // if(result == null){
        //     System.out.println("Qeury Failed, Discarded!");
        // }else{
        //     toJSONFile(result.toString(), "./subObject.json");
        // }
        
        //task4
        JSONObject json = convertXML(args[0]);
        json = addPrefix(json);
        toJSONFile(json.toString(), "./addPrefix.json");

    }
}