import org.json.*;
import java.io.File;
import java.util.*;
import java.io.IOException;

class TestClass{
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

    public static void main(String[] args){
        JSONObject json = convertXML(args[0]);
        Object result = queryJSON("/catalog/book/1", json);
    }
}