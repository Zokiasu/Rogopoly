package com.poly.gestion;

import com.poly.entity.*;
import com.poly.fightsystem.*;
import com.poly.item.*;
import com.poly.map.*;
import com.poly.move.*;
import com.poly.ui.*;
import java.io.*;

public class MainSystem {
	
	private static Boolean terminated = false;
	private Boolean NewGame;
	private Boolean FirstRun = true;

	public MainSystem ()
	{ 
		GameSystem();
	}
	
	public void GameSystem() //Gestion g�n�rale du jeu
	{
		Map map = null;
		Player player = null;
		
		new IHome();
		setNewGame(IHome.getClavier());
		
		while(terminated == false) //Tant que le joueur n'a pas sp�cifi� vouloir termin� le jeu le jeu continue
		{
			if(NewGame == true)
			{
				player = ICreation.CreationPlayer(); //Cr�ation du joueur
				ItemStart(player);//Donne les objets de d�part au joueur
				if(FirstRun == true)
				{
					IHome.MessageWelcome();
					FirstRun = false;
				}
			}
			else if (NewGame == false) {
				do {
					if(Save.RecoverPlayer(player) != null) {
						player = Save.RecoverPlayer(player);
					}else {
						GameSystem();
					}
				}while(player == null);
			}
			Move move = new Move();
			MoveNPC movenpc = new MoveNPC();
			Keyboard Keyboard = new Keyboard(); 

			while(player.getActualHp() != 0 && terminated == false) //Tant que le joueur n'est pas mort le jeu continue
			{
				if (NewGame == true)
				{
					map = GestionMap.RandMap(player);
					player.setX(map.getJoueur('x'));
					player.setY(map.getJoueur('y'));
					CleanConsole();//Nettoie la console
				}
				else if (NewGame == false)
				{
					map = Save.RecoverMap(map);
					CleanConsole();//Nettoie la console
				}
				Save.SaveGame(player, map);
				player.addMp(player.getMaxMp()); //Si le joueur change de carte ses points de mouvement sont r�actualis�
				NewGame = true;
				//Tant que le joueur n'a pas rejoint la porte de sortie sa partie continue sur la carte actuel
				while((player.getXY("X") != map.getExit('x') || player.getXY("Y")  != map.getExit('y')) && terminated == false && player.getActualHp() != 0) 
				{
					if (player.getEvasion())//V�rifie si le joueur � fuit un combat si c'est le cas d�place le joueur al�atoirement
					{
						Fight.aleaSkip(player, map, move);
						player.setEvasion(false);
					}
					new IGeneral(map, player);//Affiche la fen�tre g�n�ral avec la map et les stats du joueur
					tryNpc(map, player);//V�rifie si il y a un npc autour du joueur
					tryChest(map, player);//V�rifie si il y a un coffre autour du joueur
					Keyboard.inputPlayer(player, map);//R�cup�re ce que le joueur souhaite faire
					if (Keyboard.getDirection().equals("z") || Keyboard.getDirection().equals("q") || Keyboard.getDirection().equals("s") || Keyboard.getDirection().equals("d"))
						move.move(player, Keyboard.getDirection(), Keyboard.getMouvement(), player.getXY("X"), player.getXY("Y"), map); //d�place le joueur si il le souhaite
					tryMonster(map, player);    //V�rifie si il y a un monstre autour du joueur et le monstre l'aggresse si c'est le cas

					if (player.getActualMp() == 0)//Si les points de mouvement du joueur sont � 0
					{
						player.addMp(player.getMaxMp()); //R�initialise les points de mouvement du joueur
						movenpc.movemob(map.getListMob(), map); //D�place les monstres sur la carte
						movenpc.movenpc(map.getListNpc(), map); //D�place les pnj qui peuvent se d�placer sur la map
						player.reload(); //r�duit les cd du joueur de 1 si il en a
					}
					Save.SaveGame(player, map);
				}
			}

			if(player.getActualHp() == 0)//Si le joueur � 0 PV affiche le message de mort 
			{ 
				CleanConsole();
				System.out.println("Vous \u00eates mort votre aventure se termine ici !");
				try {
					File file = new File("player.ser");
					file.delete();
					File file1 = new File("map.ser");
					file1.delete();
				} catch(Exception i) {
					i.printStackTrace();
				}
				System.out.println("\nVous recommencez donc une nouvelle partie !");
			}
		}
	}
	
