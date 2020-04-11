import java.net.URL;

public class Storefront {
	
	private String name;
	private double price;
	private URL linkToSite;
	
	public Storefront(String name, double price, URL linkToSite) {
		this.name = name;
		this.price = price;
		this.linkToSite = linkToSite;
	}
	
	// Getter Methods
	public String getName() { return this.name; }
	public double getPrice() { return this.price; }
	public URL getLink() { return this.linkToSite; }
	
	// Setter Methods
	public void setName(String name) { this.name = name; }
	public void setPrice(double price) { this.price = price; }
	public void setLink(URL linkToSite) { this.linkToSite = linkToSite; }
	
	/**
	 * Convert data members into a usable String for file writing.
	 * @return data members combined into a single String.
	 */
	public String prepForFile() {
		return this.name + "," + this.price + "," + this.linkToSite.toString();
	}
	
}
