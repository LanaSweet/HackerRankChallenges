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
      *
     * The function returns a STRING.
     * The function accepts STRING s as parameter.
     */

            public  static String isValid(String s) {
            Map map = new HashMap<String,Integer>();
            
            String result = "YES";
            for (int i=0; i<s.length();i++){
               if(map.containsKey(s.charAt(i))) {
                   int n = (int) map.get(s.charAt(i));
                  map.put(s.charAt(i), n+1);
                       
                   } else {
                       map.put(s.charAt(i), 1);
                   }
                   
               }


            Map mapOfOccurences = new HashMap<Integer, Integer>();
            Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
            int i=0;
            while(itr.hasNext()) {
                
                Map.Entry<String, Integer> entry = itr.next();
                i = entry.getValue();
                   if(mapOfOccurences.containsKey(i)) {
                     int j = (int) mapOfOccurences.get(i);
                     mapOfOccurences.put(i, j+1);
                 } else {mapOfOccurences.put(i, 1);}
                 if (mapOfOccurences.size()>2) {
                     return "NO";
                 }
            }
             
             if (mapOfOccurences.size()>2) {
                 return "NO";
             } else if (mapOfOccurences.size()<=1) {
                 return "YES";
             } 
             
             int qNotOne =0;
             int qOne =0;
             Iterator<Map.Entry<Integer, Integer>> itr2 = mapOfOccurences.entrySet().iterator();

             i=0;
             while(itr2.hasNext()) {
                 
                 Map.Entry<Integer, Integer> entry = itr2.next();
                 i = entry.getValue();
                if(i!=1) {
                    
                 qNotOne=(int) entry.getKey();
             } else  if (i==1) {
                 qOne= (int) entry.getKey();
             
             }
                
             }

            if (qOne==0) {
                return "NO";
            } else
                if (qOne-1==qNotOne||qOne-1==0) {
                    return "YES";
                } else return "NO";

            

            }

        }

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
