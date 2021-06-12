import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static int countingValleys(int steps, String path) {
        int v=0;
        int counter=0;
        boolean valley=false;
        String [] arr = path.split("");
        for(String k:arr){
            if (k.equals("U")){
                counter=counter+1;
            } else if(k.equals("D")){counter=counter-1;}
            if(counter<0){
                valley = true;
            } else if ((counter==0)&& valley){
                v++;
                valley = false;
            }
         }
        return v;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);
        

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}