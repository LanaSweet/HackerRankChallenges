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
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY ladders
     *  2. 2D_INTEGER_ARRAY snakes
     */

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        List<Integer>[] rollsMap = new LinkedList[101];
        Map<Integer,Integer> ladderMap = getMap(ladders);
        Map<Integer,Integer> snakeMap = getMap(snakes);
        
        for(int i=1;i<100;i++){
            for(int j=1;j<=6;j++){
                
                if(i+j<=100){
                    int nextStep = i+j;
                if(ladderMap.containsKey(nextStep)){
                    nextStep = ladderMap.get(nextStep);
                }
                if(snakeMap.containsKey(nextStep)){
                    nextStep = snakeMap.get(nextStep);
                }
                if(rollsMap[i]==null){
                    
                    rollsMap[i]= new LinkedList<>();
                }
                rollsMap[i].add(nextStep);
                    
                }
                }
            }
        
        rollsMap[100]= new LinkedList<>();
    return findRollsMin(rollsMap);

    }

public static int findRollsMin(List<Integer>[] rollsMap){
    int[] results = new int[101];
    for(int i=0;i<101;i++){
        results[i]= Integer.MAX_VALUE;
    }
    results[1]=0;
    
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    while(!queue.isEmpty()){
       
        int nextStep = queue.poll();
         System.out.println(nextStep);
        for(int i:rollsMap[nextStep]){
            if(results[nextStep]+1<results[i]){
                results[i]=results[nextStep]+1;
                queue.add(i);
            }
        }
    }
    return results[100]==Integer.MAX_VALUE?-1:results[100];
}


public static Map<Integer,Integer> getMap(List<List<Integer>> table){
    Map<Integer,Integer> map = new HashMap<>();
    for(List<Integer> row: table){
        map.put(row.get(0), row.get(1));
    }
    return map;
}
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> ladders = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        ladders.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int m = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> snakes = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        snakes.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.quickestWayUp(ladders, snakes);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
    