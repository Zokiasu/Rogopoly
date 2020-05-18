package com.poly.gestion;

import com.poly.entity.*;
import com.poly.ui.*;
import com.poly.map.Map;

import java.util.*;

public class Keyboard {

	private String Direction;
    private int Mouvement;
    private Scanner readKeyboard;

    public void inputPlayer(Player player, Map map) 
    {
        readKeyboard = new Scanner(System.in);
        System.out.println("Choisissez vers o\u00f9 vous d\u00e9placer et de combien de cases:");
        Direction = readKeyboard.next();
        if(Direction.charAt(0) == '/')
        {
        	System.out.println("Commande");
        	if(Direction.equals("/aide"))
        	{
        		MainSystem.CleanConsole();
        		new IHelp(player);
        	}
        	else if(Direction.equals("/inventaire"))
        	{
        		MainSystem.CleanConsole();
        		Save.SaveGame(player, map);
        		new IStockage(player);
        		Save.SaveGame(player, map);
        	}
        	else if(Direction.equals("/parler"))
        	{
        		MainSystem.CleanConsole();
        		MainSystem.SpeakNpc(player, map);
        		Save.SaveGame(player, map);
        	}
        	else if(Direction.equals("/attaquer"))
        	{
        		MainSystem.CleanConsole();
        		MainSystem.AttackEntity(player, map);
        		Save.SaveGame(player, map);
        	}
        	else if(Direction.equals("/utiliser"))
        	{
        		String A, B, C;
        		System.out.println("Que souhaitez-vous utiliser ?");
        		A = readKeyboard.next();
        		if (INpc.TryIntoList(A, 0, player.getStock().getListStockage()))
        		{
		        	B = readKeyboard.next();
		        	C = A + " " + B;
		        	C = C.toLowerCase();
		        	if (INpc.TryIntoList(C, 1, player.getStock().getListStockage()))
		        	{
		        		MainSystem.CleanConsole();
			        	player.getStock().useItem(player, C);
			        	Save.SaveGame(player, map);
		        	}
	        		else
	        		{
		        		MainSystem.CleanConsole();
	        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
	        		}
        		}
        		else
        		{
	        		MainSystem.CleanConsole();
        			System.out.println("L'objet que vous souhaitez utiliser n'existe pas");
        		}
        	}
        	else if(Direction.equals("/ouvrir"))
        	{
        		MainSystem.CleanConsole();
        		MainSystem.OpenChest(player, map);
        		for(int i = 0; i < map.getListChest().size(); i++)
        		{
        			if(map.getListChest().get(i).getEmpty() == true)
        			{
        				map.setCase(map.getListChest().get(i).getXY("X"), map.getListChest().get(i).getXY("Y"), '\u00B7');
        				map.getListChest().remove(i);
        			}
        		}
        		Save.SaveGame(player, map);
        	}
        	else if(Direction.equals("/save"))
        	{
        		Save.SaveGame(player, map);
        		System.out.println("Votre partie a \u00e9t\u00e9 sauvegard\u00e9");
        	}
        	else if(Direction.equals("/quitter"))
        	{
        		Save.SaveGame(player, map);
        		System.out.println("Votre partie a \u00e9t\u00e9 sauvegard\u00e9");
        		MainSystem.setTerminated(true);
        	}
        	else
        	{
        		MainSystem.CleanConsole();
        		System.out.println("La commande que vous avez entr\u00e9 n'existe pas");
        	}
        }
        else if (Direction.equals("z") || Direction.equals("q") || Direction.equals("s") || Direction.equals("d"))
        {
	        try 
	        {
	            Mouvement = readKeyboard.nextInt();
	        	MainSystem.CleanConsole();
	        } 
	        catch (InputMismatchException e)
	        {
	        	MainSystem.CleanConsole();
	            Mouvement = -1;
	            readKeyboard.next();
	            System.out.println("La commande que vous avez entr\u00e9 n'existe pas");
	        }
        }
        else if (Direction.equals("zz") || Direction.equals("qq") || Direction.equals("ss") || Direction.equals("dd"))
        {
        	Direction = ""+Direction.charAt(0);
	        try 
	        {
	            Mouvement = 1;
	        	MainSystem.CleanConsole();
	        } 
	        catch (InputMismatchException e)
	        {
	        	MainSystem.CleanConsole();
	            Mouvement = -1;
	            readKeyboard.next();
	            System.out.println("La commande que vous avez entr\u00e9 n'existe pas");
	        }
        }        
        else
        {
        	MainSystem.CleanConsole();
        	System.out.println("La commande que vous avez entr\u00e9 n'existe pas");
        	
        	System.out.println("\nPour vous d\u00e9placer veuillez suivre cet exemple 'Z 1', \nseul les commandes Z, Q, S ou D sont utilisable un nombre de pas inf\u00e9rieur \u00e0 vos PM");
        	System.out.println("Pour plus d'information sur les commandes o\u00f9 le d\u00e9placement veuillez utiliser le /aide");
        }
    }
    
    public String getDirection() {
		return Direction;
	}

	public int getMouvement() {
		return Mouvement;
	}

    public static String getKeyboard(String R) {
        return R;
    }

    public static int getKeyboard(int Mouvement) {
        return Mouvement;
    }
}
