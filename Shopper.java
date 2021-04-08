/*
 * 
 * Name: Shopper.java
 * Purpose: Represents the basic structure
 * 			of a Shopper in the supermarket
 * Author: grivera64
 * Date: 04/07/2021
 * 
 */

public abstract class Shopper
{
	
	private String shopperType;
	
	public Shopper()
	{
		
		this.setShopperType("");
		
	}
	
	public Shopper(String shopperType)
	{
		
		this.setShopperType(shopperType);
		
	}
	
	public void setShopperType(String shopperType)
	{
		
		this.shopperType = shopperType;
		
	}
	
	public String getShopperType()
	{
		
		return this.shopperType;
		
	}
	
	public void setShoppingType(String shoppingType)
	{
		
		this.shopperType = shoppingType;
		
	}
	
	public abstract void setShoppingTimeRemaining(int shoppingTimeRemaining);
	public abstract int getShoppingTimeRemaining();
	public abstract void setTimeIntoCheckoutLine(int timeIntoCheckoutLine);
	public abstract int getTotalTimeCheckingOut();
	
}