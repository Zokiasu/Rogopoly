package com.poly.map;

import com.poly.entity.IStrategyNPC.*;
import com.poly.entity.*;
import com.poly.entity.IStrategyMonster.PrisonSlayer;

@SuppressWarnings("serial")
public class Prison extends Map {

    public Prison(Player p)
    {
        Make(p);
    }

    public String getMap()
    {
        return "Case Prison";
    }
    
    void FillMap()
    {
        pnj = 1; // c'est la propriete de la case prison, le pnj demande de l'argent pour sortir OU donner une clef qu'on au obtenu avant
        mob = 1;
        this.valeur=0;  // on change la valeur en fonction de la map
        while (pnj>0 || mob>0)
        { // tant qu'on a pas pose les differentes entite
            for (int j=0;j<this.y;j++)
            {
                for (int i=0;i<this.x;i++)
                {
                    if (pnj > 0 && matrice[i][j]=='\u00B7' && matrice[i-1][j]!='J' && matrice[i+1][j]!='J' && matrice[i][j-1]!='J' && matrice[i][j+1]!='J' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='G';
                        pnj--;
                    }
                    
                    if (mob > 0 && matrice[i][j]=='S' && matrice[i-1][j]!='J' && matrice[i+1][j]!='J' && matrice[i][j-1]!='J' && matrice[i][j+1]!='J' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='M';
                        mob --;
                    }
                }
            }
        }
    }
    
    public void ListNpc(Player player) 
    {    	
    	for (int j = 0; j < this.y; j++) 
    	{
            for (int i = 0; i < this.x; i++) 
            {
                if (matrice[i][j] == 'G') 
                { // on recupere la position du pnj prison                	
                	Npc pnj = new Npc(new Guardian());
                	pnj.setX(i);
               	 	pnj.setY(j);
               	 	this.getListNpc().add(pnj);
               	 	break;
                }
            }
        }

        if(player.getLevel() > 1)
        {
        	for(int i = 0; i < this.getListNpc().size(); i++)
			{
        		while(this.getListNpc().get(i).getLevel() < player.getLevel())
        		{
        			this.getListNpc().get(i).levelUp();
        		}
			}
        }
    }
    
    public void ListMob(Player player) 
    {
        for (int j = 0; j < this.y; j++) 
        {
            for (int i = 0; i < this.x; i++) 
            {
                if (matrice[i][j] == 'M') 
                { // on recupere les positions de tous les mob                	
                	Monster m = new Monster(new PrisonSlayer());
                	m.setX(i);m.setY(j);
                    this.getListMob().add(m);              
                }
            }
        }

        if(player.getLevel() > 1)
        {
        	for(int i = 0; i < this.getListMob().size(); i++)
			{
        		while(this.getListMob().get(i).getLevel() < player.getLevel())
        		{
        			this.getListMob().get(i).levelUp();
        		}
			}
        }
    }
    
    
}