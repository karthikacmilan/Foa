import java.awt.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.w3c.dom.NodeList;

public class Assignment1 {

	public static int citiesCount;
	public static Node nodes[];
	public static PriorityQueue<Node> nodesQueue;

	public static int linksCount;
	public static int newLinksCount;

	public static void djikstra(int source, int destination) {
		ArrayList<Integer> nodesList = new ArrayList<Integer>();
		int shortestPath[];
		int dist[] = new int[citiesCount];
		int prev[] = new int[citiesCount];
		int vertexSet[] = new int[citiesCount];
		
		
		nodesQueue = new PriorityQueue<Node>(citiesCount, new Comparator<Node>(){
												public int compare(Node node1, Node node2){
													return (node1.distanceFromSource <= node2.distanceFromSource ? -1 : 1);
											}});
		
		for (int i = 0; i < citiesCount; i++) {
			dist[i] = Integer.MAX_VALUE;	//distance from source to i;
			prev[i] = -1;					//previous node in optimal path from source
			
			nodesQueue.add(nodes[i]);		//Add with priority
		}
		
		dist[source] = 0;
		
		while(nodesQueue.size() != 0){
			Node temp = nodesQueue.poll();	//Lowest value of of dist array
			
			//Find all neighbours of u = vs, v[]
			for(HashMap.Entry<Integer, Integer> entry : temp.links.entrySet()) {
			    int key = entry.getKey();
			    int value = entry.getValue();
			    
			    int tempDistance = dist[temp.value] + value;
			    
			    if(tempDistance < dist[key]){
			    	dist[key] = tempDistance;
			    	prev[key] = temp.value;
			    	
			    	boolean ret = nodesQueue.remove(nodes[key]);
			    	if(ret){
			    		nodes[key].setDistanceFromSource(tempDistance);
			    		nodesQueue.add(nodes[key]);
			    	}
			    }
			}
		}
		
		System.out.println(Arrays.toString(prev));
	}

	// TODO
	public static void input_console() {
		Scanner a = new Scanner(System.in);
		int m, n;
		// System.out.println();
		n = a.nextInt(); // cities
		m = a.nextInt();
		for (int i = 0; i < m; i++) {
			// doubt

		}

	}

	public static void input_file() {
		String file = "sample.txt";
		int allLinks[][] = null;
		int allNewLinks[][] = null;

		// Read the text file name from user
		System.out.println("Enter test file name (e.g. sample.txt) : ");
		BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
		// file = fileNameReader.readLine();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String tempString;
			String[] inps = null;

			// Read number of cities
			tempString = br.readLine();
			citiesCount = Integer.parseInt(tempString);
			nodes = new Node[citiesCount];
			for(int i = 0; i < citiesCount; i++)
				nodes[i] = new Node(i);

			// Read number of links
			tempString = br.readLine();
			linksCount = Integer.parseInt(tempString);

			// Initialize queue of links
			// linksQueue = new PriorityQueue<Links>(linksCount);
			allLinks = new int[linksCount][3];

			// Iterate through each link
			for (int i = 0; i < linksCount; i++) {
				tempString = br.readLine();
				inps = tempString.split(":");

				//Populating matrix -- Remove later
				allLinks[i][0] = Integer.parseInt(inps[0]);
				allLinks[i][1] = Integer.parseInt(inps[1]);
				allLinks[i][2] = Integer.parseInt(inps[2]);
				
				//Populating nodes objects
				nodes[allLinks[i][0]].addLink(allLinks[i][1], allLinks[i][2]);
			}

			// Read number of new links
			tempString = br.readLine();
			newLinksCount = Integer.parseInt(tempString);

			allNewLinks = new int[newLinksCount][3];

			// Interate through each new link
			for (int i = 0; i < newLinksCount; i++) {
				tempString = br.readLine();
				inps = tempString.split(":");

				allNewLinks[i][0] = Integer.parseInt(inps[0]);
				allNewLinks[i][1] = Integer.parseInt(inps[1]);
				allNewLinks[i][2] = Integer.parseInt(inps[2]);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		int choice,src,dest;

		System.out.println("Enter You Choice : 1. Console 2. Input File");
		Scanner a = new Scanner(System.in);
		choice = a.nextInt();

		switch (choice) {
			case 1:
				input_console();
				break;
	
			case 2:
				input_file();
				
				System.out.print( "Enter source node : ");
				src = a.nextInt();
				System.out.print( "Enter dest node : ");
				dest = a.nextInt();
				
				djikstra(src, dest);
				break;
	
			default:
				System.out.println("Invalid");
				break;
		}

	}
}
