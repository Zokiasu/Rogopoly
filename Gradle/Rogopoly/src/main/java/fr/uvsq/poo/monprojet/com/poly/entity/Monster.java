package com.poly.entity;

import com.poly.chest.Chest;
import com.poly.entity.IStrategyMonster.*;
import com.poly.map.Map;

import java.io.IOException;
import java.lang.Math;

@SuppressWarnings("serial")
public class Monster extends Entity {
	
	private transient IStrategyMonster classe;
	private int spellCD1 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 1
	private int spellCD2 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 2
	private int spellCD3 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 3
	private int spellCD4 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 4
	private int spellCD5 = 0; //Gestion du d�lais de r�cup�ration da la comp�tence 5

	public void init(String name)  //Initialisation des statistiques commune des classes
	{
		this.name = name;
		this.level = 1;
		this.maxMp = 5;
		this.actualMp = this.maxMp;
	}
	
	public Monster(Inferior classe) //Initialise les stats du monstre inf�rieur
	{
		this.init("F55B");
		this.nameClass = "Inferieur";
		this.maxHp = 40;
		this.actualHp = this.maxHp;
		this.Att = 15;
		this.Def = 10;
		this.classe = classe;
		this.maxExp = 2;
		this.money = 5;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public Monster(Normal classe) //Initialise les stats du monstre normal
	{
		this.init("E98G");
		this.nameClass = "Normal";
		this.maxHp = 50;
		this.Att = 15;
		this.Def = 15;
		this.actualHp = this.maxHp;
		this.classe = classe;
		this.maxExp = 5;
		this.money = 10;
		this.stats = "Point de vie = " + maxHp 
				+ "Attaque = " + Att 
				+ "D\u00e9fense = " + Def;
	}
	
	public Monster(Superior classe) //Initialise les stats du monstre sup�rieur
	{
		this.init("D911");
		this.nameClass = "Superieur";
		this.maxHp = 70;
		this.Att = 20;
		this.Def = 15;
		this.actualHp = this.maxHp;
		this.classe = classe;
		this.maxExp = 8;
		this.money = 20;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public Monster(Ultimate classe) //Initialise les stats du monstre ultimate
	{
		this.init("A51");
		this.nameClass = "Ultime";
		this.maxHp = 100;
		this.Att = 25;
		this.Def = 20;
		this.actualHp = this.maxHp;
		this.classe = classe;
		this.maxExp = 10;
		this.money = 30;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public Monster(PrisonSlayer classe) //Initialise les stats du monstre tueur de prison
	{
		this.init("C52B");
		this.nameClass = "Tueur";
		this.maxHp = 100;
		this.Att = 25;
		this.Def = 20;
		this.actualHp = this.maxHp;
		this.classe = classe;
		this.maxExp = 10;
		this.money = 30;
		this.stats = "Point de vie = " + maxHp 
				+ " Attaque = " + Att 
				+ " D\u00e9fense = " + Def;
	}
	
	public void levelUp() //Ajout des stats lors du passage au niveau superieur
	{
		switch(getName())
        {
        	case "F55B" : /*Inferior*/
        		this.maxHp += 15;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(2*this.level));
        		this.Att += 10;
        		this.Def += 5;
        		this.level += 1;
        		break;
        	case "E98G" : /*normal*/
        		this.maxHp += 20;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(3*this.level));
        		this.Att += 15;
        		this.Def += 10;
        		this.level += 1;
        		break;
        	case "D911" : /*Superior*/
        		this.maxHp += 25;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(4*this.level));
        		this.Att += 20;
        		this.Def += 15;
        		this.level += 1;
        		break;
        	case "A51" : /*Ultimate*/
        		this.maxHp += 30;
        		this.maxMp += 1;
        		this.setMaxExp(this.getMaxExp()+(5*this.level));
        		this.Att += 25;
        		this.Def += 20;
        		this.level += 1;
        		break;
        }
    	if(this.level%5 == 0)
    	{
    		switch(getName())
            {
            	case "F55B" : /*Inferior*/
            		this.maxHp += 5;
            		this.maxMp += 1;
            		this.setMaxExp(this.getMaxExp()+(2*this.level));
            		this.Att += 10;
            		this.Def += 10;
            		this.level += 1;
            		break;
            	case "E98G" : /*normal*/
            		this.maxHp += 15;
            		this.maxMp += 1;
            		this.setMaxExp(this.getMaxExp()+(3*this.level));
            		this.Att += 15;
            		this.Def += 15;
            		this.level += 1;
            		break;
            	case "D911" : /*Superior*/
            		this.maxHp += 17;
            		this.maxMp += 1;
            		this.setMaxExp(this.getMaxExp()+(4*this.level));
            		this.Att += 17;
            		this.Def += 17;
            		this.level += 1;
            		break;
            	case "A51" : /*Ultimate*/
            		this.maxHp += 10;
            		this.maxMp += 1;
            		this.setMaxExp(this.getMaxExp()+(5*this.level));
            		this.Att += 20;
            		this.Def += 20;
            		this.level += 1;
            		break;
            }
    	}
		this.actualHp = this.maxHp;
		this.actualMp = this.maxMp;
	}
	
	@SuppressWarnings("null")
	public Monster AddSkill (Monster monster)
	{
		if(monster.getNameClass().equals("Inferieur"))
		{
			monster.setClasse(new Inferior());
		}
		else if(monster.getNameClass().equals("Normal"))
		{
			monster.setClasse(new Normal());
		}
		else if(monster.getNameClass().equals("Superieur"))
		{
			monster.setClasse(new Superior());
		}
		else if(monster.getNameClass().equals("Ultime"))
		{
			monster.setClasse(new Ultimate());
		}
		else if(monster.getNameClass().equals("Tueur"))
		{
			monster.setClasse(new PrisonSlayer());
		}
		else
		{
			IOException i = null;
			i.printStackTrace();
			return null;
		}
		return monster;
	}
	
	public void setClasse(IStrategyMonster classe) {
		this.classe = classe;
	}

	public IStrategyMonster getClasse() {
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

	@Override
	public void death(Map M, int x, int y, Player player) {
		M.setCase(x,y,'C');
		for(int i = 0; i <M.getListMob().size();i++)
		{
			if(M.getListMob().get(i).getXY("X") == x && M.getListMob().get(i).getXY("Y") == y)
			{
				M.getListMob().remove(i);
			}
		}
		Chest coffre = new Chest('O', player);
        coffre.setPosX(x);
        coffre.setPosY(y);
		M.getListChest().add(coffre);
	}
}
