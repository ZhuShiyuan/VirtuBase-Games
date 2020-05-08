import java.net.URL;
import java.util.ArrayList;

public class GameData {

	private String title;
	private String company;
	private URL wiki;
	private int[] indexList;
	private String[] keywords = new String[3];
	private ArrayList<Storefront> storefronts;
	private int storefrontCount;
	public KeywordIndex keywordIndex;
	private int visible;
	
	/**
	 * Instantiate a new gameData.
	 * @param title - String - Title of the game.
	 * @param company - String - Company that made the game.
	 * @param wiki - URL - Link to the game's wiki.
	 * @param indexList - int[] - List of keywords associated to the game.
	 * @param vis - String - Visibility of the game, as set by an Admin account.
	 * @param storefronts - ArrayList<Storefront> - List of storefronts that sell the game, of an unspecified length.
	 * @param keywordIndex - KeywordIndex - Index of keywords to be referenced by indexList.
	 */
	public GameData(String title, String company, URL wiki, int[] indexList, String vis, ArrayList<Storefront> storefronts, KeywordIndex keywordIndex) {
		this.title = title;
		this.company = company;
		this.wiki = wiki;
		this.indexList = indexList;
		this.keywordIndex =keywordIndex;
		this.visible = Integer.parseInt(vis);
		this.storefronts = storefronts;
		this.storefrontCount = storefronts.size();
		for (int i = 0; i < keywords.length; i++) {
			try {
				setKeyword(i, this.keywordIndex.findKeyword(indexList[i]));
			} catch (ArrayIndexOutOfBoundsException e) {
				setKeyword(indexList[i], "");
			}
		}
		
		
	}

	// Getter Methods
	/**
	 * Returns the title of the game.
	 * @return title of the game as a String.
	 */
	public String getTitle() { return this.title; }
	/**
	 * Returns the company that made the game.
	 * @return company that made the game as a String.
	 */
	public String getCompany() { return this.company; }
	/**
	 * Returns the wiki URL.
	 * @return wiki as a URL.
	 */
	public URL getWiki() { return this.wiki; }
	/**
	 * Returns the name of the keyword being searched.
	 * @param index - int - Desired index to be searched.
	 * @return name of the keyword as a String, if searched index is outside of the valid range, return "ERROR".
	 */
	public String getKeyword(int index) {
		if (index < 3 && index >= 0) return this.keywords[index];
		else return "ERROR";
	}
	/**
	 * Returns the int value of the index being searched.
	 * @param i - int - Desired index to be searched.
	 * @return value of the index as an int.
	 */
	public int getIndexNums(int i) { return this.indexList[i]; }
	/**
	 * Returns the visibility of the game.
	 * @return visibility of the game as a boolean.
	 */
	public boolean getVisible() {
		if(this.visible == 1) return true;
		return false;
	}
	/**
	 * Returns the visibility of the game.
	 * @return visibility of the game as an int.
	 */
	public int getVisibleInt() { return this.visible; }
	/**
	 * Returns the number of storefronts associated with this game.
	 * @return number of storefronts as an int.
	 */
	public int getStorefrontCount() { return this.storefrontCount; }
	/**
	 * Returns the storefront being searched.
	 * @param index - int - Desired index to be searched.
	 * @return storefront as a Storefront.
	 */
	public Storefront getStorefront(int index) {
		if (index < storefrontCount && index >= 0) return this.storefronts.get(index);
		else return null;
	}

	// Setter Methods
	/**
	 * Sets a new title for the game.
	 * @param title - String - new title.
	 */
	public void setTitle(String title) { this.title = title; }
	/**
	 * Sets a new company for the game.
	 * @param company - String - new company.
	 */
	public void setCompany(String company) { this.company = company; }
	/**
	 * Sets a new wiki link for the game.
	 * @param wiki - URL - new wiki link.
	 */
	public void setWiki(URL wiki) { this.wiki = wiki; }
	/**
	 * Sets a new visibility state for the game.
	 * @param num - int - new visibility state.
	 */
	public void setVisible(int num) { this.visible = num; }
	/**
	 * Sets a new keyword at a specified index for the game.
	 * @param index - int - index to be changed.
	 * @param keyword - String - new keyword definition.
	 * @return true if a change was made, otherwise no action is taken and return false.
	 */
	public boolean setKeyword(int index, String keyword) {
		try {
			keywordIndex.addKeyword(keyword);
			this.keywords[index] = keyword;
			this.indexList[index] = keywordIndex.findIndex(keyword);
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * Convert data members into a usable String for file writing.
	 * Title,Company,WikiURL,Keyword1,Keyword2,Keyword3,visibility,Storefronts
	 * @return data members combined into a single String.
	 */
	public String prepForFile() {
		String storeString = "";
		for (int i = 0; i < storefronts.size(); i++) {
			storeString = storeString + storefronts.get(i).prepForFile() + ",";
		}
		return 	this.title + "," + 
				this.company + "," + 
				this.wiki.toString() + "," + 
				indexList[0] + "," + 
				indexList[1] + "," + 
				indexList[2] + "," +
				visible + "," +
				storeString;
	}
	
	/**
	 * Rank the level of recommendation based on shared company and keywords.
	 * @param other - the other Game being compared to.
	 * @return a rank from 0 - 4 that determines level of recommendation.
	 */
	public int recommend(GameData other) {
		int recommendRating = 0;
		if (this.company.equals(other.getCompany())) recommendRating++;
		for (int i = 0; i < this.indexList.length; i++) {
			if (this.keywords[i].contentEquals(other.getKeyword(i))) recommendRating++;
		}
		return recommendRating;
	}

}
