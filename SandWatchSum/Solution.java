import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {



	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int[][] arr = new int[6][6];

		for (int i = 0; i < 6; i++) {
			String[] arrRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 6; j++) {
				int arrItem = Integer.parseInt(arrRowItems[j]);
				arr[i][j] = arrItem;
			}
		}
		int max = Integer.MIN_VALUE;
		int sum;
		for (int i=0; i<=3;i++) {
			for (int j=0; j<=3; j++) {
				sum = 0;
				for (int k=i; k<i+3; k++) {
					for(int l=j;l<j+3;l++) {
						if ((l==j+1) && (k==i+1)){
							sum = sum+arr[k][l];
						} else if (k!=i+1) {sum = sum+arr[k][l];}
					}

				}
				if (sum>max){
					max = sum;
				}
			}
		}


		scanner.close();
		System.out.println(max);
	}
}