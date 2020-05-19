package com.poly.entity;

import com.poly.entity.IStrategyPlayer.*;

import java.io.IOException;
import java.lang.Math;
import com.poly.inventory.*;
import com.poly.map.Map;
import com.poly.equipment.*;

@SuppressWarnings("serial")
public class Player extends Entity {

	private transient IStrategyPlayer classe;
	private Boolean evasion = false; //Gestion de la fuite du joueur
	private int spellCD1 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 1
	private int spellCD2 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 2
	private int spellCD3 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 3
	private int spellCD4 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 4
	private int spellCD5 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 5
	private Stock stock; //Initialise l'inventaire du joueur
	private Armorstorage armorStorage; //Initialise l'inventaire d'�quipement du joueur

	private int ValueMap; //Stock les valeurs des maps acheter par le joueur
    private int Expbylvl[] = new int [101];
 

	public void init(String name) //Initialisation des statistiques commune des classes
	{
		InitExp();
		this.name = name;
		this.level = 1;
		this.actuAlfredp = 0;
		this.maxMp = 4;
		this.maxExp = Expbylvl[1];
		this.setMoney(0);
		setStock(new Stock());
		try {
			setArmorStorage(new Armorstorage());
		} catch (Exception e) {}
	}
	
	public Player(String name, Wizard classe)
	{
		this.init(name);
		this.maxHp = 40;
		this.Att = 25;
		this.Def = 10;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.classe = classe;
		this.nameClass = "Sorcier";
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}

	public Player(String name, Gladiator classe) //Initialise les stats du gladiateur
	{
		this.init(name);
		this.maxHp = 80;
		this.Att = 15;
		this.Def = 20;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.classe = classe;
		this.nameClass = "Gladiateur";
		this.stats = "Point de vie = " + maxHp  + " Attaque = " + Att  + " D\u00e9fense = " + Def;
	}
	
