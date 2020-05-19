package com.poly.ui;

import java.util.Scanner;
import com.poly.chest.Chest;
import com.poly.entity.*;
import com.poly.equipment.Equipment;
import com.poly.gestion.Color;

public class IChest extends IDisplay {
	
	private static String name;
	private static Scanner scan;

	public IChest(Player player, Chest chest) 
	{
		DisplayChestContains(chest);
		getClavier(player, chest);
	}
	
	public void DisplayChestContains(Chest chest)//Affiche le contenue du coffre 
	{
		int C = 0;
		Spacer();
		System.out.println("Ce coffre contient :");
		for (int i = 0; i < chest.getChest().size(); i++)
		{
			if(chest.getChest().size() > 0)
			{
				if(chest.getChest().get(i) instanceof Equipment)
				{
					System.out.println("- " + Color.getIntensity() + Color.getBlue() + chest.getChest().get(i).getName() + Color.Reboot());
				}
				else
				{
					System.out.println("- " + chest.getChest().get(i).getName());
				}			
			}
			else 
				C += 1;
		}
		if(C >= chest.getChest().size()) 
		{
			System.out.println("Ce coffre est vide !"); 
			chest.setEmpty(true);
		}
		Spacer();
	}
	
	public static void getClavier(Player player, Chest chest) 
	{
		System.out.println("Choisissez l'objet que vous souhaitez r\u00e9cup\u00e9rer (Exemple : Potion Supr\u00eame)");
		System.out.println("Vous pouvez \u00e9galement tout r\u00e9cup\u00e9rer en \u00e9crivant 'Tout'");
		System.out.println("Si vous ne souhaitez rien r\u00e9cup\u00e9rer faite : Quitter");
		scan = new Scanner(System.in);
		int C = 0;
        setName("");
        setName(scan.nextLine());
        setName(getName().toLowerCase());

        if(getName().equals("leave") || getName().equals("quitter")) {}
        else if(chest.getChest().size() > 0)
        {
        	for(int i = 0; i < chest.getChest().size();i++)
	        {
	        	String A;
	        	A = chest.getChest().get(i).getName();
	    		A = A.toLowerCase();
	    		if(A.equals(getName()) || getName().equals("tout") || getName().equals("all"))
	    		{
	    			chest.DropObject(chest, player, getName());
	    			if (chest.getChest().size() > 0)
	    				new IChest (player, chest);
	    		}
	    		else { C+=1; }
	        }
        	if(C >= 4)
            {
        		System.out.println("Vous avez entr\u00e9 une commande incorrect.");
        		new IChest(player, chest);
            }
        }
        else if(chest.getChest().size() <= 0)
        {
    		System.out.println("Vous avez entr\u00e9 une commande incorrect.");
    		
    		
    		new IChest(player, chest);
        }
	}
	
	public static String getName() 
	{
		return name;
	}
	
	public static void setName(String name) 
	{
		IChest.name = name;
	}
}