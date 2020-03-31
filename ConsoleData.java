
public class ConsoleData implements Comparable<ConsoleData> {

	private String console;
	private double sales;
	//creates an object that holds the type of console and how many sales it had
	public ConsoleData(String console, double sales) {
		this.console = console;
		this.sales = sales;
	}

	// getters
	public String getConsole() {
		return this.console;
	}

	public double getSales() {
		return this.sales;
	}

	// setters
	public void setConsole(String console) {
		this.console = console;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	@Override // compare to 
	public int compareTo(ConsoleData other) {
		if (this.sales < other.sales) {
			return 1;
		} else {
			return -1;
		}
	}
}
