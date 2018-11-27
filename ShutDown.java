import java.util.*;
import java.io.*;
class ShutDown
{
 	public static void main(String args[]) throws Exception
 	{
		int i;
		System.out.println("Enter 1 for shutdown");
		System.out.println("Enter 2 for restart");
		System.out.println("Enter 3 for hibernate");
		System.out.println("Enter 4 to shutdown after some time");
		System.out.println("Enter 5 to restart after some time");
		System.out.println("Enter your choice");
		Scanner sc = new Scanner(System.in);
		i = sc.nextInt();
		// Create Runtime object
 		Runtime r=Runtime.getRuntime();
		switch(i)
		{
			case 1:
				// Shutdown system
 				r.exec("shutdown -s");
				break;
			case 2:
				 // Restart system
 				r.exec("shutdown -r");
				break;
			case 3:
				// Shutdown after specific time (here 60 seconds)
 				r.exec("shutdown -s -t 60");
				break;
			case 4:
				// Restart after specific time (here 60 seconds)
		 		r.exec("shutdown -r -t 60");
				break;
			case 5:
				// Hibernate system
				r.exec("shutdown -h");
				break;
			default:
				System.out.println("you have entered wrong choice");
				break;
		}

	}
}