/*
 * 
 *  Name: FastShopper.java
 *  Purpose: Represents a Fast Shopper in the supermarket
 *  Author: grivera64
 *  Date: 04/07/2021
 *  
 */

import java.util.Random;

public class FastShopper extends Shopper
{
	
	public static int fastShopperCounter = 0;
	public static final int TIMEWITHCHECKER = 1;
	
	private String fastShopperID;
	private int startTime;
	private int timeShopping;
	private int shoppingTimeRemaining;
	private int timeIntoCheckoutLine;
	private int timeOutOfCheckoutLine;
	private int endTime;
	private int totalTimeCheckingOut;
	private int totalTimeInStore;
	
	private Random randy;
	
	public FastShopper(int startTime)
	{
		
		super("FastShopper");
		this.randy = new Random(fastShopperCounter);
		this.setFastShopperID();
		this.setStartTime(startTime);
		this.setTimeShopping();
		this.setShoppingTimeRemaining(this.getTimeShopping());
		
	}
	
	public void setStartTime(int startTime)
	{
		
		this.startTime = startTime;
		
	}
	
	public int getStartTime()
	{
		
		return this.startTime;
		
	}
	
	public int getTimeShopping()
	{
		
		return this.timeShopping;
		
	}
	
	public int getShoppingTimeRemaining()
	{
		
		return this.shoppingTimeRemaining;
		
	}
	
	public void setShoppingTimeRemaining(int shoppingTimeRemaining)
	{
		
		this.shoppingTimeRemaining = shoppingTimeRemaining;
		
	}
	
	public int getTimeIntoCheckoutLine()
	{
		
		return this.timeIntoCheckoutLine;
		
	}
	
	public void setTimeIntoCheckoutLine(int timeIntoCheckoutLine)
	{
		
		this.timeIntoCheckoutLine = timeIntoCheckoutLine;
		
	}
	
	public int getTimeOutOfCheckoutLine()
	{
		
		return this.timeOutOfCheckoutLine;
		
	}
	
	public int getEndTime()
	{
		
		return this.endTime;
		
	}
	
	public int getTotalTimeCheckingOut()
	{
		
		return this.totalTimeCheckingOut;
		
	}
	
	public int getTotalTimeInStore()
	{
		
		return this.totalTimeInStore;
		
	}
	
	public void setFastShopperID()
	{
		
		FastShopper.fastShopperCounter++;
		this.fastShopperID = this.getShopperType() + FastShopper.fastShopperCounter;
		
	}
	
	public String getFastShopperID()
	{
		
		return this.fastShopperID;
		
	}
	
	public void setTimeShopping()
	{
		
		this.timeShopping = this.randy.nextInt(6) + 1;
		
	}
	
	public void setTimeOutOfCheckoutLine(int timeOutOfCheckoutLine)
	{
		
		this.timeOutOfCheckoutLine = timeOutOfCheckoutLine;
		
		this.calculateFinalDurations();
		
	}
	
	public void calculateFinalDurations()
	{
		
		this.endTime = this.getTimeOutOfCheckoutLine() + FastShopper.TIMEWITHCHECKER;
		
		this.totalTimeCheckingOut = 
				Math.abs(this.getEndTime() - this.getTimeIntoCheckoutLine());
		
		this.totalTimeInStore = 
				Math.abs(this.getEndTime() - this.getStartTime());
		
	}
		
	public String toString()
	{
		
		return String.format("%14s%9d%11d%16d%16d%16d\n", 
				this.getFastShopperID(), this.getStartTime(), this.getEndTime(),
				this.getTimeShopping(), this.getTotalTimeCheckingOut(),
				this.getTotalTimeInStore());
		
	}
	
}