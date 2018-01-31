/**
 * Describes an alcohol drink
 * @author Nagay Valentyn
 *
 */
public class AlcoholDrink extends Drink {
	private String classification;
	private String strength;
	
	/**
	 * Constructs an object which describes an alcohol drink in a store.
	 * {@inheritDoc}
	 * @param classification which a type of alcohol
	 * @param strength the capacity of alcohol
	 */
	public AlcoholDrink(String title, double purchasePrice, double volume, int availability, int quantityOfSold,
			int quantityOfPurchased, String classification, String strength) {
		super(title, purchasePrice, volume, availability, quantityOfSold, quantityOfPurchased);
		this.classification = classification;
		this.strength = strength;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString() + "Classification: " + classification + " Strength: " + strength;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toStringForFile() {
		return String.format("Алкогольный, %s, %.2f, %s, %.2f, %s, %d, %d, %d", getTitle(), getPurchasePrice(),
				classification, getVolume(), strength, getQuantity(), getQuantityOfSold(), getQuantityOfPurchased());
	}
	

}
