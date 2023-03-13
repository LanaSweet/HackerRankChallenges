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
   
  	public static void preOrder( Node root ) {
      
    	if( root == null)
        	return;
      
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
     
    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

	public static Node insert(Node root,int data) {
        if(root==null){
            return new Node(data);
        }
        if (root.data>data){
            aux(root.left, root, true, data);
        } else{
            aux(root.right, root, false, data);
        }
        return root;
    	
    }
public static void aux(Node root, Node prev, boolean left, int data){
    if(root==null){
            Node inserted = new Node(data);
            if(left){
                prev.left=inserted;
            } else {
                 prev.right = inserted;
            }
            return;
        }
        if (root.data>data){
            aux(root.left,root,true, data);
        } else{
            aux(root.right,root, false,data);
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
      	preOrder(root);
    }	
}