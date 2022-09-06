//Honor Code: I have neither given nor received unauthorized aid on this assignment.

import java.util.ArrayList;

public class Person {

	private String name;
	private boolean explored;
	private String predecessor;
	private ArrayList<String> friendsList = new ArrayList<String>();
	
	//constructor method
	public Person() {
		
	}
	
	//creates a constructor for global variable string name
	public Person(String stringName) {
		this.name = stringName;
	}
	
	//sets the global variable name 
	public void setName(String strName) {
		this.name = strName;
	}
	
	//returns the global variable name
	public String getName() {
		return this.name;
	}
	
	//sets the boolean of the names explored
	public void setExplored (boolean nameExplored) {
		this.explored = nameExplored;
	}
	
	//returns the boolean of the names explored
	public boolean getExplored() {
		return this.explored;
	}
	
	//sets the  of the predecessor name
	public void setPredecessor(String predName) {
		this.predecessor = predName;
	}
	
	//returns the name of the predecessor name
	public String getPredecessor() {
		return this.predecessor;
	}
	
	//sets the friends list array 
	public void setFriendsList (ArrayList<String> friendsNames) {
		this.friendsList = friendsNames;
	}
	
	//returns the friends list array
	public ArrayList<String> getFriendsList() {
		return this.friendsList;
	}
	
	
}
