package com.poly.ui;

import java.util.*;
import com.poly.map.Map;
import com.poly.entity.*;
import com.poly.gestion.MainSystem;
import com.poly.item.*;

public class INpc extends IDisplay {
	
	private Scanner scan;
	public ArrayList<Item> StockageIn = new ArrayList<Item>();

	public INpc(Player player, Npc npc, Map map) 
	{
		MainSystem.CleanConsole();
		Spacer();
		displayEntity(player);
		Spacer();
		displayNpc(npc);
		
		if (npc.getNameClass().equals("Marchand")) 
			displayTrader(npc);
		
		else if (npc.getNameClass().equals("Intendant")) 
			displaySteward(map);
		
		else if (npc.getNameClass().equals("Gardien")) 
			displayGuardian();
		
		getClavier(player, npc, map);
	}
	
	public void getClavier(Player player, Npc npc, Map map) 
	{
		String S;
		scan = new Scanner(System.in);
		S = scan.nextLine();
		S = S.toLowerCase();
		if(S.equals("vendre"))
		{
			if(npc.getNameClass().equals("Marchand"))
			{
				SaleNpc(player, npc, map);
	        }
			else
			{
				System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
				getClavier(player, npc, map);
			}
		}
		else if (S.equals("payer"))
		{
			if(npc.getNameClass().equals("Gardien"))
			{
				if(player.getMoney() > 100*player.getLevel())
				{
					player.removeMoney(100*player.getLevel());
					map.setCase(npc.getXY("X"), npc.getXY("Y"), 'S');
					map.setPositionSortieX(npc.getXY("X"));
					map.setPositionSortieY(npc.getXY("Y"));
					for(int i = 0; i <map.getListNpc().size();i++)
					{
						if(map.getListNpc().get(i).getXY("X") == npc.getXY("X") && map.getListNpc().get(i).getXY("Y") == npc.getXY("Y"))
						{
							map.getListNpc().remove(i);
						}
					}
					System.out.println("Tr\u00e8s bien cher aventurier, puisse votre aventure vous apportez richesse et puissance.");
				}
				else
				{
					System.out.println("Vous n'avez pas la somme que je r\u00e9clame ! \n\nMerci de ne pas me faire perdre mon temps.");
				}
			}
			else
			{
				System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
				getClavier(player, npc, map);
			}
		}
		else if (S.equals("acheter"))
		{
			if(npc.getNameClass().equals("Intendant"))
			{
				BuyMap(player, npc, map);
			}
			else
			{
				System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
				getClavier(player, npc, map);
			}
		}
		else if (S.equals("clef"))
		{
			if(npc.getNameClass().equals("Gardien"))
			{
				int i;
				for(i = 0; i < player.getStock().getListStockage().size(); i++)
	        	{
	        		String S1 = "clef prison";
	        		
	        		if(TryIntoList(S1, 0, player.getStock().getListStockage()) && TryIntoList(S1, 1, player.getStock().getListStockage()))
	        		{				        			
		        		player.getStock().getListStockage().remove(i);
		        		break;
	        		}
	        	}
				if (i >= player.getStock().getListStockage().size())
				{
					MainSystem.CleanConsole();
					System.out.println("Vous ne poss\u00e9dez pas l'objet requis ! \n\nMerci de ne pas me faire perdre mon temps.");
				}
				else
				{
					map.setCase(npc.getXY("X"), npc.getXY("Y"), 'S');
					map.setPositionSortieX(npc.getXY("X"));
					map.setPositionSortieY(npc.getXY("Y"));
					for(i = 0; i <map.getListNpc().size(); i++)
					{
						if(map.getListNpc().get(i).getXY("X") == npc.getXY("X") && map.getListNpc().get(i).getXY("Y") == npc.getXY("Y"))
						{
							map.getListNpc().remove(i);
						}
					}
					MainSystem.CleanConsole();
					System.out.println("Tr\u00e8s bien cher aventurier, puisse votre aventure vous apportez richesse et puissance.");
				}
			}
			else
			{
				System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
				getClavier(player, npc, map);
			}
		}
		else if (S.equals("quitter"))
		{
			MainSystem.CleanConsole();
			System.out.println("Tr\u00e9s bien cher aventurier, puisse votre aventure vous apportez richesse et puissance.");
		}
		else if(TryIntoList(S,0, npc.getShop()) && TryIntoList(S, 1, npc.getShop()))
		{
			BuyNpc(player, npc, S, scan);
			new INpc(player, npc, map);
		}
		else 
		{
			System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
			getClavier(player, npc, map);
		}
	}

