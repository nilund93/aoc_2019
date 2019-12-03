package advent_of_code;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day_3 {
	static String fn = "C:\Users\nicla\Git\aoc_2019\02/day3_input.txt";
	static List<String> wire1;	//Stored in a list where wire1 goes, each element is a move
	static List<String> wire2;	//Stored in a list where wire2 goes, each element is a move
	static int lowest;
	static HashMap<String, Integer> hmap;
	static HashMap<String, Integer> hmapsteps;
	
	//read from file-functions
	private static void initiate() {
		try(BufferedReader br = new BufferedReader(new FileReader(fn))){
			String line;
			int i = 0;
			while((line=br.readLine()) != null){
				if(i==0) {
					wire1 = new ArrayList<String>(Arrays.asList(line.split(",")));
				}
				else {
					wire2 = new ArrayList<String>(Arrays.asList(line.split(",")));
				}
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		lowest = Integer.MAX_VALUE;
		hmap = new HashMap<String, Integer>();
		hmapsteps = new HashMap<String, Integer>();
	}
	
	//other functions
	public static int manhattanDistance(int x, int y) {
		return Math.abs(x) + Math.abs(y);
	}
	
	/*
	 * Loop through a matrix of the wirelist x wiredistance in size.
	 * Wirecheck is used when a wire has already been put down and we want to know if our 
	 * wire would intersect.
	 * If we find our index x_y in the hashmap, then we have an intersection (during wirecheck = true)
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void runWire(List<String> wire, boolean wirecheck) {
		int x = 0;
		int y = 0;
		for(int i=0; i < wire.size(); i++) {
			char direction = wire.get(i).charAt(0);
			int wiredist = Integer.parseInt(wire.get(i).substring(1));
			int xx = 0;
			int yy = 0;
			//Right, left, up, down
			if (direction=='R') xx = 1;
			if (direction=='L')	xx = -1;
			if (direction=='U') yy = 1;
			if (direction=='D')	yy = -1;
			
			for(int j=0; j < wiredist; j++) {
				x+=xx; 
				y+=yy;
				if(!wirecheck) {
					hmap.put(x + "_" + y, 1);
				}
				else {
					if(hmap.getOrDefault(x+"_"+y, 0) == 1){
						int dist = manhattanDistance(x, y);
						if(dist < lowest) lowest = dist;
					}
				}
			}
		}
	}
	//part 2 solution
	/*
	 * Get a new hashmap where we store the amount of steps to the intersection
	 * Exchange the manhattandistance with a simple addition of steps to this place
	 * ???
	 * PROFIT
	 */
	public static void runWire2(List<String> wire, boolean wirecheck) {
		int x = 0;
		int y = 0;
		int steps = 0;
		for(int i=0; i < wire.size(); i++) {
			char direction = wire.get(i).charAt(0);
			int wiredist = Integer.parseInt(wire.get(i).substring(1));
			int xx = 0;
			int yy = 0;
			if (direction=='R') xx = 1;
			if (direction=='L')	xx = -1;
			if (direction=='U') yy = 1;
			if (direction=='D')	yy = -1;
			
			for(int j=0; j < wiredist; j++) {
				x+=xx; y+=yy; steps++;
				String thispos = x+"_"+y;
				if(!wirecheck) {
					hmap.put(x + "_" + y, 1);
					if(!hmapsteps.containsKey(thispos)) {
						hmapsteps.put(thispos, steps);
					}
				}
				else {
					if(hmap.getOrDefault(thispos, 0) == 1){
						int dist = hmapsteps.get(thispos) + steps;
						if(dist < lowest) lowest = dist;
					}
				}
			}
		}
	}
	
	//main
	public static void main(String[] args) {
		initiate();
		runWire(wire1, false);
		runWire(wire2, true);
		System.out.println(lowest);
		//part 2, don't forget to reset stuff
		initiate();
		runWire2(wire1, false);
		runWire2(wire2, true);
		System.out.println(lowest);
		//return 489 for part 1, 93654 for part 2
	}
}