	public static boolean testLinux()
	{
		if(System.getProperty("os.name").equals("Linux"))
			return true;

		return false;	
	}

	public void ItemStart(Player Player) //Objet avec lequel le joueur commence le jeu
	{
		Item potion = new Potion(1);
		Item potion1 = new Potion(2);
		Item potion2 = new Potion(3);
		Item clefp = new Key(1);
		Item clefc = new Key(2);
		for(int i = 0; i < 5; i++)
		{
			Player.getStock().AddStock(potion);
			Player.getStock().AddStock(potion1);
			Player.getStock().AddStock(potion2);
		}
		Player.getStock().AddStock(clefp);
		Player.getStock().AddStock(clefc);
	}
	
	public void tryMonster(Map mapi, Entity Play) //Test si il y a un monstre � c�t� du joueur
    {
		int RecoverPosX = -1; //R�cup�re la position X � test�
		int RecoverPosY = -1; //R�cup�re la position Y � test�
		Boolean CheckEntity = true; //Si il n'y a aucun adversaire renvoie faux
		
		if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'M') //V�rifie si il y a un monstre � droite du joueur
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'M') //V�rifie si il y a un monstre � gauche du joueur 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'M') //V�rifie si il y a un monstre devant du joueur
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'M') //V�rifie si il y a un monstre derri�re du joueur
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}
		else //Si il n'y aucun monstre � c�t� du joueur initialise CheckEntity � faux pour �viter de chercher le monstre inutilement
		{
			CheckEntity = false;
		}

		if(CheckEntity == true) //Si return vrai c'est qu'il a un monstre � c�t� du joueur
		{
			for(int i = 0; i < mapi.getListMob().size() && (RecoverPosX != -1 && RecoverPosY != -1); i++) 
			{
				//Recherche de quel mob il s'agit et fait en sorte que le monstre agresse le joueur
				if((RecoverPosX == mapi.getListMob().get(i).getXY("X")) && RecoverPosY == mapi.getListMob().get(i).getXY("Y"))
				{
					new Fight(Play, mapi.getListMob().get(i), mapi);//Lance le combat entre le joueur et le monstre
					RecoverPosX = -1;
					RecoverPosY = -1;
				}
			}
		}
    }
	
	public void tryNpc(Map mapi, Player Play) //Test si un PNJ est proche du joueur
    {
		String I = "Souhaitez-vous parler \u00e0 l'intendant ?";
		String M = "Souhaitez-vous parler au pnj ?";
		String G = "Souhaitez-vous parler au gardien ?";
		String O = "\nSi oui veuillez utiliser la commande /parler ou /attaquer pour l'agresser."
				+ "\n\nAttention les intendants et les gardiens de prison sont des redoutables adversaires ";
		
		if(mapi.getCase(Play.getXY("X")+1,Play.getXY("Y")) == 'I') //V�rifie si l'intendant est � droite du joueur
		{
			System.out.println(I + O);
		}
		else if(mapi.getCase(Play.getXY("X")-1,Play.getXY("Y")) == 'I') //V�rifie si l'intendant est � gauche du joueur
		{
			System.out.println(I + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")+1) == 'I') //V�rifie si l'intendant est en haut du joueur
		{ 
			System.out.println(I + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")-1) == 'I') //V�rifie si l'intendant est en bas du joueur
		{ 
			System.out.println(I + O);
		}
		else if(mapi.getCase(Play.getXY("X")+1,Play.getXY("Y")) == 'G') //V�rifie si le gardien est � droite du joueur
		{
			System.out.println(G + O);
		}
		else if(mapi.getCase(Play.getXY("X")-1,Play.getXY("Y")) == 'G') //V�rifie si le gardien est � gauche du joueur
		{
			System.out.println(G + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")+1) == 'G') //V�rifie si le gardien est en haut du joueur
		{ 
			System.out.println(G + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")-1) == 'G') //V�rifie si le gardien est en bas du joueur
		{
			System.out.println(G + O);
		}
		else if(mapi.getCase(Play.getXY("X")+1,Play.getXY("Y")) == 'P') //V�rifie si un pnj est � droite du joueur
		{
			System.out.println(M + O);
		}
		else if(mapi.getCase(Play.getXY("X")-1,Play.getXY("Y")) == 'P') //V�rifie si un pnj est � gauche du joueur
		{
			System.out.println(M + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")+1) == 'P') //V�rifie si un pnj est en haut du joueur
		{ 
			System.out.println(M + O);
		}
		else if(mapi.getCase(Play.getXY("X"),Play.getXY("Y")-1) == 'P') //V�rifie si un pnj est en bas du joueur
		{
			System.out.println(M + O);
		}
    }
	
	public void tryChest(Map mapi, Player Play) //Test si il y a un coffre � c�t� du joueur
	{
		if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'C')
		{
			System.out.println("Souhaitez-vous ouvrir le coffre ?\nSi oui utiliser la commande /ouvrir.");
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'C') 
		{
			System.out.println("Souhaitez-vous ouvrir le coffre ?\nSi oui utiliser la commande /ouvrir.");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'C') 
		{
			System.out.println("Souhaitez-vous ouvrir le coffre ?\nSi oui utiliser la commande /ouvrir.");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'C') 
		{ 
			System.out.println("Souhaitez-vous ouvrir le coffre ?\nSi oui utiliser la commande /ouvrir.");
		}
	}
	
	public static void OpenChest(Player Play, Map mapi)	//V�rifie si il y a un coffre proche du joueur
	{
		int RecoverPosX = -1; //R�cup�re la position X � test�
		int RecoverPosY = -1; //R�cup�re la position Y � test�
		Boolean CheckEntity = true; //Si il n'y a aucun adversaire renvoie faux		

		if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'C') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'C') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'C') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'C') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}
		else
		{
			System.out.println("Il n'y a aucun coffre proche de vous !");
			CheckEntity = false;
		}
		
		//Si un coffre est trouv� recherche � quel coffre les positions correspondent
		if(CheckEntity == true)	
		{
			for(int i = 0; i < mapi.getListChest().size() && (RecoverPosX != -1 && RecoverPosY != -1); i++) 
			{
				if((RecoverPosX == mapi.getListChest().get(i).getXY("X")) && RecoverPosY == mapi.getListChest().get(i).getXY("Y"))
				{
					mapi.getListChest().get(i).CheckChest(Play, mapi.getListChest().get(i));
					RecoverPosX = -1;
					RecoverPosY = -1;
				}
			}
		}
	}
	
	public static void SpeakNpc(Player Play, Map mapi) //V�rifie si il y a un pnj proche du joueur pour lui parler
	{
		int RecoverPosX = -1; //R�cup�re la position X � test�
		int RecoverPosY = -1; //R�cup�re la position Y � test�
		Boolean CheckEntity = true; //Si il n'y a aucun adversaire renvoie faux
		
		if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'I') 
		{
			new INpc(Play, mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'I') 
		{
			new INpc(Play,mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'I') 
		{ 
			new INpc(Play,mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'I') 
		{ 
			new INpc(Play,mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'G') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'G') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'G') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'G') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}
		else if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'P') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X") - 1, Play.getXY("Y")) == 'P') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") + 1) == 'P') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y") - 1) == 'P') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}
		
		
		else
		{
			System.out.println("Il n'y a aucun pnj proche de vous !");
			CheckEntity = false;
		}

		if(CheckEntity == true)
		{
			for(int i = 0; i < mapi.getListNpc().size() && (RecoverPosX != -1 && RecoverPosY != -1); i++) 
			{
				if((RecoverPosX == mapi.getListNpc().get(i).getXY("X")) && RecoverPosY == mapi.getListNpc().get(i).getXY("Y"))
				{
					new INpc(Play,mapi.getListNpc().get(i), mapi);
					RecoverPosX = -1;
					RecoverPosY = -1;
				}
			}
		}
	}
	
	public static void AttackEntity(Player Play, Map mapi) //V�rifie si il y a une entit� proche du joueur pour l'attaquer
	{
		int RecoverPosX = -1; //R�cup�re la position X � test�
		int RecoverPosY = -1; //R�cup�re la position Y � test�
		Boolean CheckEntity = true; //Si il n'y a aucun adversaire renvoie faux
		
		if(mapi.getCase(Play.getXY("X") + 1, Play.getXY("Y")) == 'I') 
		{
			new Fight(Play, mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X")-1, Play.getXY("Y")) == 'I') 
		{
			new Fight(Play, mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")+1) == 'I') 
		{ 
			new Fight(Play, mapi.getListNpc().get(0), mapi);
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")-1) == 'I') 
		{ 
			new Fight(Play, mapi.getListNpc().get(0), mapi);
		}		
		else if(mapi.getCase(Play.getXY("X")+1, Play.getXY("Y")) == 'P') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X")-1, Play.getXY("Y")) == 'P') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")+1) == 'P') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")-1) == 'P') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}		
		else if(mapi.getCase(Play.getXY("X")+1, Play.getXY("Y")) == 'G') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X")-1, Play.getXY("Y")) == 'G') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")+1) == 'G') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")-1) == 'G') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}		
		else if(mapi.getCase(Play.getXY("X")+1, Play.getXY("Y")) == 'M') 
		{
			RecoverPosX = Play.getXY("X") + 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X")-1, Play.getXY("Y")) == 'M') 
		{
			RecoverPosX = Play.getXY("X") - 1;
			RecoverPosY = Play.getXY("Y");
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")+1) == 'M') 
		{
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") + 1;
		}
		else if(mapi.getCase(Play.getXY("X"), Play.getXY("Y")-1) == 'M') 
		{ 
			RecoverPosX = Play.getXY("X");
			RecoverPosY = Play.getXY("Y") - 1;
		}		
		else
		{
			System.out.println("Il n'y a aucun adversaire proche de vous !");
			CheckEntity = false;
		}

		if(CheckEntity == true)
		{
			for(int i = 0; i < mapi.getListNpc().size(); i++) 
			{
				if (RecoverPosX == -1 && RecoverPosY == -1)
				{
					break;
				}
				else if((RecoverPosX == mapi.getListNpc().get(i).getXY("X")) && RecoverPosY == mapi.getListNpc().get(i).getXY("Y"))
				{
					new Fight(Play, mapi.getListNpc().get(i), mapi);
					RecoverPosX = -1;
					RecoverPosY = -1;
				}
			}
			
			for(int i = 0; i < mapi.getListMob().size() && (RecoverPosX != -1 && RecoverPosY != -1); i++) 
			{
				if((RecoverPosX == mapi.getListMob().get(i).getXY("X")) && RecoverPosY == mapi.getListMob().get(i).getXY("Y"))
				{
					new Fight(Play, mapi.getListMob().get(i), mapi);
					RecoverPosX = -1;
					RecoverPosY = -1;
				}
			}
		}
	}
	
	public void SlowWrite(String message, long millisPerChar) //Ecris les messages lettre par lettre
	{
		for(int i = 0; i < message.length(); i++)
		{
			System.out.print(message.charAt(i));
			try {Thread.sleep(millisPerChar);}
			catch(InterruptedException e) {e.printStackTrace();}
		}
	}

	public static void CleanConsole() //Nettoie la console
	{
		
		if(testLinux()) {
			System.out.println("\033[H\033[2J");

			}
		
		else {
			for(int i = 0; i < 100; i++)
			{
				System.out.println("\n");
			}
			
			
		}
		
	}

	public Boolean getNewGame() {
		return NewGame;
	}

	public static Boolean getTerminated() {
		return terminated;
	}

	public void setNewGame(Boolean newGame) {
		NewGame = newGame;
	}

	public static void setTerminated(Boolean termin) {
		terminated = termin;
	}
}