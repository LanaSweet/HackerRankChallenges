import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static void staircase(int n) {
        for(int j=0;j<n; j++){
        StringBuilder s= new StringBuilder("");
            for(int i=0; i<n;i++){
                if (i>=n-j-1){
                    s.append("#");
                } else s.append(" ");
            }
            System.out.println(s.toString());
           }
         }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.staircase(n);

        bufferedReader.close();
    }
}
