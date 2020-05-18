package com.poly.ui;

import com.poly.map.*;
import com.poly.entity.*;

public class IGeneral extends IDisplay {
	
	public IGeneral(Map m, Player p) 
	{
		
		DisplayMap(m, p);
	}
	
	public void DisplayMap(Map map, Player player) 
	{
		Spacer();
		displayEntity(player);
		System.out.println(RandomMessage());
		Spacer();
		map.ShowMap();
		Spacer();
		System.out.println("Besoin d'information suppl\u00e9mentaire ? Utiliser /aide.");
	}
}
