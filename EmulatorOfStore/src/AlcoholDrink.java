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
	public AlcoholDrink(String title, double purchasePrice, double volume, int availability, String classification,
			String strength) {
		super(title, purchasePrice, volume, availability);
		this.classification = classification;
		this.strength = strength;
	}
	
	

}
