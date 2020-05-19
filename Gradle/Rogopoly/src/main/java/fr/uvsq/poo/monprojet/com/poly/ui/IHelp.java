package com.poly.ui;

import java.util.*;
import com.poly.entity.*;
import com.poly.entity.IStrategyMonster.*;
import com.poly.entity.IStrategyNPC.*;
import com.poly.gestion.MainSystem;

public class IHelp extends IDisplay {

	private Scanner scan;

	public IHelp(Player player) 
	{
		HomeHelp(player);
	}
	
	public void HomeHelp(Player player)
	{
		MainSystem.CleanConsole();

		Spacer();
		System.out.println("Bienvenue dans l'interface d'aide de Rogopoly"
				+ "\n\nSur quel point souhaiteriez avoir des informations ?"
				+ "\n - Les informations concernant vos comp\u00e9tences [Commande : comp\u00e9tence]"
				+ "\n - Les diff\u00e9rents monstres et les diff\u00e9rents pnj [Commande : entit\u00e9]"
				+ "\n - Les diff\u00e9rentes commandes \u00e0 votre disposition [Commande : commande]"
				+ "\n - Sur le syst\u00e8me du jeu (D\u00e9placement, type de map, ... [Commande : syst\u00e8me]"
				+ "\nPour acc\u00e9der aux diff\u00e9rentes cat\u00e9gories veuillez utiliser les commandes appropri\u00e9es."
				+ "\n\nNoter cependant que les diff\u00e9rentes pages d'aide sont accessible quelque soit l'endroit "
				+ "\no\u00f9 vous vous trouvez dans l'interface d'aide."
				+ "\n\nPour fermer la fen\u00eatre entrez 'quitter'.");
		Spacer();
		getClavier(player);
	}
	
