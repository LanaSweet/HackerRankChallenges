
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static int pageCount(int n, int p) {
        
        int min = 0;
        if((n%2==0 && p%2==0)||(n%2!=0 && p%2!=0)){
            min = Math.abs(n-p)/2;
        } else if (n%2!=0){
            min = Math.abs(n-p-1)/2;
        } else min = (n+1-p)/2;
        if (p%2==0){
            if (p/2<min){
                min = p/2;
          }
            } else if (((p-1))/2<min){
                min = (p-1)/2;}
        
        return min;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
    