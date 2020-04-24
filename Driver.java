import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.net.MalformedURLException;

public class Driver {
	
	public static KeywordIndex keyDex = new KeywordIndex();
	public static ArrayList<GameData> gameList = new ArrayList<GameData>();
	public static ArrayList<CommentData> commentList = new ArrayList<CommentData>();

	public static void main(String[] args) {
		retrieveGamesList();
		dataTest(gameList.get(0), commentList.get(0));
		writeToFile();
	}
	
	// Read data from database file
	public static void retrieveGamesList() {
		
		try {
			Scanner input = new Scanner(new File("database.csv"));
			String[] stringList = input.nextLine().split(",");
			keyDex.readList(stringList);
			
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
			input.close();
			
			//Comments
			Scanner commentInput = new Scanner(new File("commentdatabase.csv"));
			
			while(commentInput.hasNext()) {
				String[] commentData = commentInput.nextLine().split(",");
				
				String game = commentData[0];
				String username = commentData[1];
				String comment = commentData[2];
				
				commentList.add(new CommentData(game, username, comment));
			}
			
			commentInput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("READ FILE NOT FOUND");
		}
	}
	
	// Write data to database file
	public static void writeToFile() {
		try {
			PrintWriter writer = new PrintWriter("database.csv");
			String[] keywords = keyDex.writeList();
			String formatKeyword = "";
			for (String i :keywords) {
				formatKeyword = formatKeyword + i + ",";
			}
			writer.println(formatKeyword);
			for (int i=0; i<gameList.size();i++) {
				writer.println(gameList.get(i).prepForFile());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("WRITE FILE NOT FOUND");
		}
	}
	
	// Tester method, prints data members of chosen game and first listed storefront
	public static void dataTest(GameData game, CommentData comment) {
		System.out.println(game.getTitle());
		System.out.println(game.getCompany());
		System.out.println(game.getWiki());
		System.out.println(game.getStorefront(0).getName());
		System.out.println(game.getStorefront(0).getPrice());
		System.out.println(game.getStorefront(0).getLink());
		System.out.println(game.getKeyword(0));
		System.out.println(game.getKeyword(1));
		System.out.println(game.getKeyword(2));
		
		System.out.println("-----Test Comment-----");
		System.out.println(comment.getGame());
		System.out.println(comment.getUsername());
		System.out.println(comment.getComment());
	}

}
