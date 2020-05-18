package com.poly.equipment;

import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Weapon extends Equipment{
	
	private Weapon(int Arme, int rarety)
	{		
		this.type = Arme;
		this.rarety = rarety;
		this.priceSale = Math.round(this.price*(float)60/100);;
		
		switch(type)
		{
			case 0:
				name = "Arme vide";
				break;
			case 1: //Arc
				switch(rarety) 
				{
					case 1:
						this.name = "Arc normal";
						this.Att = 50;
						this.price = 7;
						break;
					case 2:
						this.name = "Arc rare";
						this.Att = 70;
						this.price = 15;
						break;
					case 3:
						if(MainSystem.testLinux())
							this.name = "Arc \u00e9pique";
						else
							this.name = "Arc epique";
						this.Att = 100;
						this.price = 30;
						break;
				}
				break;
			case 2: //Dague
				switch(rarety)
				{
					case 1:
						this.name = "Dague normale";
						this.Att = 50;
						this.price = 7;
						break;
					case 2:
						this.name = "Dague rare";
						this.Att = 70;
						this.price = 15;
						break;
					case 3:
						if(MainSystem.testLinux())
							this.name = "Dague \u00e9pique";
						else
							this.name = "Dague epique";
						this.Att = 100;
						this.price = 30;
						break;
				}
				break;
			case 3: //Lance
				switch(rarety)
				{
					case 1:
						this.name = "Lance normale";
						this.Att = 50;
						this.price = 7;
						break;
					case 2:
						this.name = "Lance rare";
						this.Att = 70;
						this.price = 15;
						break;
					case 3:
						if(MainSystem.testLinux())
							this.name = "Lance \u00e9pique";
						else
							this.name = "Lance epique";
						this.Att = 100;
						this.price = 30;
						break;
				}
				break;
			case 4: //Grimoire
				switch(rarety)
				{
					case 1:
						this.name = "Grimoire normal";
						this.Att = 50;
						this.price = 7;
						break;
					case 2:
						this.name = "Grimoire rare";
						this.Att = 70;
						this.price = 15;
						break;
					case 3:

						if(MainSystem.testLinux())
							this.name = "Grimoire \u00e9pique";
						else
							this.name = "Grimoire epique";
						this.Att = 100;
						this.price = 30;
						break;
				}
				break;
		}
		this.priceSale = Math.round(this.price*(float)60/100);
		this.Description = "Point de vie = " + this.Hp  + ", Att = " + this.Att;
	}
	
	public static Weapon create(int Arme, int rarety) throws Exception
	{
		if(Arme < 0 || Arme > 4 || rarety < 0 || rarety > 3)
		{
			throw new Exception("Vous ne pouvez pas cr\u00e9er un objet qui n'existe pas");
		}
		else
		{
			Weapon weapon = new Weapon(Arme,rarety);
			return weapon;
		}
	}
}
