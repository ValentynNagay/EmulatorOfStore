import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


/**
 * Represents an store with appropriate features.
 * @author Nagay Valentyn
 *
 */
public class Shop {
	
	private final int OPENING_TIME = 8;
	private final int CLOSING_TIME = 21;
	private final int NUMBER_DAYS_OF_MONTH = 30;
	private final int NUMBER_HOURS_OF_DAY = 24;
	private final int MAX_NUMBER_OF_BUYERS = 10;
	
	private double income = 0;
	private double expense = 0;
	private ArrayList<Drink> drinks = new ArrayList<>();
	
	// initialize the local
	{
		Locale.setDefault(new Locale("en", "US"));
	}
	
	public static void main(String[] args) {
		new Shop().emulate();	
	}
	
	/**
	 * Emulates for one month the working a drink shop
	 */
	public void emulate()  {
		try {
			initializeShop();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	
		for (int day = 1; day <= NUMBER_DAYS_OF_MONTH; day++) {
			for (int hour = 0; hour < NUMBER_HOURS_OF_DAY; hour++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				if (isOpenedShop(hour)) {
					System.out.println("**************************************************************************");
					System.out.println("* Date: " + day + " day of the month. Hour: " + hour + " of the day --> the shop is opened. *");
					
					if (hour == OPENING_TIME) {
						System.out.println("----------------------------------------------------------------------\n"
								+ "Opening of the shop...");
					}
						
					
					
					Buyer[] buyers = new Buyer[new Random().nextInt(MAX_NUMBER_OF_BUYERS) + 1]; // arriving buyers each hour
					for (int i = 0; i < buyers.length; i++) {
						buyers[i] = new Buyer(i + 1);
					}
					System.out.println("* In the shop are " + buyers.length + " buyers now.\t\t\t\t\t\t *");
					System.out.println("**************************************************************************");
					for (Buyer buyer : buyers) {
						buyer.buyRandomProducts(drinks, this, day, hour);
					}
					
					if (hour == CLOSING_TIME) {
						for (Drink drink : drinks) {
							makeAdditionalPurchase(drink);
						}
						System.out.println("Additional purchase of the drinks...");
						System.out.println("Closing of the shop..."
								+ "\n-----------------------------------------------------------------------");
					}
				} else {
					System.out.println("Date: " + day + " day of the month. Hour: " + hour + " of the day --> the shop is closed.");
				}
			}
		}
		makeReport();
		saveToFile();
	}
	
	/**
	 * Applies the standard mark up for selling a drink
	 * @param drink 
	 * @param quantity
	 */
	public void applyStandardMarkUp(Drink drink, int quantity) {
		income += (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.10)) * quantity;
		drink.addQuantityOfSold(quantity);
		
		System.out.println("Sold: " + quantity + " x " + drink.getTitle());
		System.out.printf("Price: %.2f%n", (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.10)));
		System.out.println("Applied the standard mark-up." + "\n-------------------------------");
	}
	
	/**
	 * Applies the weekend mark up for selling a drink
	 * @param drink
	 * @param quantity
	 */
	public void applyWeekendMarkUp(Drink drink, int quantity) {
		income += (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.15)) * quantity;
		drink.addQuantityOfSold(quantity);
		
		System.out.println("Sold: " + quantity + " x " + drink.getTitle());
		System.out.printf("Price: %.2f%n", (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.15)) * quantity);
		System.out.println("Applied the weekend mark-up." + "\n-------------------------------");
	}
	
	/**
	 * Applies the evening mark up for selling a drink
	 * @param drink
	 * @param quantity
	 */
	public void applyEveningMarkUp(Drink drink, int quantity) {
		income += (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.08)) * quantity;
		drink.addQuantityOfSold(quantity);
		
		System.out.println("Sold: " + quantity + " x " + drink.getTitle());
		System.out.printf("Price: %.2f%n", (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.08)) * quantity);
		System.out.println("Applied the evening mark-up." + "\n-------------------------------");
	}
	
	/**
	 * Applies the gross mark up for selling a drink
	 * @param drink
	 * @param quantity
	 */
	public void applyGrossMarkUp(Drink drink, int quantity) {
		income += (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.10)) * 2;
		income += (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.07)) * (quantity - 2);
		drink.addQuantityOfSold(quantity);
		
		System.out.println("Sold: " + quantity + " x " + drink.getTitle());
		System.out.printf("Price for the first two units: %.2f%n", (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.10)));
		System.out.printf("Price for other units: %.2f%n", (drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.07)));
		System.out.println("Applied the gross mark-up." + "\n-------------------------------");
    }
	
	/**
	 * Initializes shop's data
	 * @throws FileNotFoundException if file has not found
	 */
	private void initializeShop() throws FileNotFoundException {
		Scanner in = new Scanner(new File(System.getProperty("user.dir") + "/src/main/resources/data.csv"), "UTF-8");
		
		while (in.hasNext()) {
			String [] fields = in.nextLine().split(", ");
			
			String typeOfDrink = fields[0];
			try {
				if (typeOfDrink.contains("Alcoholic")) {
					/* **********************************
					 * fields[1] is a title
					 * fields[2] is a purchase price
					 * fields[3] is a group
					 * fields[4] is a volume
					 * fields[5] is a strength
					 * fields[6] is an availability
					 * fields[7] is an quantity of sold
					 * fields[8] is an quantity of purchased
					 * **********************************/
					drinks.add(new AlcoholDrink(fields[1], Double.parseDouble(fields[2]), Double.parseDouble(fields[4]),
							Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Integer.parseInt(fields[8]), fields[3], fields[5]));
				} else if (typeOfDrink.contains("Non-alcoholic")) {
					/* **********************************
					 * fields[1] is a title
					 * fields[2] is a purchase price
					 * fields[3] is a group
					 * fields[4] is a volume
					 * fields[5] is a composition
					 * fields[6] is an availability
					 * fields[7] is an quantity of sold
					 * fields[8] is an quantity of purchased
					 * **********************************/
					drinks.add(new SoftDrink(fields[1], Double.parseDouble(fields[2]), Double.parseDouble(fields[4]),
							Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Integer.parseInt(fields[8]), fields[3], fields[5]));
				}
			} catch (Exception e) {
				System.err.println("This record is incorrect: " + Arrays.toString(fields));
				continue; // skipping an incorrect record;
			}
		}
		in.close();
	}

	
	
	/**
	 * Checks whether the shop is opened or not
	 * @param hour is the parameter for checking
	 * @return
	 */
	private boolean isOpenedShop(int hour) {
		return (hour >= OPENING_TIME) && (hour <= CLOSING_TIME); 
	}
	
	/**
	 * Makes additional purchase for the shop
	 * @param drink is the parameter for checking
	 */
	private void makeAdditionalPurchase (Drink drink) {
		if (drink.getQuantity() < 10) {
			drink.purchaseProduct(150);
			expense -= (150 * drink.getPurchasePrice());
		}
	}
	
	
	/**
	 * Makes month's report for the shop 
	 */
	private void makeReport() {
		System.out.println("\n\n\t\t\t ***REPORT***\n*************************************************************");
		System.out.println("The number dosetupnode/sold for each item:");
		for (Drink drink : drinks) {
			System.out.println("Drink: " + drink.getTitle() + " Sold: " + drink.getQuantityOfSold());
			System.out.println("Drink: " + drink.getTitle() + " Bought: " + drink.getQuantityOfPurchased());
		}
		System.out.println("-------------------------------------------------------------");
		System.out.printf("The income of the shop from sales: %.2f%n", income + expense);
		System.out.printf("Purchased for the sum of: %.2f%n", -(expense));
		System.out.println("*************************************************************");
		System.out.println("Information in the data file has been updated");

	}
	
	/**
	 * Updates data info for the shop by rewriting the data file
	 */
	private void saveToFile() {
		try {
			PrintWriter writer = new PrintWriter("data.csv");
			for (Drink drink : drinks) {
				writer.println(drink.toStringForFile());
			}
			writer.flush();
			writer.close();

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	
}



