import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

      public static int getTotalX(List<Integer> a, List<Integer> b) {
      List<Integer> c = new ArrayList<>();
      List<Integer> result = new ArrayList<>();
      boolean number;
      int max = Integer.MIN_VALUE;
      for (int i:b){
          if (i>max){max = i;}
      }
      for (int i=1; i<=max; i++){
          number = true;
           for (int j:a){
          if ((i%j)!=0){
              number = false;
              }
      }
      if (number){        
          c.add(i);
      }
     }
     for (int i:c){       
         number = true;
          for (int j:b){
              if ((j%i)!=0){
                 number = false;
              }
          }
          if (number){
              result.add(i);
          }
      }
     return result.size();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        String[] brrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> brr = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrTemp[i]);
            brr.add(brrItem);
        }

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}