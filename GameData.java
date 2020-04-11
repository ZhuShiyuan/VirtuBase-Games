import java.net.URL;
import java.util.ArrayList;

public class GameData {

	private String title;
	private String company;
	private URL wiki;
	private int[] indexList;
	private String[] keywords = new String[3];
	private ArrayList<Storefront> storefronts;
	public KeywordIndex keywordIndex;
	
	//Creates an object that holds the data on one single game
	public GameData(String title, String company, URL wiki, int[] indexList, ArrayList<Storefront> storefronts, KeywordIndex keywordIndex) {
		this.title = title;
		this.company = company;
		this.wiki = wiki;
		this.indexList = indexList;
		this.keywordIndex =keywordIndex;
		this.storefronts = storefronts;
		for (int i = 0; i < keywords.length; i++) {
			try {
				setKeyword(indexList[i], this.keywordIndex.findKeyword(indexList[i]));
			} catch (ArrayIndexOutOfBoundsException e) {
				setKeyword(indexList[i], "");
			}
		}
		
		
	}

	// Getter Methods
	public String getTitle() { return this.title; }
	public String getCompany() { return this.company; }
	public URL getWiki() { return this.wiki; }
	public String getKeyword(int index) {
		if (index < 3 && index >= 0) return this.keywords[index];
		else return "ERROR";
	}
	public Storefront getStorefront(int index) {
		if (index < storefronts.size() && index >= 0) return this.storefronts.get(index);
		else return null;
	}

	// Setter Methods
	public void setTitle(String title) { this.title = title; }
	public void setCompany(String company) { this.company = company; }
	public void setWiki(URL wiki) { this.wiki = wiki; }
	public boolean setKeyword(int index, String keyword) {
		try {
			this.keywords[index] = keyword;
			keywordIndex.addKeyword(keyword);
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * Convert data members into a usable String for file writing.
	 * Title,Company,WikiURL,Keyword1,Keyword2,Keyword3,Storefronts
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