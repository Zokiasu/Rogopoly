package com.poly.chest;

import com.poly.entity.Player;
import com.poly.equipment.*;
import com.poly.item.Item;
import com.poly.item.Key;
import com.poly.item.Potion;
import com.poly.ui.IChest;

import java.util.*;

@SuppressWarnings("serial")
public class Chest implements java.io.Serializable{

    Random rand = new Random();
    
    private int posX;//Position en X du coffre sur la map
	private int posY;//Position en Y du coffre sur la map
	private Boolean empty = false;//Variable qui verifie si le coffre est vide ou non
	private ArrayList<Item> Chest = new ArrayList<Item>();//Liste d'item que contient le coffre
    private char ChestStatus;//Etat du coffre (Ouvert/Fermer)

    
    public Chest(char cheststatus, Player player)
    {
        this.ChestStatus = cheststatus;
        InitChest(player);
    }

    public void InitChest(Player player)//Initialise le coffre et ce qu'il contient
    {
        int Equipmentrarety = 0; //rarete des equipements
        int Chance = 0; //Pourcentage de chance (Potion & Clef)
        int ObjectType = 0; //Definis le type de l'objet
        int WeaponType = 0;
        DefChestStatus(ChestStatus); //Init coffre ouvert ou ferm�
        try
        {
            if(player.getLevel() > 5 && player.getLevel() < 10) //Niveau du joueur entre 5 et 10, rarete equipement entre 1 et 2
            	Equipmentrarety = 1 + rand.nextInt(2);
            else if(player.getLevel() < 7) //Niveau du joueur inf�rieur \u00e0 7, raret� �quipement 1
            	Equipmentrarety = 1;
            else //Sinon, raret� �quipement random 1 \u00e0 3
            	Equipmentrarety = 1 + rand.nextInt(3);
            
            for(int i = 0; i < rand.nextInt(4) + 2; i++)//Ajoute un \u00e0 un de 2 \u00e0 5 objets
            {
                ObjectType = rand.nextInt(8);
                
                switch (ObjectType)
                {
                    case 0://Gestion drop potion
                    	Chance =  rand.nextInt(100);
	                    	if(Chance > 99)//5% de chance d'avoir la potion 5
	                    		Chest.add(new Potion(5));
	                    	else if(Chance > 90)//15% de chance d'avoir la potion 4
	                    		Chest.add(new Potion(4));
	                    	else if(Chance > 50)//30% de chance d'avoir la potion 3
	                    		Chest.add(new Potion(3));
	                    	else if(Chance > 10)//40% de chance d'avoir la potion 2
	                    		Chest.add(new Potion(2));
	                    	else//10% de chance d'avoir la potion 1
	                    		Chest.add(new Potion(1));
                        break;
                    case 1://Gestion drop clef
                    	Chance =  rand.nextInt(100);
	                    	if(Chance > 95) //5% de chance d'avoir la clef de la prison
	                    		Chest.add(new Key(2));
	                    	else //95% de chance d'avoir la clef d'un coffre
		                    	Chest.add(new Key(1));
                        break;
                    case 2://Gestion drop plastrons
                    	Chest.add(Breastplate.create(Equipmentrarety));
                        break;
                    case 3://Gestion drop gants
                    	Chest.add(Gauntlet.create(Equipmentrarety));
                        break;
                    case 4://Gestion drop chapeaux
                    	Chest.add(Headgear.create(Equipmentrarety));
                        break;
                    case 5://Gestion drop bottes
                    	Chest.add(Boots.create(Equipmentrarety));
                        break;
                    case 6://Gestion drop armes
                    	WeaponType = 1 + rand.nextInt(4);
                    	Chest.add(Weapon.create(WeaponType, Equipmentrarety));
                        break;
                    case 7://Gestion drop jambi�res
                    	Chest.add(Greaves.create(Equipmentrarety));
                        break;
                }
            }
        }
        catch (Exception E) {}
    }

    public int DefChestStatus(char ChestStatus) //D�finis l'�tat du coffer (Fermer/Ouvert)
    {
        if(ChestStatus == 'F') //D�finit le coffre comme fermer
            return  0;
        else if (ChestStatus == 'O') //D�finit le coffre comme ouvert
            return  1;
        else
        	return -1;
    }

    public int CheckChest(Player player, Chest chest)//V�rifie si le coffre est ouvert ou fermer
    {
        if(ChestStatus == 'O')//Si le coffre est ouvert affiche son contenue
        	new IChest(player, chest);
        else if (ChestStatus == 'F')//Si le coffre est fermer v�rifie que le joueur ait la clef
        {
        	if (player.getStock().GetNumberItem("clef coffre") > 0)
        	{        	
                player.getStock().useItem(player, "clef coffre");
                player.addMoney(5);
                System.out.println("Vous utilisez une de vos clef de coffre");
                setChestStatus('O');
                new IChest(player, chest);
            }
        	else
        	{
    			System.out.println("D\u00e9sol\u00e9 vous n'avez pas l'objet n\u00e9cessaire pour ouvrir le coffre.");
        	}
        }
        return 0;
    }

    public void DropObject(Chest chest, Player player, String name)//Retire les objets du coffre et l'ajoute \u00e0 l'inventaire du joueur
	{
    	//vide le coffre si le joueur veut tout r�cup�rer et ajoute \u00e0 l'inventaire du joueur
		if(name.equals("tout") || name.equals("all"))
		{
			for (int i = 0; i < chest.getChest().size(); i++) 
		    {
				player.getStock().AddStock(chest.getChest().get(i));
	        }
			for(int i = chest.getChest().size()-1; i >= 0; i--)
			{
				chest.getChest().remove(i);
			}
			chest.setEmpty(true);
		}
		//Sinon v�rifie que l'objet est dans le coffre et l'ajoute \u00e0 l'inventaire du joueur
		else
		{
		    for (int i = 0; i < chest.getChest().size(); i++) 
		    {
	            if (name.equals(chest.getChest().get(i).getName().toLowerCase()))
	            {
	                player.getStock().AddStock(chest.getChest().get(i));
	                chest.DeleteChest(chest, i);
	                break;
	            }
	        }
		}
    }

	public void DeleteChest(Chest chest, int c)//Supprime un �l�ment du coffre
	{
	    chest.getChest().remove(c);
    }

	////// Getters //////
	
	public ArrayList<Item> getChest() {
		return Chest;
	}

	public char getChestStatus() 
	{
		return ChestStatus;
		}
	
    public int getXY(String A)
    {
    	A = A.toLowerCase();
		if (A.equals("x"))
		{
			return posX;
		}
		else if (A.equals("y"))
		{
			return posY;
		}
		return 0;
	}
	
    public Boolean getEmpty() 
    {
    	return empty; 
    }
    
    ////// Setters //////
    
    public void setChestStatus(char ec) 
    {
    	this.ChestStatus = ec;
    }
	
    public void setPosX(int posX) 
    {
    	this.posX = posX;
    }
	
    public void setPosY(int posY) 
    {
    	this.posY = posY;
    }
	
    public Item[] setChest(Item[] chest) 
    {
    	return chest;
    }
	
    public void setEmpty(Boolean empty) 
    { 
    	this.empty = empty; 
    }
}