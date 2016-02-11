import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Class for the Assignment Project 1
 */
public class Assignment1 {
	public static int citiesCount;
	public static Node nodes[];
	public static int source;
	public static int destination;
	
	public static int linksCount;
	public static int newLinksCount;

	public static void djikstra(boolean isNew) {
		int dist[] = new int[citiesCount];
		int prev[] = new int[citiesCount];
		
		PriorityQueue<Node> nodesQueue = new PriorityQueue<Node>(citiesCount, new Comparator<Node>(){
												public int compare(Node node1, Node node2){
													return (node1.distanceFromSource <= node2.distanceFromSource ? -1 : 1);
											}});
		
		FibonacciHeap<Node> fibQueue = new FibonacciHeap<Node>();
		
		nodes[source].distanceFromSource = 0;
		
		for (int i = 0; i < citiesCount; i++) {
			dist[i] = Integer.MAX_VALUE;	//distance from source to i;
			prev[i] = -1;					//previous node in optimal path from source
			
			nodesQueue.add(nodes[i]);		//Add with priority
			fibQueue.enqueue(nodes[i], nodes[i].getDistanceFromSource());
		}
		
		dist[source] = 0;
		
		while(nodesQueue.size() != 0){
			Node temp = nodesQueue.poll();	//Lowest value of of dist array
			Node tempFib = fibQueue.dequeueMin().getValue();
			
			//Find all neighbours of u = vs, v[]
			for(HashMap.Entry<Integer, Link> entry : temp.links.entrySet()) {
			    int key = entry.getKey();
			    Link value = entry.getValue();
			    
			    int tempDistance = dist[temp.value] + value.value;
			    
			    if(tempDistance < dist[key] && (temp.flag + value.status <= 1)){
			    	dist[key] = tempDistance;
			    	prev[key] = temp.value;
			    	
			    	//Reduce priority - PQ
			    	boolean ret = nodesQueue.remove(nodes[key]);
			    	if(ret){
			    		nodes[key].setDistanceFromSource(tempDistance);
			    		nodes[key].flag = temp.flag + value.status; //change to 0 or 1
			    		nodesQueue.add(nodes[key]);
			    	}
			    	
			    	/*
			    	//Reduce priority - FH
			    	nodes[key].setDistanceFromSource(tempDistance);
			    	nodes[key].flag = temp.flag + value.status; //change to 0 or 1
			    	
			    	FibonacciHeap.Entry<Node> updateEntry = new FibonacciHeap.Entry<Node>(nodes[key], tempDistance);
			    	
			    	fibQueue.g
			    	fibQueue.decreaseKey(updateEntry, (double)tempDistance);
			    	fibQueue.
			    	*/
			    }
			}
		}
		
		System.out.println("Src-" + source + " Dest-" + destination);
		System.out.println(Arrays.toString(prev));
		System.out.println(Arrays.toString(dist));
		
		int reached = destination;
		ArrayList<Integer> listArray = new ArrayList<Integer>();
		
		listArray.add(0, reached);
		while(reached != source){
			if(isNew){
				Link isPresent = nodes[prev[reached]].newLinks.get(reached);
				if(isPresent != null){
					System.out.println("New Link used (" + prev[reached] + "," + reached + ")");
				}
			}
			
			listArray.add(0, prev[reached]);
			reached = prev[reached];
		}
		
		System.out.println(listArray.toString());
		
	}

