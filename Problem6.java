/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1
* Kazuhisa Kondo (1000100753)    
*********************************************************/

import java.util.Scanner;

public class Problem6 {
	public static void main(String[] args) {
		String str;
		String firstName="", lastName="";
		String newLastName="";
		String newFirstName="";
		
		int len=0;
		int flag=0;
		int rNum=0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter your name and last name: ");
		str = input.nextLine();
		len = str.length();
		System.out.println("The length of string is: "+ len);
	
		/* Separate first name and last name */
	    for(int i=0; i<len; i++) {
	    	if(str.charAt(i) == ' ') flag=1;
	    	else {
	    		if(flag==0) firstName=firstName+str.charAt(i);
	    		else lastName=lastName + str.charAt(i);
	    	}
	    }
	    
	    System.out.println("Name: "+ firstName);
	    System.out.println("Last Name: "+ lastName);
	    
	    System.out.println("Initials: "+ firstName.charAt(0) + lastName.charAt(0));
	    System.out.println("Uppercase: "+ firstName.toUpperCase()+" " + lastName.toUpperCase() );
	   
	    
	    /* Delete one character randomly */
	    len = lastName.length();
	    rNum=((int) (Math.random()*(len-1)));
	    for(int i=0; i<len;i++) {
	    	if(i != rNum) newLastName = newLastName + lastName.charAt(i);
	    }
	    System.out.println("Last Name after deleting a character: "+ newLastName);
	    
	    /* Delete a repeating character */
	    len = firstName.length();
	    for(int i=0; i<len;i++) {
	    	if(i==len-1) newFirstName+=firstName.charAt(i);
	    	else if(firstName.charAt(i)!=firstName.charAt(i+1)) newFirstName+=firstName.charAt(i);
	    
	    }
	    System.out.println("Name after removing dupulicates: "+ newFirstName);
	    
}
}