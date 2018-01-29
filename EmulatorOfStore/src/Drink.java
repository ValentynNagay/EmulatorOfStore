
/**
 * Presents a drink in general case.
 * @author Nagay Valentyn
 *
 */
public abstract class Drink {
	private String title;
	private double purchasePrice;
	private double volume;
	private int availability;
	
	/**
	 * Constructs an object which describes a general drink in a store.
	 * @param title a title of a drink
	 * @param purchasePrice how much it cost
	 * @param volume a capacity of bottle or something else
	 * @param availability a quantity of it
	 */
	public Drink(String title, double purchasePrice, double volume, int availability) {
		this.title = title;
		this.purchasePrice = purchasePrice;
		this.volume = volume;
		this.availability = availability;
	}

	public String getTitle() {
		return title;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public double getVolume() {
		return volume;
	}

	public int getAvailability() {
		return availability;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}	
}