	public Player(String name, Bowman classe) //Initialise les stats de l'archer
	{
		this.init(name);
		this.maxHp = 50;
		this.Att = 20;
		this.Def = 10;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.classe = classe;
		this.nameClass = "Archer";
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public Player(String name, Assassin classe) //Initialise les stats de l'assassin
	{
		this.init(name);
		this.maxHp = 50;
		this.Att = 20;
		this.Def = 10;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.classe = classe;
		this.nameClass = "Assassin";
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}

	public void levelUp() //Ajout des stats lors du passage au niveau superieur
	{
		switch(getNameClasse())
        {
        	case "Assassin" : /*Assassin*/
        		this.maxHp += 13;
        		this.Att += 3;
        		this.Def += 2;
        		this.level += 1;
        		break;
        	case "Archer" : /*Archer*/
        		this.maxHp += 13;
        		this.Att += 2;
        		this.Def += 2;
        		this.level += 1;
        		break;
        	case "Gladiateur" : /*Gladiateur*/
        		this.maxHp += 20;
        		this.Att += 2;
        		this.Def += 3;
        		this.level += 1;
        		break;
        	case "Sorcier" : /*Mage*/
        		this.maxHp += 10;
        		this.Att += 5;
        		this.Def += 2;
        		this.level += 1;
        		break;
        }
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.maxExp = this.Expbylvl[this.level];
		this.money += this.ValueMap;
		this.actuAlfredp = 0;
	}

	public void AddEquipement(Equipment objet) //Ajout des stats d'un equipement
	{
		if (objet.getHp() != 0) 
		{
			maxHp += objet.getHp();
			if(actualHp + objet.getHp() >= maxHp) 
				actualHp = maxHp;
			else 
				actualHp += objet.getHp();
		}
		if (objet.getMP() != 0) 
		{
			maxMp += objet.getMP();
			if(actualMp >= maxMp ) 
				actualMp = maxMp;
			else 
				actualMp += objet.getMP();
		}
		if (objet.getAtt() != 0) 
		{
			if(objet instanceof Weapon)
			{
				int A = 0;
				A = objet.getAtt() - Math.round(objet.getAtt()*(float)50/100);
				if(this.getNameClass().equals("Sorcier") && objet.getType() != 4)
				{
					Att += A;
				}
				else if(this.getNameClass().equals("Gladiateur") && objet.getType() != 3)
				{
					Att += A;
				}
				else if(this.getNameClass().equals("Archer") && objet.getType() != 1)
				{
					Att += A;
				}
				else if(this.getNameClass().equals("Assassin") && objet.getType() != 2)
				{
					Att += A;
				}
				else
				{
					Att += objet.getAtt();
				}
			}
			else
			{
				Att += objet.getAtt();
			}
		}
		if (objet.getDef() != 0) 
		{
			Def += objet.getDef();
		}
	}

	public void RemoveEquipement(Equipment objet) //Retire les stats d'un equipement
	{
			if (objet.getHp() != 0) {
				maxHp -= objet.getHp();
				actualHp -= objet.getHp();
			}
			if (objet.getMP() != 0) {
				maxMp -= objet.getMP();
				actualMp -= objet.getMP();
			}
			if (objet.getAtt() != 0) {
				if(objet instanceof Equipment)
				{
					int A = 0;
					A = objet.getAtt() - Math.round(objet.getAtt()*(float)50/100);
					if(this.getNameClass().equals("Sorcier") && objet.getType() != 4)
					{
						System.out.println("Bijour S");
						Att -= A;
					}
					else if(this.getNameClass().equals("Gladiateur") && objet.getType() != 3)
					{
						System.out.println("Bijour G");
						Att -= A;
					}
					else if(this.getNameClass().equals("Archer") && objet.getType() != 1)
					{
						System.out.println("Bijour Ar");
						Att -= A;
					}
					else if(this.getNameClass().equals("Assassin") && objet.getType() != 2)
					{
						System.out.println("Bijour As");
						Att -= A;
					}
					else
					{
						Att -= objet.getAtt();
					}
				}
				else
				{
					Att -= objet.getAtt();
				}
			}
			if (objet.getDef() != 0) {
				Def -= objet.getDef();
			}
	}	   
	
	//Initialise l'exp�rience que le joueur \u00e0 besoin \u00e0 chaque niveau
	public void InitExp() 
    {
    	Expbylvl[1] = 100;
    	for(int i = 2; i < Expbylvl.length; i++)
    	{
    		Expbylvl[i] = Expbylvl[i-1] + (20*(i-1));
    		if(i%10 == 0)
    		{
    			Expbylvl[i] += 100*i;
    		}
    	}
    }
	
	@SuppressWarnings("null")
	public Player AddSkill (Player player)
	{
		if(player.getNameClass().equals("Sorcier"))
		{
			player.setClasse(new Wizard());
		}
		else if(player.getNameClass().equals("Gladiateur"))
		{
			player.setClasse(new Gladiator());
		}
		else if(player.getNameClass().equals("Archer"))
		{
			player.setClasse(new Bowman());
		}
		else if(player.getNameClass().equals("Assassin"))
		{
			player.setClasse(new Assassin());
		}
		else
		{
			IOException i = null;
			i.printStackTrace();
			return null;
		}
		return player;
	}

	@Override
	public void death(Map M, int x, int y, Player player) {
		//Inutile pour le joueur
	}
	
	//////Getters //////
	public int getValueMap() {
		return ValueMap;
	}
	public Boolean getEvasion() {
		return evasion;
	}
	public IStrategyPlayer getClasse() {
		return classe;
	}
	public int getSpellCD1() {
		return spellCD1;
	}
	public int getSpellCD2() {
		return spellCD2;
	}
	public int getSpellCD3() {
		return spellCD3;
	}
	public int getSpellCD4() {
		return spellCD4;
	}
	public int getSpellCD5() {
		return spellCD5;
	} 
    public String getNameClasse() {
		return nameClass;
	}
	public Armorstorage getArmorStorage() {
		return armorStorage;
	}
	public Stock getStock() {
		return stock;
	}
	public int[] getExpbylvl() {
		return Expbylvl;
	}

	////// Setters //////
	public void setClasse(IStrategyPlayer classe) {
		this.classe = classe;
	}
	public void setValueMap(int valueMap) {
		ValueMap = valueMap;
	}
	public void setSpellCD1(int spellCD1) {
		this.spellCD1 = spellCD1;
	}
	public void setSpellCD2(int spellCD2) {
		this.spellCD2 = spellCD2;
	}
	public void setSpellCD3(int spellCD3) {
		this.spellCD3 = spellCD3;
	}
	public void setSpellCD4(int spellCD4) {
		this.spellCD4 = spellCD4;
	}
	public void setSpellCD5(int spellCD5) {
		this.spellCD5 = spellCD5;
	}
	public void setEvasion(Boolean evasion) {
		this.evasion = evasion;
	}
	public void setArmorStorage(Armorstorage armorStorage) {
		this.armorStorage = armorStorage;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	// Reduit les cd des skills.
	public void reload() 
	{
		this.spellCD1 = Math.max(0, this.spellCD1-1);
		this.spellCD2 = Math.max(0, this.spellCD2-1);
		this.spellCD3 = Math.max(0, this.spellCD3-1);
		this.spellCD4 = Math.max(0, this.spellCD4-1);
		this.spellCD5 = Math.max(0, this.spellCD5-1);
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell1(Entity target) 
	{
		if (this.spellCD1 > 0) return false;
		else {
			this.spellCD1 = this.classe.spell1(this, target);
			return true;
		}
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell2(Entity target) 
	{
		if (this.spellCD2 > 0) return false;
		else {
			this.spellCD2 = this.classe.spell2(this, target);
			return true;
		}
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell3(Entity target) 
	{
		if (this.spellCD3 > 0) return false;
		else {
			this.spellCD3 = this.classe.spell3(this, target);
			return true;
		}
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell4(Entity target) 
	{
		if (this.spellCD4 > 0) return false;
		else {
			this.spellCD4 = this.classe.spell4(this, target);
			return true;
		}
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell5(Entity target) 
	{
		if (this.spellCD5 > 0) return false;
		else {
			this.spellCD5 = this.classe.spell5(this, target);
			return true;
		}
	}
}
