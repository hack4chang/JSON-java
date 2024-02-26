import org.json.*;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.util.function.Function;

class TestClass {
    // using DoubleRecursive.xml as testing XML
    public static JSONObject convertXML(String path) {
        try {
            // StringBuilder str = new StringBuilder();
            // JSONPointer pointer = new JSONPointer("/catalog/book/1/author/1/Gender");
            // JSONObject replace = new JSONObject();
            // replace.put("Gender", "Female");
            // Function<String, String> keyTransformer = a -> "swe262_" + a;
            FileReader xml = new FileReader(path);
            // JSONObject json = XML.toJSONObject(xml, keyTransformer);
            // JSONObject json = XML.toJSONObject(xml);
            JSONObject json = XML.toJSONObject("<Books><book><title>AAA</title><author>ASmith</author></book><book><title>BBB</title><author>BSmith</author></book></Books>");
            return json;
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public static Object queryJSON(String queryPath, JSONObject json) {
        try {
            JSONPointer pointer = new JSONPointer(queryPath);
            Object result = pointer.queryFrom(json);
            return result;
        } catch (JSONPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        JSONObject json = convertXML(args[0]);
        json.toStream().forEach(System.out::println);
    }
}