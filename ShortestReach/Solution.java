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
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        Map<Integer,List<Integer>> edgesMapped = getEdgesMapped(edges);

        System.out.println(edgesMapped);
        boolean[] visited = new boolean[n];
        Queue<Integer> nodes = new LinkedList<>();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        nodes.add(s-1);
        distances[s-1] =0;
        int totalDistance = 0;
        while(!nodes.isEmpty()){
            int cur = nodes.remove();

            List<Integer> connectedWithCur = edgesMapped.get(cur);
            if(connectedWithCur!=null){
                for(Integer node: connectedWithCur){
                    if(!visited[node] && (distances[cur] + 6) <distances[node]){
                        distances[node] =distances[cur] + 6;
                        nodes.add(node);
                    }
                }                 
            }
            totalDistance = totalDistance +6;
            visited[cur]= true;

        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<n;i++){
            if(i==s-1) {
                continue;
            }
            if(distances[i]==Integer.MAX_VALUE){
                res.add(-1);
            } else 
            {
                res.add(distances[i]);
            }

        }
        return res;

    }

 public static Map<Integer,List<Integer>> getEdgesMapped(List<List<Integer>> edges){
     Map<Integer,List<Integer>> res = new HashMap<>();
     for(List<Integer> edge: edges){
         int node1= edge.get(0)-1;
         int node2= edge.get(1)-1;
         List<Integer> node1Edges= res.getOrDefault(node1, new ArrayList<>());
         node1Edges.add(node2);
         res.put(node1, node1Edges);
         List<Integer> node2Edges= res.getOrDefault(node2, new ArrayList<>());
         node2Edges.add(node1);
         res.put(node2, node2Edges);
     }
     return res;
 }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println(q);
        IntStream.range(0, q).forEach(qItr -> {
            try {
                
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = Result.bfs(n, m, edges, s);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