	public void displayNpc(Npc npc) 
	{
		System.out.println(npc.getName() + " (" + npc.getNameClass() + ")");
		System.out.println(((Npc)npc).getSpeech());
		Spacer();
	}
	
	public void displaySteward(Map map)
	{
		System.out.println("J'imagine que le concept d'acheter une carte vous \u00e9chappe ?"
				+ "\n\nCela est simple, si vous achetez une carte vous achetez\nen v\u00e9rit\u00e9 la valeur de cette carte,"
				+ " cette somme vous sera\nattribu\u00e9e \u00e0 chaque fois que vous gagnez un niveau suppl\u00e9mentaire."
				+ "\nLa carte actuelle co\u00fbterait " + map.getValue() + " pi\u00e8ces."
				+ "\n\nAlors \u00eates vous int\u00e9ress\u00e9 ?\nSi oui, \u00e9crivez 'Acheter' sinon \u00e9crivez 'Quitter'.");
		Spacer();
	}	
	
	public void BuyMap(Player player, Npc npc, Map map)
	{
		if (player.getMoney() >= map.getValue())
		{
			player.removeMoney(map.getValue());
			System.out.println("Vous avez pay\u00e9 " + map.getValue());
			player.setValueMap(player.getValueMap() + map.getValue());
			map.setCase(npc.getXY("X"), npc.getXY("Y"), '\u00B7');
			for(int i = 0; i <map.getListNpc().size();i++)
			{
				if(map.getListNpc().get(i).getXY("X") == npc.getXY("X") && map.getListNpc().get(i).getXY("Y") == npc.getXY("Y"))
				{
					map.getListNpc().remove(i);
				}
			}
		}
		else {
			MainSystem.CleanConsole();
			System.out.println("Vous ne disposez pas d'assez d'argent pour l'achat de cette carte.");
		}
	}
	
	public void displayGuardian()
	{
		System.out.println("Pour vous \u00e9chapper de cette carte je peux vous offrir trois options :"
				+ "\n\nLa premi\u00e8re est de me payer votre lib\u00e9ration, "
				+ "la deuxi\u00e8me serait\nd'avoir une cl\u00e9 de la prison "
				+ "et enfin la troisi\u00e8me est de combattre \nle monstre de cette salle afin d'ouvrir la porte de sortie."
				+ "\n\nQue souhaiteriez-vous faire ? \nPayer, utiliser votre clef ou combattre le monstre ?"
				+ "\n\nSi vous souhaitez payer \u00e9crivez 'Payer', pour utiliser votre clef \n\u00e9crivez 'Clef', sinon \u00e9crivez'Quitter'.");
		Spacer();
	}
	
	public void displayTrader(Npc npc) /*Affiche les 4 items vendu par le marchand*/
	{
		System.out.println("Seriez-vous int\u00e9ress\u00e9 par l'une de mes nobles marchandises ci-dessous.\n");
		Item C = null;
		if(npc.getShop().size() > 1)
		{
			for(int i = 0; i < npc.getShop().size(); i++)
			{
				C = npc.getShop().get(i);
				System.out.println(C.getName() + ". Prix : " + C.getPrice() + "\n" + C.getDescription() + ".");
			}
		}
		else
		{
			for(int i = 0, j = 1; i < 4; i++)
			{
				String X = aleaShop(npc);
				if (X == null) {}
				else 
				{
					System.out.println(j + ". " + X);
					j += 1;
				}
			}
		}
		System.out.println("\nVeuillez entrer le nom de l'objet que vous souhaitez acheter,"
				+ "\nsi vous souhaitez me vendre quelques-uns de vos objets veuillez \u00e9crire 'vendre'."
				+ "\n\u00e9crivez 'quitter' pour fermer la fen\u00eatre du marchand");
		Spacer();
	}
	
