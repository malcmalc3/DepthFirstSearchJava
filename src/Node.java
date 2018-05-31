//Node class
//Class to create linked list of vertexes for going through DFS algorithm
//Programmer: Malcolm Milton
//Date: 11/6/15

public class Node {
	private Vertex vertex; //Points to the the actual vertex
	private Node child; //Points to the child of the vertex
	
	public Node(Vertex v, Node c) //Class constructor, sets the vertex and child of the vertex
	{
		vertex = v;
		child = c;
	}
	
	public Vertex getVertex() {return vertex;} //Method to return the pointer to the vertex of this node
	
	public void setChild(Node c) {child = c;} //Method to set the child of this node
	
	public Node getChild() {return child;} //Methid to return the child of this node
}