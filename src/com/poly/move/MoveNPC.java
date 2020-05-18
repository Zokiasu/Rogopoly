package com.poly.move;

import com.poly.entity.*;
import com.poly.map.Map;
import java.util.*;

public class MoveNPC {

    Random r = new Random();

    protected int direction = 0; 
    protected int moveCharac = 0;
    protected int comp = 0;
    protected String Direction = "";
    Move move = new Move();
    
    public Boolean tryWall(Map map, Entity entity, String string)
    {
    	int A = 0, B = 0;
    	
    	if (string.equals("z"))
    	{
    		A = entity.getXY("X") ;
    		B = entity.getXY("Y")-1;
       		if(map.getCase(A, B) != '\u00B7')
       		{
       			return false;
       		}
    	}
    	else if (string.equals("s"))
    	{
    		A = entity.getXY("X");
    		B = entity.getXY("Y")+1;
       		if(map.getCase(A, B) != '\u00B7')
       		{
       			return false;
       		}
    	}
    	else if (string.equals("q"))
    	{
    		A = entity.getXY("X")-1;
    		B = entity.getXY("Y");
       		if(map.getCase(A, B) != '\u00B7')
       		{
       			return false;
       		}
    	}
    	else if (string.equals("d"))
    	{
    		A = entity.getXY("X")+1;
    		B = entity.getXY("Y");
      		if(map.getCase(A, B) != '\u00B7')
       		{
       			return false;
       		}        	
    	}
    	return true;
    }

    public void movemob(ArrayList<Monster> Monster, Map mapi) 
    {
        for( int i = 0; i < Monster.size(); i++) 
        {
        	moveCharac =  1 + r.nextInt(Monster.get(i).getMaxMp()-1);
        	comp = 0;
        	Boolean Z = false, Q = false, S = false, D = false;
            do {
	            direction = r.nextInt(4);
	            switch(direction)
	            {
	            	case 0 :
	            		Direction = "z";
	            		Z = true;
	            		break;
	            	case 1 :
	            		Direction = "s";
	            		S = true;
	            		break;
	            	case 2 :
	            		Direction = "q";
	            		Q = true;
	            		break;
	            	case 3 :
	            		Direction = "d";
	            		D = true;
	            		break;
	            }
	            if(Z == true && Q == true && S == true && D == true) comp = 4;
            } while(tryWall(mapi, Monster.get(i), Direction) == false && comp != 4);
            if(comp < 4)
            {
            	move.move(Monster.get(i), Direction, moveCharac, Monster.get(i).getXY("X"), Monster.get(i).getXY("Y"), mapi);
            	Monster.get(i).addMp(Monster.get(i).getMaxMp());
            }
        }
    }

    public void movenpc(ArrayList<Npc> Npc, Map mapi) 
    {
        for( int i = 1; i < Npc.size(); i++) 
        {
        	moveCharac =  1 + r.nextInt(Npc.get(i).getMaxMp());   
        	comp = 0;         
        	Boolean Z = false, Q = false, S = false, D = false;
            do {
	            direction = r.nextInt(4);
	            switch(direction)
	            {
	            	case 0 :
	            		Direction = "z";
	            		Z = true;
	            		break;
	            	case 1 :
	            		Direction = "s";
	            		S = true;
	            		break;
	            	case 2 :
	            		Direction = "q";
	            		Q = true;
	            		break;
	            	case 3 :
	            		Direction = "d";
	            		D = true;
	            		break;
	            }
	            if(Z == true && Q == true && S == true && D == true) comp = 4;
            } while(tryWall(mapi, Npc.get(i), Direction) == false && comp != 4);
            if(comp < 4)
            {
            	move.move(Npc.get(i), Direction, moveCharac, Npc.get(i).getXY("X"), Npc.get(i).getXY("Y"), mapi);
            	Npc.get(i).addMp(Npc.get(i).getMaxMp());
            }
        }
    }
}
;