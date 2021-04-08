/*
 * 
 * Name: ModelSupermarket.java
 * Purpose: Serves as the driver class of the program
 * Author: grivera64
 * Date: 04/08/2021
 * 
 */

import java.util.Scanner;
import java.io.IOException;

public class ModelSupermarket
{
	
	public static void main(String[] args) throws IOException
	{
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.printf("Please enter the name of the Supermarket:\n");
		String name = keyboard.nextLine();
		
		Supermarket market = new Supermarket(name);
		
		System.out.printf("Please enter the name of the output file for Supermarket results: ");
		String fileName = keyboard.nextLine();
		
		System.out.printf("Please enter the number of minutes to operate the Supermarket:\n");
		int minutes = keyboard.nextInt();
		keyboard.nextLine();
		
		market.openSupermarket();
		
		market.operateSupermarket(minutes);
		
		market.generateSupermarketResults(fileName);
		
		keyboard.close();
		
	}
	
}