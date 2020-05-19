package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Breastplate extends Equipment {

	private Breastplate(int A)
	{		
		this.rarety = A;
		
		switch(rarety)
		{
			case 0 :
				this.name = "Plastron vide";
				break;
			case 1 :
				this.name = "Plastron normal";
				this.Hp = 7;
				this.Def = 7;
				this.price = 5;
				break;
			case 2 :
				this.name = "Plastron rare";
				this.Hp = 20;
				this.Def = 15;
				this.price = 10;
				break;
			case 3 :
				if(MainSystem.testLinux())
					this.name = "Plastron \u00e9pique";
				else
					this.name = "Plastron epique";
				this.Hp = 100;
				this.Def = 30;
				this.price = 20;
				break;
		}
		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp + ", Att = " + this.Att + ", Def = " + this.Def;
	}
	
	public static Breastplate create(int A) throws Exception
	{
		if(A < 0 || A > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Breastplate breastplate = new Breastplate(A);
			return breastplate;
		}
	}
}
