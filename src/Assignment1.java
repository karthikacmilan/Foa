import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	public static void input_file() throws FileNotFoundException, IOException
	{
		String file = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	int foo = Integer.parseInt(line);
		    }
		    
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException{
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
