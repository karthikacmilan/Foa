import java.util.Scanner;

public class Assignment1 {
	public static void console_input()
	{
		Scanner a = new Scanner(System.in);
		int m,n;
		//System.out.println();
		n = a.nextInt(); //cities
		m = a.nextInt();
		for(int i = 0 ; i < m ; i ++)
		{
			//doubt
		}
		
	}
	public static void input_file()
	{
		
	}
	
	public static void main(String args[]){
		int choice; 
		
		System.out.println("Enter You Choice : 1. Console 2. Input File");
		Scanner a = new Scanner(System.in);
		choice = a.nextInt();
		switch(choice)
		{
		case 1: 
			console_input();
			break;
		case 2: 
			input_file();
			break;
		default: 
			System.out.println("Invalid");
			
		}
	}
}
