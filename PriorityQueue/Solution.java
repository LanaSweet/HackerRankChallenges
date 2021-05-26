import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

        class Student implements Comparable<Student>{
            private int id;
            private String name;
            private double cpga;
            
            public Student(int id, String name, double cpga) {
                
                this.id = id;
                this.name = name;
                this.cpga = cpga;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getCpga() {
                return cpga;
            }

            public void setCpga(double cpga) {
                this.cpga = cpga;
            }
            
            public int compareTo(Student s){
                if (s.cpga==this.cpga){
                    return this.name.compareTo(s.name);
                } else if( this.cpga<s.cpga) return 1; else return -1;
            
            
        }

			@Override
			public String toString() {
				return "Student [id=" + id + ", name=" + name + ", cpga=" + cpga + "]";
			}
            
        }

         class Priorities{
             List<Student> getStudents(List<String> events) {
                 Queue<Student> queue = new PriorityQueue<Student>();
                 for (String event:events){
                    
                    
                           String[] splitArray = event.split("\\s+");
                        
                     switch(splitArray[0]){
                         case "ENTER":
                         Student student = new Student(Integer.parseInt(splitArray[3]),splitArray[1],Double.parseDouble(splitArray[2]));
                         queue.add(student);
                         
                         break;
                         case "SERVED":
                        System.out.println( queue.poll());
                         break;
                     }
                 }
                 List<Student> studentList = 
                 new ArrayList();
                 int size = queue.size();
                 for (int i=0; i<size;i++) {
                	 studentList.add(queue.poll());
                 }
                 return studentList;
             }
         }

public class Solution{
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}