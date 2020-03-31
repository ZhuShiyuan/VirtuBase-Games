
public class GameData implements Comparable<GameData> {

	private String title;
	private String console;
	private int year;
	private double sales;
	
	//Creates an object that holds the data on one single game
	public GameData(String title, String console, int year, double sales) {
		this.title = title;
		this.console = console;
		this.year = year;
		this.sales = sales;
	}

	// getters
	public String getTitle() {
		return this.title;
	}

	public String getConsole() {
		return this.console;
	}

	public int getYear() {
		return this.year;
	}

	public double getSales() {
		return this.sales;
	}

	// setters
	public void setTitle(String title) {
		this.title = title;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	//compare to - by title, console, year, sales
	public int compareTo(GameData other) {
		if (this.title.compareTo(other.title) > 0) {
			return 1;
		} else if (this.title.compareTo(other.title) < 0) {
			return -1;
		} else {
			if (this.console.compareTo(other.console) > 0) {
				return 1;
			} else if (this.console.compareTo(other.console) < 0) {
				return -1;
			} else {
				if (this.year < other.year) {
					return 1;
				} else if (this.year > other.year) {
					return -1;
				} else {
					if (this.sales < other.sales) {
						return 1;
					} else if (this.sales > other.sales) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		}
	}

}
