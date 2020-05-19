package com.poly.map;

import com.poly.entity.IStrategyNPC.*;
import com.poly.entity.*;

@SuppressWarnings("serial")
public class Marchande extends Map {

    public Marchande(Player p)
    {
        Make(p);
    }

    public String getMap()
    {
        return "Case Marchande";
    }

    void FillMap()
    {
        pnj = pnj*5; // c'est la propriete de la case marchande
        this.valeur+= coffre*3 +pnj*2 + mob;  // on change la valeur en fonction de la map
        while (coffre >0 || pnj>0 || mob>0 || Steward>0)
        { // tant qu'on a pas pose les differentes entite
            for (int j=0;j<this.y;j++)
            {
                for (int i=0;i<this.x;i++)
                {
                    if (pnj > 0 && matrice[i][j]=='\u00B7' && matrice[i-1][j]!='J' && matrice[i+1][j]!='J' && matrice[i][j-1]!='J' && matrice[i][j+1]!='J' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='P';
                        pnj--;
                    }

                    if (coffre > 0 && matrice[i][j]=='\u00B7' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='C';
                        coffre --;
                    }

                    if (mob > 0 && matrice[i][j]=='\u00B7' && matrice[i-1][j]!='J' && matrice[i+1][j]!='J' && matrice[i][j-1]!='J' && matrice[i][j+1]!='J' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='M';
                        mob --;
                    }
                    
                    if (Steward > 0 && matrice[i][j]=='\u00B7' && matrice[i-1][j]!='J' && matrice[i+1][j]!='J' && matrice[i][j-1]!='J' && matrice[i][j+1]!='J' && r.nextInt(100)==5)
                    {// est-ce que l'emplacement est disponible ?
                        matrice[i][j]='I';
                        posStewardX =i;
                        posStewardY=j;
                        Steward --;
                    }
                }
            }     
        }
    }
    
    public void ListNpc(Player player) 
    {
    	Npc npc = new Npc(new Steward());
    	 this.getListNpc().add(npc); // on ajoute d'abord l'intednant
    	 npc.setX(getSteward('x'));
    	 npc.setY(getSteward('y'));
    	
    	for (int j = 0; j < this.y; j++) 
    	{
            for (int i = 0; i < this.x; i++) 
            {
                if (matrice[i][j] == 'P') 
                { // on recupere les positions de tous les Pnj                	
                	Npc pnj = new Npc(new Trader());
                	pnj.setX(i);
               	 	pnj.setY(j);
               	 	this.getListNpc().add(pnj);
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
                	Monster m = mobType(player);
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