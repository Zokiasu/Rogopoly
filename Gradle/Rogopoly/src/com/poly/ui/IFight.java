package com.poly.ui;

import com.poly.entity.*;

public class IFight extends IDisplay {
	
	public IFight(Entity Player, Entity Monster) 
	{
		Spacer();
		displayEntity(Player);
		Spacer();
		displayEntity(Monster);
		Spacer();
	}
}
