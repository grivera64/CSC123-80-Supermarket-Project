/*
 *
 * Name: Supermarket.java
 * Purpose: Manages the simulation of a supermarket
 * Author: grivera64
 * Date: 04/08/2021
 * 
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

public class Supermarket
{
	
	private String superName;
	private Random randy = new Random(5);
	private int bigCheckerOccupied = 0;
	private int fastCheckerOccupied = 0;
	private ArrayList<Shopper> currentShoppers = new ArrayList<Shopper>();
	private ArrayList<BigShopper> bigCheckOut = new ArrayList<BigShopper>();
	private ArrayList<FastShopper> fastCheckOut = new ArrayList<FastShopper>();
	private ArrayList<Shopper> doneShopping = new ArrayList<Shopper>();
	
	private double avgTimeBig = 0, 
				avgTimeFast = 0;
	private int numOfBig = 0,
				numOfFast = 0;
	
	public Supermarket(String superName)
	{
		
		this.setSuperName(superName);
		
	}
	
	public String getSuperName()
	{
		
		return this.superName;
		
	}
	
	public void setSuperName(String superName)
	{
		
		this.superName = superName;
		
	}
	
	public void openSupermarket()
	{
		
		Shopper currShopper;
		
		for (int i = 0; i <= 14; i++)
		{
			
			if (i % 3 == 0)
			{
				
				currShopper = new BigShopper(0);
				
			}
			else
			{
				
				currShopper = new FastShopper(0);
				
			}
			
			this.currentShoppers.add(currShopper);
			
		}
		
	}
	
	public void operateSupermarket(int minutes)
	{
		
		int minCounter = 1;
		
		Shopper currShopper;
		Shopper tempShopper;
		
		while (!this.currentShoppers.isEmpty() || !this.bigCheckOut.isEmpty() 
				|| !this.fastCheckOut.isEmpty())
		{
			
			if (minCounter <= minutes  && minCounter % 5 == 0)
			{
				
				for (int i = 0; i < 9; i++)
				{
					
					if (i % 3 == 0)
					{
						
						currShopper = new BigShopper(minCounter);
						
					}
					else
					{
						
						currShopper = new FastShopper(minCounter);
						
					}
					
					this.currentShoppers.add(currShopper);
					
				}
				
			}
			
			for (int i = 0; i < this.currentShoppers.size(); i++)
			{
				
				tempShopper = this.currentShoppers.get(i);
				
				tempShopper.setShoppingTimeRemaining(
						tempShopper.getShoppingTimeRemaining() - 1
						);
				
				if (tempShopper.getShoppingTimeRemaining() == 0)
				{
					
					if (tempShopper instanceof BigShopper)
					{
						
						BigShopper tempBig = (BigShopper) tempShopper;
						
						tempBig.setTimeIntoCheckoutLine(minCounter);
						
						this.bigCheckOut.add(tempBig);
						
					}
					else
					{
						
						FastShopper tempFast = (FastShopper) tempShopper;
						
						tempFast.setTimeIntoCheckoutLine(minCounter);
						
						this.fastCheckOut.add(tempFast);
						
					}
					
					this.currentShoppers.remove(tempShopper);
					
					i--;
					
				}
				
			}
			
			if (!this.bigCheckOut.isEmpty() && this.bigCheckerOccupied == 0)
			{
				
				BigShopper tempBig = this.bigCheckOut.remove(0);
				
				tempBig.setTimeOutOfCheckoutLine(minCounter);
				
				this.doneShopping.add(tempBig);
				
				this.bigCheckerOccupied = BigShopper.TIMEWITHCHECKER;
				
			}
			else if (this.bigCheckerOccupied != 0)
			{
				
				this.bigCheckerOccupied--;
				
			}
			
			if (!this.fastCheckOut.isEmpty() && this.fastCheckerOccupied == 0)
			{
				
				FastShopper tempFast = this.fastCheckOut.remove(0);
				
				tempFast.setTimeOutOfCheckoutLine(minCounter);
				
				this.doneShopping.add(tempFast);
				
				this.fastCheckerOccupied = FastShopper.TIMEWITHCHECKER;
				
			}
			else if (this.fastCheckerOccupied != 0)
			{
				
				this.fastCheckerOccupied--;
				
			}
			
			minCounter++;
		}
		
	}
	
	public void generateSupermarketResults(String fileName) throws IOException
	{
		
		PrintWriter fileWriter = new PrintWriter(new File(fileName));
		
		fileWriter.printf("Data For Supermarket Beheshti Supermarket\n");
		fileWriter.printf("%13s%16s%11s%16s%16s%16s", 
				"Shopper ID", "Start Time", "End Time", "Time Shopping", 
				"Checkout Time", "Time in Store");
		
		Iterator<Shopper> shoppers = this.doneShopping.iterator();
		
		
		shoppers.forEachRemaining((Shopper shopper) -> {
			
			if (shopper instanceof BigShopper)
			{
				
				BigShopper tempBig = (BigShopper) shopper;
				
				fileWriter.printf("%14s%9d%11d%16d%16d16d\n", 
					tempBig.toString(), tempBig.getStartTime(),
					tempBig.getEndTime(), tempBig.getTimeShopping(),
					tempBig.getTotalTimeCheckingOut(),
					tempBig.getTotalTimeInStore()
				);
				
				
				this.avgTimeBig += tempBig.getTotalTimeCheckingOut();
				this.numOfBig++;
				
			}
			else
			{
				
				FastShopper tempFast = (FastShopper) shopper;
				
				fileWriter.printf("%14s%9d%11d%16d%16d16d\n", 
					tempFast.toString(), tempFast.getStartTime(),
					tempFast.getEndTime(), tempFast.getTimeShopping(),
					tempFast.getTotalTimeCheckingOut(),
					tempFast.getTotalTimeInStore()
				);
				
				this.avgTimeFast += tempFast.getTotalTimeCheckingOut();
				this.numOfFast++;
				
			}
			
		});
		
		this.avgTimeFast /= this.numOfFast;
		this.avgTimeBig /= this.numOfBig;
		
		System.out.printf("The average time checking out on the "
				+ "Big Queue for %d Shoppers is %.2f minutes\n", 
				this.numOfBig, this.avgTimeBig);
		
		System.out.printf("The average time checking out on the "
				+ "Fast Queue for %d Shoppers is %.2f minutes\n", 
				this.numOfBig, this.avgTimeBig);
		
		fileWriter.close();
		
	}
	
}