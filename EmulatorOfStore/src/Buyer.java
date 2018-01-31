import java.util.ArrayList;
import java.util.Random;

/**
 * Provides minimal functionalities which describe a buyer.
 * @author Nagay Valentyn
 *
 */
public class Buyer {
	private final int MAX_NUMBER_PRODUCT_FOR_BUYING = 10;
	private int id;
	
	/**
	 * Creates a buyer 
	 * @param id uses for identification the buyer
	 */
	public Buyer(int id) {
		this.id = id;
	}
	
	/**
	 * Provides a function for buying drinks from the shop
	 * @param drinks
	 * @param shop
	 * @param day
	 * @param hour
	 */
	public void buyRandomProducts(ArrayList<Drink> drinks, Shop shop, int day, int hour) {
		Random rand = new Random();
		int quantityOfProducts = rand.nextInt(MAX_NUMBER_PRODUCT_FOR_BUYING) + 1;
		
		System.out.println("Buyer with id = " + id + " has bought those drinks:");
		int i = 0;
		while(i < quantityOfProducts) {
			
			// checking whether the shop is empty or not
			boolean isEmpty = false;
			for (Drink drink : drinks) {
				if (drink.getQuantity() == 0) isEmpty = true;
				else {
					isEmpty = false;
					break;
				}
			}
			
			// if the shop isn't empty
			if(!isEmpty) {
				Drink soldDrink = drinks.get(rand.nextInt(drinks.size())); // gets the random drink for selling
				int quantityOfOneProduct = rand.nextInt(quantityOfProducts) + 1; // sets the random quantity of the drinks
				
				if ((i + quantityOfOneProduct) <= quantityOfProducts) {
					if (soldDrink.getQuantity() >= quantityOfOneProduct) {
						
						// chooses the politic of the mark up for selling drinks
						if (quantityOfOneProduct > 2) {
							shop.applyGrossMarkUp(soldDrink, quantityOfOneProduct);
						} else if (hour >= 18 && hour <= 20) {
							shop.applyEveningMarkUp(soldDrink, quantityOfOneProduct);
						} else if ((day % 6 == 0) || (day % 7 == 0)) {
							shop.applyWeekendMarkUp(soldDrink, quantityOfOneProduct);
						} else {
							shop.applyStandardMarkUp(soldDrink, quantityOfOneProduct);
						}
						
						i += quantityOfOneProduct;
					} else {
						continue;
					}
				} else {
					continue;
				}
			} else {
				System.out.println("The shop is empty today. Come here tomorrow, please.");
				break;
			}	
		}
	}
	
}
