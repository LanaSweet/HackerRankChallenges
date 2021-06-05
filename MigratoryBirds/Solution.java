import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    public static int migratoryBirds(List<Integer> arr) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i: arr){
        if (map.containsKey(i)){
            int j = map.get(i);
            j++;
            map.put(i,j);
        } else map.put(i,1);
    }
    int mostSightedBird=0;
    int sighted=0;
    for (Integer bird:map.keySet()){
        if (map.get(bird)>sighted) {
            mostSightedBird=bird;
            sighted = map.get(bird);
            
        } else if (map.get(bird)==sighted && bird<mostSightedBird) {
            mostSightedBird=bird;
            
        }
    }
    return mostSightedBird;
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < arrCount; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        int result = Result.migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}