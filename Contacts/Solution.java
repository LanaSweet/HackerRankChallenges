import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {


    public static List<Integer> contacts(List<List<String>> queries) {
        List<Integer> results = new ArrayList<>();
        List<String> contacts = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
    for(List<String> query: queries){
        switch(query.get(0)){
            case "add":
            for(int j=1;j<=query.get(1).length();j++){
                String sub = query.get(1).substring(0,j);
                if(map.get(sub)==null){
                    map.put(sub,1);
                } else { 
                    map.put(sub, map.get(sub)+1);
                }
            }
            break;
            
            case "find":
             if(map.containsKey(query.get(1))){
                results.add(map.get(query.get(1)));
            } else     results.add(0);
            break;
        }
    }
    return results;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> queries = new ArrayList<>();

        IntStream.range(0, queriesRows).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.contacts(queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
