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

    /*
     * Complete the 'journeyToMoon' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        List<List<Integer>> countries = getGroupsByCountry(astronaut,n);
        
        int singleAstronautCountries = countries.get(countries.size()-1).size();
        
        List<Integer> sizes = getSizesOfCountriesGroups(countries);
        
        long result =0;
        for(int i=0; i<sizes.size()-1;i++){
            for(int j=i+1; j<sizes.size();j++){
                result= result + sizes.get(i)*sizes.get(j);
        }
        }
        
        //Calculation of the remainder consisting of countries with one member only.
        //This is the sum of the number from 0 to lastItemSize-1;
        result = result+(long)(singleAstronautCountries-1)*singleAstronautCountries/2;
       
       return result;
    }
    
    //Method returns list of sizes of each group by country.
    public static List<Integer> getSizesOfCountriesGroups(List<List<Integer>> countries){
        List<Integer> result = new ArrayList<>();
        for(List<Integer> country: countries){
            result.add(country.size());
        }        
        return result;
    }
    
    public static List<List<Integer>> getGroupsByCountry(List<List<Integer>> astronaut, int n){
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> controlSet = new HashSet<>();
        for(List<Integer> pair:astronaut){
            if(!controlSet.contains(pair.get(0))&& !controlSet.contains(pair.get(1))){
                List<Integer> country = new ArrayList<>();
                country.add(pair.get(0));
                country.add(pair.get(1));
                result.add(country);
                controlSet.add(pair.get(0));
                controlSet.add(pair.get(1));
            } else {
                if(controlSet.contains(pair.get(0))&&controlSet.contains(pair.get(1))) {
                    int countryIdx1 = -1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).contains(pair.get(0))){
                            countryIdx1 = i;
                            break;
                        }
                    }
                    int countryIdx2 = -1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).contains(pair.get(1))){
                            countryIdx2 = i;
                            break;
                        }
                    }
                    if(countryIdx1!=countryIdx2) {
                        List<Integer> country2 = result.get(countryIdx2);
                        result.get(countryIdx1).addAll(country2);
                        result.remove(countryIdx2);
                    }
              continue;
            }else
                if(controlSet.contains(pair.get(0))){
                    int countryIdx = -1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).contains(pair.get(0))){
                            countryIdx = i;
                            break;
                        }
                    }
                    
                    result.get(countryIdx).add(pair.get(1));
                    controlSet.add(pair.get(1));
                    continue;
                } else{
                    int countryIdx = -1;
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).contains(pair.get(1))){
                            countryIdx = i;
                            break;
                        }
                    }
                    result.get(countryIdx).add(pair.get(0));
                    
                    controlSet.add(pair.get(0));
                    
                }
            }
        }
        List<Integer> country = new ArrayList<>();
        
        //In the last group adding all the astronaut the are the only astronaut from their country.
        for(int i=0;i<n;i++){
            if(controlSet.add(i)){               
                country.add(1);
              
            }
        }
        
        result.add(country);
        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int p = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> astronaut = new ArrayList<>();

        IntStream.range(0, p).forEach(i -> {
            try {
                astronaut.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
