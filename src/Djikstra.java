import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Djikstra
{

    
    static final int V=9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
void printSolution(int dist[], int n)
{
    System.out.println("Vertex   Distance from Source");
    for (int i = 0; i < V; i++)
        System.out.println(i+" \t\t "+dist[i]);
}
void dijkstra(int graph[][], int src)
{
    int dist[] = new int[V]; 

    Boolean sptSet[] = new Boolean[V];

   
    for (int i = 0; i < V; i++)
    {
        dist[i] = Integer.MAX_VALUE;
        sptSet[i] = false;
    }

   
    dist[src] = 0;

    
    for (int count = 0; count < V-1; count++)
    {
        
        int u = minDistance(dist, sptSet);

        
        sptSet[u] = true;

        for (int v = 0; v < V; v++)

            if (!sptSet[v] && graph[u][v]!=0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u]+graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
    }

    
    printSolution(dist, V);
}
	public static void main( String args[])
	{
		HashMap<Integer, HashMap<Integer, Integer>> Adj = new HashMap<Integer, HashMap<Integer, Integer>>();
	
		
		//System.out.println("Enter the number of vertices");
		Scanner Input = new Scanner(System.in);
		int n = Input.nextInt();
		int adj_m[][] = new int[n+1][n+1];
		for(int i = 0 ; i < n ; i ++ )
		{
			for(int j = 0 ; j < n ; j ++)
			{
				adj_m[i][j] = 0; 
			}
		}
		
		String sample = "1:2:3 1:3:5 2:3:7 2:4:8 3:4:1 3:6:5 4:5:1 5:6:5";
		String[] inp = sample.split(" ");
		//HashMap <Integer, Integer> Adj1 = new HashMap<Integer,Integer>();
		for(int i=0;i<inp.length;i++)
		{
			String[] spl = inp[i].split(":");
			
			adj_m[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])] = Integer.parseInt(spl[2]);
			
		}
		Djikstra t = new Djikstra();
        t.dijkstra(adj_m, 1);
		
	}}