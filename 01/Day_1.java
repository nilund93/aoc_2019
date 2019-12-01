package advent_of_code_day1;
import java.util.*; 
import java.lang.Math;
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*;

public class Day_1 {
	public static List<String> readFileInList(String fileName) 
	{ 
		List<String> lines = Collections.emptyList(); 
		try
		{ 
			lines = 
				Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
			}
		catch (IOException e){ 
			// do something 
			e.printStackTrace(); 
			} 
		return lines; 
		}
	public static int calculateFuel(int f) {
		
		int fuelcost = f / 3 -2;
		if(fuelcost > 0) {
			return fuelcost + calculateFuel(fuelcost);
		}
		else {
			return 0;
		}
	}

	public void calculate()
	{ 
		String fn = "C:\Users\nicla\Git\aoc_2019\01/da1_input.txt";
		List<String> l = readFileInList(fn); 
		Iterator<String> itr = l.iterator();
		int fuel_sum = 0;
		while (itr.hasNext()) {
			fuel_sum += calculateFuel(Integer.parseInt(itr.next()));
		}
		System.out.println("Total sum of fuel requirement is: " + fuel_sum);

	}
	
}