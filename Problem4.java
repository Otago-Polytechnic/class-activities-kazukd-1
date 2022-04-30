/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1
* Kazuhisa Kondo (1000100753)    
*********************************************************/
import java.util.Scanner;

public class Problem4 {
	
		public static String printGrade(int mark) {
		    String grades = "D C-C C+B-B B+A-A A+";
			String marks = "00505560657075808590";
			String grade="";
			
			if(mark<0 || mark >100) return "Invalid mark!";
			
			int i = 1;
			while(i <= 10) {
				if(mark >= Integer.parseInt(marks.substring((i-1)*2,(i*2))))
					grade=grades.substring((i-1)*2,i*2);
				i++;
		    }
			
		    return grade;
		}
		
	public static void main(String[] args) {
		int courses;
		int mark;
		int fail=0;
		
		int min=100;
		int max=0;
		int total=0;
		int average=0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the number of courses: ");
		courses = input.nextInt();
		int [] marks = new int [courses];
		int i=0;
		while(i<courses) {
			System.out.print("Enter the mark (0-100) for course"+ (i+1) + ": ");
			mark = input.nextInt();
			if(mark<0 || mark>100) System.out.println("The mark is invalid! try again:");
			else {
				if(mark<50) fail++;
				if(mark > max) max=mark;
				if(mark < min) min=mark;
				total = total + mark;
				
				marks[i]=mark;
				i++;
			}
			
		}
		
		average= total / courses;
		
		System.out.println("The maximum mark is: "+max);
		System.out.println("The minimum mark is: "+min);
		System.out.println("The average mark is: "+average);
		System.out.println("The average grade is: "+printGrade(average));
		System.out.println("The number of failed courses is: "+fail);
		

	}

}
