import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;



public class Solution {

    public static void main(String[] args) {
         
 Scanner sc = new Scanner(System.in);   
 int n = sc.nextInt();
 int[] a = new int[n];
 int sum =0;
 List<Integer> aList = new ArrayList<>();
 Map<Integer, Integer> frequency = new HashMap<>();
 for(int i=0;i<n;i++){
     a[i] = sc.nextInt();
     sum+=a[i];
     aList.add(a[i]);
     if(frequency.containsKey(a[i])){
         int k= frequency.get(a[i]);
         frequency.put(a[i], k+1);
     }
     else frequency.put(a[i],1);
 }
 BigDecimal avg = new BigDecimal(sum);
 avg = avg.divide(new BigDecimal(n));
 avg = avg.setScale(1, RoundingMode.HALF_DOWN);
  System.out.println(avg);
 
 
 Collections.sort(aList);
 
 BigDecimal median = new BigDecimal(aList.get((n/2)-1)+aList.get(n/2));
 median= median.divide(new BigDecimal(2));
 median = median.setScale(1, RoundingMode.HALF_DOWN);
 System.out.println(median);
 
 int max = Collections.max(frequency.values());
 List<Integer> maxFrecuency = new ArrayList<>();
 for (int i :frequency.keySet()) {
     if(frequency.get(i)==max) {
         maxFrecuency.add(i);
     }
 }
 System.out.println(Collections.min(maxFrecuency));
  }

}