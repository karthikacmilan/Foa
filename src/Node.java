import java.util.ArrayList;
import java.util.HashMap;


public class Node {
	public int value;
	public int distanceFromSource;
	public int flag;
	public HashMap<Integer, Link> links;
	public HashMap<Integer, Link> newLinks;
	
	public Node(int nodeValue){
		this.value = nodeValue;
		this.distanceFromSource = Integer.MAX_VALUE;
		this.flag = 0;
		links = new HashMap<Integer, Link>();
		newLinks = new HashMap<Integer, Link>();
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
	
	public void addLink(int toNode, int length){
		Link newLink = new Link(length, 0);
		links.put(toNode, newLink);
	}
	
	public void addNewLink(int toNode, int length){
		Link newLink = new Link(length, 1);
		newLinks.put(toNode, newLink);
	}
	
	
	public void mergeLinks(){
		//Iterate through each new link
		for(HashMap.Entry<Integer, Link> entry : newLinks.entrySet()){
			//check if key exists
			if(links.containsKey((entry.getKey()))){
				//Check if value is lower, ignore if higher
				if(entry.getValue().getValue() < links.get(entry.getKey()).getValue())
					links.put(entry.getKey(), entry.getValue());
			}
			//if key doesnt exist
			else{
				links.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
}
