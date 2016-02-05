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
import java.io.InputStreamReader;

public class Assignment1 {
	
	public static int citiesCount;
	public static int linksCount;
	public static int newLinksCount;
	
	
	//TODO
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
		//Map<List,Integer> hm = new HashMap<List, Integer>();
		
		String file = "sample.txt";
		int allLinks[][] = null;
		int allNewLinks[][] = null;
		
		//Read the text file name from user
		System.out.println("Enter test file name (e.g. sample.txt) : ");
		BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        //file = fileNameReader.readLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String tempString;
		    String[] inps = null;
		    
		    //Read number of cities
		    tempString = br.readLine();
		    citiesCount = Integer.parseInt(tempString);
		    //System.out.println(citiesCount);
		    
		    //Read number of links
		    tempString = br.readLine();
		    linksCount = Integer.parseInt(tempString);
		    //System.out.println(linksCount);

		    allLinks = new int[linksCount][3];
		    
		    //Iterate through each link 
		    for(int i = 0; i < linksCount; i++){
		    	tempString = br.readLine();
		    	inps = tempString.split(":");
		    	
		    	allLinks[i][0] = Integer.parseInt(inps[0]);
		    	allLinks[i][1] = Integer.parseInt(inps[1]);
		    	allLinks[i][2] = Integer.parseInt(inps[2]);
			    //System.out.println(allLinks[i][0] + ":" + allLinks[i][1] + ":" + allLinks[i][2]);
		    }
		    
		    //Read number of new links
		    tempString = br.readLine();
		    newLinksCount = Integer.parseInt(tempString);
		    //System.out.println(newLinksCount);
		    
		    allNewLinks = new int[newLinksCount][3];
		    
		    //Interate through each new link
		    for(int i = 0; i < newLinksCount; i++){
		    	tempString = br.readLine();
		    	inps = tempString.split(":");
		    	
		    	allNewLinks[i][0] = Integer.parseInt(inps[0]);
		    	allNewLinks[i][1] = Integer.parseInt(inps[1]);
		    	allNewLinks[i][2] = Integer.parseInt(inps[2]);	
		    	//System.out.println(allLinks[i][0] + ":" + allLinks[i][1] + ":" + allLinks[i][2]);
		    }
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
