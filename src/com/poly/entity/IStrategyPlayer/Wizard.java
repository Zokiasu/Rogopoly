package com.poly.entity.IStrategyPlayer;

import com.poly.entity.*;

public class Wizard implements IStrategyPlayer{
	
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
		if (summoner instanceof Player)
			((Player)summoner).setEvasion(true);
		return 0;
	}

	@Override
	public int spell3(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 3
		System.out.println(summoner.getName() + " utilise " + getSpellName3());
		int A = Math.round((3+(summoner.getLevel()/3))*summoner.getAtt()/ Math.round(target.getDef()*(float)30/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 6;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		int C = 0;
		System.out.println(summoner.getName() + " utilise " + getSpellName4());
		int A =  Math.round((3+(summoner.getLevel()/3))*summoner.getAtt()/ Math.round(target.getDef()*(float)50/100));
		for(int i = 0; i < 2; i++)
		{
			target.removeHp(A);
			System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
			C += A;
		}
		System.out.println(summoner.getName() + " a inflig\u00e9 un total de " + C + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		return 11;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		System.out.println(summoner.getName() + " utilise " + getSpellName5());
		if(summoner.getActualHp() <= (summoner.getMaxHp()/10)) {
			int A = Math.round(summoner.getMaxHp()*(float)50/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + A + " PV.");
		}
		else {
			int A = Math.round(summoner.getMaxHp()*(float)10/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + A + " PV.");
		}
		return 21;
	}

	@Override
	public String getSpellName1() {
		return "Coup de m\u00eal\u00e9e";
	}

	@Override
	public String getSpellName2() {
		return "Fuite";
	}

	@Override
	public String getSpellName3() {
		return "Pique";
	}

	@Override
	public String getSpellName4() {
		return "Avalanche";
	}

	@Override
	public String getSpellName5() {
		return "R\u00e9g\u00e9n\u00e9ration";
	}

	@Override
	public String getSpellDescription1() {
		return "Inflige une attaque basique \u00e0 l'adversaire";
	}

	@Override
	public String getSpellDescription2() {
		return "Vous mettez fin au combat et vous fuyez dans une direction al\u00e9atoire";
	}

	@Override
	public String getSpellDescription3() {
		return "Vous ignorez 30% de la d\u00e9fense de l'adversaire et vous l'attaquer";
	}

	@Override
	public String getSpellDescription4() {
		return "Inflige trois attaques r\u00e9p\u00e9t\u00e9s ignorant 50% de la d\u00e9fense de l'adversaire";
	}

	@Override
	public String getSpellDescription5() {
		return "Si vous avez moins de 10% de vos points de vie maximum vous r\u00e9cup\u00e9rez 50% de vos points de vie. Sinon vous r\u00e9cup\u00e9rez 10%";
	}
}
