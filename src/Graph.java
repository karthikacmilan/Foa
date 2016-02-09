import java.util.Scanner;

public class Graph {

    private Node[] vertices; 
    private int size; 
    private MinPriorityQueue queue;

    public Graph(int size) {
        this.size = size;
        vertices = new Node[size];
        addNodes();
        queue = new MinPriorityQueue(size);
    }

    public class Node {
        int name;
        int cost;
        Neighbour neighbourList;
        State state;

        Node(int name) {
            this.name = name;
            state = State.NEW;
            cost = Integer.MAX_VALUE;
        }
    }

    public class Neighbour {
        int index;
        int weight;
        Neighbour next;

        public Neighbour(int index, Neighbour next, int weight) {
            this.index = index;
            this.next = next;
            this.weight = weight;
        }
    }

    private void addNodes() {
        for (int i = 1; i <= size; i++) {
            addNode(i);
        }
    }

    public void addNode(int name) {
        vertices[name - 1] = new Node(name);
    }

    public void addEdge(int sourceName, int destiName, int weight) {
        int srcIndex = sourceName - 1;
        int destiIndex = destiName - 1;
        Node srcNode = vertices[srcIndex];
        Node destiNode = vertices[destiIndex];
        srcNode.neighbourList = new Neighbour(destiIndex, srcNode.neighbourList, weight);
        
        destiNode.neighbourList = new Neighbour(srcIndex, destiNode.neighbourList, weight);
    }

    public void computeSortestPathsFrom(int sourceNodeName) {
        for (int i = 0; i < size; i++) {
            if (vertices[i].name == sourceNodeName) {
                applyDijkstraAlgorithm(vertices[i]);
                break;
            }
        }
    }

    
    private void applyDijkstraAlgorithm(Node sourceNode) {
        queue.add(sourceNode);
        sourceNode.state = State.IN_Q;
        sourceNode.cost = 0; 
        while (!queue.isEmpty()) {
            Node visitedNode = queue.remove();
            visitedNode.state = State.VISITED;
            Neighbour connectedEdge = visitedNode.neighbourList;
            while (connectedEdge != null) {
                Node neighbour = vertices[connectedEdge.index];
                // adding the not enqued neighbor nodes in the queue
                if (neighbour.state == State.NEW) {
                    queue.add(neighbour);
                    neighbour.state = State.IN_Q;
                }
                
                if (neighbour.state != State.VISITED && ((connectedEdge.weight + visitedNode.cost) < neighbour.cost)) {
                    neighbour.cost = connectedEdge.weight + visitedNode.cost;
                }
                connectedEdge = connectedEdge.next;
            }
        }
        
        //now printing the shortest distances
        for(int i = 0; i < size; i++){
            if(vertices[i].cost != Integer.MAX_VALUE){
                System.out.println("distance from "+sourceNode.name +" to "+vertices[i].name+" is " +vertices[i].cost);
            }else{
                System.out.println(vertices[i].name +" is not reachable from "+sourceNode.name);
            }
        }
    }

    public enum State {
        NEW, IN_Q, VISITED
    };

  
    public class MinPriorityQueue {
        Node[] queue;
        int maxSize;
        int rear = -1, front = -1;

        MinPriorityQueue(int maxSize) {
            this.maxSize = maxSize;
            queue = new Node[maxSize];
        }

        public void add(Node node) {
            queue[++rear] = node;
        }

        public Node remove() {
            Node minValuedNode = null;
            int minValue = Integer.MAX_VALUE;
            int minValueIndex = -1;
            front++;
            for (int i = front; i <= rear; i++) {
                if (queue[i].state == State.IN_Q && queue[i].cost < minValue) {
                    minValue = queue[i].cost;
                    minValuedNode = queue[i];
                    minValueIndex = i;
                }
            }

            swap(front, minValueIndex); 
            queue[front] = null;// lets not hold up unnecessary references in
                                
            return minValuedNode;
        }

        public void swap(int index1, int index2) {
            Node temp = queue[index1];
            queue[index1] = queue[index2];
            queue[index2] = temp;
        }

        public boolean isEmpty() {
            return front == rear;
        }
    }

    public static void main(String[] args) {
    	System.out.println("Enter the number of vertices");
    	Scanner a = new Scanner(System.in);
    	int city = a.nextInt();
        Graph graph = new Graph(city);
        System.out.println("Enter the number of edges");
        int edges = a.nextInt();
        for(int i = 0 ; i < edges ; i ++ )
        {
        	//System.out.println("Default");
       	String edgeline = a.nextLine();
       	String[] eline = edgeline.split(":");
       	int x = Integer.parseInt(eline[0]);
        	int y = Integer.parseInt(eline[1]);
        	int z = Integer.parseInt(eline[2]);
        	
        graph.addEdge(x,y ,z );
        }
        graph.computeSortestPathsFrom(1);
    }
}