	public void ClassPlayerHelp(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Votre classe est " + player.getNameClasse() + " et vos comp\u00e9tences sont :");
		System.out.println("- " + player.getClasse().getSpellName1() + " : " + player.getClasse().getSpellDescription1());
		System.out.println("- " + player.getClasse().getSpellName2() + " : " + player.getClasse().getSpellDescription2());
		System.out.println("- " + player.getClasse().getSpellName3() + " : " + player.getClasse().getSpellDescription3());
		System.out.println("- " + player.getClasse().getSpellName4() + " : " + player.getClasse().getSpellDescription4());
		System.out.println("- " + player.getClasse().getSpellName5() + " : " + player.getClasse().getSpellDescription5());
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	public void CommandsHelp(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Les commandes dont vous disposez sont :"
				+ "\n- '/aide' : Qui vous ouvre l'interface d'aide, qui regroupe toute les informations concernant le fonctionnement du jeu."
				+ "\n- '/inventaire' : Cette commande ouvre l'interface de votre inventaire ainsi que la liste des \u00e9quipements que vous avez d'actuellement \u00e9quip\u00e9."
				+ "\n- '/utiliser' : Cette commande vous permet d'utiliser un objet se trouvant dans votre inventaire sans l'ouvrir"
				+ "\n- '/parler' : Vous permet de parler \u00e0 un pnj \u00e0 proximit\u00e9 de vous."
				+ "\n- '/attaquer' : Vous permet d'attaquer un pnj \u00e0 proximit\u00e9 de vous."
				+ "\n- '/ouvrir' : Vous permet d'ouvrir un coffre \u00e0 proximit\u00e9 de vous."
				+ "\n- '/save' : Vous permet de sauvegarder votre partie actuel."
				+ "\n- '/quitter' : Vous permet de sauvegarder et de terminer votre partie.");
		System.out.println("Pour revenir \u00e0 l'interface d'aide entrez 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	public void SystemHelp(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Sur quel partie du jeu souhaitez-vous plus d'information ?\n"
				+ "- Les d\u00e9placements [Commande : d\u00e9placement]\n"
				+ "- Les types de maps [Commande : map]\n"
				+ "- Les combats [Commande : combat]\n"
				+ "- L'inventaire [Commande : inventaire]\n"
				+ "- Les \u00e9l\u00e9ments autre que le joueur (Coffres, pnj ...)[Commande : autre]\n");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	public void SystemDeplacement(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("D\u00e9placement\n");
		
		System.out.println("Pour vous d\u00e9placer sur la map, utilisez les commandes suivantes du clavier suivit du nombre de pas voulu (s\u00e9par\u00e9 d'un espace) :");
		System.out.println("z : Haut");
		System.out.println("d : Bas");
		System.out.println("q : Gauche");
		System.out.println("d : Droite");
		System.out.println("Exemple pour se d\u00e9placer de 3 pas vers le haut : z 3\n");
		
		System.out.println("Si vous n'avez pas le nombre n\u00e9c\u00e9ssaire de PM disponible le d\u00e9placement sera impossible");
		System.out.println("Si rencontrez un mur votre d\u00e9placement sera stopp\u00e9");
		System.out.println("Pour atteindre la sortie 'S' il ne suffit pas de la traverser, il faut se positionner dessus\n");
		
		System.out.println("Pour revenir \u00e0 l'interface syst\u00e8me entrez 'syst\u00e8me'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	public void SystemMap(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Map\n");
		
		System.out.println("Il existe 4 types de map avec chacune des caract\u00e9ristiques diff\u00e9rentes que voici :\n");
		
		System.out.println("Map Monstre");
		System.out.println("C'est celle sur laquelle vous tomberez le plus souvent, on y trouve principalement des monstres");
		System.out.println("Elle a le moins de valeur\n");
		
		System.out.println("Map Marchande");
		System.out.println("Deux\u00e8me map la plus courante, on y trouve principalement des PNJ marchands");
		System.out.println("Elle a une valeure moyenne\n");
		
		System.out.println("Map Chance");
		System.out.println("Une map assez are, on y trouve principalement des coffres ferm\u00e9s");
		System.out.println("Elle a plus de valeur que les autres\n");
		
		System.out.println("Map Prison");
		System.out.println("C'est la map la plus rare\non y trouve seulement un monstre particuli\u00e8rement puissant");
		System.out.println("et un PNJ gardien de prison");
		System.out.println("Pour en sortir 3 possibilit\u00e9s : Utiliser une clef de prison / Vaincre le monstre / Vaincre le Gardien");
		System.out.println("Elle n'a pas de valeur, on ne peut pas l'acheter\n");
		
		System.out.println("Pour revenir \u00e0 l'interface syst\u00e8me entrez 'syst\u00e8me'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	public void SystemCombat(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Combat\n");
		
		System.out.println("Vous pouvez utiliser une de vos 4 comp\u00e9tences en \u00e9crivant le num\u00e9ro correspondant");
		System.out.println("Il vous est aussi possible de fuir tout combat en \u00e9crivant 2\n");
				
		System.out.println("Pour plus d'information sur vos comp\u00e9tences [Commande : comp\u00e9tence]\n");		
		
		System.out.println("Pour revenir \u00e0 l'interface syst\u00e8me entrez 'syst\u00e8me'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	public void SystemInventaire(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Inventaire\n");
		
		System.out.println("Pour ouvrir votre inventaire pendant le jeu entrez '/inventaire'");
		System.out.println("Il est s\u00e9par\u00e9 en deux parties, les utilisables (clef, potions, \u00e9quipements non \u00e9quip\u00e9s) et les \u00e9quipements \u00e9quip\u00e9s");
		System.out.println("Vous pourrez alors en savoir plus sur les objets que vous poss\u00e9dez en \u00e9crivant '/info [objet]' \n");		
		
		System.out.println("Pour revenir \u00e0 l'interface syst\u00e8me entrez 'syst\u00e8me'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	public void SystemAutre(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Autre\n");
		
		System.out.println("PNJ");
		System.out.println("Il y a trois types de pnj qui se distringuent sur la map");
		System.out.println("les plus courants sont les marchands 'P', ils peuvent vous vendre ou vous acheter des items");
		System.out.println("vous trouverez aussi un intendant 'I' par map (sauf prison) qui pourra vous vendre celle-ci");
		System.out.println("et enfin les gardiens de prison 'G' vous permettent de sortir si vous avez la clef");
		System.out.println("Pour plus d'information sur les pnj[Commande : pnj\n");
		
		System.out.println("Monstres");
		System.out.println("Le niveau des monstres varie et \u00e9volue au court de votre aventure selon votre niveau");
		System.out.println("et le nombre de map visit\u00e9");
		System.out.println("si vous arrivez \u00e0 vaincre le monstre de la prison vous pourrez en sortir sans clef");
		System.out.println("Pour plus d'information sur les monstres[Commande : monstre\n");
		
		System.out.println("Coffres");
		System.out.println("Il vous faudra des clefs pour ouvir les coffres");
		System.out.println("sauf ceux qui apparaissent apr\u00e8s avoir vaincu un monstre ou un PNJ\n");
		
		System.out.println("L'argent");
		System.out.println("Les moyens basiques pour se faire un peu d'argent sont");
		System.out.println("- Vaincre des monstre ou des PNJ");
		System.out.println("- Vendre les objets obtenus dans les coffres aux PNJ");
		System.out.println("Si vous voulez gagner beaucoup il va falloir investire et acheter une map aupr\u00e8s de l'intendant");
		System.out.println("A chaque fois que vous passerez de niveau vous obtiendrez la somme total des maps achet\u00e9es au cours de votre partie\n");		
		
		System.out.println("Pour revenir \u00e0 l'interface syst\u00e8me entrez 'syst\u00e8me'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide faite 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	public void PnjMobHelp(Player player)
	{
		MainSystem.CleanConsole();
		Spacer();
		System.out.println("Les diff\u00e9rents types de PNJ: \n");
		Npc npc = new Npc(new Steward());
		System.out.println("-" + npc.getName() +" ("+ npc.getNameClass()+") "+ npc.getStats());
		npc = new Npc(new Guardian());	
		System.out.println("-" + npc.getName() +" ("+ npc.getNameClass()+") "+ npc.getStats());
		npc = new Npc(new Trader());	
		System.out.println("-" + npc.getName() +" ("+ npc.getNameClass()+") "+ npc.getStats() + "\n");
		System.out.println("Les diff\u00e9rents types de Monstres: \n");
		Monster mob = new Monster(new Inferior());
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		mob = new Monster(new Normal());
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		mob = new Monster(new Superior());
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		mob = new Monster(new Ultimate());
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		mob = new Monster(new PrisonSlayer());
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		System.out.println("\nEntrez le nom de l'entit\u00e9 ou sa classe pour plus d'information.");
		System.out.println("\nPour revenir \u00e0 l'interface d'aide entrez 'aide' sinon entrez 'quitter'.");
		Spacer();
		getClavier(player);
	}
	
	public void SpellNpc(Npc npc, Player player) 
	{
		MainSystem.CleanConsole();
		System.out.println("" + npc.getName() +" ("+ npc.getNameClass()+") "+ npc.getStats() + "\n");
		System.out.println("- " + npc.getClasse().getSpellName1() + " : " + npc.getClasse().getSpellDescription1());
		System.out.println("- " + npc.getClasse().getSpellName2() + " : " + npc.getClasse().getSpellDescription2());
		System.out.println("- " + npc.getClasse().getSpellName3() + " : " + npc.getClasse().getSpellDescription3());
		System.out.println("- " + npc.getClasse().getSpellName4() + " : " + npc.getClasse().getSpellDescription4());
		System.out.println("- " + npc.getClasse().getSpellName5() + " : " + npc.getClasse().getSpellDescription5() + "\n");
		System.out.println("Pour revenir \u00e0� l'interface monstre et pnj entrez 'monstre'");
		System.out.println("Pour revenir \u00e0� l'interface d'aide entrez 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	public void SpellMob(Monster mob, Player player) 
	{
		MainSystem.CleanConsole();
		System.out.println("-" + mob.getName() +" : "+ mob.getStats());
		System.out.println("- " + mob.getClasse().getSpellName1() + " : " + mob.getClasse().getSpellDescription1());
		System.out.println("- " + mob.getClasse().getSpellName2() + " : " + mob.getClasse().getSpellDescription2());
		System.out.println("- " + mob.getClasse().getSpellName3() + " : " + mob.getClasse().getSpellDescription3());
		System.out.println("- " + mob.getClasse().getSpellName4() + " : " + mob.getClasse().getSpellDescription4());
		System.out.println("- " + mob.getClasse().getSpellName5() + " : " + mob.getClasse().getSpellDescription5() + "\n");
		System.out.println("Pour revenir \u00e0 l'interface monstre et pnj entrez 'monstre'");
		System.out.println("Pour revenir \u00e0 l'interface d'aide entrez 'aide' sinon entrez 'quitter'");
		Spacer();
		getClavier(player);
	}
	
	
	
	public void getClavier(Player player) 
	{	
		String command;
		scan = new Scanner(System.in);
		command = scan.nextLine();
		command = command.toLowerCase();
		if ("aide".equals(command) || "help".equals(command))
		{
			HomeHelp(player);
		}
		else if ("comp\u00e9tences".equals(command) || "comp\u00e9tence".equals(command) || "skill".equals(command))
		{
			 ClassPlayerHelp(player);
		}
		else if ("commande".equals(command) || "commandes".equals(command) || "command".equals(command) || "commands".equals(command))
		{
			CommandsHelp(player);
		}
		else if ("monstre".equals(command) || "pnj".equals(command) || "monstres".equals(command) || "entity".equals(command) || "entit\u00e9".equals(command) || "pnjs".equals(command) || "mob".equals(command) || "mobs".equals(command))
		{
			PnjMobHelp(player);
		}
		else if("intendant".equals(command) ||  "Ben".equals(command)) 
		{
			SpellNpc(new Npc(new Steward()), player);
		}
		else if("gardien".equals(command) ||  "Alfred".equals(command)) 
		{
			SpellNpc(new Npc(new Guardian()), player);
		}
		else if("marchand".equals(command) ||  "Lustig".equals(command)) 
		{
			SpellNpc(new Npc(new Trader()), player);
		}		
		else if("f55b".equals(command)) 
		{
			SpellMob(new Monster(new Inferior()), player);
		}		
		else if("e98g".equals(command)) 
		{
			SpellMob(new Monster(new Normal()), player);
		}		
		else if("d911".equals(command)) 
		{
			SpellMob(new Monster(new Superior()), player);
		}
		else if("a51".equals(command))
		{
			SpellMob(new Monster(new Ultimate()), player);
		}
		else if("c52b".equals(command)) 
		{
			SpellMob(new Monster(new PrisonSlayer()), player);	
		}
		else if("syst\u00e8me".equals(command) || "system".equals(command) || "systeme".equals(command)) 
		{
			SystemHelp(player);
		}
		else if("d\u00e9placement".equals(command) || "deplacement".equals(command)) 
		{
			SystemDeplacement(player);
		}		
		else if("combat".equals(command)) 
		{
			SystemCombat(player);
		}
		else if("inventaire".equals(command))
		{
			SystemInventaire(player);
		}
		else if("autre".equals(command)) 
		{
			SystemAutre(player);
		}
		else if("map".equals(command)) 
		{
			SystemMap(player);			
		}		
		else
		{
			System.out.println("D\u00e9sol\u00e9 je n'ai pas compris votre demande.");
			getClavier(player);
		}
	}
}
