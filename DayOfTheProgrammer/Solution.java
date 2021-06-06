import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static String dayOfProgrammer(int year) {
        int day =0;
        if(year>1918){
            if((year%400==0)||(year%4==0&&year%100!=0) ){
                 day = 12;
            } else day = 13;
        } else if (year==1918){
            day=26;
        } else if (year%4==0){
            day = 12;
        } else day = 13;
    
return String.valueOf(day)+".09."+String.valueOf(year);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}