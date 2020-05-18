package com.poly.entity.IStrategyPlayer;

import com.poly.entity.*;

public class Gladiator implements IStrategyPlayer{
	
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
		summoner.setAtt(summoner.getAtt()+100);
		int A = Math.round((3+(summoner.getLevel()/2))*summoner.getAtt() / target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		summoner.setAtt(summoner.getAtt()-100);
		return 4;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		System.out.println(summoner.getName() + " utilise " + getSpellName4());
		if(summoner.getActualHp() <= (summoner.getMaxHp()/2)) {
			int A = Math.round(summoner.getMaxHp()*(float)30/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + (summoner.getMaxHp()*(50/100)) + " PV.");
		}
		else {
			int A = Math.round(summoner.getMaxHp()*(float)30/100);
			summoner.addHp(A);
			System.out.println(summoner.getName() + " a gagn\u00e9 " + (summoner.getMaxHp()*(10/100)) + " PV.");
		}
		return 6;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		int C = 0;
		int A = 0;
		System.out.println(summoner.getName() + " utilise " + getSpellName5());
		for(int i = 0; i < 5; i++)
		{
			do {
				A = (int) (Math.random()*15);
			} while(A < 10);
			int B = Math.round((A+(summoner.getLevel()/A))*summoner.getAtt()/target.getDef());
			target.removeHp(B);
			C += B;
			System.out.println(target.getName() + " a perdu " + B + " PV.");
		}
		System.out.println(target.getName() + " a perdu un total de " + C + " PV.");
		return 11;
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
		return "Tranchage";
	}

	@Override
	public String getSpellName4() {
		return "R\u00e9cup\u00e9ration";
	}

	@Override
	public String getSpellName5() {
		return "D\u00e9ferlante";
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
		return "Votre attaque est augment\u00e9 pour cette comp\u00e9tence et vous lui infligez une attaque";
	}

	@Override
	public String getSpellDescription4() {
		return "Si vous avez moins de 50% de vos PV vous r\u00e9cup\u00e9r\u00e9 30% de vos PV max sinon vous n'en r\u00e9cup\u00e9rer que 10%";
	}

	@Override
	public String getSpellDescription5() {
		return "Inflige cinq attaque d'intensit\u00e9 viarante \u00e0 l'adversaire";
	}
}
