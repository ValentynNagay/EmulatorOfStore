import java.util.ArrayList;

/**
 *  Describes a soft drink
 * @author Nagay Valentyn
 *
 */
public class SoftDrink extends Drink {
	private String group;
	private ArrayList<String> composition;
	
	/**
	 * Constructs an object which describes a soft drink in a store.
	 * {@inheritDoc}
	 * @param group
	 * @param composition
	 */
	public SoftDrink(String title, double purchasePrice, double volume, int availability, String group,
			ArrayList<String> composition) {
		super(title, purchasePrice, volume, availability);
		this.group = group;
		this.composition = composition;
	}
	
	
}
