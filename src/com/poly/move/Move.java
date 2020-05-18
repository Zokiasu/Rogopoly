package com.poly.move;

import com.poly.map.*;
import com.poly.entity.*;
import com.poly.gestion.*;

public class Move {

	public void move(Entity entity, String direction, int mouv, int px, int py, Map map)
	{
		new Keyboard();
        char y ='\u00B7';
        char c = map.matrice[px][py];
        direction = direction.toLowerCase();
        if (entity.getActualMp() - mouv < 0) // 
        {
            System.out.println("Pas le nombre de PM n\u00e9cessaires");
        }
        else
        {
            if (entity.getActualMp() >= 0)
            {
            	for (int a = 0; a < mouv && (direction.equals("z") || direction.equals("q") || direction.equals("s") || direction.equals("d")); a++) 
            	{
                    if (direction.equals("z")) 
                    { // Direction vers le haut
                        if (map.getCase(px, py - 1) == '\u00B7' || (map.getCase(px, py - 1) == 'S' && entity.getClass().getName()=="com.poly.entity.Player" )) 
                        {
                        	map.setCase(px, py, y);
                            y = map.getCase(px, py - 1);
                        	entity.removeMp(1);
                            py--;
                        }
                        else
                        {
                        	if (entity instanceof Player) System.out.println("Un obstacle vous emp\u00e9che de vous d\u00e9placer");
                        }
                    }
                    else if (direction.equals("d")) 
                    { // Direction vers la droite

                        if (map.getCase(px + 1, py) == '\u00B7' || (map.getCase(px + 1, py) == 'S' && entity.getClass().getName()=="com.poly.entity.Player" )) 
                        {
                        	map.setCase(px, py, y);
                            y = map.getCase(px + 1, py);
                        	entity.removeMp(1);
                            px++;
                        }
                        else if (map.getCase(px + 1, py) == '+' || map.getCase(px + 1, py) == 'I' || map.getCase(px + 1, py) == 'C' || map.getCase(px + 1, py) == 'P')
                        {
                        	if (entity instanceof Player) System.out.println("Un obstacle vous emp\u00e9che de vous d\u00e9placer");
                        }
                    } 
                    else if (direction.equals("s")) 
                    { // Direction vers le bas

                        if (map.getCase(px, py + 1) == '\u00B7' || (map.getCase(px, py + 1) == 'S' && entity.getClass().getName()=="com.poly.entity.Player" )) 
                        {
                        	map.setCase(px, py, y);
                            y = map.getCase(px, py + 1);
                        	entity.removeMp(1);
                            py++;
                        }
                        else
                        {
                        	if (entity instanceof Player) System.out.println("Un obstacle vous emp\u00e9che de vous d\u00e9placer");
                        }
                    } 
                    else if (direction.equals("q")) 
                    { // Direction vers la gauche

                        if (map.getCase(px - 1, py) == '\u00B7' || (map.getCase(px - 1, py) == 'S' && entity.getClass().getName()=="com.poly.entity.Player" )) 
                        {
                        	map.setCase(px, py, y);
                            y = map.getCase(px - 1, py);
                        	entity.removeMp(1);
                            px--;
                        }
                        else
                        {
                        	if (entity instanceof Player) System.out.println("Un obstacle vous emp\u00e9che de vous d\u00e9placer");
                        }
                    }
                }
            }
            map.setEntity(entity, px, py, c);
        }
    }
}