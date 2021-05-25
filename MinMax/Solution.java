import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

 
    public static void miniMaxSum(List<Integer> arr) {
        long min=Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long sum=0;
        for (int i=0; i<5;i++){
            sum = sum+arr.get(i);
        }
        long sumTemp = 0;
        for (int i=0; i<5;i++){
            sumTemp = sum-arr.get(i);
            if (sumTemp<min){min=sumTemp;}
            if (sumTemp>max){max=sumTemp;}
        }
        System.out.println( min + " " + max );
      }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        Result.miniMaxSum(arr);

        bufferedReader.close();
    }
}
