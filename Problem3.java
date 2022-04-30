/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1
* Kazuhisa Kondo (1000100753)    
*********************************************************/
import java.util.Scanner;

public class Problem3 {
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
		int mark;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter mark:");
		mark = input.nextInt();
		System.out.print(printGrade(mark));
		
	}
}