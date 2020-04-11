import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.net.MalformedURLException;

public class Driver {
	
	public static KeywordIndex keyDex;
	public static ArrayList<GameData> gameList;

	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(new File("database.csv"));
			//retrieveKeywordIndex(input);
			retrieveGamesList(input);
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
	}
	
	public static void retrieveKeywordIndex(Scanner input) {
		String[] stringList = input.nextLine().split(",");
		keyDex.readList(stringList);
	}
	
	// Reads the data from the file and puts it into an sorted array
	public static void retrieveGamesList(Scanner input) {
		
		while (input.hasNext()) {
			String[] data = input.nextLine().split(",");
			
			String title = data[0];
			String company = data[1];
			URL wiki;
			try { wiki = new URL(data[2]); } catch (MalformedURLException e) {
				System.out.println("MALFORMED URL FOR " + data[2]); 
				wiki = null;
			}
			int[] keywords = new int[] {Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5])};
			ArrayList<Storefront> storeList = new ArrayList<Storefront>();
			for (int i = 6; i < data.length; i+=3) {
				URL storeURL;
				try { storeURL = new URL(data[i+2]); } catch (MalformedURLException e) {
					System.out.println("MALFORMED URL FOR " + data[i]); 
					storeURL = null;
				}
				storeList.add(new Storefront(data[i],Double.parseDouble(data[i+1]),storeURL));
			}
			gameList.add(new GameData(
					title,
					company, 
					wiki, 
					keywords, 
					storeList,
					keyDex));
			}
	}

//	// partitions the array list of games
//	public static int partition(GameData[] gameData, int start, int end) {
//		GameData pivot = gameData[end];
//		int swapLocation = start;
//
//		for (int i = start; i < end; i++) {
//			if (gameData[i].compareTo(pivot) < 0) {
//				GameData temp = gameData[i];
//				gameData[i] = gameData[swapLocation];
//				gameData[swapLocation] = temp;
//				swapLocation++;
//			}
//		}
//
//		gameData[end] = gameData[swapLocation];
//		gameData[swapLocation] = pivot;
//		return swapLocation;
//
//	}
//
//	// Sorts the data from the file into a sorted arrayList of games
//	public static void quickSort(GameData[] gameData, int start, int end) {
//
//		if (start >= end) {
//			return;
//		} else {
//			int pivotLocation = partition(gameData, start, end);
//			quickSort(gameData, start, pivotLocation - 1);
//			quickSort(gameData, pivotLocation + 1, end);
//		}
//
//	}
//
//	// Writes the data to the sorted.csv file
//	public static void writeSortedToFile() {
//		try {
//			GameData[] gameData = retrieveGamesList();
//			PrintWriter writer = new PrintWriter("sorted.csv");
//			for (int i = 0; i < gameData.length; i++) {
//				writer.println(gameData[i].getTitle() + "," + gameData[i].getConsole() + "," + gameData[i].getYear()
//						+ "," + gameData[i].getSales());
//			}
//			writer.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("FILE NOT FOUND");
//		}
//	}
//
//	// Writes the data to the profits.csv file
//	public static void writeProfitsToFile() {
//		try {
//			ConsoleData[] consoleData = countMoney();
//			PrintWriter writer = new PrintWriter("profits.csv");
//			for (int i = 0; i < consoleData.length; i++) {
//				writer.println(consoleData[i].getConsole() + "," + consoleData[i].getSales());
//			}
//			writer.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("FILE NOT FOUND");
//		}
//	}

}

