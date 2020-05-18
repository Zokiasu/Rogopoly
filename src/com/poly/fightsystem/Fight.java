package com.poly.fightsystem;

import java.util.*;
import com.poly.entity.*;
import com.poly.gestion.MainSystem;
import com.poly.gestion.Save;
import com.poly.map.Map;
import com.poly.move.*;
import com.poly.ui.*;
	
public class Fight {

	private int nba;
	private Scanner comp;
	
	//Cette fonction gere le tour par tour, le match continue tant que personne n'est mort
	public Fight(Entity fighter1, Entity fighter2, Map M)
	{
		while (fighter1.getActualHp() != 0 && fighter2.getActualHp() != 0)
		{
			reloadCD(fighter1, fighter2);
			new IFight(fighter1, fighter2);
			fighting(fighter1, fighter2, M);
			if(fighter1 instanceof Player) 
			{
				if(((Player)fighter1).getEvasion()) break;
			}
			fighting(fighter2, fighter1, M);
			if(fighter2 instanceof Player) 
			{
				if(((Player)fighter2).getEvasion()) break;
			}
			
		}
		endFight(fighter1, fighter2, M);
	}
	
	//Lance la fonction de reduction de cd
	public void reloadCD(Entity fighter1, Entity fighter2)
	{
		if (fighter1 instanceof Player)	
			((Player) fighter1).reload();
		else if (fighter1 instanceof Monster)
			((Monster) fighter1).reload();
		else if (fighter1 instanceof Npc)
			((Npc) fighter1).reload();
		
		if (fighter2 instanceof Player)
			((Player) fighter2).reload();
		else if (fighter2 instanceof Monster)
			((Monster) fighter2).reload();
		else if (fighter2 instanceof Npc)
			((Npc) fighter2).reload();
	}
	
	//Ajoute l'exp\u00e9rience que le joueur \u00e0 gagner en tuant un monstre
	public void AddExp(Entity fighterW, Entity fighterL, Map map)
	{
		int A = fighterL.getMaxExp()*10;
		if(A > fighterW.getMaxExp())
			A = A/2;
		int B = fighterW.getActuAlfredp() + A;
		fighterW.addActuAlfredp(A);
		System.out.println("Vous avez gagn\u00e9 " + A + " Exp");
		while(B >= fighterW.getMaxExp())
		{
			B -= fighterW.getMaxExp();
			((Player) fighterW).levelUp();
			fighterW.setActuAlfredp(B);
			
			for(int i = 0; i < map.getListNpc().size(); i++)
			{
				map.getListNpc().get(i).levelUp();
			}
			for(int i = 0; i < map.getListMob().size(); i++)
			{
				map.getListMob().get(i).levelUp();
			}
		}
	}
	
	//Fonction d'affichage des messages de fin de combat
	public void endFight(Entity fighter1, Entity fighter2, Map M)
	{
		System.out.println("\nFin du combat");
		if(fighter1 instanceof Player)
		{
			if (fighter1.getActualHp() != 0 && !((Player)fighter1).getEvasion()) 
			{
				if(fighter2.getName().equals("C52B")) 
				{
					System.out.println("Coucou");
					((Monster)fighter2).getClasse().death(M, fighter2.getXY("X"), fighter2.getXY("Y"), ((Player)fighter1));
				}
				else fighter2.death(M, fighter2.getXY("X"), fighter2.getXY("Y"), ((Player)fighter1));
				fighter1.setMoney(fighter2.getMoney() + fighter1.getMoney());
				AddExp(fighter1, fighter2, M);
				System.out.println(fighter1.getName() + " a gagn\u00e9 le combat");
			}
			else if (fighter2.getActualHp() != 0) 
			{
				if(((Player)fighter1).getEvasion()) System.out.println(fighter1.getName() + " a fuit le combat");
				System.out.println(fighter2.getName() + " a gagn\u00e9 le combat");
			}
		}
		else if(fighter2 instanceof Player)
		{
			if (fighter2.getActualHp() != 0 && !((Player)fighter2).getEvasion()) 
			{
				if(fighter1.getName().equals("C52B")) 
				{
					System.out.println("Coucou");
					((Monster)fighter1).getClasse().death(M, fighter1.getXY("X"), fighter1.getXY("Y"), ((Player)fighter2));
				}
				else fighter1.death(M, fighter1.getXY("X"), fighter1.getXY("Y"), ((Player)fighter2));
				fighter2.setMoney(fighter2.getMoney() + fighter1.getMoney());
				AddExp(fighter2, fighter1, M);
				System.out.println(fighter2.getName() + " a gagn\u00e9 le combat");
			}
			else if (fighter1.getActualHp() != 0) 
			{
				if(((Player)fighter2).getEvasion()) System.out.println(fighter1.getName() + " a fuit le combat");
				System.out.println(fighter1.getName() + " a gagn\u00e9 le combat");
			}
		}
		else if (fighter2.getActualHp() == 0 && fighter1.getActualHp() == 0) System.out.println("Personne n'a gagn\u00e9 le combat");
	}

