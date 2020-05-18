package com.poly.entity.IStrategyMonster;

import com.poly.chest.Chest;
import com.poly.entity.*;
import com.poly.map.Map;

public class Normal implements IStrategyMonster{
	
	@Override
	public int spell1(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 1
		System.out.println(summoner.getName() + " utilise " + getSpellName1());
		int A = Math.round((2+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 0;
	}

	@Override
	public int spell2(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 2
		System.out.println(summoner.getName() + " utilise " + getSpellName2());
		int A = Math.round((3+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 5;
	}

	@Override
	public int spell3(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 3
		System.out.println(summoner.getName() + " utilise " + getSpellName3());
		int A = Math.round((5+(summoner.getLevel()/2))*summoner.getAtt()/ Math.round(target.getDef()*(float)65/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 5;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		System.out.println(summoner.getName() + " utilise " + getSpellName1());
		int A = Math.round((2+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 10;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		System.out.println(summoner.getName() + " utilise " + getSpellName1());
		int A = Math.round((2+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 10;
	}

	@Override
	public String getSpellName1() {
		return "Coup de m\u00eal\u00e9e";
	}

	@Override
	public String getSpellName2() {
		return "Coup ardent";
	}

	@Override
	public String getSpellName3() {
		return "Coupure Sanglante";
	}

	@Override
	public String getSpellName4() {
		return "";
	}

	@Override
	public String getSpellName5() {
		return "";
	}

	@Override
	public String getSpellDescription1() {
		return "Inflige une attaque basique \u00e0 l'adversaire";
	}

	@Override
	public String getSpellDescription2() {
		return "Inflige d'important d\u00e9g\u00e2t avec une probabilit\u00e9 de doubl\u00e9 ses d\u00e9g\u00e0ts";
	}

	@Override
	public String getSpellDescription3() {
		return "Inflige des d\u00e9g\u00e0ts ignorant une partie de la d\u00e9fense de l'ennemi";
	}

	@Override
	public String getSpellDescription4() {
		return "";
	}

	@Override
	public String getSpellDescription5() {
		return "";
	}

	@Override
	public void death(Map M, int x, int y, Player player) {
		M.setCase(x,y,'C');
		for(int i = 0; i <M.getListMob().size();i++)
		{
			if(M.getListMob().get(i).getXY("X") == x && M.getListMob().get(i).getXY("Y") == y)
			{
				M.getListMob().remove(i);
			}
		}
		Chest coffre = new Chest('O', player);
        coffre.setPosX(x);
        coffre.setPosY(y);
		M.getListChest().add(coffre);
	}
}
