package com.poly.inventory;

import java.util.*;
import com.poly.entity.*;
import com.poly.equipment.*;
import com.poly.item.*;

@SuppressWarnings("serial")
public class Stock implements java.io.Serializable{

	public ArrayList<Item> Stockage = new ArrayList<Item>();
		
	public void AddStock(Item item)//Ajout un item dans l'inventaire
	{
		Stockage.add(item);
	}
	
	public void RemoveStock(Item item)//retirer un item dans l'inventaire
	{
		for(int i = 0; i < Stockage.size(); i++)
		{
			if(Stockage.get(i) == item)
			{
				Stockage.remove(i);
				break;
			}
		}
	}

	public void RemoveStock(String item)//retirer un item dans l'inventaire
	{
		for(int i = 0; i < Stockage.size(); i++)
		{
			String B = Stockage.get(i).getName().toLowerCase();
			if(B.equals(item))
			{
				Stockage.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Item> getListStockage() 
	{
		return Stockage;
	}
	
	public int GetNumberItem(Item item)//Compte le nombre d'item dans l'inventaire
	{
		int NumberItem = 0;
		for(int i = 0; i < Stockage.size(); i++)
		{
			if(Stockage.get(i).getName() == item.getName())
			{
				NumberItem += 1;
			}
		}
		return NumberItem;
	}
	
	public int GetNumberItem(String string)//Compte le nombre d'item dans l'inventaire
	{
		int NumberItem = 0;
		String A = null;
		string = string.toLowerCase();
		for(int i = 0; i < Stockage.size(); i++)
		{
			A = Stockage.get(i).getName();
			A = A.toLowerCase();
			if(A.equals(string))
			{
				NumberItem += 1;
			}
		}
		return NumberItem;
	}

	public Player useItem(Player player, String name)//utilise un item de l'inventaire
	{
		for(int i = 0; i < player.getStock().getListStockage().size();i++)
		{
			Item A = player.getStock().getListStockage().get(i);
			String B = A.getName().toLowerCase();
			name = name.toLowerCase();
			if(name.equals(B))
			{
				if (A instanceof Equipment)
				{
					try {
						player.getArmorStorage().addStorageEquipment((Equipment) A, player);
						player.getStock().RemoveStock(B);
					} catch (Exception e) { e.printStackTrace(); }
					return player;
				}
				else if (A instanceof Potion)
				{
					((Potion) A).usePotion(player, ((Potion) A));
					player.getStock().RemoveStock(B);
					return player;
				}
				else if (A instanceof Key)
				{
					player.getStock().RemoveStock(A);
					return player;
				}
			}
		}
		System.out.println("Vous ne poss\u00e9dez pas cet objet.");
		return player;
	}
	
	public Player RemoveItem(Player player, String name)//retire un item de l'inventaire d'equipement
	{
		for(int i = 0; i < player.getArmorStorage().getStorage().length;i++)
		{
			Item A = player.getArmorStorage().getStorage()[i];
			String B = A.getName().toLowerCase();
			if(B.equals(name))
			{
				try {
					player.getArmorStorage().RemoveStorageEquipment((Equipment) A, player);
					player.getStock().AddStock(A);
				} catch (Exception e) { e.printStackTrace(); }
				return player;
			}
		}
		System.out.println("Vous ne pouvez pas jeter ou retirer cet objet ou vous ne le poss\u00e9dez pas");
		return player;
	}
}