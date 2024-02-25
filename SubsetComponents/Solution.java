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
     * Complete the 'findConnectedComponents' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY d as parameter.
     */

    public static int findConnectedComponents(List<Long> d) {
    int size = d.size();
    int numSubset = 1<<size;    
    int sum = 0;
    
    for(int i =0;i <numSubset;i++){
        List<Long> subset = new ArrayList<>();
        for(int j=0;j<size;j++){
            int mask = 1<<j;
            if((mask&i)!=0){
                Long el = d.get(j);
                if(getNumBits(el)>1){
                Iterator it = subset.iterator();
                while(it.hasNext()){
                    long subsetEl = (long)it.next();
                    if((subsetEl&el)!=0){
                        el|=subsetEl;
                        it.remove();
                    }
                }
                subset.add(el);                    
                }
            }
            
        }
        int subsetSize = subset.size();
        long merged = 0;
        for(long s: subset){
            merged = merged|s;
        }
        sum = sum +64 - getNumBits(merged) + subsetSize;
        
    }
    return sum;

    }
    
        public static int getNumBits(long n) {
        int res = 0;
        while(n!=0) {
            if((n&1)!=0) {
                res++;
            }
            n=n>>1;
        }
        return res;
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int dCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> d = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        int components = Result.findConnectedComponents(d);

        bufferedWriter.write(String.valueOf(components));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
