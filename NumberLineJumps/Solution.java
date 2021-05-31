import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

 
    public static String kangaroo(int x1, int v1, int x2, int v2) {
        int dif1=Math.abs((x1+v1)-(x2+v2));
        int dif2=Math.abs((x1+2*v1)-(x2+2*v2));
        boolean yes=false;
        if (dif1==0 || dif2==0){return "YES";} else{
            int i=3;
            while(dif2<dif1) {
               dif1=dif2;
               dif2=Math.abs((x1+i*v1)-(x2+i*v2));
               i++;
                if (dif2==0){return "YES";}
            }
        }
         return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int x1 = Integer.parseInt(firstMultipleInput[0]);

        int v1 = Integer.parseInt(firstMultipleInput[1]);

        int x2 = Integer.parseInt(firstMultipleInput[2]);

        int v2 = Integer.parseInt(firstMultipleInput[3]);

        String result = Result.kangaroo(x1, v1, x2, v2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}