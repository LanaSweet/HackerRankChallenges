package com.gromivchuk.CatsAndShoppingCenters;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Node{
    int center;
    int dist;
    int fish;
    
    public Node(int center, int dist, int fish){
        this.center = center;
        this.dist = dist;
        this.fish = fish;
    }
}
class Result {

    /*
     * Complete the 'shop' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. STRING_ARRAY centers
     *  4. 2D_INTEGER_ARRAY roads
     */

    public static int shop(int n, int k, List<String> centers, List<List<Integer>> roads) {
    Integer[] fishCompactionPerCenter = getFishCompactionPerCenter(centers,k);
    Map<Integer, Map<Integer, Integer>> distances = getDistancesPerCenter(roads);
    
    Integer[][] adjMatrix = new Integer[n][1<<k];
    for(Integer[] a: adjMatrix){
        Arrays.fill(a, Integer.MAX_VALUE);
    }   
     
    boolean[][] visited = new boolean[n][1<<k];
        
    Queue<Node> unvisited = new PriorityQueue<>(Comparator.comparingInt(x -> x.dist));
    unvisited.add(new Node(0,0,fishCompactionPerCenter[0]));
    
    while( !unvisited.isEmpty()){
        Node current = unvisited.poll();
        int currentCenter = current.center;
        int currentDistance = current.dist;
        int currentFish = current.fish;
        
        Map<Integer, Integer> neighbours = distances.get(currentCenter);
        neighbours.forEach((city, distance) -> {
            int totalDist = distance + currentDistance;
            int totalFish = currentFish | fishCompactionPerCenter[city];
            if(!visited[city][totalFish] && (totalDist < adjMatrix[city][totalFish])) {
                unvisited.add(new Node(city, totalDist, totalFish));
                adjMatrix [city][totalFish] = totalDist;
            }            
        });
        visited[currentCenter][currentFish] = true;
    }
    
    int min = Integer.MAX_VALUE;
    
    int endCity = adjMatrix.length -1;
    int endFishCompaction = adjMatrix[endCity].length;
    for(int i=0; i< endFishCompaction; i++){
        for(int j=0;j <endFishCompaction; j++){
            if((i|j) == ((1<<k)-1)){
                min = Math.min(Math.max(adjMatrix[endCity][i],adjMatrix[endCity][j]), min);
            }
        }
    }
        return min;
    }

 public static Map<Integer, Map<Integer, Integer>> getDistancesPerCenter(List<List<Integer>> roads){
     Map<Integer, Map<Integer, Integer>> distances = new HashMap<>();
     for(List<Integer> road: roads){
         int startCity = road.get(0)-1;
         int endCity = road.get(1)-1;
         int edge = road.get(2);
         Map<Integer,Integer> startCityConnections = distances.getOrDefault(startCity, new HashMap<>()); 
         Map<Integer,Integer> endCityConnections = distances.getOrDefault(endCity, new HashMap<>()); 
         startCityConnections.put(endCity, edge);
         endCityConnections.put(startCity, edge);
         distances.put(startCity, startCityConnections);
         distances.put(endCity, endCityConnections);
     }
     return distances;
 }
    public static Integer[] getFishCompactionPerCenter(List<String> centers, int k){
        Integer[] res = new Integer[centers.size()];
        for(int i=0;i<centers.size();i++){
            String[] values = centers.get(i).split("\\s");
            int fishCompaction = 0;
            for(String value: List.of(values).subList(1, values.length)){
                fishCompaction |= 1<<(Integer.parseInt(value)-1);
            }
            res[i] = fishCompaction;
        }
        return res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<String> centers = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<List<Integer>> roads = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                roads.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.shop(n, k, centers, roads);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
