package com.poly.item;

@SuppressWarnings("serial")
public class Key extends Item
{	
	public Key (int Type)	
	{
        super();
        if(Type > 0 || Type < 3)
		{
			this.ID = Type;
			keyType(ID);
			this.priceSale = Math.round(this.price*(float)60/100);;
		}
	}
	
	public void keyType(int id)
	{
		switch(id)
        {
		 	case 1 : 
		 		Name = "Clef Coffre";
		 		Description = "Cette clef vous permet d'ouvrir les coffres que vous croiserez sur la carte";
		 		price = 10;
		 		break;
        	case 2 : 
		 		Name = "Clef Prison";
		 		Description = "Cette clef vous permet de vous \u00e9chappez de la prison";
		 		price = 20;
        		break;
        		
        }
	}
}
