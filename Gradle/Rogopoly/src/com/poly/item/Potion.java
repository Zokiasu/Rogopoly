package com.poly.item;

import com.poly.entity.Player;
import com.poly.gestion.MainSystem;

@SuppressWarnings("serial")
public class Potion extends Item 
{
	private int Hp;	
	
	public Potion(int Type)
	{
        super();
        if(Type > 0 || Type < 6)
		{
			this.ID = Type;
			potionType(ID);
			this.priceSale = Math.round(this.price*(float)60/100);;
		}
	}
	
	public void potionType(int id)
	{
		 switch(id)
         {
		 	case 1 : 
		 		Name = "Potion simple";
		 		Description = "Cette potion vous r\u00e9g\u00e9n\u00e8re 5% de vos points de vie maximum";
		 		price = 5;
		 		setHp(5); 
		 		break;
         	case 2 : 
		 		Name = "Potion normale";
		 		Description = "Cette potion r\u00e9g\u00e9n\u00e8re 20% de vos points de vie maximum";
		 		price = 7;
         		setHp(20); 
         		break;
         	case 3 : 
         		if(MainSystem.testLinux())
    		 		Name = "Potion sup\u00e9rieure";
				else
					Name = "Potion superieure";
		 		Description = "Cette potion r\u00e9g\u00e9n\u00e8re 50% de vos points de vie maximum";
		 		price = 15;
         		setHp(50); 
         		break;
         	case 4 : 
         		if(MainSystem.testLinux())
    		 		Name = "Potion \u00e9pique";
				else
					Name = "Potion epique";
		 		Description = "Cette potion r\u00e9g\u00e9n\u00e8re 70% de vos points de vie maximum";
		 		price = 50;
         		setHp(70); 
         		break;
         	case 5 :  
         		if(MainSystem.testLinux())
    		 		Name = "Potion supr\u00eame";
				else
					Name = "Potion supreme";
		 		Description = "Cette potion r\u00e9g\u00e9n\u00e8re 100% de vos points de vie maximum";
		 		price = 100;
         		setHp(100); 
         		break;
         		
         }
	}
	
	public void usePotion(Player player, Potion potion)
	{
		int A;
		A = Math.round(player.getMaxHp()*(float)potion.getEffect()/100);
		player.addHp(A);
		System.out.println("Vous gagnez " + A + " PV.");
	}

	public int getEffect() {
		return Hp;
	}
	public void setHp(int hp) {
		Hp = hp;
	}
}
