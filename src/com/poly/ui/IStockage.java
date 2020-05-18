package com.poly.ui;

import java.util.*;
import com.poly.entity.*;
import com.poly.equipment.Equipment;
import com.poly.gestion.Color;
import com.poly.gestion.MainSystem;
import com.poly.item.Item;

public class IStockage extends IDisplay {
	
	private static String KeyboardRead;
	private static Scanner scan;
	public ArrayList<Item> StockageIn = new ArrayList<Item>();

	public IStockage(Player player) 
	{
		Spacer();
		displayEntity(player);
		Spacer();
		DisplayStockage(player);
		getClavier(player);
	}

	public void DisplayStockage(Player player) //Affiche l'inventaire du joueur et son inventaire d'équipement
	{
		String Object;
		int TryArmorStorage = 0;
		
		System.out.println("Vous avez dans votre inventaire :\n");
		
		for(int i = 0; i < player.getStock().getListStockage().size() && player.getStock().getListStockage().size() > 0 ; i++)//Affiche l'inventaire
		{
			Object = player.getStock().getListStockage().get(i).getName();
			if(tryList(Object) == true)
			{
				if(player.getStock().getListStockage().get(i) instanceof Equipment) //Si l'item est un equipement l'affiche en bleu
				{
					System.out.println("["+ player.getStock().GetNumberItem(Object)+"] " + Color.getIntensity() + Color.getBlue() + Object + Color.Reboot());
				}
				else
				{
					System.out.println("["+ player.getStock().GetNumberItem(Object)+"] " + Object );
				}
				StockageIn.add(player.getStock().getListStockage().get(i));
			}
		}
		
		if (player.getStock().getListStockage().size() <= 0) //Si l'inventaire ne contient rien notifie que l'inventaire est vide
			System.out.println("Votre inventaire est vide !");
		
		System.out.println("\nVotre \u00e9quipement actuellement \u00e9quip\u00e9 est :");
		for(int i = 0; i < player.getArmorStorage().storage.length; i++)//Affiche l'inventaire d'equipement
		{
			Object = player.getArmorStorage().getArmorStorage(i);
			if(Object != null) //Si l'emplacement n'est pas null affiche l'objet
				System.out.println(Color.getIntensity() + Color.getBlue() + Object + Color.Reboot());
			else 
				TryArmorStorage +=1;
		}
		
		if (TryArmorStorage >= player.getArmorStorage().storage.length) //Si toute les cases ont été testé notifie que l'inventaire est vide
			System.out.println("Vous n'avez aucun \u00e9quipement d'\u00e9quip\u00e9 actuellement !");
		Spacer();
	}
	
	public Boolean tryList(String string)//Verifie si l'objet est dans la liste d'objet qui sont afficher
	{
		String ObjectToTry = null;
		string = string.toLowerCase();
		for(int j = 0; j < StockageIn.size(); j++)
		{
			ObjectToTry = StockageIn.get(j).getName();
			ObjectToTry = ObjectToTry.toLowerCase();
			if(string.equals(ObjectToTry))//Si l'objet y est retourne faux
			{
				return false;
			}
		}
		return true;
	}
	
