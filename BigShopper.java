import java.util.Random;

/*
 * 
 * Name: BigShopper.java
 * Purpose: Represents a shopper in the Supermarket
 * Author: grivera64
 * Date: 04/08/2021
 * 
*/

public class BigShopper extends Shopper
{
	
	public static int bigShopperCounter = 0;
	
	public static final int TIMEWITHCHECKER = 2;
	
	private String bigShopperID;
	private int startTime;
	private int timeShopping;
	private int shoppingTimeRemaining;
	private int timeIntoCheckoutLine;
	private int timeOutOfCheckoutLine;
	private int endTime;
	private int totalTimeCheckingOut;
	private int totalTimeInStore;
	
	private Random randy;
	
	public BigShopper(int startTime)
	{
		
		super("BigShopper");
		this.randy = new Random(bigShopperCounter);
		this.setBigShopperID();
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
	
	public void setBigShopperID()
	{
		
		BigShopper.bigShopperCounter++;
		this.bigShopperID = this.getShopperType() + BigShopper.bigShopperCounter;
		
	}
	
	public String getBigShopperID()
	{
		
		return this.bigShopperID;
		
	}
	
	public void setTimeShopping()
	{
		
		this.timeShopping = this.randy.nextInt(11) + 5;
		
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
				this.getBigShopperID(), this.getStartTime(), this.getEndTime(),
				this.getTimeShopping(), this.getTotalTimeCheckingOut(),
				this.getTotalTimeInStore());
		
	}
	
	
}