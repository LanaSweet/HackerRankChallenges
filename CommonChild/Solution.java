package com.gromivchuk.HackerRankCommonChild;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class Solution {



	/*
	 * Complete the 'commonChild' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts following parameters:
	 *  1. STRING s1-
	 *  2. STRING s2
	 */


	public static int commonChild(String s1, String s2) {

		int n = s1.length();
		int m = s2.length();
		String maxS="";
		String minS="";
		int max;
		int min;
		if(n>m) {
			max=n;
			maxS=s1;
			min=m;
			minS=s2;
		} else {
			max=m;
			maxS=s2;
			min=n;
			minS=s1;
		}
		char[] maxString=new char[max+1];
		char[] minString=new char[min+1];
		int[][] matrix = new int[min+1][max+1];
		maxString[0]=minString[0]=0;
		matrix[0][0]=0;
		for(int i=1;i<min+1;i++) {
			matrix[i][0]=0;
			minString[i]=minS.charAt(i-1);
			for(int j=1;j<max+1;j++) {
				if(i==1) {
					matrix[0][j]=0;
					maxString[j]=maxS.charAt(j-1);
				}
				if(maxString[j]==minString[i]) {

					matrix[i][j]= matrix[i-1][j-1]+1;

				} else {
					matrix[i][j]=Math.max(matrix[i-1][j], matrix[i][j-1]);
				}	    			
			}	    		
		}	    	
		return matrix[min][max];
	}





	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


		String s1 = bufferedReader.readLine();

		String s2 = bufferedReader.readLine();



		int result = Solution.commonChild(s1, s2);
		System.out.println(result);
		bufferedReader.close();

	}
}
