package main;

import java.util.Scanner;

public class Converter {
	//Unit Conversion Method - Grabs user input for value to convert
	private static double collectQuantity(String unit1, String unit2, Scanner userchoice)
	{
		double quantity;
		System.out.println("Enter the value in\t" + unit1 + "\tto convert to\t" +unit2+":\t");
		quantity = userchoice.nextDouble();
		return quantity;
	}
	//Converts Cups to Teaspoons
	public static double CupstoTeaSpoons(double quantity)
	{
		return quantity * 48;
	}
	//Converts Miles to Kilometers
	public static double MilestoKilometers(double quantity)
	{
		return quantity * 1.609;
	}
	//Converts US Gallons to Imperial Gallons
	public static double UStoImperial(double quantity)
	{
		return quantity/1.201;
	}
	public static void main(String[] args) {
		int menuSelection = 0;
		double unit1;
		double unit2;
		Scanner userchoice = new Scanner(System.in);
		while(menuSelection !=4)
		{
			System.out.println("Please select an option:\n 1. Cups to Teaspoons \n"
					+ "2. Miles to Kilometers \n 3. US Gallons to Imperial Gallons \n"
					+ "4. Quit");
		 menuSelection = userchoice.nextInt();
		 //Grabs formulas from functions to execute
			switch(menuSelection)
			{
			case 1:
				unit1 = collectQuantity("cups", "teaspoons", userchoice);
				unit2 = CupstoTeaSpoons(unit1);
				System.out.println(unit2 + "\tteaspoons");
				break;
			case 2:
				unit1 = collectQuantity("miles", "kilometers", userchoice);
				unit2 = MilestoKilometers(unit1);
				System.out.println(unit2 + "\tkilometers");
				break;
			case 3:
				unit1 = collectQuantity("US Gallons", "Imperial Gallons", userchoice);
				unit2 = UStoImperial(unit1);
				System.out.println(unit2 + "\tgallons");
				break;
			case 4:
				System.out.println("Thank you");
				System.exit(0);
				break;
			default:
					System.out.println("Invalid Choice");
			}
			System.out.println();
		}
		userchoice.close();
	}

}
