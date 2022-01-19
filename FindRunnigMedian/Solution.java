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
     * Complete the 'runningMedian' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static List<Double> runningMedian(List<Integer> a) {
           Queue<Integer> halfDown = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> halfUp = new PriorityQueue<>();

        int n=a.size();
        List<Double> result = new ArrayList<>();
        for(int j=1; j<=n; j++){
            int i = a.get(j-1);
            BigDecimal bd = new BigDecimal(0);
            if(halfUp.isEmpty()) {
                halfUp.add(i);
                bd = new BigDecimal(i);
            } else 
                if (halfUp.size()==halfDown.size()) {
            
                if(i>halfDown.peek()) {
                    halfUp.add(i);
                    
                } else {
                    
                    halfUp.add(halfDown.remove());
                    halfDown.add(i);
                    
                }
                bd = new BigDecimal(halfUp.peek());
            } else if (halfUp.size()>halfDown.size()) {
                if (i<halfUp.peek()) {
                    halfDown.add(i);
                    
                } else {
                    halfDown.add(halfUp.remove());
                    halfUp.add(i);
                    
                }
                bd = new BigDecimal(halfDown.peek());
                bd = bd.add(new BigDecimal(halfUp.peek()));
                bd = bd.divide(new BigDecimal(2));
            
            }
            
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            
            result.add(bd.doubleValue());
        }
        return result;
        }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Double> result = Result.runningMedian(a);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
