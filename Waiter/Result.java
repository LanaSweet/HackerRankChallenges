package com.waiter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Result {

    /*
     * Complete the 'waiter' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY number
     *  2. INTEGER q
     */

    public static List<Integer> waiter(List<Integer> number, int q) {
    int[] primes = findPrimeNumbers(q);
    List<Integer> result = new ArrayList<>();
    boolean[] added = new boolean[number.size()];
    Arrays.fill(added, false);

    Deque<Integer> a = new ArrayDeque<Integer>(number); 
    Deque<Integer> b; 
    for(int p:primes){
        Deque<Integer> aNext = new ArrayDeque<>();
        b= new ArrayDeque<>();
        while(!a.isEmpty()){
            int i = a.pollFirst();
            if(i%p==0 ){
                b.addFirst(i);
            } else{
                aNext.addFirst(i);
            }            
        }
        while(!b.isEmpty()){
            result.add(b.pollLast());
        }
        a=aNext;
    }
        while(!a.isEmpty()){
            result.add(a.pollLast());
        
        }
        return result;
    }
    
    public static int[] findPrimeNumbers(int k) {
        int n= 14;
        if(k>6) {
            n= (int) ((int) k * (Math.log(k) + Math.log(Math.log(k)))); 
        }

// Create a boolean array to mark the numbers from 2 to n
boolean[] isPrime = new boolean[n + 1];
// Initialize all the elements to true
Arrays.fill(isPrime, true);
// Loop from 2 to the square root of n
for (int i = 2; i * i <= n; i++) {
// If i is marked as prime
if (isPrime[i]) {
// Mark all the multiples of i as composite
for (int j = i * i; j <= n; j += i) {
isPrime[j] = false;
}
}
}
// Create an ArrayList to store the prime numbers
ArrayList<Integer> primes = new ArrayList<>();
// Loop from 2 to n
for (int i = 2; i <= n; i++) {
// If i is marked as prime, add it to the list
if (isPrime[i]&&primes.size()<k) {
primes.add(i);
}
}
// Convert the ArrayList to an int array
int[] result = new int[primes.size()];
for (int i = 0; i < result.length; i++) {
result[i] = primes.get(i);
}
// Return the int array
return result;
}
}



