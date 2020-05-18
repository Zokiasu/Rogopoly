package com.poly.map;

import java.util.Random;

import com.poly.entity.IStrategyMonster.*;
import com.poly.chest.Chest;
import com.poly.entity.*;
import com.poly.gestion.*;


import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class Map implements java.io.Serializable{


    Random r = new Random();
    protected int x = 10 + r.nextInt(50); // on initialise al\u00e9atoirement les dimensions de la matrice (entre 10*10 et 60*40)
	protected int y = 10 + r.nextInt(20);
    public char matrice[][] = new char [x][y];
    private int i, j; //entier utiliser pour les differentes boucles
    private int joueur = 1; // il y a un seul joueur a mettre
    private int sortie = 1; // il y a une seule sortie a mettre
    protected int valeur =(x*y)/2; // la valeur de la map initalise, elle sera augmentee selon le type de map
    // initialisation de tous les objets dans leur limitations basiques et leurs listes
    protected int coffre = ((x+y)/20);
    protected int pnj = ((x+y)/25);
    protected int mob = ((x+y)/20)*2;
    protected int Steward= 1;
    private ArrayList<Npc> listNpc = new ArrayList<>();
    private ArrayList<Monster> listMob = new ArrayList<>();
    private ArrayList<Chest> listChest = new ArrayList<>();
    // initialisation des positions des differents objets/personnages unique
    protected int positionJoueurX;
    protected int positionJoueurY;
    protected int positionSortieX;
    protected int positionSortieY;    
    protected int posStewardX;
    protected int posStewardY;
    //Creation des differentes fonction abstraite
    abstract void FillMap();
    abstract String getMap(); //Check le type de map
	abstract public void ListNpc(Player p); //Remplis la liste des npc des differentes map
	abstract public void ListMob(Player p);
    
    public int getPositionSortieX() {
		return positionSortieX;
	}
	public int getPositionSortieY() {
		return positionSortieY;
	}
	
	public void setPositionSortieX(int positionSortieX) {
		this.positionSortieX = positionSortieX;
	}
	public void setPositionSortieY(int positionSortieY) {
		this.positionSortieY = positionSortieY;
	}
	
	public void Make(Player p) 
    { //creation d'une map simple avec seulement les murs et le vide
        MakeContours();
        MakeMur();
        MakePlayerExit();
        FillMap();
        ListAll(p);
    }
    
    private void MakeContours()
    {
        for (j=0;j<this.y;j++)
        {
            for (i=0;i<this.x;i++)
            {
                if ((i==0 || i==this.x-1) || (j==0 || j==this.y-1))
                {
                    matrice[i][j] = '#'; // on fait d'abord les contours de la map
                }
            }
        }
    }

    private void MakeMur()
    {
        for (j=1;j<this.y-1;j++) 
        {
            for (i = 1; i < this.x - 1; i++) 
            {
                if (r.nextInt(9) == 1) 
                {
                    matrice[i][j] = (matrice[i - 1][j] != '#' && matrice[i - 1][j] != '\u00B7' && matrice[i - 2][j] != '\u00B7') || (matrice[i][j - 1] != '#' && matrice[i][j - 1] != '\u00B7' && matrice[i][j - 2] != '\u00B7') ? '\u00B7' : '#';
                } 
                else 
                {
                	matrice[i][j] = '\u00B7'; // le sol (ou vde)
                }
            }
        }
    }

    private void MakePlayerExit()
    {
        while (joueur ==1 || sortie ==1)
        { // tant qu'on a pas pose une sortie et un joueur
            for (j=0;j<this.y;j++)
            {
                for (i=0;i<this.x;i++)
                {
                    if (joueur >0 && matrice[i][j]=='\u00B7' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ? si oui il y a une chance sur 100 de mettre le joueur
                        matrice[i][j]='J';
                        joueur--;

                        positionJoueurX =i;
                        positionJoueurY=j;
                    }

                    if (sortie > 0 && matrice[i][j]=='\u00B7' && r.nextInt(100)==12)
                    {// est-ce que l'emplacement est disponible ? si oui il y a une chance sur 100 de mettre la sortie
                        matrice[i][j]='S';
                        sortie--;

                        positionSortieX=i;
                        positionSortieY=j;
                    }
                }
            }
        }
    }

    public void ShowMap()
    {// affiche la map
    	
        System.out.println("\t\t\t"+getMap() + " (Valeur : "+getValue()+")");

        for (j=0;j<this.y;j++)
        {
            for (i=0;i<this.x;i++) // on affiche chaque entités avec différentes couleurs (ça marche que sur linux)
            { 
            	if (matrice[i][j]=='J') { 
            		System.out.print(Color.getIntensity());
            		System.out.print(Color.getGreen());
            	}
            	else if (matrice[i][j]=='P') { 
            		System.out.print(Color.getIntensity());
            		System.out.print(Color.getBlue());
            	}
            	else if (matrice[i][j]=='I') { 
            		System.out.print(Color.getCyan());
            	}
            	else if (matrice[i][j]=='M') { 
            		System.out.print(Color.getRed());
            	}
            	else if (matrice[i][j]=='C') { 
            		System.out.print(Color.getYellow());
            	}
            	else if (matrice[i][j]=='G') { 
            		System.out.print(Color.getIntensity());
            		System.out.print(Color.getYellow());
            	}
            	else if (matrice[i][j]=='S') { 
            		
            		System.out.print(Color.getGreen());
            	}
            	
            	else if (matrice[i][j]=='#'){
            		
            		System.out.print(Color.getIntensity());
            	}
            	
                System.out.print(matrice[i][j]);
                System.out.print(Color.Reboot());
                
            }
            System.out.println();
        }
    }
    
    public int getValue() {
    	
    	
    	return this.valeur;
    }

    public void ListAll(Player p)
    {
        ListNpc(p);
        ListMob(p);
        ListCof(p);
    }

    public void ListCof(Player player) 
    {
        for (j = 0; j < this.y; j++) 
        {
            for (i = 0; i < this.x; i++) 
            {
                if (matrice[i][j] == 'C') 
                {
                    Chest coffre = new Chest('F', player);
                    coffre.setPosX(i);
                    coffre.setPosY(j);
                    this.getListChest().add(coffre);
                }
            }
        }
    }

    public char getCase(int posx, int posy)
    {
        return matrice[posx][posy];
    }

    public void setCase(int posx,int posy,char ch)
    { // change le contenu d'une position
        matrice[posx][posy] = ch;
    }

    public int getJoueur(char p)
    { // char p correspond a x ou y selon ce qu'on veut recuperer
        if (p=='x')
        {
            return positionJoueurX;
        }
        else if (p=='y')
        {
            return positionJoueurY;
        }
        else return -1;
    }
    
    public Player getPlayer(Player play, char pos)
    { // char p correspond a x ou y selon ce qu'on veut recuperer
        if (pos=='x')
        {
        	play.setX(positionJoueurX);
        }
        else if (pos=='y')
        {
            play.setY(positionJoueurY);
        }
        return play;
    }

    public void setEntity(Entity E, int px, int py, char c)
    { // nouvelle pos du joueur
        E.setX(px);
        E.setY(py);
        setCase(px,py,c);
    }

    public int getExit(char p)
    { // char p correspond a x ou y selon ce qu'on veut recuperer
        if (p=='x')
            return positionSortieX;
        else if (p=='y')
            return positionSortieY;
        else 
        	return -1;
    }
    
    public int getSteward(char p)
    { // char p correspond a x ou y selon ce qu'on veut recuperer
        if (p=='x')
            return posStewardX;
        else if (p=='y')
            return posStewardY;
        else 
        	return -1;
    }
    
    public Entity getEntity(int posx, int posy)
    { // donne le contenu d'une coordonee precise
            for (i = 0; i <getListMob().size() ; i++) 
            {
            	if (getListMob().get(i).getXY("X")== posx && getListMob().get(i).getXY("Y")==posy) 
            	{ // on parcours la liste pour voir si un monstre a cette position
            		return getListMob().get(i);
                }
            }            
            
            for (i = 0; i <getListNpc().size() ; i++) 
            {
            	if (getListNpc().get(i).getXY("X")== posx && getListNpc().get(i).getXY("Y")==posy) 
            	{ // on parcours la liste pour voir si un pnja cette position
            		return getListNpc().get(i);
                }
            }
            
            return new Monster(new Inferior());   
    }
	
    public int getXY(String A)
	{
    	A = A.toLowerCase();
		if (A.equals("x"))
		{
			return x; 
		}
		else if (A.equals("y"))
		{
			return y;
		}
		return 0;
	}
	
    public ArrayList<Monster> getListMob() {
		return listMob;
	}

	public ArrayList<Npc> getListNpc() {
		return listNpc;
	}
	
	public ArrayList<Chest> getListChest() {
		return listChest;
	}

	public void setListMob(ArrayList<Monster> listMob) {
		this.listMob = listMob;
	}
	
	public void setListNpc(ArrayList<Npc> listNpc) {
		this.listNpc = listNpc;
	}

	public void setListChest(ArrayList<Chest> listChest) {
		this.listChest = listChest;
	}
	
	public Monster mobType(Player p) { // quel monstre en fonction du niveau
		
		Random ran = new Random();
		int monst = ran.nextInt(100); 
		
		if (p.getLevel() < 5) { // on fait le poucentage de chance de chaque mob en fonction du lvl
			
			if (monst<95){ // 80% de monstre inférieur etc ..
				
				return new Monster(new Inferior());
			}
			
			else if (monst<99){ 
				
				return new Monster(new Normal());
			}
			else {
				
				return new Monster(new Superior());
			}
			
		}
		
		else if (p.getLevel() < 10) { // on fait le poucentage de chance de chaque mob en fonction du lvl
			
			if (monst<50){ 
				
				return new Monster(new Inferior());
			}
			
			else if (monst<90){ 
				
				return new Monster(new Normal());
			}
			else {
				
				return new Monster(new Superior());
			}
			
		}
		
		else { // on fait le poucentage de chance de chaque mob en fonction du lvl
			
			if (monst<15){ // .
				
				return new Monster(new Inferior());
			}
			
			else if (monst<60){ 
				
				return new Monster(new Normal());
			}
			else if (monst<95){ 
				
				return new Monster(new Superior());
			}
			
			else{ 
	
				return new Monster(new Ultimate());
			}
			
		}
	}
}