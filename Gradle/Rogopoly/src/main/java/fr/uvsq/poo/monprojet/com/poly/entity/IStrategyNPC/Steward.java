package com.poly.entity.IStrategyNPC;

import com.poly.entity.*;

public class Steward implements IStrategyNPC{
	
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
		int A = Math.round((8+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 5;
	}

	@Override
	public int spell3(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 3
		System.out.println(summoner.getName() + " utilise " + getSpellName1());
		int A = Math.round((5+(summoner.getLevel()/2))*summoner.getAtt()/ Math.round(target.getDef()*(float)65/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 5;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		System.out.println(summoner.getName() + " utilise " + getSpellName4());
		if(summoner.getActualHp() <= (summoner.getMaxHp()/2)) {
			int A = Math.round(summoner.getMaxHp()*(float)25/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + A + " PV.");
		}
		else {
			int A = Math.round(summoner.getMaxHp()*(float)10/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + A + " PV.");
		}
		return 7;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		System.out.println(summoner.getName() + " utilise " + getSpellName5());
		int C = 0;
		for (int i = 0; i < (int) (Math.random()*8 + 2); i++)
		{
			int B = (int) (Math.random()*5 + 5);
			int A = Math.round((B+(summoner.getLevel()/Math.round(B/2)))*summoner.getAtt()/target.getDef());
			System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
			target.removeHp(A);
			C += A;
		}
		System.out.println(target.getName() + " a perdu un total de " + C + " PV.");
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
		return "Gain de vitalit\u00e9";
	}

	@Override
	public String getSpellName5() {
		return "Massacre";
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
		return "Vous redonne 25% de vos PV totaux si vous \u00eates en dessous des 50% sinon vous en r\u00e9cup\u00e9rez 10%";
	}

	@Override
	public String getSpellDescription5() {
		return "Vous inflige al\u00e9atoire entre 2 et 10 coups d'intensit\u00e9 variante";
	}
}
