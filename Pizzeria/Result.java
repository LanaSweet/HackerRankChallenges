package com.pizzeria;

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

	public static class ComparatorHandMade implements Comparator<List<Integer>>{
		@Override
		public int compare(List<Integer> list1, List<Integer> list2){
			return list1.get(1).compareTo(list2.get(1));
		}
	}
	/*
	 * Complete the 'minimumAverage' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts 2D_INTEGER_ARRAY customers as parameter.
	 */

	public static long minimumAverage(List<List<Integer>> customers) {
		int n = customers.size();
		
		customers = customers.stream().sorted((o1,o2)-> o1.get(0)-o2.get(0)).collect(Collectors.toList());
		long totalWaitTime = customers.get(0).get(0);
		long waitTime = totalWaitTime;

		Queue<List<Integer>> queue = new PriorityQueue<>(new ComparatorHandMade());

		queue.add(customers.remove(0));
		
		while(!queue.isEmpty()) {
			List<Integer> next = queue.poll();
			waitTime = waitTime + next.get(1);
			long currentWaitTime = waitTime - next.get(0);
			totalWaitTime = totalWaitTime + currentWaitTime;
			
			
			while(!customers.isEmpty() 
					&& customers.get(0).get(0)<=waitTime) {
				queue.add(customers.remove(0));
			}
			if(!customers.isEmpty()&&queue.isEmpty()) {
				if (waitTime<customers.get(0).get(0)) {
					waitTime = waitTime + (customers.get(0).get(0) - waitTime);
				}
				queue.add(customers.remove(0));
			}
		}


		return totalWaitTime/n;

	}


}


