import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void topView(Node root) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> depthMap = new HashMap<>();
        map.put(0, root.data);
        depthMap.put(0, 0);
        List<Integer> minMax = new ArrayList<>();
        minMax.add(0);
        minMax.add(0);
        topViewAux(root.left, map, depthMap, -1, 1, minMax);
        topViewAux(root.right, map, depthMap, 1,1,minMax);
        int min = minMax.get(0);
         int max = minMax.get(1);
         for(int i=min;i<=max;i++){
             System.out.print(map.get(i) + " ");
         }
    }
public static void topViewAux(Node root, Map<Integer, Integer> map, Map<Integer, Integer> depthMap,int level, int depth, List<Integer> minMax) {
        if(root==null){
            return;
        }
        if(map.get(level)==null){
            map.put(level,root.data);
            depthMap.put(level, depth);
            if(level<minMax.get(0)){
                minMax.set(0,level);
            }
            if(level>minMax.get(1)){
                minMax.set(1,level);
            }
        } else if (depthMap.get(level)!=null&&depthMap.get(level)>depth){
            map.put(level,root.data);
            depthMap.put(level,depth);
        }
        topViewAux(root.left, map, depthMap, level-1, depth +1, minMax);
        topViewAux(root.right, map, depthMap,level+1, depth +1, minMax);
   
    }
	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }	
}