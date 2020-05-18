package com.poly.ui;

import java.util.*;

import com.poly.entity.IStrategyPlayer.*;
import com.poly.entity.*;

public class ICreation extends IDisplay {
	
	private static Scanner scan;
	private static String name;
	private static int numberclass;
	
	public ICreation() 
	{		
		CreationPlayer();
	}
	
	public static Player CreationPlayer() 
	{		
		scan = new Scanner(System.in);
		System.out.println("Cr\u00e9ation du personnage");
		System.out.println("\nEntrez votre pseudo :");
		name = scan.next();
		Spacer();
		System.out.println("Choisissez votre classe en entrant un des num\u00e9ros suivants :");
		System.out.println("0 : Assassin");
		System.out.println("1 : Archer");
		System.out.println("2 : Gladiateur");
		System.out.println("3 : Sorcier");
		Spacer();
		do {
			try 
			{
				numberclass = scan.nextInt();
			} 
			catch (InputMismatchException e) 
			{  
				numberclass = -1;
				scan.next();
			}
			if (numberclass < 0 || numberclass > 3) 
			{
				System.out.println("Cette classe n'existe pas ! Entrez un de ces num\u00e9ros [0,1,2,3].");
			}	
		} while(numberclass < 0 || numberclass > 3);
		
		switch(numberclass)
		{
			case 0 : return new Player(name, new Assassin());
			case 1 : return new Player(name, new Bowman());
			case 2 : return new Player(name, new Gladiator());
			case 3 : return new Player(name, new Wizard());
		}
		
		return null;
	}
}