	public String aleaShop(Npc npc) /*Retourne un item a insere dans le shop al\u00e9atoirement*/
	{
		int A;
		A = (int) (Math.random()*100 + 1);
		Item item = null;
		if (A == 100)
		{
			item = new Potion(5);
		}
		else if (A == 70)
		{
			item = new Potion(4);
		}
		else
		{
			A = (int) (Math.random()*4 + 1);			
			switch(A)
			{
				 case 1 :
					 item = new Potion(1);
					 break;
				 case 2 :
					 item = new Potion(2);
					 break;
				 case 3 :
					 item = new Potion(3);
					 break;
				 case 4 :
					 item = new Key(1);
					 break;
				 case 5 :
					 item = new Key(2);
					 break;
			}			
		}
		if(TryIntoList(item.getName(), 0, npc.getShop()) && TryIntoList(item.getName(), 1, npc.getShop())) 
		{
			return null;
		}
		else 
		{ 
			npc.getShop().add(item); 
			return item.getName() + ". Prix : " + item.getPrice() + "\n" + item.getDescription() + ".";
		}
	}
	
	public void BuyNpc(Player player, Npc npc, String S, Scanner scan) /*Gestion de l'achat d'item*/
	{
		int D = -1;
		Item C;
		System.out.println("Combien souhaiteriez-vous en acheter ?");
		do {
			try { D = scan.nextInt();}
			catch(Exception e)
			{ 
				scan.next();
				System.out.println("Je n'ai pas compris votre demande, veuillez entrer un nombre correct ou 0 pour abandonner.");
				D = -1;
			}
		} while (D == -1);
		if(TryMoney(player, npc, D, S) >= 0)
		{
			C = npc.getShop().get(TryMoney(player, npc, D, S));
			if(D > 0)
			{
				for(int j = 0; j < D; j++)
				{
					player.getStock().AddStock(C);
					player.removeMoney(C.getPrice());
				}
			}
			else
			{
				System.out.println("Le nombre entrez est incorrection, l'achat est donc abandonn\u00e9");
			}
		}
		else if (TryMoney(player, npc, D, S) == -1) 
			System.out.println("Vous ne poss\u00e9dez pas la monnaie n\u00e9cessaire pour cet achat.");
		else if (TryMoney(player, npc, D, S) < -1) 
			System.out.println("Je n'ai pas compris votre demande.");
	}
	
	public int TryMoney(Player player, Npc npc, int D, String S) /*V\u00e9rifie que le joueur \u00e0 assez d'argent pour acheter*/
	{
		String B; int i, C = 1;
		for(i = 0; i < npc.getShop().size(); i++)
		{
			B = npc.getShop().get(i).getName();
    		B = B.toLowerCase();
    		if(S.equals(B))
    		{
    			if(D*npc.getShop().get(i).getPrice() > player.getMoney())  return -1; 
    			else return i;
    		}
    		else
    		{
    			C += 1;
    		}
		}
		if (C > npc.getShop().size())
		{
			return -2;
		}
		return 0;
	}
	
	public void SaleNpc(Player player, Npc npc, Map map) /*Gestion de l'interface de vente d'item*/
	{
		String Action = null;
		int NbToSale = 0, NbTry = 0;
		Boolean Repeat = true;
		
		do {
			scan = new Scanner(System.in);
        	StockageIn.clear();
			displayInventory(player);

			Action = scan.nextLine();
			try 
    		{ 
				Action = wordTry(Action, 0) + " " + wordTry(Action, 1); 
			} catch(Exception e) { }
			Action = Action.toLowerCase();
			
			if (Action.equals("acheter"))
        	{
        		new INpc(player, npc, map);
        		Repeat = false;
        	}
        	else if (Action.equals("quitter"))
        	{
        		Repeat = false;
        	}
        	else if (Action.equals("tout") || Action.equals("all"))
        	{
        		do {
	        		for(int i = 0; i < player.getStock().getListStockage().size();i++)
	        		{
	        			SaleItem(player, player.getStock().getListStockage().get(i).getName().toLowerCase(),1);
	        		}
        		}while(player.getStock().getListStockage().size() > 0);
        		new INpc(player, npc, map);
        		Repeat = false;
        	}
        	try 
        	{
	        	if(TryIntoList(Action, 0, player.getStock().getListStockage()) && TryIntoList(Action, 1, player.getStock().getListStockage()))
	        	{
	        		if(player.getStock().GetNumberItem(Action) == 1)
	        		{
	        			NbToSale = 1;
	        		}
	        		else
	        		{
		        		System.out.println("Combien souhaiteriez-vous en vendre ?"); 
		        		do {
				        	try
				        	{
				        		NbToSale = scan.nextInt();
				        	}
				        	catch(Exception e) 
				        	{
				        		NbToSale = 0;
				        		NbTry += 1;
				        		scan.next();
				        		System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande, veuillez choisir le nombre d'objet \u00e0 vendre.");
				        	}
		        		} while(NbToSale == 0 && NbTry != 3);
	        		}
	        		
	        		if(NbTry >= 3)
	        		{
	        			System.out.println("Vous avez ex\u00e9cuter un nombre action important sans succ\u00e8s, la vente est donc annuler.");
	        		}
	        		else
	        		{
	        			SaleItem(player, Action, NbToSale);
	        		}
	        	}
	        	else System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
        	}
        	catch (Exception e) 
        	{ 
        		System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
        	}
		} while(Repeat);
	}
	