	public static void getClavier(Player player) 
	{
		System.out.println("Choisissez l'objet que vous souhaitez utiliser (Exemple: Potion normale)"
				+ "\nNotez que si vous utilisez un \u00e9quipement celui-ci vous sera \u00e9quip\u00e9."
				+ "\n\nPour retirer un \u00e9quipement utilisez la fonction 'retirer' suivis \ndu nom de l'\u00e9quipement.");
		System.out.println("\nSi vous souhaitez plus d'information concernant un objet utiliser \nla commande /info suivis du nom de l'objet.");
		System.out.println("\nPour fermer l'inventaire faite : Quitter");
		scan = new Scanner(System.in);
		setKeyboardRead(scan.next());
		setKeyboardRead(getKeyboardRead().toLowerCase());

        String A;
        String B;
        String C;//Recupere le premier mot entrer par le joueur
        String D;//Recupere la deuxieme partie du mot entrer par le joueur
        int i;//Parcours l'inventaire
        int j;//Parcours le stockage d'armure
        Boolean AStorage = false;//Si l'objet est dans le stockage d'armure ne parcous pas le reste
        if(getKeyboardRead().equals("quitter")) {}
        else if(getKeyboardRead().equals("/info")) //Si le joueur utilise /info
        {
        	System.out.println("Quel est l'objet sur lequel vous souhaitez avoir plus d'information ?");
        	C = scan.next();
        	C = C.toLowerCase();
	        D = scan.next();
	        A = C + " " + D;
	        A = A.toLowerCase();
        	for(j = 0; j < player.getArmorStorage().getStorage().length; j++)
        	{
        		B = player.getArmorStorage().getStorage()[j].getName();
        		B = B.toLowerCase();
        		if(A.equals(B))
        		{
        			MainSystem.CleanConsole();
        			System.out.println(player.getArmorStorage().getStorage()[j].getName() 
        					+ " : "
        					+ player.getArmorStorage().getStorage()[j].getDescription()+"\n");
        			AStorage = true;
        			break;
        		}
        	}
        	if (INpc.TryIntoList(C, 0, player.getStock().getListStockage()) && AStorage == false)
    		{
		        if (INpc.TryIntoList(A, 1, player.getStock().getListStockage()) || INpc.TryIntoList(A, 1, player.getStock().getListStockage()))
		        {
		        	for(i = 0; i < player.getStock().getListStockage().size();i++)
		        	{
		        		B = player.getStock().getListStockage().get(i).getName();
		        		B = B.toLowerCase();
		        		if(A.equals(B))
		        		{
		        			MainSystem.CleanConsole();
		        			System.out.println(player.getStock().getListStockage().get(i).getName() 
		        					+ " : "
		        					+ player.getStock().getListStockage().get(i).getDescription()+"\n");
		        			break;
		        		}
		        	}
		        	if (i >= player.getStock().getListStockage().size() && j >= player.getArmorStorage().getStorage().length)
		        	{
		        		MainSystem.CleanConsole();
		        		System.out.println("Vous ne poss\u00e9dez pas cet objet ou la commande \u00e9x\u00e9cuter n'est pas la bonne.");
		        	}
		        }
		        else
		        {
		        	MainSystem.CleanConsole();
		        	System.out.println("Vous ne poss\u00e9dez pas cet objet ou la commande \u00e9x\u00e9cuter n'est pas la bonne.");
		        }
    		}
        	else if (AStorage == false && !INpc.TryIntoList(C, 0, player.getStock().getListStockage()))
        	{
        		MainSystem.CleanConsole();
        		System.out.println("Vous ne poss\u00e9dez pas cet objet ou la commande \u00e9x\u00e9cuter n'est pas la bonne.");       		
        	}
        	new IStockage(player);
        }
        else if(getKeyboardRead().equals("remove") || getKeyboardRead().equals("retirer") || getKeyboardRead().equals("retire"))//Commande pour retirer un objet equiper
        {
        	setKeyboardRead(scan.next() + " " + scan.next());
        	setKeyboardRead(getKeyboardRead().toLowerCase());
            player.getStock().RemoveItem(player, getKeyboardRead());
            new IStockage (player);
        }
        else
        {
        	setKeyboardRead(getKeyboardRead() + " " + scan.next());
            setKeyboardRead(getKeyboardRead().toLowerCase());
            player.getStock().useItem(player, getKeyboardRead());
            new IStockage (player);
        }
	}

	public static String getKeyboardRead() {
		return KeyboardRead;
	}

	public static void setKeyboardRead(String keyboardRead) {
		KeyboardRead = keyboardRead;
	}
}



