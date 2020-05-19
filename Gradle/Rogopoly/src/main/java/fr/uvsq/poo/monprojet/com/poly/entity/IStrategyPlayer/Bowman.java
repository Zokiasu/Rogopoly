package com.poly.entity.IStrategyPlayer;

import com.poly.entity.*;

public class Bowman implements IStrategyPlayer{
	
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
		summoner.setAtt(summoner.getAtt()+50);
		int A = Math.round( (5 + (summoner.getLevel()/2) )*summoner.getAtt()/Math.round(target.getDef()*(float)80/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		summoner.setAtt(summoner.getAtt()-50);
		return 4;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		System.out.println(summoner.getName() + " utilise " + getSpellName4());
		int A = Math.round((5+(summoner.getLevel()/2))*summoner.getAtt()/ Math.round(target.getDef()*(float)50/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 6;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		int B = 0;
		for(int i = 0; i < 10; i++)
		{
			int A = Math.round((2+(summoner.getLevel()/3))*summoner.getAtt()/target.getDef());
			B += A;
		}
		System.out.println(target.getName() + " a perdu un total de " + B + " PV.");
		target.removeHp(B);
		return 16;
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
		return "Fl\u00e8che puissante";
	}

	@Override
	public String getSpellName4() {
		return "Fl\u00e8che saignante";
	}

	@Override
	public String getSpellName5() {
		return "Pluie de fl\u00e8che";
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
		return "Lors de l'utilisation de cette comp\u00e9tence celle-ci consid\u00e8re des points d'attaques suppl\u00e9mentaire pour son effet.";
	}

	@Override
	public String getSpellDescription4() {
		return "Vous infligez d'important d\u00e9g\u00e2t \u00e0 la cible";
	}

	@Override
	public String getSpellDescription5() {
		return "Vous attaquer avec 10 fl\u00e8ches qui infligent plus ou moins de d\u00e9g\u00e2t";
	}
}
