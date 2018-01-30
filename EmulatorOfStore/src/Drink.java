/**
 * Presents a drink in general case.
 * @author Nagay Valentyn
 *
 */
public class Drink {
	private String title;
	private double purchasePrice;
	private double volume;
	private int quantity;
	private int quantityOfSold = 0;
	private int quantityOfPurchased = 0;
	
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
		this.quantity = availability;
	}

	/**
	 * Gets the title of a drink
	 * @return the title of a drink
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the purchase price of the drink
	 * @return the purchase price of the drink
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Gets the volume of the drink
	 * @return the volume of the drink
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Gets the quantity of the drink of the shop
	 * @return the quantity of the drink of the shop
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Gets the quantity of sold
	 * @return the quantity of sold
	 */
	public int getQuantityOfSold() {
		return quantityOfSold;
	}
	
	/**
	 * Gets the quantity of purchased 
	 * @return the quantity of purchased
	 */
	public int getQuantityOfPurchased() {
		return quantityOfPurchased;
	}
	
	/**
	 * Adds a sold quantity to drink's information
	 * @param quantity 
	 */
	public void addQuantityOfSold(int quantity) {
		quantityOfSold += quantity;
    	this.quantity -= quantity;
	}
    
    /**
     * Purchases some quantity for increasing quantity of the drink
     * @param quantity how much it needs to buy
     */
    public void purchaseProduct(int quantity) {
    	quantityOfPurchased += quantity;
    	this.quantity += quantity;
    }
    
    /**
     * Overrides for showing standard information about the drink
     * @return the standard information about the drink
     */
    @Override
    public String toString() {
    	return "Title: " + title + String.format("Volume: %.2f", volume);
    }
}
