import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {


    public static void plusMinus(List<Integer> arr) {
        int plus = 0;
        int minus = 0;
        int zero =0;
        BigDecimal n = new BigDecimal(arr.size());
        for(int i:arr){
            if(i<0){
                minus++;
            } else if (i>0) plus++; else zero++;
        }
        //Fraction of negative numbers
        BigDecimal plusFraction= new BigDecimal(plus);
        plusFraction = plusFraction.divide(n,6,RoundingMode.HALF_UP);    
        System.out.println(plusFraction.toString());
 
        //Fraction of positive numbers
        BigDecimal minusFraction= new BigDecimal(minus);
        minusFraction = minusFraction.divide(n,6,RoundingMode.HALF_UP);       
        System.out.println(minusFraction.toString());
 
        //Fraction of zeros
        BigDecimal zeroFraction= new BigDecimal(zero);
        zeroFraction = zeroFraction.divide(n,6,RoundingMode.HALF_UP);
        System.out.println(zeroFraction.toString());
  }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}