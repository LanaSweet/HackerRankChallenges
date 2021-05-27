import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static String timeConversion(String s) {
        int hour = (int)    Integer.parseInt(s.substring(0, 2));
        String s1 = "";
        
        if (s.substring(8,10).equals("PM")) {
            if (hour==12) {
            s1 = s.substring(0, 8);
            
        } else {
            hour = hour + 12;
            if (hour<10) {
                s1 = "0" + String.valueOf(hour)+s.substring(2, 8);
            } else { 
                
                s1 = String.valueOf(hour)+s.substring(2, 8);
            }
        }} else {
            if (hour==12) {
            s1 = "00"+s.substring(2, 8);
        } else s1 = s.substring(0, 8);
        }
        return s1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
