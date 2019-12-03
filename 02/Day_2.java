package advent_of_code;

import java.util.*;
import java.io.*;

public class Dec_02{
	static List<Integer> l;
	
	public static List<Integer> getInfoFromFile(String file){
		List<String> strlist = null;
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while((line=br.readLine()) != null){
				strlist = new ArrayList<String>(Arrays.asList(line.split(",")));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		List<Integer> retlist = new ArrayList<Integer>();
		for(String i : strlist){
			retlist.add(Integer.parseInt(i));
		}
		return retlist;
	}

	public static void opcode(int noun, int verb ){
		
		int pos = 0;
		l.set(pos+1, noun);
		l.set(pos+2, verb);
		while(l.get(pos) != 99) {
			int retpos = l.get(pos + 3);	//return position
			int retval = 0;								//return value
			if(l.get(pos) == 1 ) {				//addition intcode
				retval = l.get(l.get(pos + 1)) + l.get(l.get(pos+2));
				l.set(retpos, retval);
			}
			else if (l.get(pos) == 2){		//multiplication intcode
				retval = l.get(l.get(pos + 1)) * l.get(l.get(pos+2));
				l.set(retpos, retval);
			}
			else {
				System.out.println("Something went wrong in opcode.");
				/*
				System.out.println(l.get(pos));
				System.out.println(l.get(pos+1));
				System.out.println(l.get(pos+2));
				System.out.println(retpos);
				*/
			}
			pos +=4;
			
		}
		if(l.get(pos) == 99) {
			//System.out.println("HALT!");
		}
		
	}
	public static int bruteForce() {
		for(int i = 0; i < 100; i++ ) {
			for(int j = 0; j < 100; j++) {
				parseList();
				opcode(i, j);
				if(l.get(0) == 19690720) {
					return (100 * l.get(1) + l.get(2));
				}
			}
		}
		return 0;
	}
	public static void parseList() {
		String fn = "C:\Users\nicla\Git\aoc_2019\02/day2_input.txt";
		l = getInfoFromFile(fn);
	}
	public static void main(String[] args) {
		parseList();
		opcode(12,2);
		//print answer for part 1
		System.out.println("Position 0 is: " + l.get(0));	//4576384
		//print answer for part 2
		System.out.println("The result of 100 * noun + verb-bruteforce is is: " + bruteForce());
		
	}
}