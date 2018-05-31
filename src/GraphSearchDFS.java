//GraphSearchDFS main class
//Class to carry out the Depth First Search algorithm using the classes Node and Vertex
//Programmer: Malcolm Milton
//Date: 11/6/15

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphSearchDFS
{	
	public static void main(String[] args)
	{
		File file = new File("Search.txt"); //Uses the file Search.txt which contains all information of the vertexes
		try
		{
			Scanner scanner = new Scanner(file); //To scan through the Search.txt
			ArrayList<Node> list = new ArrayList<Node>(); //ArrayList of Nodes, the Nodes are basically linked lists because they have child pointers, each index will have a distinct vertex for the head of the list
			
			int amount = scanner.nextInt(); //The first line of the file specifies the amount of vertexes
			scanner.nextLine(); //Skips to the next line of the file
			String str = scanner.nextLine(); //str is set to the next
			
			for(int x=0; x<amount; x++) //Loops for adding the amount of vertexes to list, the letter of the vertex is retrieved from str
				list.add(new Node(new Vertex(str.charAt(x*2), x), null)); //Adds a new Node to list, the Vertex of the Node is also created, the child is null
			
			int edges = scanner.nextInt(); //This line of the file specifies the number of edges
			scanner.nextLine(); //Skips to the next line of the file
			
			for(int x=0; x<edges; x++) //For the number of edges specified by the file
			{
				str = scanner.nextLine(); //There are edges amount of lines for 
				char first = str.charAt(0); //The parent vertex's letter
				char second = str.charAt(2); //The child vertex's letter
				int one = 0; //Will be used for the index of the vertex
				int two = 0; //Will be used for the index of the vertex
				for(int i=0; i<list.size(); i++) //Searches the list for the index of the parent and child vertexes
				{
						if(list.get(i).getVertex().getLetter() == first) //If this vertex's letter is the parent
							one = i; //Stores the index of the vertex
						if(list.get(i).getVertex().getLetter() == second) //If this vertex's letter is the parent
							two = i; //Stores the index of the vertex
				}
				Node temp = list.get(one); //Sets the temp node to the index of the parent vertex (parent's node)
				while(temp.getChild() != null) //Until the temp node does not have a child
					temp = temp.getChild(); //Sets temp to the child node
				temp.setChild(new Node(list.get(two).getVertex(),null)); //Creates new node to be the next child
			}

			System.out.println("Travel: " + DepthFirstSearch(list)); //Calls DepthFirstSearch, which returns String of the order of the vertexes visited
			System.out.print("Times:  ");
			for(int x=0; x<amount; x++) //Loops through list of Nodes to print out the times of the vertexes
			{
				Vertex temp = list.get(x).getVertex(); //Sets temp to the next Vertex
				System.out.print(temp.getLetter() + ":" + temp.getVisitTime() + "/" + temp.getFinishTime() + " ");
			}
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.print("File not found");
		}
	}
	
	static int time = 0; //Global variable to record times for visits and finish times of nodes
	
	private static String DepthFirstSearch(ArrayList<Node> list) //Method to perform DepthFirstVisit on each Vertex
	{
		String str = ""; //String to get the order of travel from DepthFirstVisit
		for(int x=0; x<list.size(); x++) //For every head node in the list
			if(list.get(x).getVertex().getColor() == 0) //If the node is unsearched
				str += DepthFirstVisit(list.get(x), list); //Calls DepthFirstVisit for this node and saves the order of travel in str
		return str; //Returns the order of vertexes traveled to
	}

	private static String DepthFirstVisit(Node temp, ArrayList<Node> list) //Method that visits the node for the first time and recursively calls for other unvisited nodes
	{
		temp.getVertex().visit(time++); //Calls the method to turn the color of the vertex to gray and record the visit time
		String order = temp.getVertex().getLetter() + " "; //Adds this node to the order of visited vertexes
		Node temp2 = list.get(temp.getVertex().getIndex()).getChild(); //Sets to the child of the parameter node
		
		while(temp2 != null) //While the child of the parameter node actually exists
		{
			if(temp2.getVertex().getColor() == 0) //If the child is unsearched
            	order += DepthFirstVisit(temp2, list); //Recursively calls this method to search the node and saves the order of travel in str
			temp2 = temp2.getChild(); //Sets temp2 to the child of the current temp2
		}
		temp.getVertex().done(time++); //Calls the method to turn the color of the vertex to black and record the finish time
		return order; //Returns the order of travel
	}
}