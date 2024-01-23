# Milestone 1

## Functions 
- convertXML(String path): convert a xml file into JSON object
- writeFile(String content, String path): write content to file where the path is specified
- queryJSON(String queryPath, JSONObject json): return the result after querying the JSON object
- addPrefix(JSONObject json), addPrefix(JSONArray arr): two mutually recursive functions that add prefix to all keys in JSON object

## How to run the code
All 5 tasks exist in Test.java, in order to execute all tasks respectively, please uncomment the desiring running task and comment other tasks.

- First compile the java source code
```linux
javac -cp :.json-java.jar Test.java
```
- Then run the code by execute command under.
```linux
java -cp :.json-java.jar Test
```

