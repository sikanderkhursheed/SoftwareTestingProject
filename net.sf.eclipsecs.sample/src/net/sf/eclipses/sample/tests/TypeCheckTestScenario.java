package net.sf.eclipses.sample.tests;

public class TypeCheckTestScenario{

	public static void main(String[] args) {
		
		 int number = 1;
		 
	      if (number > 0) {
	         System.out.println("Number is positive.");
	      }
	      else if (number < 0) {
	         System.out.println("Number is negative.");
	      }
	      else {
	         System.out.println("Number is 0.");
	      } 
	
	switch (number) {
	case 1:
		 System.out.println("Number is positive.");
		break;
	case 2:
		 System.out.println("Number is negative.");
		break;
	case 3:
        System.out.println("Number is 0.");
		break;
				
	}
		
	}
}