	//Fonction qui definis alatoirement la direction vers laquel fuit le joueur
	public static void aleaSkip(Player player, Map map, Move move)
	{
		String Direction = null;
		int Steps;
		int Direct;
		Direct = (int) (Math.random()*3);
		switch(Direct)
		{
			case 0 : Direction = "Z"; break;
			case 1 : Direction = "D"; break;
			case 2 : Direction = "S"; break;
			case 3 : Direction = "Q"; break;
		}
		Steps = (int) (Math.random()*(player.getActualMp()-1) + 1);
		move.move(player, Direction, Steps, player.getXY("X"), player.getXY("Y"), map);
	}
	
	//Affiche les comp\u00e9tences des differentes classes et si ils sont inutilisable
	public void displaySkillClass(Entity fighterD)
	{
		System.out.print("1. "+((Player)fighterD).getClasse().getSpellName1());
		if(((Player)fighterD).getSpellCD1() > 0)
		{
			System.out.print(". R\u00e9utilisable dans : " + ((Player)fighterD).getSpellCD1() + " tours.");
		}
    	System.out.print("\n2. "+((Player)fighterD).getClasse().getSpellName2());
		if(((Player)fighterD).getSpellCD2() > 0)
		{
			System.out.print(". R\u00e9utilisable dans : " + ((Player)fighterD).getSpellCD2() + " tours.");
		}
    	System.out.print("\n3. "+((Player)fighterD).getClasse().getSpellName3());
		if(((Player)fighterD).getSpellCD3() > 0)
		{
			System.out.print(". R\u00e9utilisable dans : " + ((Player)fighterD).getSpellCD3() + " tours.");
		}
    	System.out.print("\n4. "+((Player)fighterD).getClasse().getSpellName4());
		if(((Player)fighterD).getSpellCD4() > 0)
		{
			System.out.print(". R\u00e9utilisable dans : " + ((Player)fighterD).getSpellCD4() + " tours.");
		}
		System.out.print("\n5. "+((Player)fighterD).getClasse().getSpellName5());
		if(((Player)fighterD).getSpellCD5() > 0)
		{
			System.out.print(". R\u00e9utilisable dans : " + ((Player)fighterD).getSpellCD5() + " tours.");
		}
		System.out.println("\n\nPour plus d'info utiliser la commande /info 'Num\u00e9ro de la comp\u00e9tence' \nou '0' pour toute les comp\u00e9tences.");
	}
	
	//Lance la comp\u00e9tences demander par le joueur
	public void UseSkill(int B, Entity summoner, Entity target)
    {
    	switch(B)
    	{
    		case 1 :
    			summoner.spell1(target);
    			break;
    		case 2 :
    			summoner.spell2(target);
    			break;
    		case 3 :
    			summoner.spell3(target);
    			break;
    		case 4 :
    			summoner.spell4(target);
    			break;
    		case 5 :
    			summoner.spell5(target);
    			break;
    	}
    }
	
