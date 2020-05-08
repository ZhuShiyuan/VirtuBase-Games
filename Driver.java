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
	public static ArrayList<LoginData> loginList = new ArrayList<LoginData>();

	public static void main(String[] args) {
		retrieveGamesList();
		writeToDatabase();
	}
	
	// Read data from database files
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
				String visible = data[6];
				for (int i = 7; i < data.length; i+=3) {
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
						visible,
						storeList,
						keyDex));
				}
			input.close();
			
			//Comments
			Scanner commentInput = new Scanner(new File("commentdatabase.csv"));
			commentList.clear();
			
			while(commentInput.hasNext()) {
				String[] commentData = commentInput.nextLine().split(",");
				
				String game = commentData[0];
				String username = commentData[1];
				String comment = commentData[2];
				if(commentData.length > 2) {
					for(int k = 3; k < commentData.length; k++) {
						comment = comment + "," + commentData[k];
					}
				}
				
				commentList.add(new CommentData(game, username, comment));
			}
			
			commentInput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("READ FILE NOT FOUND");
		}
	}
	
	//Collects login database
	public static void retrieveLogin() {
		try {
			Scanner loginInput = new Scanner(new File("logindatabase.csv"));
			loginList.clear();
			
			while(loginInput.hasNext()) {
				String[] loginData = loginInput.nextLine().split(",");
				
				String user = loginData[0];
				String pass = loginData[1];
				
				loginList.add(new LoginData(user, pass));
			}
			
			loginInput.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("READ FILE NOT FOUND");
		}
	}
	
	// Write data to database file
	public static void writeToDatabase() {
		try {
		    PrintWriter databaseWriter = new PrintWriter("database.csv");
		    
		    //headers
		    databaseWriter.println("FPS,Military,Multiplayer,Sci-Fi,Sandbox,Voxel,Visible,Survival,"
		    		+ "Competitive,Shooter,Battle-Royale,RPG,Open-World");
		    
		    //old comments
		    for(int k = 0; k < gameList.size(); k++) {
		    	databaseWriter.print(gameList.get(k).getTitle() + "," + gameList.get(k).getCompany() + "," +
		    			gameList.get(k).getWiki() + "," + gameList.get(k).getIndexNums(0) + "," + 
		    			gameList.get(k).getIndexNums(1) + "," + gameList.get(k).getIndexNums(2) + "," + 
		    			gameList.get(k).getVisibleInt() + "," + 
		    			gameList.get(k).getStorefront(0).getName() + "," + 
		    			gameList.get(k).getStorefront(0).getPrice() + "," +
		    			gameList.get(k).getStorefront(0).getLink());
		    	if(gameList.get(k).getStorefrontCount() == 2) {
		    		databaseWriter.println("," + gameList.get(k).getStorefront(1).getName() + "," + 
			    			gameList.get(k).getStorefront(1).getPrice() + "," +
			    			gameList.get(k).getStorefront(1).getLink());
		    	} else databaseWriter.println();
		    }
		    
		    databaseWriter.close();
		    
		} catch (FileNotFoundException e) {
			System.out.println("WRITE FILE NOT FOUND");
		}
	}
	
	// Write data to commentdatabase file
	public static void writeToFile() {
		try {
		    PrintWriter commentWriter = new PrintWriter("commentdatabase.csv");
		    
		    //old comments
		    for(int j = 0; j < commentList.size(); j++) {
		    	commentWriter.println(commentList.get(j).getGame() + "," + commentList.get(j).getUsername() +
		    			"," + commentList.get(j).getComment());
		    }
		    
		    //new comment
		    if(!CommentWindow.getDeleting()) {
		    	commentWriter.println(CommentWindow.output());
		    }
		    
		    commentWriter.close();
		    
		} catch (FileNotFoundException e) {
			System.out.println("WRITE FILE NOT FOUND");
		}
	}
	
	//Write to login file
	public static void writeToLogin() {
		//Logins
	    
	    PrintWriter loginWriter;
		try {
			loginWriter = new PrintWriter("logindatabase.csv");
			//old logins
		    for(int k = 0; k < loginList.size(); k++) {
		    	loginWriter.println(loginList.get(k).getUsername() + "," + loginList.get(k).getPassword());
		    }
		    
		   //new login
		    loginWriter.println(Login.newReg());
		    loginWriter.close();
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
