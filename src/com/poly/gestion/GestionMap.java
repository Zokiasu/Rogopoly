package com.poly.gestion;

import com.poly.entity.Player;
import com.poly.map.*;
import java.util.Random;

public class GestionMap 
{	
	public static Map RandMap(Player p) 
	{
		Random r = new Random();
		int x = r.nextInt(100); 
		
		if (x<40)  
			return new Monstre(p);	   
		else if (x<75) 
			return new Marchande(p);
		else if (x<95) 
			return new Chance(p);
		else 
			return new Prison(p);
	}
}
