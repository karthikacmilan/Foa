import java.util.ArrayList;
import java.util.HashMap;


public class Node {
	public int value;
	public int distanceFromSource;
	//public ArrayList<Integer> nodes;
	public HashMap<Integer, Integer> links;
	
	public Node(){
		this.value = -1;
		this.distanceFromSource = -1;
		links = new HashMap<Integer, Integer>();
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}
	
	public void addLink(int node, int length){
		links.put(node, length);
	}
	
	public int getClosestNode(){
		int closest = -1;		
		int min = Integer.MAX_VALUE;
		int key, value;
		
		for (HashMap.Entry<Integer, Integer> entry : links.entrySet()) {
		    key = entry.getKey();
		    value = entry.getValue();
		    
		    if(value < min){
		    	closest = key;
		    	min = value;
		    }
		}
		
		return closest;
		
	}

}
