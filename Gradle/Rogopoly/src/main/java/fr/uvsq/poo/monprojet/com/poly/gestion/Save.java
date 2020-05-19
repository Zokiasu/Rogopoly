package com.poly.gestion;

import java.io.*;
import com.poly.entity.Player;
import com.poly.map.Map;

public class Save {
		
	public static void SaveGame(Player player, Map map)
	{
		try {
			FileOutputStream fileout = new FileOutputStream("map.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(map);
			fileout.close();
			FileOutputStream fileout1 = new FileOutputStream("player.ser");
			ObjectOutputStream out1 = new ObjectOutputStream(fileout1);
			out1.writeObject(player);
			fileout1.close();
		} catch(IOException i) { i.printStackTrace(); }
	}
	
	public static Player RecoverPlayer(Player player) {
		File f = new File("player.ser");
		if (!f.exists()) {
			System.out.println("Fichier non-existant");
			return null;
		} else {
			try {
				FileInputStream filein = new FileInputStream("player.ser");
				ObjectInputStream in = new ObjectInputStream(filein);
				player = (Player) in.readObject();
				in.close();
				filein.close();
				//Si le joueur correspond \u00e0 la classe du test lui attribut ses comp\u00e9tences
				player.AddSkill(player);
			} catch (IOException i) {
				i.printStackTrace();
				return null;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return null;
			}
			return player;
		}
	}
	
	public static Map RecoverMap (Map map) {
		File f = new File("map.ser");
		if (!f.exists()) {
			System.out.println("Fichier non-existant");
			return null;
		} else {
			try {
				FileInputStream filein = new FileInputStream("map.ser");
				ObjectInputStream in = new ObjectInputStream(filein);
				map = (Map) in.readObject();
				in.close();
				filein.close();
				for (int i = 0; i < map.getListMob().size(); i++) {
					map.getListMob().get(i).AddSkill(map.getListMob().get(i));
				}
				for (int i = 0; i < map.getListNpc().size(); i++) {
					map.getListNpc().get(i).AddSkill(map.getListNpc().get(i));
				}
			} catch (IOException i) {
				i.printStackTrace();
				return null;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return null;
			}
			return map;
		}
	}
}
