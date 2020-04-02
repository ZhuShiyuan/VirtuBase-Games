import java.util.ArrayList;
import java.util.Arrays;
public class KeywordIndex {
	
	private ArrayList<String> keywordList;
	
	
	/**
	 * Reads most current keyword list from file.
	 * @param list - array of Strings to be converted to keywordList
	 */
	public void readList(String[] list) {
		keywordList = new ArrayList<String>(Arrays.asList(list));
	}
	
	
	/**
	 * Convert keywordList to array of Strings to be written to file.
	 * @return array of keywords to be written to file.
	 */
	public String[] writeList() {
		String[] toFileList = new String[keywordList.size()];
		for (int i = 0; i < toFileList.length; i++) {
			toFileList[i] = keywordList.get(i);
		}
		return toFileList;
	}
	
	/**
	 * Add a new keyword to the list, while checking for redundant entries.
	 * @param newKeyword - new keyword to be added to the list.
	 * @return true if keyword was added, false if keyword was already present.
	 */
	public boolean addKeyword(String newKeyword) {
		for (int i = 0; i < keywordList.size(); i++) {
			if (newKeyword.equals(keywordList.get(i))) return false;
		}
		keywordList.add(newKeyword);
		return true;
	}
	
	/**
	 * Correlate an index to its keyword and return it.
	 * @param index - index of keyword.
	 * @return keyword that correlates to index as a String.
	 * @throws ArrayIndexOutOfBoundsException - thrown if searched index is outside the bounds of the keywordList.
	 */
	public String findKeyword(int index) throws ArrayIndexOutOfBoundsException {
		if (index == -1) return "";
		else if (index < keywordList.size() && index >= 0) return keywordList.get(index);
		else throw new ArrayIndexOutOfBoundsException("Invalid keyword entry, ensure using most current keyword list.");
	}
	
}