	//Verifie si la comptence demander est en cd ou non
	public boolean tryCD(int B, Entity summoner)
    {
		if (summoner instanceof Player) 
		{
	    	switch(B)
	    	{
	    		case 1 :
	    			if(((Player) summoner).getSpellCD1() > 0)
	    		    	return false;
	    			break;
	    		case 2 :
	    			if(((Player) summoner).getSpellCD2() > 0)
	    		    	return false;
	    			break;
	    		case 3 :
	    			if(((Player) summoner).getSpellCD3() > 0)
	    		    	return false;
	    			break;
	    		case 4 :
	    			if(((Player) summoner).getSpellCD4() > 0)
	    		    	return false;
	    			break;
	    		case 5 :
	    			if(((Player) summoner).getSpellCD5() > 0)
	    		    	return false;
	    			break;
	    	}
		}
		else if  (summoner instanceof Monster) 
		{
	    	switch(B)
	    	{
	    		case 1 :
	    			if(((Monster) summoner).getSpellCD1() > 0)
	    		    	return false;
	    			break;
	    		case 2 :
	    			if(((Monster) summoner).getSpellCD2() > 0)
	    		    	return false;
	    			break;
	    		case 3 :
	    			if(((Monster) summoner).getSpellCD3() > 0)
	    		    	return false;
	    			break;
	    		case 4 :
	    			if(((Monster) summoner).getSpellCD4() > 0)
	    		    	return false;
	    			break;
	    		case 5 :
	    			if(((Monster) summoner).getSpellCD5() > 0)
	    		    	return false;
	    			break;
	    	}			
		}

		else if  (summoner instanceof Npc) 
		{
	    	switch(B)
	    	{
	    		case 1 :
	    			if(((Npc) summoner).getSpellCD1() > 0)
	    		    	return false;
	    			break;
	    		case 2 :
	    			if(((Npc) summoner).getSpellCD2() > 0)
	    		    	return false;
	    			break;
	    		case 3 :
	    			if(((Npc) summoner).getSpellCD3() > 0)
	    		    	return false;
	    			break;
	    		case 4 :
	    			if(((Npc) summoner).getSpellCD4() > 0)
	    		    	return false;
	    			break;
	    		case 5 :
	    			if(((Npc) summoner).getSpellCD5() > 0)
	    		    	return false;
	    			break;
	    	}
		}
    	return true;
    }
    
	//Retourne les informations des skills demander via la cmd /info
    public void Command(String string1, int Integer, Entity information)
    {
    	if(string1.equals("/info")) {
    		switch(Integer) {
    			case 0 :
    				System.out.println(((Player)information).getClasse().getSpellName1() + " : " + ((Player)information).getClasse().getSpellDescription1());
        			System.out.println(((Player)information).getClasse().getSpellName2() + " : " + ((Player)information).getClasse().getSpellDescription2());
        			System.out.println(((Player)information).getClasse().getSpellName3() + " : " + ((Player)information).getClasse().getSpellDescription3());
        			System.out.println(((Player)information).getClasse().getSpellName4() + " : " + ((Player)information).getClasse().getSpellDescription4());
        			System.out.println(((Player)information).getClasse().getSpellName5() + " : " + ((Player)information).getClasse().getSpellDescription5());
    				break;
        		case 1 :
        			System.out.print(((Player)information).getClasse().getSpellName1() + " : " + ((Player)information).getClasse().getSpellDescription1());
        			break;
        		case 2 :
        			System.out.print(((Player)information).getClasse().getSpellName2() + " : " + ((Player)information).getClasse().getSpellDescription2());
        			break;
        		case 3 :
        			System.out.print(((Player)information).getClasse().getSpellName3() + " : " + ((Player)information).getClasse().getSpellDescription3());
        			break;
        		case 4 :
        			System.out.print(((Player)information).getClasse().getSpellName4() + " : " + ((Player)information).getClasse().getSpellDescription4());
        			break;
        		case 5 :
        			System.out.print(((Player)information).getClasse().getSpellName5() + " : " + ((Player)information).getClasse().getSpellDescription5());
        			break;
        	}
    	}
    	else
    		System.out.println("Commande executer incorrect.");
    }
	
