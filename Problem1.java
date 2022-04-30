/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1
* Kazuhisa Kondo (1000100753)    
*********************************************************/
import java.util.Scanner;

public class Problem1 {
	
	public static void main(String[] args) {
		
		int a,b,c;
		double x1,x2;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Solve aX2+bX+c = 0 ");
		System.out.print("Enter a: ");
		a = input.nextInt();
		System.out.print("Enter b: ");
		b = input.nextInt();
		System.out.print("Enter c: ");
		c = input.nextInt();
		
		x1 =(-b +  Math.sqrt(b*b-4*a*c))/ (2*a);
		x2 = (-b -  Math.sqrt(b*b-4*a*c))/ (2*a);
		
		System.out.println("Solve "+a+"X2+ " + b+"X+ "+c+" = 0 ");
		System.out.println("X1 is : " + x1);
		System.out.println("X2 is : " + x2);
		
		
		
	}

}