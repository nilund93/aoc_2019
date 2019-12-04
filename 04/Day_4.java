package advent_of_code;
import java.util.*;

public class Day_4 {
	static int startpass = 0;
	static int endpass = 0;
	private static void parsePassword(String pw){
		String[] parts = pw.split("-");
		startpass = Integer.parseInt(parts[0]);
		endpass = Integer.parseInt(parts[1]);
	}
	private static boolean condition_One(int check){
		//atleast Two adjacent digits are the same
		int last = 10;
		int current = 0;
		String number = String.valueOf(check);
		for(int j = 0; j < number.length(); j++){
			current = Integer.parseInt(String.valueOf(number.charAt(j)));
			if (current == last){
				return true;
			}
			last = current;
		}
		return false;
	}
	private static boolean condition_Two(int check){
		//Digits never decrease
		int last = 0;	//this can never be true
		int current = 0;
		String number = String.valueOf(check);
		for (int j = 0; j < number.length(); j++){
			current = Integer.parseInt(String.valueOf(number.charAt(j)));
			if (current < last){
				return false;
			}
			last = current;
		}
		return true;
	}
	/*
	private static boolean condition_Three(){
		
	}
	*/

	public static void main (String[] args){
		String password = "134564-585159"; //change this string to test other inputs
		int passwordsum = 0; 							 //int to save a legit password
		parsePassword(password);
		System.out.println(startpass);
		System.out.println(endpass);
		for(int i=startpass; i < endpass; i++){
			if(condition_One(i) && condition_Two(i)){
				passwordsum+=1;
			}
		}
		System.out.println("Amount of available passwords: " + passwordsum);

	}	
}