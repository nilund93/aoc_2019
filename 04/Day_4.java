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
		//Digits never decrease
		int last = 0; int current = 0;
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
	private static boolean condition_Two(int check){
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
	
	private static boolean condition_Two_Two(int check){
		//atleast Two adjacent digits are the same
		//part 2 - two adjacent matching digits are not part of a larger group of matching digits
		// i.e 123444 is not ok, while 111122 is ok (due to 22)
		int[] countAdj = new int[10];
		int last = 0; int current = 0;
		String number = String.valueOf(check);
		for(int i = 0; i < number.length(); i++){
			current = Integer.parseInt(String.valueOf(number.charAt(i)));
			if(current == last) countAdj[current]++;
			last = current;
		}
		for(int digit : countAdj){
			if(digit==1) return true;
		}
		return false;
	}
	public static void main (String[] args){
		String password = "134564-585159"; //change this string to test other inputs
		int passwordsum = 0; 							 //int to save a legit password
		int passwordsum2 = 0;							//int so save part 2 legit passwords
		parsePassword(password);
		System.out.println(startpass);
		System.out.println(endpass);
		for(int i=startpass; i < endpass; i++){
			if(condition_One(i) && condition_Two(i)) passwordsum+=1;
			if(condition_One(i) && condition_Two_Two(i)) passwordsum2+=1;
		}
		System.out.println("Amount of available passwords: " + passwordsum); //returns 1929
		System.out.println("Amount of available part 2 passwords: " + passwordsum2); //returns 1306
	}	
}