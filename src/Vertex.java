//Class Vertex
//Holds the vertex's information: color, letter, visit time, and finish time
//Programmer: Malcolm Milton
//Date: 11/6/2015

public class Vertex {
	private int color = 0; //0=white 1=gray 2=black
	private int index; //The index that the vertex is the head of
	private char letter; //The vertex's letter
	private int visitTime; //The time the vertex is visited
	private int finishTime; //The time the vertex has finished being searched
	
	public Vertex(char l, int i) {letter = l; index = i;} //Class constructor, sets the letter and index of the vertex
	
	public char getLetter()	{return letter;} //Method to return the letter of the vertex
	
	public int getIndex() {return index;} //Method to return the vertex's index
	
	public int getColor() {return color;} //Method to return the color of the vertex
	
	public void visit(int t){color=1; visitTime = t;} //Method to call when vertex is first visited, sets color to gray, sets visitTime to parameter
	
	public void done(int t)	{color = 2; finishTime = t;} //Method to call when vertex is finished being searched, sets color to black, sets finishTime to parameter
	
	public int getVisitTime() {return visitTime;} //Returns the visit time of the vertex
	
	public int getFinishTime() {return finishTime;} //Method to return the finish time of the vertex
}