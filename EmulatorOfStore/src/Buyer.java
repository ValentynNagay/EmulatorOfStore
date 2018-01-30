import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Provides minimal functionalities which describe a buyer.
 * @author Nagay Valentyn
 *
 */
public class Buyer {
	private final int MAX_NUMBER_PRODUCT_FOR_BUYING = 10;
	
	private int id;
	

	public Buyer(int id) {
		this.id = id;
	}
	
	
	
	public void buyRandomProducts(ArrayList<Drink> drinks, Shop shop, int day, int hour) {
		System.out.println("Buyer with id = " + id + " has bought those drinks:");
		Random rand = new Random();
		int quantityOfProducts = rand.nextInt(MAX_NUMBER_PRODUCT_FOR_BUYING) + 1;
		
		
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
			
			if(!isEmpty) {
				Drink soldDrink = drinks.get(rand.nextInt(drinks.size()));
				
				//quantityOfOneProduct = quantityOfProducts - quantityOfOneProduct;
				int quantityOfOneProduct = rand.nextInt(quantityOfProducts) + 1;
				
				if ((i + quantityOfOneProduct) <= quantityOfProducts) {
					if (soldDrink.getQuantity() >= quantityOfOneProduct) {
						
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
