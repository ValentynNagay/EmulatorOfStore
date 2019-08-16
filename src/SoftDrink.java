/**
 *  Describes a soft drink
 * @author Nagay Valentyn
 *
 */
public class SoftDrink extends Drink {
	private String group;
	private String composition;
	
	/**
	 * Constructs an object which describes a soft drink in a store.
	 * {@inheritDoc}
	 * @param group
	 * @param composition
	 */
	public SoftDrink(String title, double purchasePrice, double volume, int availability, int quantityOfSold,
			int quantityOfPurchased, String group, String composition) {
		super(title, purchasePrice, volume, availability, quantityOfSold, quantityOfPurchased);
		this.group = group;
		this.composition = composition;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return super.toString() + " Group: " + group + "Composition: " + composition;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public String toStringForFile() {
		return String.format("Безалкогольный, %s, %.2f, %s, %.2f, %s, %d, %d, %d", getTitle(), getPurchasePrice(),
				group, getVolume(), composition, getQuantity(), getQuantityOfSold(), getQuantityOfPurchased());
	}
	
	
}
