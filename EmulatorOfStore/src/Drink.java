import java.math.BigDecimal;

/**
 * Presents a drink in general case.
 * @author Nagay Valentyn
 *
 */
public abstract class Drink {
	private String title;
	private BigDecimal purchasePrice;
	private double volume;
	private int availability;
	private BigDecimal retailPrice;
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
		this.purchasePrice = new BigDecimal(purchasePrice);
		this.volume = volume;
		this.availability = availability;
		retailPrice = new BigDecimal(purchasePrice);
	}

	public String getTitle() {
		return title;
	}

	public double getPurchasePrice() {
		return purchasePrice.doubleValue();
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
		this.purchasePrice = new BigDecimal(purchasePrice);
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	/**
	 * 
	 */
	public void applyStandardMarkUp() {
		retailPrice = purchasePrice.add(purchasePrice.multiply(new BigDecimal(0.10)));
	}
	
	/**
	 * 
	 */
	public void applyWeekendMarkUp() {
		retailPrice = purchasePrice.add(purchasePrice.multiply(new BigDecimal(0.15)));
	}
	
	/**
	 * 
	 */
	public void applyEveningMarkUp() {
		retailPrice = purchasePrice.add(purchasePrice.multiply(new BigDecimal(0.08)));
	}
	
	/**
	 * 
	 */
    public void applyGrossMarkUp() {
    	retailPrice = purchasePrice.add(purchasePrice.multiply(new BigDecimal(0.07)));
    }
    
    /**
     * 
     * @param quantity
     */
    public void sell(int quantity) {
    	quantityOfSold += quantity;
    	availability -= quantity;
    }
    
    /**
     * 
     * @param quantity
     */
    public void buy(int quantity) {
    	quantityOfPurchased += quantity;
    	availability += quantity;
    }
}
