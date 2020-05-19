package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Gauntlet extends Equipment {

	
	private Gauntlet(int A)
	{		
		this.rarety = A;
		
		switch(rarety)
		{
			case 0 :
				this.name = "Gants vides";
				break;
			case 1 :
				this.name = "Gants normaux";
				this.Hp = 5;
				this.Att = 5;
				this.Def = 7;
				this.price = 5;
			 break;
		 case 2 :
			 	this.name = "Gants rares";
				this.Hp = 20;
				this.Att = 10;
				this.Def = 15;
				this.price = 10;
			 break;
		 case 3 :
			 	if(MainSystem.testLinux())
			 		this.name = "Gants \u00e9piques";
			 	else
			 		this.name = "Gants epiques";
				this.Hp = 50;
				this.Att = 15;
				this.Def = 20;
				this.price = 20;
			 break;
		}
		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp + ", Att = " + this.Att + ", Def = " + this.Def;
	}
	
	public static Gauntlet create(int A) throws Exception
	{
		if(A < 0 || A > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Gauntlet gauntlet = new Gauntlet(A);
			return gauntlet;
		}
	}
}
