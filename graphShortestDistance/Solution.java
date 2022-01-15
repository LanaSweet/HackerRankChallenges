package graphShortestDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	 static class Graph {
		List<ArrayList<Integer>> adjacencyList;
		int size;
		
		Graph(int size){
			this.size = size;
			adjacencyList = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<size;i++) {
				adjacencyList.add(new ArrayList<Integer>());
			}
		}
		
		void addEdge(int edge1, int edge2) {
			adjacencyList.get(edge1-1).add(edge2-1);
			adjacencyList.get(edge2-1).add(edge1-1);
		}
		public int[] minDistance(int startNode) {
			int[] distances = new int[size];
			Arrays.fill(distances, -1);
			distances[startNode-1]=0;
			
			Queue<Integer> nextNodes = new LinkedList<>();
			nextNodes.add(startNode-1);
			
			while(!nextNodes.isEmpty()) {
				int cur= nextNodes.poll();
				for (int node: adjacencyList.get(cur)) {
				if(distances[node]==-1) {
					distances[node] = distances[cur]+6;
					nextNodes.add(node);
				}
				}
			}
			
			return distances;
		}
		
		void printDistances(int[] distances, int startNode){
			for(int i=0;i<size;i++) {
				if(distances[i]!=0) {
					System.out.print(distances[i]+ " ");
				}
				System.out.println();
			}
		}
		
	}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();
        for (int i=0;i<q;i++){
            String numOfEdgesAndNodes = sc.nextLine();

            String[] dimensionsSplit = numOfEdgesAndNodes.split("\\s");

          int n =  Integer.valueOf(dimensionsSplit[0].trim());
          int m =  Integer.valueOf(dimensionsSplit[1].trim());
          
          Graph graph= new Graph(n);
          for (int j=0;j<m;j++) {
        	  String strEdge = sc.nextLine();
        	  String[] edgeSplit = strEdge.split("\\s");
              int edge1 =  Integer.valueOf(edgeSplit[0].trim());
              int edge2 =  Integer.valueOf(edgeSplit[1].trim());
             graph.addEdge(edge1, edge2);
       	  
          }
          String startNodeStr = sc.nextLine();
          int startNode = Integer.valueOf(startNodeStr);
          graph.printDistances(graph.minDistance(startNode), startNode);
            
        }
        sc.close();
    }
        
}
