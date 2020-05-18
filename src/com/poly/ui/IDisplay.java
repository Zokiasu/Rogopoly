package com.poly.ui;

import java.util.Random;
import com.poly.entity.*;

public class IDisplay {
	
	Random rand = new Random();
	
	public static void Spacer()
	{
		System.out.println("____________________________________________________________________________");
	}
	
	public void displayEntity(Entity entity) 
	{
		if (entity instanceof Player)
		{
			System.out.println(entity.getName()+" ("+((Player)entity).getNameClasse()+") " 
							+"Niv : "+ entity.getLevel() 
							+ "  Att : " + entity.getAtt()  
							+ "  Def : " + entity.getDef());
		}
		else 
		{
			System.out.println(entity.getName());	
			System.out.println("Niv : "+ entity.getLevel() 
							+ "  Att : " + entity.getAtt()  
							+ "  Def : " + entity.getDef());
		}
		
		System.out.println("Exp : "+ entity.getActuAlfredp()+"/"+ entity.getMaxExp()
				+ "  PM : "+ entity.getActualMp()+"/"+entity.getMaxMp()
				+ "  PV : "+ entity.getActualHp()+"/"+entity.getMaxHp()
				+ "  Argent : "+ entity.getMoney());
	}
	
	public String RandomMessage()
	{
		int SelectMessage = 0;
		SelectMessage = rand.nextInt(11);
		switch(SelectMessage)
		{
			case 0 :
				return "Info : Pour acc\u00e9dez \u00e0 votre inventaire utiliser la commande /inventaire.";
			case 1 :
				return "Info : Pour utiliser une potion faite /utiliser suivis du 'nom de la potion'.";
			case 2 :
				return "Info : Rappelez-vous que si vous utilisez une clef dans l'inventaire "
						+ "\ncelle-ci sera d\u00e9truite.";
			case 3 :
				return "Info : Pour sauvegarder votre partie utiliser /save.";
			case 4 :
				return "Info : Vous avez la possibilit\u00e9 de vendre les objets que vous r\u00e9cup\u00e9rez "
						+ "\ndans les coffres.";
			case 5 :
				return "Info : Suite \u00e0 un combat vos comp\u00e9tences ne sont pas r\u00e9initialis\u00e9, \nhors de la fen\u00eatre de combat les comp\u00e9tences perdent un tour de r\u00e9cup\u00e9ration "
						+ "\n\u00e0 chaque fois que vous utilisez tout vos PMs.";
			case 6 :
				return "Info : Penser \u00e0 vous soigner avec vos potions apr\u00e8s vos combats ou pendant celui-ci.";
			case 7 :
				return "Info : Si vous n'\u00e9quipez pas une arme adapt\u00e9 \u00e0 votre classe vous perdez 50% \ndes stats de cette arme.";
			default :
				return "Pour vos d\u00e9placements utiliser Z, Q, S ou D suivis du nombre de pas (Exemple : Z 2).";
		}
	}
}
