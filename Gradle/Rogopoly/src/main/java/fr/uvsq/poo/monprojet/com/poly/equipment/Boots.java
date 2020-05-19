package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Boots extends Equipment {
	
	private Boots(int A)
	{		
		this.rarety = A;
		
		switch(rarety)
		{
			case 0 :
				this.name = "Bottes vides";
				break;
			case 1:
				this.name = "Bottes normales";
				this.Hp = 5;
				this.Def = 5;
				this.price = 5;
				break;
			case 2:
				this.name = "Bottes rares";
				this.Hp = 10;
				this.Def = 8;
				this.PM = 1;
				this.price = 10;
				break;
			case 3:
				if(MainSystem.testLinux())
					this.name = "Bottes \u00e9piques";
				else
					this.name = "Bottes epiques";
				this.Hp = 25;
				this.Def = 20;
				this.PM = 2;
				this.price = 20;
				break;
		}

		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp + ", Att = " + this.Att + ", Def = " + this.Def;
	}
	
	public static Boots create(int A) throws Exception
	{
		if(A < 0 || A > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Boots boots = new Boots(A);
			return boots;
		}
	}
}
