package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Headgear extends Equipment {
	
	private Headgear(int A)
	{		
		this.rarety = A;
		
		switch(rarety)
		{
			case 0 :
				this.name = "Casque vide";
				break;
			case 1 :
				this.name = "Casque normal";
				this.Hp = 15;
				this.Att = 5;
				this.Def = 10;
				this.price = 5;
				break;
			case 2 :
				this.name = "Casque rare";
				this.Hp = 25;
				this.Att = 10;
				this.Def = 20;
				this.price = 10;
				break;
			case 3 :
				if(MainSystem.testLinux())
					this.name = "Casque \u00e9pique";
				else
					this.name = "Casque epique";
				this.Hp = 80;
				this.Att = 15;
				this.Def = 25;
				this.price = 20;
				break;
		}
		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp  + ", Att = " + this.Att + ", Def = " + this.Def;
	}
	
	public static Headgear create(int A) throws Exception
	{
		if(A < 0 || A > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Headgear headgear = new Headgear(A);
			return headgear;
		}
	}
}
