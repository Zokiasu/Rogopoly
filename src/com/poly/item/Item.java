package com.poly.item;

@SuppressWarnings("serial")
public class Item implements java.io.Serializable{
	
	protected String Name;
	protected String Description;
	protected int ID;
	protected int price;
	protected int priceSale;

	public Item() {/**/}

	public String getDescription() { return Description; }
	public int getPriceSale() { return priceSale; }
	public int getID() { return ID; }
	public String getName() { return Name; }
	public int getPrice() { return price; }
	public void setID(int iD) { this.ID = iD; }
	public void setName(String name) { this.Name = name; }
	public void setDescription(String description) { this.Description = description; }
	public void setPrice(int price) { this.price = price; }
}
