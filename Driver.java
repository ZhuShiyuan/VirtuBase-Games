import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		writeSortedToFile();
		writeProfitsToFile();
	}

	// Reads the data from the file and puts it into an sorted array
	public static GameData[] retrieveGamesList() {

		ArrayList<GameData> gameDataAL = new ArrayList<GameData>();

		try {
			Scanner input = new Scanner(new File("gamedata.csv"));
			while (input.hasNext()) {
				String[] currentString = input.nextLine().split(",");
				gameDataAL.add(new GameData(currentString[1], currentString[2], Integer.parseInt(currentString[3]),
						Double.parseDouble(currentString[6])));
			}
			GameData[] gameData = gameDataAL.toArray(new GameData[0]);
			quickSort(gameData, 0, gameData.length - 1);
			return gameData;
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
			return null;
		}
	}

	// partitions the array list of games
	public static int partition(GameData[] gameData, int start, int end) {
		GameData pivot = gameData[end];
		int swapLocation = start;

		for (int i = start; i < end; i++) {
			if (gameData[i].compareTo(pivot) < 0) {
				GameData temp = gameData[i];
				gameData[i] = gameData[swapLocation];
				gameData[swapLocation] = temp;
				swapLocation++;
			}
		}

		gameData[end] = gameData[swapLocation];
		gameData[swapLocation] = pivot;
		return swapLocation;

	}

	// Sorts the data from the file into a sorted arrayList of games
	public static void quickSort(GameData[] gameData, int start, int end) {

		if (start >= end) {
			return;
		} else {
			int pivotLocation = partition(gameData, start, end);
			quickSort(gameData, start, pivotLocation - 1);
			quickSort(gameData, pivotLocation + 1, end);
		}

	}

	// partitions the array list of consoles
	public static int partitionProfit(ConsoleData[] consoleData, int start, int end) {
		ConsoleData pivot = consoleData[end];
		int swapLocation = start;

		for (int i = start; i < end; i++) {
			if (consoleData[i].compareTo(pivot) < 0) {
				ConsoleData temp = consoleData[i];
				consoleData[i] = consoleData[swapLocation];
				consoleData[swapLocation] = temp;
				swapLocation++;
			}
		}

		consoleData[end] = consoleData[swapLocation];
		consoleData[swapLocation] = pivot;
		return swapLocation;

	}

	// Sorts the data from the file into a sorted arrayList of consoles
	public static void quickSortProfit(ConsoleData[] consoleData, int start, int end) {

		if (start >= end) {
			return;
		} else {
			int pivotLocation = partitionProfit(consoleData, start, end);
			quickSortProfit(consoleData, start, pivotLocation - 1);
			quickSortProfit(consoleData, pivotLocation + 1, end);
		}

	}

	// Looks through the listed games and makes a list of the different consoles
	public static ArrayList<ConsoleData> makeConsoleList() {
		ArrayList<ConsoleData> consoleList = new ArrayList<ConsoleData>();
		GameData gameData[] = retrieveGamesList();
		consoleList.add(new ConsoleData(gameData[0].getConsole(), 0));
		for (int i = 0; i < gameData.length; i++) {
			int x = 0;
			for (int j = 0; j < consoleList.size(); j++) {
				if (!gameData[i].getConsole().equals(consoleList.get(j).getConsole()))
					x++;
				if (x == consoleList.size())
					consoleList.add(new ConsoleData(gameData[i].getConsole(), 0));
			}
		}
		return consoleList;
	}

	// Sums the money made on each console and puts it in the ConsoleData object
	public static ConsoleData[] countMoney() {
		ArrayList<ConsoleData> consoleList = makeConsoleList();
		ConsoleData[] consoleData = consoleList.toArray(new ConsoleData[0]);
		GameData gameData[] = retrieveGamesList();
		for (int i = 0; i < consoleData.length; i++) {
			for (int j = 0; j < gameData.length; j++) {
				if (gameData[j].getConsole().equals(consoleData[i].getConsole())) {
					double temp = consoleData[i].getSales();
					consoleData[i].setSales(gameData[j].getSales() + temp);
				}
			}
		}
		quickSortProfit(consoleData, 0, consoleData.length - 1);
		return consoleData;
	}

	// Writes the data to the sorted.csv file
	public static void writeSortedToFile() {
		try {
			GameData[] gameData = retrieveGamesList();
			PrintWriter writer = new PrintWriter("sorted.csv");
			for (int i = 0; i < gameData.length; i++) {
				writer.println(gameData[i].getTitle() + "," + gameData[i].getConsole() + "," + gameData[i].getYear()
						+ "," + gameData[i].getSales());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
	}

	// Writes the data to the profits.csv file
	public static void writeProfitsToFile() {
		try {
			ConsoleData[] consoleData = countMoney();
			PrintWriter writer = new PrintWriter("profits.csv");
			for (int i = 0; i < consoleData.length; i++) {
				writer.println(consoleData[i].getConsole() + "," + consoleData[i].getSales());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
		}
	}

}
