
//Honor Code: I have neither given nor received unauthorized aid on this assignment.


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class SixDegreesDriver {

	static ArrayList <Person> peeps = new ArrayList<Person>();
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String file = "friends.txt";
		readFile(file);
		
		Scanner scan = new Scanner (System.in);
		
		 
		String playAgain = "y"; //initialized in the beginning so it starts with this while loop
		while (playAgain.equals("y")) {
 
			System.out.println("Enter the name of the first person: ");
			String firstName = scan.nextLine();
			while (!peeps.contains(getPerson(firstName))) {
				System.out.println("Error: " + firstName + " is not in the list");
				System.out.println("Enter the name of the first person: ");
				firstName = scan.nextLine();
			}
				
			System.out.println("Enter the name of the second person: ");
			String secondName = scan.nextLine();
			while (!peeps.contains(getPerson(secondName))) {
				System.out.println("Error: " + secondName + " is not in the list");
				System.out.println("Enter the name of the second person: ");
				secondName = scan.nextLine();
			}
	
			
			
			
			System.out.println("Relation: "); 
			ArrayList <String> chainListx = search(getPerson(firstName), getPerson(secondName)); //sets what should be returned to a variable
			
			System.out.println("\nDegrees of Separation: "); 
			System.out.println(chainListx.size()-1);

//			for (int i = chainListx.size()-1; i>=0; i--) { //prints the chainList in reverse order
//				System.out.print(chainListx.get(i)+ " ");
//			}
			//System.out.println(chainListx.size()-1);
						
			System.out.println("Want to try another query? (y/n)");
			playAgain = scan.nextLine();
		}
		
		System.out.println("\nComputing Average Degree of Separation... \nAverage Degree of Separation: ");
		System.out.println(connectArray());

		
	}
	
	public static ArrayList<String> search(Person A, Person B) {
		//first part of algorithm

		ArrayList<Person> ExploreList = new ArrayList <Person>(); //list of names of people explored
		
		for (int i=0; i<peeps.size(); i++) {
			peeps.get(i).setExplored(false); 
			peeps.get(i).setPredecessor(null);			
		}
				
		boolean found = false; //person B not found yet
		
		ExploreList.add(A); //add person A to ExploreList
		A.setExplored(true);

		while (!ExploreList.isEmpty() & !found) { 
			Person x = ExploreList.get(0); //saves x to get index 0
			String name = x.getName();
			ExploreList.remove(0);
			
			if (x == B)  //if they have the same name
				found = true;
			else {
				ArrayList<String> friendsList = x.getFriendsList();
				
				if (friendsList==null) {
					System.out.println("They are not connected");
				}
				else {
					for (int i=0; i < x.getFriendsList().size(); i++) {
						Person y = getPerson(x.getFriendsList().get(i));
						
						
						if (y.getExplored() == false) {
							ExploreList.add(y);
							y.setExplored(true);
							y.setPredecessor(x.getName()); 
							
						} //if statement
					} //for loop
				} //if else 	
			} //else statement
				
				
		} //while loop
		
		
		//second part of algorithm
		ArrayList<String> chainList = new ArrayList<String>();
		String current = B.getName();
		while (current != null) {
			chainList.add(current);
			current = getPerson(current).getPredecessor();
		}
		for (int i = chainList.size()-1; i>=0; i--) { //prints the chainList in reverse order
			System.out.print(chainList.get(i)+ " ");
		}
		
		return chainList;
	
	}
	
	public static double connectArray() {
//		row: people we checked
//		colum: all people -1
		int rows= peeps.size();
		int columns= peeps.size()-1;
		double total = 0; //keeps count on the total degrees of separation between person j and person i
		
		int [][] connectArray = new int [rows][columns];
		int currentRow = 0;
		int currentColumn = 0;
		
		
		//populate array
		for (Person i: peeps) {
			
			for (Person j: peeps) {
				if (i != j) {
					ArrayList<String> pplChainList = search(i, j); //initializes array to search peeps
					int chainVariable = pplChainList.size()-1;
					if (chainVariable == 0) {
						chainVariable = peeps.size()+1; //adding the number of people on the list when person is 0
					}
					connectArray[currentRow][currentColumn] = chainVariable;
					
					currentColumn++; //update currentColumn count
				}
			}
			currentColumn=0;
			currentRow++;
			
			System.out.println();
		} //for loop
		
		for (int row = 0; row < connectArray.length; row++) {
			for (int column = 0; column < connectArray[0].length; column++) {
				total += connectArray [row][column];
			}
			}
		

		//return connectArray;
		int peepPairsNum = (peeps.size()) * (peeps.size()-1);
		
		double avgDegSep = total/peepPairsNum;

		return avgDegSep;
	}
	
	
	
	public static Person getPerson(String name){
		//for each Person in peeps
		for(int i=0; i<peeps.size(); i++){
			//Check if this Person’s name is the name we are 
			//looking for and if so, return the Person
			if(peeps.get(i).getName().equals(name))
				return peeps.get(i);
		//if we never found the name, return null (the code 
		//should never get here, but the Java compiler requires 
		//this).
		}
		return null; 
	}
	
	
	public static void readFile(String file) throws IOException {
		
		Scanner fr = new Scanner (new File(file));
		
		while (fr.hasNextLine()) {
			String line = fr.nextLine();
			String [] names = line.split("\t"); //splits row across by names separated by a tab space
			
			//use first name to create Person
			Person p = new Person (names[0]);
			
			//rest of names go into friendsList 
			for (int i=1; i<names.length; i++) 
				p.getFriendsList().add(names[i]);
			
			peeps.add(p); 
			
		}
		
		
	}
	
}
