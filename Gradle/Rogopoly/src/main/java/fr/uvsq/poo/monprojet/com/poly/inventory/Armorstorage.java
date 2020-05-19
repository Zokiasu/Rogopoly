package com.poly.inventory;

import com.poly.entity.*;
import com.poly.equipment.*;

@SuppressWarnings("serial")
public class Armorstorage implements java.io.Serializable{
	
	public Armorstorage() throws Exception
	{
		initStorageArmor();
	}

	//Initialisation de tout les objets a valeur null (Qui ne donne aucune stat)
	public Equipment [] storage = new Equipment [6];
	
	//On remplit chaque case du tableau pour determine l'emplacement de chaque objet
	public void initStorageArmor() throws Exception//Initialise chaque case de l'inventaire d'armure
	{		
		storage[0] = Breastplate.create(0);
		storage[1] = Greaves.create(0);
		storage[2] = Boots.create(0);
		storage[3] = Gauntlet.create(0);
		storage[4] = Headgear.create(0);
		storage[5] = Weapon.create(0, 0);
	}
	
	//Retourne ce qu'il y a dans l'emplacement demander
	public String getArmorStorage(int A)
	{ 
		if(getStorage()[A].getrarety() != 0) 
			return getStorage()[A].getName();
		else 
			return null;
	}
	
	public Equipment[] getStorage() {
		return storage;
	}

	public void addStorageEquipment(Equipment objet, Player Player)	//Ajoute un equipement dans l'inventaire et l'equipe au joueur
	{
		//Test si le nom de l'objet que l'on souhaite ajoute correspond au nom que l'on souhaite
		if (objet instanceof Weapon)
		{
			//Test si le type de l'objet que le souhaite ajoute correspond a celui qui est deja utilise
			if((objet.getrarety()) == (storage[5].getrarety()) && (objet.getType()) == (storage[5].getType()))
			{
				System.out.println("Vous ne pouvez pas \u00e9quiper un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			}
			else
			{
				//Si non, equipe l'objet dans l'inventaire d'\u00e9quipement et ajoute les stats au joueur
				if(storage[5].getrarety() != 0) Player.getStock().AddStock(storage[5]);
				Player.RemoveEquipement(storage[5]);
				storage[5] = objet;
				System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
				Player.AddEquipement(objet);				
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
			}
		}
		
		else if (objet instanceof Headgear)
		{
			if((objet.getrarety()) == (storage[4].getrarety()))
				System.out.println("Vous ne pouvez pas \u00e9quiper un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			if(storage[4].getrarety() != 0) Player.getStock().AddStock(storage[4]);
			Player.RemoveEquipement(storage[4]);
			storage[4] = objet;
			System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
			Player.AddEquipement(objet);
			System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
		}
		
		else if (objet instanceof Breastplate)
		{
			if((objet.getrarety()) == (storage[0].getrarety()))
				System.out.println("Vous ne pouvez pas \u00e9quiper un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			if(storage[0].getrarety() != 0) Player.getStock().AddStock(storage[0]);
			Player.RemoveEquipement(storage[0]);
			storage[0] = objet;
			System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
			Player.AddEquipement(objet);
			System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
		}
		
		else if (objet instanceof Greaves)
		{
			if((objet.getrarety()) == (storage[1].getrarety()))
				System.out.println("Vous ne pouvez pas \u00e9quiper un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			if(storage[1].getrarety() != 0) Player.getStock().AddStock(storage[1]);
			Player.RemoveEquipement(storage[1]);
			storage[1] = objet;
			System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
			Player.AddEquipement(objet);
			System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
		}
		
		else if (objet instanceof Boots)
		{
			if((objet.getrarety()) == (storage[2].getrarety()))
				System.out.println("Vous ne pouvez pas \u00e9quiper un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			if(storage[2].getrarety() != 0) Player.getStock().AddStock(storage[2]);
			Player.RemoveEquipement(storage[2]);
			storage[2] = objet;
			System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
			Player.AddEquipement(objet);
			System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
		}
		else if (objet instanceof Gauntlet)
		{
			if((objet.getrarety()) == (storage[3].getrarety()))
				System.out.println("Vous ne pouvez pas \u00e9quip\u00e9 un objet d\u00e9j\u00e0 \u00e9quip\u00e9");
			if(storage[3].getrarety() != 0) Player.getStock().AddStock(storage[3]);
			Player.RemoveEquipement(storage[3]);
			storage[3] = objet;
			System.out.println("Vous avez \u00e9quip\u00e9 " + objet.getName());
			Player.AddEquipement(objet);
			System.out.println(objet.getName()+ " a \u00e9t\u00e9 ajout\u00e9 dans l'inventaire d'\u00e9quipement");
		}
		else
			System.out.println("Vous ne pouvez \u00e9quiper uniquement des objets de type \u00e9quipements");
	}
	
	public void RemoveStorageEquipment(Equipment objet, Player Player) throws Exception//retire un equipement dans l'inventaire et le desequipe du joueur
	{
		//Test si le nom de l'objet que l'on souhaite retir\u00e9 correspond au nom que l'on souhaite
		if (objet instanceof Weapon)
		{
			//Test si le type de l'objet que le souhaite desequipe correspond a celui qui est equipe et ne correspond pas a l'objet null
			if((objet.getrarety()) == (storage[5].getrarety()) && (objet.getrarety()) != (Weapon.create(0, 0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[5]);
				storage[5] = Weapon.create(0, 0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");
			}

			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		
		else if (objet instanceof Headgear)
		{
			//Test si le type de l'objet que le souhaite desequipe correspond a celui qui est equipe et ne correspond pas a l'objet null
			if((objet.getrarety()) == (storage[4].getrarety()) && (objet.getrarety()) != (Headgear.create(0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[4]);
				storage[4] = Headgear.create(0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");
			}
			
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		
		else if (objet instanceof Breastplate)
		{
			if((objet.getrarety()) == (storage[0].getrarety()) && (objet.getrarety()) != (Breastplate.create(0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[0]);
				storage[0] = Breastplate.create(0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");
			}
			
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		
		else if (objet instanceof Greaves)
		{
			if((objet.getrarety()) == (storage[1].getrarety()) && (objet.getrarety()) != (Greaves.create(0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[1]);
				storage[1] = Greaves.create(0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");
			}
			
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		
		else if (objet instanceof Boots)
		{
			if((objet.getrarety()) == (storage[2].getrarety()) && (objet.getrarety()) != (Boots.create(0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[2]);
				storage[2] = Boots.create(0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");				
			}
			
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		else if (objet instanceof Gauntlet)
		{
			if((objet.getrarety()) == (storage[3].getrarety()) && (objet.getrarety()) != (Gauntlet.create(0).getrarety()))
			{
				System.out.println("Vous desequipez " + objet.getName());
				Player.RemoveEquipement(storage[3]);
				storage[3] = Gauntlet.create(0);
				System.out.println(objet.getName()+ " a \u00e9t\u00e9 retir\u00e9 de l'inventaire d'\u00e9quipement");
			}
			
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
		}
		else
			System.out.println("Vous ne pouvez pas retirer un objet qui n'est pas \u00e9quip\u00e9");
	}

}
