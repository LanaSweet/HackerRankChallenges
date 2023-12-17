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
     * Complete the 'unboundedKnapsack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arr
     */

    public static int unboundedKnapsack(int k, List<Integer> arr) {
    Collections.sort(arr);
    Set<Integer> set = new HashSet<>(arr);
    List<Integer> unique = new ArrayList<>(set);
    
    boolean[] sumRegister = new boolean[k+1];
    sumRegister[0]= true;
    for(int i=0;i<unique.size();i++){
        for(int j=0;j<=k;j++){
            if(j>=unique.get(i)){
                sumRegister[j]=sumRegister[j]|sumRegister[j-unique.get(i)];
            }
        }
    }
    for(int j=k;k>=0;j--){
        if(sumRegister[j]){
            return j;
        }
    }
    return 0;
}
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());
        while(t>0){
            t--;
                   String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.unboundedKnapsack(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        }


        bufferedReader.close();
        bufferedWriter.close();
    }
}
