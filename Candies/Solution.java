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

    /*
     * Complete the 'candies' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static long candies(int n, List<Integer> arr) {
    long sum = 1;
    int prev = 1;
    int prevNote=arr.get(0);
    boolean firstTimeDescending = true;
    int lastBeforeDescend =1;
    int next = 0;
    boolean lastDesc = false;
    for(int i=1;i<arr.size();i++){
        int curr = arr.get(i);
        if(curr>prevNote){
            if(!firstTimeDescending){
                sum= sum - lastBeforeDescend + Math.max(lastBeforeDescend,next);
                firstTimeDescending = true;
                prev=1;
            }
            prev = prev+1;
            sum = sum + prev;
            lastBeforeDescend = prev;
           lastDesc = false;
        } else if (curr==prevNote){
            if(!firstTimeDescending){
                sum= sum - lastBeforeDescend + Math.max(lastBeforeDescend,next);
                firstTimeDescending = true;
                prev=1;
            }            
            sum ++;
            prev = 1;
            lastBeforeDescend = prev;
            lastDesc = false;
            
            
        } else {
            if(firstTimeDescending){
                 prev = 1;
                 sum = sum + 1;
                 System.out.println("1");
                 firstTimeDescending = false;
                 next = 2;
                 lastDesc = true;
            } else{
                
                prev = prev+1;
                sum = sum + prev;
                System.out.println(prev);
                next = prev+1;
                lastDesc = true;
            }
             
        }        
        prevNote = arr.get(i);
    }
    if(lastDesc){
                sum= sum - lastBeforeDescend + Math.max(lastBeforeDescend,next);
                               
            }

    return sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