	public static boolean TryIntoList(String S, int WordToTry, ArrayList<Item> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(wordTry(S, WordToTry).toLowerCase().equals(wordTry(list.get(i).getName().toLowerCase(), WordToTry)))
			{
				return true;
			}
		}
		return false;
	}
	
	public void displayInventory(Player player)
	{
		Spacer();
		displayEntity(player);
		Spacer();
		System.out.println("Que souhaiteriez-vous me vendre ?\n");
		
		String A;
		for(int i = 0; i < player.getStock().getListStockage().size();i++)
		{
			A = player.getStock().getListStockage().get(i).getName();
			if(tryList(A) == true)
			{
				if(player.getStock().getListStockage().size() > 0)
				{
					System.out.println("["+ player.getStock().GetNumberItem(A)+"] " + A + ".  Prix : " + player.getStock().getListStockage().get(i).getPriceSale());
				}
				StockageIn.add(player.getStock().getListStockage().get(i));
			}
		}
		if (player.getStock().getListStockage().size() <= 0)
		{
			System.out.println("Votre inventaire est vide !");
		}
		System.out.println("\nVeuillez entrer le nom de l'objet que vous souhaitez vendre dans un premier temps,"
				+ "\nsi vous souhaitez revenir \u00e0 la fen\u00eatre d'achat veuillez \u00e9crire 'acheter'."
				+ "\nEntrez 'quitter' pour fermer la fen\u00eatre du marchand");
		Spacer();
	}
	
	public Boolean tryList(String string)
	{
		String A = null;
		string = string.toLowerCase();
		for(int j = 0; j < StockageIn.size(); j++)
		{
			A = StockageIn.get(j).getName();
			A = A.toLowerCase();
			if(string.equals(A))
			{
				return false;
			}
		}
		return true;
	}
	
	public static String wordTry(String string, int WordNumber) {
	    String[] tab = string.split(" ");
	    return tab[WordNumber];
	}
		
	public void SaleItem(Player player, String A, int D) /*Vend les objets du joueur le nombre de fois qu'il le souhaite*/
	{
		String B;
		
    	if(D < 1) D = 1;
    	
    	if(TryNbItemSale(player, A, D))
    	{
        	for(int j = 0; j < D; j++)
        	{	        	
	        	int C = 0;	
	        	for(int i = 0; i < player.getStock().getListStockage().size();i++)
	        	{
	        		B = player.getStock().getListStockage().get(i).getName();
	        		B = B.toLowerCase();
	        		if(A.equals(B))
	        		{				        			
		        		player.addMoney(player.getStock().getListStockage().get(i).getPriceSale());
		        		player.getStock().getListStockage().remove(i);
			        	i = player.getStock().getListStockage().size() + 1;
	        		}
	        		else if (!A.equals(B))
	        		{
	        			C += 1;
	        		}
	        	}
	        	if (C >=  player.getStock().getListStockage().size()) j = D + 1;
			}
    	}
    	else if (!TryNbItemSale(player, A, D) && !A.equals("quitter") && !A.equals("acheter")) 
    		System.out.println("La commande \u00e9x\u00e9cute\u00e9e n'est pas la bonne ou vous n'avez pas le nombre d'objet/l'objet que vous souhaitez vendre.");
	}
	
	public boolean TryNbItemSale (Player player, String A, int D) /*V\u00e9rifie que le nbr d'item que le souhaite vendre n'est pas sup\u00e9rieur au nombre d'item que l'on a*/
	{
		String B;
		for(int i = 0; i < player.getStock().getListStockage().size();i++)
    	{
    		B = player.getStock().getListStockage().get(i).getName();
    		B = B.toLowerCase();
    		if(A.equals(B))
    		{
    			if(D > player.getStock().GetNumberItem(player.getStock().getListStockage().get(i).getName()))
        			return false;
    			else
    				return true;
    		}
    	}
		return false;
	}
}	