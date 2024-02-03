# SWE262P Project Milestone #2 - #5
UCI MSWE 2024 Winter - SWE262P Programming Styles

## Group Members
1. Han Chang (hchang14@uci.edu)
2. Kruthi Chandrashekar (kruthic@uci.edu)

## Build Instruction
The org.json package can be built from the command line, Maven, and Gradle. The unit tests can be executed from Maven, Gradle, or individually in an IDE e.g. Eclipse.

**Building from the command line**
*Build the class files by executing the existing bash script from directory src/main/java/Hank_test*
```Linux 
bash jar.sh
```
*Create the jar file in the current directory*
```Linux
jar cf json-java.jar org/json/*.class
```
*Compile a program that uses the jar (see example code below)*
```Linux
javac -cp .;json-java.jar Test.java (Windows)
javac -cp .:json-java.jar Test.java (Unix Systems)
```
*Test file contents*
```Linux
import org.json.JSONObject;
public class Test {
    public static void main(String args[]){
       JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
       System.out.println(jo);
    }
}
```
*Execute the Test file*
```Linux
java -cp .;json-java.jar Test (Windows)
java -cp .:json-java.jar Test (Unix Systems)
```

*Expected output*
```JSON
{"abc":"def"}
```


### Milestone #2

#### Spec
- Add an overloaded static method to the XML class with the signature
```Java
static JSONObject toJSONObject(Reader reader, JSONPointer path) 
```
which does, inside the library, the same thing that task 2 of milestone 1 did in client code, before writing to disk. Being this done inside the library, you should be able to do it more efficiently. Specifically, you shouldn't need to read the entire XML file, as you can stop parsing it as soon as you find the object in question.
- Add an overloaded static method to the XML class with the signature
```Java
static JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement) 
```
which does, inside the library, the same thing that task 5 of milestone 1 did in client code, before writing to disk. Are there any possible performance gains from doing this inside the library? If so, implement them in your version of the library.
- Write unit tests that use these two new functions, both for obtaining correct results and for testing error conditions.

### How to run

#### Method 1 - Use Maven
Use Maven to run the Junit test from src/main/java/test

#### Method 2 - Using Bash Script
Under src/main/java/Hank_test, you can build and create the .jar file using
```Linux
bash jar.sh
```
It will create an json-java.jar under the current directory.
To execute the testing file TestClass.java, use this command
```Linux
bash javaCompileExecute.sh
```

### Implementation

- For the first rubric:
To found the subobject under the given path, first I 
check if the open tag Name fit the given key or not, also validate the path correctness along the way. After I found all keys in the given path,
I started to collect json object and stopped when the related close tag found. Then I skip the reader to the end of the file.
That way I would improve the performance without reading the whole XML file.

- For the second rubric:
I did validation on the given key path along reading the file, once the given path didn't match the actual file, it would return errors.
After I found the last key from the given path at open tag section, I skipped the reader to the end of the close tag, and accumulated the given JSON object.




