package com.poly.entity;

import com.poly.map.Map;

@SuppressWarnings("serial")
public abstract class Entity implements java.io.Serializable{
	
	protected int actualHp; //Point de vie actuel
    protected int maxHp; //Maximum des points de vie
	protected int actualMp; //Point de mouvement actuel
    protected int maxMp; //Maximum des points de mouvement
    protected int level; //Niveau de l'entit�
    protected int actuAlfredp; //Point d'exp�rience actuel
    protected int maxExp; //Maximum point d'exp�rience
    protected int Att; //Point d'attaque de l'entit�
    protected int Def; //Point de d�fense de l'entit�
    protected int X; //Position X sur la map de l'entit�
    protected int Y; //Position Y sur la map de l'entit�
	protected int money; //Argent de l'entit�
	protected String name; //Nom de l'entit�
	protected String nameClass; //Nom de la classe de l'entit�
	protected String stats; //Liste des stats de l'entit�
	protected Boolean IsAttacked = false; //V�rifie si le joueur est attaqu�
	protected Boolean FirstFight = true;
	
	public abstract void death(Map M, int x, int y, Player player); //m�thode qui d�finie les actions \u00e0 faire si l'entit� meurt
	public abstract Boolean spell1(Entity target); //G�re la comp�tence 1
	public abstract Boolean spell2(Entity target); //G�re la comp�tence 2
    public abstract Boolean spell3(Entity target); //G�re la comp�tence 3
    public abstract Boolean spell4(Entity target); //G�re la comp�tence 4
	public abstract Boolean spell5(Entity target); //G�re la comp�tence 5
    
	////// Getters //////
	
    public String getStats() {
		return stats;
	}

	public Boolean getAttacked() {
		return IsAttacked;
	}
	public int getLevel() {
		return level;
	}
	public int getMaxExp() {
		return maxExp;
	}  
	public int getActuAlfredp() {
		return actuAlfredp;
	}
    public int getActualHp() {
    	return actualHp;
    }
	public int getMaxHp() {
		return maxHp;
	}
	public int getActualMp() {
		return actualMp;
	}
	public int getMaxMp() {
		return maxMp;
	}
	public int getAtt() {
		return Att;
	}
	public int getDef() {
		return Def;
	}
	public int getXY(String A){
		A = A.toLowerCase();
		if (A.equals("x"))
		{
			return X; 
		}
		else if (A.equals("y"))
		{
			return Y;
		}
		return 0;
	}
	public String getName() {
		return name;
	}
	public int getMoney() {
		return money;
	}
    public String getNameClass() {
		return nameClass;
	}
	
	////// Setters //////
    
	public void setAttacked(Boolean attacked) {
		IsAttacked = attacked;
	}
	public void setLvL(int lvL) {
		this.level = lvL;
	}
	public void setActuAlfredp(int exp) {
		this.actuAlfredp = exp;
	}
	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}
	public void addActuAlfredp(int xp) {
		this.actuAlfredp = this.actuAlfredp + xp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
    public void setActualHp(int hp) {
    	this.actualHp = hp;
    }
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	public void setActualMp(int actualMp) {
		this.actualMp = actualMp;
	}
	public void setAtt(int Att) {
		this.Att = Att;
	}
	public void setDef(int def) {
		this.Def = def;
	}
	public void setX(int x) {
		this.X = x;
	}
	public void setY(int y) {
		this.Y = y;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	////// Add //////
	
	public void addHp(int A){
		this.actualHp = Math.min(this.actualHp + A, this.maxHp);
	}
	
	public void addMp(int A){ 
		this.actualMp = Math.min(this.actualMp + A, this.maxMp);
	}
	
	public void addMoney(int A){		 
		this.money += A;
	}
	
	////// Remove //////
	
	public void removeMoney(int A)
	{
		this.money = Math.max(0, this.money - A);
	}
	public void removeHp(int A){ 
		this.actualHp = Math.max(this.actualHp - A, 0);
	}
	public void removeMp(int A){ 
		this.actualMp =  Math.max(this.actualMp - A, 0);
	}
}