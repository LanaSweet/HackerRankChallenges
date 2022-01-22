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

class Node {
    Node left;
    Node right;
    int data;
    int level;

    Node(int data, int level) {
        this.data = data;
        this.level = level;
        left = null;
        right = null;

    }

    public static void swapp(Node root, int k) {
        Queue<Node> q = new LinkedList<>();

        if (root!=null){
            q.add(root);
            while(!q.isEmpty()){
                Node cur = q.poll();
                if(cur.level%k==0) {
                    Node temp = cur.left;
                    cur.left=cur.right;
                    cur.right=temp;
                }

                if(cur.left!=null){
                    q.add(cur.left);

                }
                if(cur.right!=null){
                    q.add(cur.right);
                }

            }
        } 
    }

    static void inorder(Node node, List<Integer> result) {
           if (node != null) {
                inorder(node.left, result);            
                result.add(node.data);
                inorder(node.right, result);
        }
    }

    public static List<Integer> inOrderTraversal(Node root){
        List<Integer> result = new ArrayList<Integer>();
        Stack<Node> stackOfNodes = new Stack<>();
        if(root!=null) {
            if(root.left!=null) {
                inorder(root.left, result);
            }            
           result.add(1);
           if(root.right!=null) {
                inorder(root.right, result);
                
             }           
        }
        return result;
    }
}
class Result {
    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int size = indexes.size();
        Node root;
        if(size==0) {
            return result;
        } else
            root = new Node(1,1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        for(List<Integer> list:indexes) {
            if(!queue.isEmpty()) {
                Node parent = queue.poll();
                if(list.get(0)>0) {
                    queue.add(parent.left  = new Node(list.get(0),parent.level+1));
                }
                if(list.get(1)>0) {
                    queue.add(parent.right = new Node(list.get(1),parent.level+1));
                }
            }
        }

        List<Integer> list;
        for(int q:queries) {
            list = new ArrayList<Integer>();
            Node.swapp(root, q);
            list = Node.inOrderTraversal(root);
            result.add(list);
        }
        return result;


    }

}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
            .map(
                r -> r.stream()
                    .map(Object::toString)
                    .collect(joining(" "))
            )
            .map(r -> r + "\n")
            .collect(toList())
            .forEach(e -> {
                try {
                    bufferedWriter.write(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
