import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private Random rand = new Random();
	
	private double income = 0;
	private double expense = 0;
	
	private ArrayList<Drink> drinks = new ArrayList<>();
	
	
	private void initializeStore() throws FileNotFoundException {
		Scanner in = new Scanner(new File("data.csv"));
		
		while (in.hasNext()) {
			String [] fields = in.nextLine().split(", ");
			
			String typeOfDrink = fields[0];
			try {
				if (typeOfDrink.equalsIgnoreCase("﻿Безалкогольный")) {
					/*
					 * fields[1] is a title
					 * fields[2] is a purchase price
					 * fields[3] is a group
					 * fields[4] is a volume
					 * fields[5] is a composition
					 * fields[6] is an availability
					 */
					drinks.add(new SoftDrink(fields[1], Double.parseDouble(fields[2]), Double.parseDouble(fields[4]),
							Integer.parseInt(fields[6]), fields[3], fields[5]));
				} else if (typeOfDrink.equalsIgnoreCase("Алкогольный")) {
					/*
					 * fields[1] is a title
					 * fields[2] is a purchase price
					 * fields[3] is a group
					 * fields[4] is a volume
					 * fields[5] is a strength
					 * fields[6] is an availability
					 */
					drinks.add(new AlcoholDrink(fields[1], Double.parseDouble(fields[2]), Double.parseDouble(fields[4]),
							Integer.parseInt(fields[6]), fields[3], fields[5]));
				} 
			} catch (Exception e) {
				System.err.println("This record is incorrect: " + Arrays.toString(fields));
				continue; // skipping an incorrect record;
			}
			
			
		}
		System.out.println(drinks);
	}

	
	
	/**
	 * 
	 * @param hour
	 * @return
	 */
	public boolean isOpenedStore(int hour) {
		return (hour >= 8) && (hour <= 21); 
	}
	
	/**
	 * 
	 * @param drink
	 */
	public void makeAdditionalPurchase (Drink drink) {
		if (drink.getQuantity() < 10) {
			drink.purchaseProduct(150);
			expense -= (150 * drink.getPurchasePrice());
		}
	}
	
	public void emulate() {
		for (int i = 0; i < NUMBER_DAYS_OF_MONTH; i++) {
			for (int j = 0; j < NUMBER_HOURS_OF_DAY; j++) {
				if (isOpenedStore(j)) {
					Buyer[] buyers = new Buyer[rand.nextInt(MAX_NUMBER_OF_BUYERS) + 1]; // arriving buyers each hour
					
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void applyStandardMarkUp(Drink drink, int quantity) {
		income += drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.10);
		drink.addQuantityOfSold(quantity);
	}
	
	/**
	 * 
	 */
	public void applyWeekendMarkUp(Drink drink, int quantity) {
		income += drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.15);
		drink.addQuantityOfSold(quantity);
	}
	
	/**
	 * 
	 */
	public void applyEveningMarkUp(Drink drink, int quantity) {
		income += drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.08);
		drink.addQuantityOfSold(quantity);
	}
	
	/**
	 * 
	 */
	public void applyGrossMarkUp(Drink drink, int quantity) {
		income += drink.getPurchasePrice() + (drink.getPurchasePrice() * 0.07);
		drink.addQuantityOfSold(quantity);
    }
	
	public static void main(String[] args) {
		try {
			new Shop().initializeStore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



