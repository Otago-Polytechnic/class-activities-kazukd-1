/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1
* Kazuhisa Kondo (1000100753)    
*********************************************************/
import java.util.Scanner;

public class Problem2 {
	
	public static void main(String[] args) {
		int investmentAmount;
		double rate;
		int years;
		double futureValue;
		String nl= System.lineSeparator();
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter investmentAmount:");
		investmentAmount = input.nextInt();
		System.out.print("Enter annualInterestRate(%):");
		rate = input.nextDouble();
		System.out.print("Enter number of years:");
		years = input.nextInt();
		
		futureValue= investmentAmount * Math.pow((1 + rate/100),years) ;
		futureValue = Math.floor(futureValue*100)/100;
		
		System.out.println(nl + "Future balance of account is " + futureValue);
		
	}

}