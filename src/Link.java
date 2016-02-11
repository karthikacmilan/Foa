/*
 * Class to represent a Link in the graph
 */
public class Link {
	public int value;	//Lenght of the links
	public int status;	//0 for old, 1 for new link
	
	public Link(int value_p, int status_p){
		this.value = value_p;
		this.status = status_p;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
