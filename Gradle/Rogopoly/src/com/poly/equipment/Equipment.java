package com.poly.equipment;

import com.poly.item.Item;

@SuppressWarnings("serial")
public class Equipment extends Item {
	
	public int Hp;
	public int Att;
	public int Def;
	public int PM;
	public int price;
	public int type;
	public int rarety;
	public String name;

    public Equipment() {
        super();
    }

    public int getHp(){ return this.Hp; }
	
	public int getAtt(){ return this.Att; }
	
	public int getDef(){ return this.Def; }
	
	public int getMP(){ return this.PM; }
	
	public int getPrice(){ return this.price; }
	
	public int getType(){ return this.type; }
	
	public int getrarety(){ return this.rarety; }
	
	public String getName(){ return this.name; }
}
