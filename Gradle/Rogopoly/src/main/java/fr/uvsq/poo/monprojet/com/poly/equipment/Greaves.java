package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Greaves extends Equipment {

	private Greaves(int A)
	{		
		this.rarety = A;
		
		switch(rarety)
		{
			case 0 :
				if(MainSystem.testLinux())
					this.name = "Jambi\u00e8res vide";
				else
					this.name = "Jambieres vide";
				break;
			case 1 :
				if(MainSystem.testLinux())
					this.name = "Jambi\u00e8res normales";
				else
					this.name = "Jambieres normales";
				this.Hp = 10;
				this.Def = 10;
				this.price = 5;
			 break;
		 case 2 :
				if(MainSystem.testLinux())
					this.name = "Jambi\u00e8res rares";
				else
					this.name = "Jambieres rares";
				this.Hp = 20;
				this.Def = 17;
				this.price = 10;
			 break;
		 case 3 :
				if(MainSystem.testLinux())
					this.name = "Jambi\u00e8res \u00e9piques";
				else
					this.name = "Jambieres epiques";
				this.Hp = 50;
				this.Def = 25;
				this.price = 20;
			 break;
		}
		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp + ", Att = " + this.Att + ", Def = " + this.Def;
	}
	
	public static Greaves create(int A) throws Exception
	{
		if(A < 0 || A > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Greaves greaves = new Greaves(A);
			return greaves;
		}
	}
}
