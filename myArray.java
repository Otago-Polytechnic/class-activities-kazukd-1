/********************************************************
* IX608001 Intermediate Application Development Concepts
*                 Class Activity 1 Problem5
* Kazuhisa Kondo (1000100753)    
*********************************************************/
import java.util.*;
/**
 * Print (forward, reverse), calculate average, 
 * search, find frequency number for array elements
 * 
 * @author Kazuhisa Kondo 
 * @version 1.0, 29 Apr 2022
 */
public class myArray
{
int array[] = new int[10];
//Constructor
public myArray(int inputArray[])
{
   this.array = inputArray;
}
//Printing array elements
public void printArray()
{
      for(Integer i: array)
         System.out.print(i + "  "); 
      System.out.println("  "); 

}
//Searching X in the array
public boolean search(int X)
{
	for(Integer i: array)
		if(i==X) return true;
	return false;
}

//Calculating the average of array elements
public double average()
{
	int total=0;
	for(Integer i: array)
		total+=i;
	return ((double)(total) / array.length);
}

// Reversing the order of array elements
public void reverse()
{
	int len = array.length;
    int array_tmp;
    for (int i = 0; i < len / 2; i++) {
        array_tmp = array[i];
        array[i] = array[len - 1 - i];
        array[len - 1 - i] = array_tmp;
    }
}
//Finding the most frequent element in the array
public int mode()
{
	int frequentNumber =0;
	int frequentCount =0;
	
	for(int i=0; i < array.length;i++) {
		int count=0;
		for(Integer j: array)
			if(array[i]==j) count++;
		if(count>frequentCount) {
			frequentCount = count;
			frequentNumber = array[i];
		}
	}
	return frequentNumber;
}

public static void main(String args[])
{
   myArray test =  new myArray(new int[]{3,4,5,2,1,2,9,11,2,6});
   System.out.print("Array elements: ");
   test.printArray();
   if (test.search(10)) 
      System.out.println("10 exists in the array");
  else 
      System.out.println("10 does not exist in the array");
 if (test.search(5)) 
      System.out.println("5 exists in the array");
  else 
      System.out.println("5 does not exist in the array");
   System.out.println("The average is:"+test.average());
   System.out.println("Mode is:"+test.mode());
   test.reverse();
   System.out.println("Array in reverse order:");
   test.printArray();
   
   

}
}