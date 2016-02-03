import java.awt.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
		Map<List,Integer> hm = new HashMap<List, Integer>();
		String file = "d:/input.txt";
		String input = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	input = input + line + " " ;
		    }		
		    
		    String[] inp = input.split(" ");
		    int n = Integer.parseInt(inp[0]); //n
		    int m = Integer.parseInt(inp[1]); //m
		    String[] inps = null;
		    List al=new List();
		    for(int i = 2 ; i < m+2 ; i ++ )
		    {
		    	inps = inp[i].split(":");
		    	
		    	al.add(inps[0]);
		    	al.add(inps[1]);
		    	int value = Integer.parseInt(inps[2]);
		    	hm.put(al, value); //hashmap of cities and distance
		    	value = 0;
		    	
		    	
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