	//Test de quel type est l'attaquant et execute les differents test
	public void fighting(Entity fighterD, Entity fighterA, Map map)
	{
		String A = null;
		int C = 0;
		nba = 0;
		if(fighterD.getActualHp() != 0 && fighterA.getActualHp() != 0) 
		{
			System.out.println("\nTour de "+ fighterD.getName());
			if (fighterD instanceof Monster || fighterD instanceof Npc) 
			{
				nba = (int) (Math.random()*4 + 1);
				if (tryCD(nba, fighterD)){
					UseSkill(nba, fighterD, fighterA);
				} else {
					while(tryCD(nba, fighterD) == false) 
					{
						nba = (int) (Math.random()*4 + 1);
						if (tryCD(nba, fighterD)) {
							UseSkill(nba, fighterD, fighterA);
							break;
						}
					}
				}
			}
			else if (fighterD instanceof Player) 
			{
				Boolean ok = false;
				System.out.println("Voici les comp\u00e9tences que vous pouvez utiliser : ");
				displaySkillClass(fighterD);
				System.out.println("\nVous pouvez utiliser une potion en entrant /utiliser 'nom de la potion'."
						+ "\nEntrez le nombre correspondant \u00e0 la comp\u00e9tence que vous souhaitez utiliser :");
				do {
					comp = new Scanner(System.in);
					
					try 
					{ 
						nba = comp.nextInt();
					} 
					catch(InputMismatchException e) 
					{
						nba = 0;
						A = comp.next();
						A = A.toLowerCase();
						if(A.equals("/info")) 
						{
							try {
								System.out.println("Quel est la comp\u00e9tence sur laquelle vous souhaitez plus d'information ?");
								C = comp.nextInt();
							}
							catch(InputMismatchException e1) {
								C = -1; A = null;
							}
							if(C >= 0 && C < 6) 
								Command(A,C, fighterD);
							else 
								System.out.println("La combinaison saisie n'est pas une comp\u00e9tence ou une action possible.");
						}
						else if (A.equals("/utiliser")) 
						{
							String B, E, D;
			        		System.out.println("Que souhaitez-vous utiliser ?");
			        		B = comp.next();
			        		if (INpc.TryIntoList(B, 0, ((Player)fighterD).getStock().getListStockage()))
			        		{
					        	E = comp.next();
					        	D = B + " " + E;
					        	D = D.toLowerCase();
					        	if (INpc.TryIntoList(D, 1, ((Player)fighterD).getStock().getListStockage()))
					        	{
					        		MainSystem.CleanConsole();
					        		((Player)fighterD).getStock().useItem(((Player)fighterD), D);
						        	Save.SaveGame(((Player)fighterD), map);
					        	}
				        		else
				        		{
					        		MainSystem.CleanConsole();
				        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
				        		}
			        		}
			        		else
			        		{
				        		MainSystem.CleanConsole();
			        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
			        		}
			        		ok = true;
						}
						else 
						{
				    		System.out.println("La combinaison saisie n'est pas une comp\u00e9tence ou une action possible.");
						}
					}
					
					if(nba > 5)
					{
						System.out.println("La combinaison saisie n'est pas une comp\u00e9tence ou une action possible.");
					}
				} while((nba == 0 || nba > 5) && ok == false);
				
				if(nba != 0 && nba <= 5) 
				{
					if (tryCD(nba, fighterD)) 
					{
						MainSystem.CleanConsole();
						UseSkill(nba, fighterD, fighterA);
					} 
					else 
					{
						ok = false;
						while(tryCD(nba, fighterD) == false && ok == false) 
						{
							nba = 0;
							System.out.println("Vous ne pouvez pas utiliser cette comp\u00e9tence pour le moment");
							try 
							{ 
								nba = comp.nextInt(); 
							}
							catch(InputMismatchException e) 
							{
								nba = 0;
								A = comp.next();
								A = A.toLowerCase();
								if(A.equals("/info")) 
								{
									try {
										C = comp.nextInt();
									}
									catch(InputMismatchException e1) {
										C = -1; A = null;
									}
									if(C >= 0 && C < 6) 
										Command(A,C, fighterD);
									else 
										System.out.println("La combinaison saisie n'est pas une comp\u00e9tence ou une action possible.");
								}
								else if (A.equals("/utiliser")) 
								{
									String B, E, D;
					        		System.out.println("Que souhaitez-vous utiliser ?");
					        		B = comp.next();
					        		if (INpc.TryIntoList(B, 0, ((Player)fighterD).getStock().getListStockage()))
					        		{
							        	E = comp.next();
							        	D = B + " " + E;
							        	D = D.toLowerCase();
							        	if (INpc.TryIntoList(D, 1, ((Player)fighterD).getStock().getListStockage()))
							        	{
							        		MainSystem.CleanConsole();
							        		((Player)fighterD).getStock().useItem(((Player)fighterD), D);
								        	Save.SaveGame(((Player)fighterD), map);
							        	}
						        		else
						        		{
							        		MainSystem.CleanConsole();
						        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
						        		}
					        		}
					        		else
					        		{
						        		MainSystem.CleanConsole();
					        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
					        		}
					        		ok = true;
								}
								else 
								{
						    		System.out.println("La combinaison saisie n'est pas une comp\u00e9tence ou une action possible.");
								}
							}
							if (tryCD(nba, fighterD) && nba != 0) 
							{
								MainSystem.CleanConsole();
								UseSkill(nba, fighterD, fighterA);
								ok = true;
							}
						}
					}
			   	}				
			}
		}
	}
}