	/*
	 * Read from the console
	 */
	public static void input_console() throws IOException {
		String tempString;
		String[] inps = new String[3];
		//Scanner a = new Scanner(System.in);
		int n1, n2, length;
		BufferedReader screenReader = new BufferedReader(new InputStreamReader(System.in));
		
		//Input number of cities
		System.out.println("Please enter the number of cities");
		tempString = screenReader.readLine();
		citiesCount = Integer.parseInt(tempString); 
		nodes = new Node[citiesCount];
		for(int i = 0; i < citiesCount; i++)
			nodes[i] = new Node(i);
		
		//Input the number of links
		System.out.println("Please enter the number of roads");
		tempString = screenReader.readLine();
		linksCount = Integer.parseInt(tempString);
		
		//Input the links
		System.out.println("Please input the roads in the following format - start:destination:distance");
		for (int i = 0; i < linksCount; i++) {
			  System.out.println("Road "+(i+1));
			  //tempString = a.nextLine();
			  tempString = screenReader.readLine();

			  inps = tempString.split(":");
			  
			  //Populating matrix -- Remove later
			  n1 = Integer.parseInt(inps[0]);
			  n2 = Integer.parseInt(inps[1]);
			  length = Integer.parseInt(inps[2]);
				
			  //Populating nodes objects
			  nodes[n1].addLink(n2, length);
		}
		
		//Input number of new links
		System.out.println("Please enter number of new roads: ");
		tempString = screenReader.readLine();
		newLinksCount = Integer.parseInt(tempString);
		
		//Input the new links
		System.out.println("Please input the newly proposed roads");
		for(int i = 0; i < newLinksCount; i++){
			  System.out.println("Proposed Road "+(i+1));
			  //tempString = a.nextLine();
			  tempString = screenReader.readLine();
			  inps = tempString.split(":");
			  
			  //Populating matrix -- Remove later
			  n1 = Integer.parseInt(inps[0]);
			  n2 = Integer.parseInt(inps[1]);
			  length = Integer.parseInt(inps[2]);
				
			  //Populating nodes objects
			  nodes[n1].addLink(n2, length);
		}
		
		System.out.println("Please enter the source");
		tempString = screenReader.readLine();
		source = Integer.parseInt(tempString);
		
		System.out.println("Please enter the destination");
		tempString = screenReader.readLine();
		destination = Integer.parseInt(tempString);
		
		screenReader.close();
	}

	/*
	 * Read from an input file
	 */
	public static void input_file() throws IOException {
		String file = "sample_1.txt";
		int n1, n2, length;

		// Read the text file name from user
		System.out.println("Enter test file name (e.g. sample_1.txt) : ");
		BufferedReader screenReader = new BufferedReader(new InputStreamReader(System.in));
		file = screenReader.readLine();

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

			// Iterate through each link
			for (int i = 0; i < linksCount; i++) {
				tempString = br.readLine();
				inps = tempString.split(":");

				//Populating matrix -- Remove later
				n1 = Integer.parseInt(inps[0]);
				n2 = Integer.parseInt(inps[1]);
				length = Integer.parseInt(inps[2]);
				
				//Populating nodes objects
				nodes[n1].addLink(n2, length);
			}

			// Read number of new links
			tempString = br.readLine();
			newLinksCount = Integer.parseInt(tempString);

			// Interate through each new link
			for (int i = 0; i < newLinksCount; i++) {
				tempString = br.readLine();
				inps = tempString.split(":");

				n1 = Integer.parseInt(inps[0]);
				n2 = Integer.parseInt(inps[1]);
				length = Integer.parseInt(inps[2]);
				
				nodes[n1].addNewLink(n2, length);
			}
			
			//Read the source and destination
			tempString = br.readLine();
			source = Integer.parseInt(tempString);
			
			tempString = br.readLine();
			destination = Integer.parseInt(tempString);
			

		} catch (FileNotFoundException e) {
			System.out.println("Inputed file doesnt exist!");
			System.exit(0);
		}
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		int choice;

		try{
			System.out.println("Enter You Choice : 1. Console 2. Input File");
			Scanner a = new Scanner(System.in);
			choice = a.nextInt();
	
			switch (choice) {
				case 1:
					input_console();

					//With old link
					djikstra(false);
					
					//With old and new links
					for(int i = 0; i < citiesCount; i++){
						nodes[i].mergeLinks();
						nodes[i].distanceFromSource = Integer.MAX_VALUE;
					}
					
					djikstra(true);

					break;
		
				case 2:
					input_file();
	
					//With old link
					djikstra(false);
					
					//With old and new links
					for(int i = 0; i < citiesCount; i++){
						nodes[i].mergeLinks();
						nodes[i].distanceFromSource = Integer.MAX_VALUE;
					}
					
					djikstra(true);
					break;
		
				default:
					System.out.println("Invalid");
					break;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		

	}
}
