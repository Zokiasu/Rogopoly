package com.poly.ui;

import java.util.*;
import com.poly.gestion.MainSystem;

public class IHome extends IDisplay {
	
	private static Scanner scan;
	private static String KeyboardRead;
	public String [] WelcomeMessage = new String [16];

	public IHome() 
	{		
		home();
	}
		
	public void home()
	{
		WelcomeMessage[1] = "\n      #################################################################";
		WelcomeMessage[2] = "\n      #                                                               #";
		WelcomeMessage[3] = "\n      #    #####  ###### ###### ###### ###### ###### #     #     #    #";
		WelcomeMessage[4] = "\n      #    #   #  #    # #      #    # #    # #    # #      #   #     #";
		WelcomeMessage[5] = "\n      #    #####  #    # # #### #    # ###### #    # #       # #      #";
		WelcomeMessage[6] = "\n      #    #  #   #    # #    # #    # #      #    # #        #       #";
		WelcomeMessage[7] = "\n      #    #   #  ###### ###### ###### #      ###### ######  #        #";
		WelcomeMessage[8] = "\n      #                                                               #";
		WelcomeMessage[9] = "\n      #################################################################\n";
		WelcomeMessage[11] = "\n                         Bienvenue sur Rogopoly                     \n";
		WelcomeMessage[13] = "\n           [Nouvelle Partie]                   [Charger Partie]     \n";
		WelcomeMessage[15] = "\nEntrez l'action souhaiter :\n";
		
		for(int i = 0; i < WelcomeMessage.length; i++)
		{
			if (i == 0 || i == 10 || i == 12 || i == 14)
			{
				Spacer();
			}
			else if (i == 2 || i == 8)
			{
				System.out.print(WelcomeMessage[i]);
			}
			else
			{
				System.out.print(WelcomeMessage[i]);
			}

		}
	}
	
	public static void MessageWelcome()
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("\n                      [Guide pour d\u00e9butant]                    ");
		Spacer();
		System.out.println("La carte est repr\u00e9sent\u00e9e par plusieurs symboles :"
				+ "\n\n- Tout d'abord, les murs, ils sont repr\u00e9sent\u00e9s par les symboles '#',"
				+ "\nle J repr\u00e9sente le joueur, le S repr\u00e9sente la sortie de la carte, "
				+ "\nles M repr\u00e9sentent les monstres, les C sont des coffres scell\u00e9s, "
				+ "\nle I repr\u00e9sente l'intendant et les P sont les marchands. "
				
				+ "\nSur la carte prison vous croiserez le symbole G, celui-ci repr\u00e9sente "
				+ "\nle gardien de prison. Le symbole '\u00B7' repr\u00e9sente les endroits "
				+ "\no\u00f9 vous pouvez librement vous d\u00e9placez. "
				
				+ "\n\nConcernant les d\u00e9placements, ceux-ci s'ex\u00e9cutent de la mani\u00e8re suivante :"
				+ "\n- Dans un premier temps vous choisissez la direction avec Z(Haut), "
				+ "\nQ(Gauche), S(Bas) ou D(Droite) puis vous \u00e9crivez le nombre de pas"
				+ "\nque vous souhaitez faire ceux-ci sont limit\u00e9s par vos points de mouvement."
				+ "\n\nPour consulter les objets dont vous disposez vous avez la commande /inventaire,"
				+ "\npour quitter le jeu et en faire une sauvegarde vous avez la commande /quitter."
				+ "\n\nPour plus d'information veuillez utiliser la commande /aide pendant le jeu.");
		Spacer();
		@SuppressWarnings("resource")
		Scanner waitForKeypress = new Scanner(System.in);
		System.out.println("\nAppuyez sur la touche Entr\u00e9e pour continuer");
		waitForKeypress.nextLine();		
	}
	
	public static Boolean getClavier() 
	{
		scan = new Scanner(System.in);
		setKeyboardRead("");
		setKeyboardRead(scan.nextLine());
		setKeyboardRead(getKeyboardRead().toLowerCase());

        if(getKeyboardRead().equals("nouvelle partie") || getKeyboardRead().equals("nouvelle") || getKeyboardRead().equals("news")) 
        {
        	return true;
        }
        else if(getKeyboardRead().equals("charger partie") || getKeyboardRead().equals("charge")) 
        {
        	return false;
        }
        else
        {
        	System.out.println("Vous avez entr\u00e9 une action incorrect.");
        	return getClavier();
        }
	}
	
	public static String getKeyboardRead() {
		return KeyboardRead;
	}

	public static void setKeyboardRead(String keyboardRead) {
		KeyboardRead = keyboardRead;
	}
}
