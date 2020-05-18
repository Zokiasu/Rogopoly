package com.poly.entity;

import com.poly.chest.Chest;
import com.poly.entity.IStrategyNPC.*;
import com.poly.item.Item;
import com.poly.map.Map;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Npc extends Entity {
	
	private transient IStrategyNPC classe;
	private String speech; //Gestion de ce que dit le pnj
	private int spellCD1 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 1
	private int spellCD2 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 2
	private int spellCD3 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 3
	private int spellCD4 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 4
	private int spellCD5 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 5
	private ArrayList<Item> shop; //Stock ce que vend le marchand
	
	
	public void init(String name) //Initialisation des statistiques commune des classes
	{
		this.name = name;
		this.level = 1;
		this.actuAlfredp = 0;
		this.maxMp = 5;
	}
	
	public Npc(Guardian classe) //Initialise les stats du gardien
	{
		this.init("Alfred");
		this.setSpeech("Si vous souhaitez sortir vous devrez me payer.");
		this.nameClass = "Gardien";
		this.maxHp = 5000;
		this.classe = classe;
		this.Att = 1000;
		this.Def = 1000;
		this.money = 5000;
		this.maxExp = 1000;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}

	public Npc(Steward classe) //Initialise les stats de l'intendant
	{
		this.init("Ben");
		this.setSpeech("Voudriez-vous acheter cette carte ?");
		this.nameClass = "Intendant";
		this.maxHp = 500;
		this.classe = classe;
		this.Att = 250;
		this.Def = 250;
		this.money = 500;
		this.maxExp = 100;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public Npc(Trader classe) //Initialise les stats du marchand
	{
		this.init("Lustig");
		this.setSpeech("Bonjour aventurier, je suis un noble marchand, enchant\u00e9 de faire votre connaissance.");
		this.nameClass = "Marchand";
		this.maxHp = 100;
		this.classe = classe;
		this.Att = 50;
		this.Def = 50;
		this.money = 15;
		this.maxExp = 25;
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
		shop = new ArrayList<Item>();
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}

	public void levelUp() //Ajout des stats lors du passage au niveau superieur
	{
		switch(getName())
        {
        	case "Lustig" : /*Marchand*/
        		this.maxHp += 10;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(2*this.level));
        		this.Att += 10;
        		this.Def += 10;
        		this.level += 1;
        		break;
        	case "Ben" : /*Intendant*/
        		this.maxHp += 15;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(4*this.level));
        		this.Att += 15;
        		this.Def += 15;
        		this.level += 1;
        		break;
        	case "Alfred" : /*Guardien*/
        		this.maxHp += 100;
        		this.maxMp += 10;
        		this.setMaxExp(this.getMaxExp()+(4*this.level));
        		this.Att += 100;
        		this.Def += 100;
        		this.level += 1;
        		break;
        }
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
	}

	@Override
	public void death(Map M, int x, int y, Player player) 
	{
		M.setCase(x,y,'C');//Remplace le symbole du npc par le symbole du coffre
		for(int i = 0; i <M.getListNpc().size();i++) //Supprime le coffre de la liste
		{
			if(M.getListNpc().get(i).getXY("X") == x && M.getListNpc().get(i).getXY("Y") == y)
			{
				M.getListNpc().remove(i);
			}
		}
		Chest coffre = new Chest('O', player); //Cr�e un coffre ouvert
        coffre.setPosX(x);
        coffre.setPosY(y);
		M.getListChest().add(coffre);
	}
	
	@SuppressWarnings("null")
	public Npc AddSkill (Npc npc)
	{
		if(npc.getNameClass().equals("Gardien"))
		{
			npc.setClasse(new Guardian());
		}
		else if(npc.getNameClass().equals("Intendant"))
		{
			npc.setClasse(new Steward());
		}
		else if(npc.getNameClass().equals("Marchand"))
		{
			npc.setClasse(new Trader());
		}
		else
		{
			IOException i = null;
			i.printStackTrace();
			return null;
		}
		return npc;
	}

	////// Setters //////
	public void setShop(ArrayList<Item> shop) {
		this.shop = shop;
	}
	public void setSpeech(String speech) {
		this.speech = speech;
	}
	public void setClasse(IStrategyNPC classe) {
		this.classe = classe;
	}

	////// Getters //////
	public String getSpeech() {
		return speech;
	}
	public IStrategyNPC getClasse() {
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
	public ArrayList<Item> getShop() {
		return shop;
	}
	
	public void reload() // Reduit les cd des skills.
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

		this.spellCD1 = this.classe.spell1(this, target);
		return true;
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell2(Entity target) 
	{
		if (this.spellCD2 > 0) return false;

		this.spellCD2 = this.classe.spell2(this, target);
		return true;
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell3(Entity target) 
	{
		if (this.spellCD3 > 0) return false;
			
		this.spellCD3 = this.classe.spell3(this, target);
		return true;
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell4(Entity target) 
	{
		if (this.spellCD4 > 0) return false;

		this.spellCD4 = this.classe.spell4(this, target);
		return true;
	}
	
	// Renvoie Vrai si le sort s'est lance, faux sinon.
	public Boolean spell5(Entity target) 
	{
		if (this.spellCD5 > 0) return false;

		this.spellCD5 = this.classe.spell5(this, target);
		return true;
	}
}