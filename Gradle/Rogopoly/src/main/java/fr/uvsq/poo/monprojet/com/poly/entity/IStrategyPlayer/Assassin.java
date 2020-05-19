package com.poly.entity.IStrategyPlayer;

import com.poly.entity.*;

public class Assassin implements IStrategyPlayer{
	
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
		int A = Math.round((5+(summoner.getLevel()/2))*summoner.getAtt()/ Math.round(target.getDef()*(float)5/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		A = Math.round((5+(summoner.getLevel()/2))*summoner.getAtt()/ Math.round(target.getDef()*(float)5/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		return 6;
	}

	@Override
	public int spell4(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 4
		System.out.println(summoner.getName() + " utilise " + getSpellName4());	
		summoner.setAtt(summoner.getAtt()+50);
		int A = Math.round((4+(summoner.getLevel()/3))*summoner.getAtt()/ Math.round(target.getDef()*(float)50/100));
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		summoner.setAtt(summoner.getAtt()-50);
		return 21;
	}

	@Override
	public int spell5(Entity summoner, Entity target) { //Effet de la comp\u00e9tence 5
		System.out.println(summoner.getName() + " utilise " + getSpellName5());		
		summoner.setAtt(summoner.getAtt()+100);
		int A = Math.round((6+(summoner.getLevel()/2))*summoner.getAtt()/target.getDef());
		System.out.println(summoner.getName() + " a inflig\u00e9 " + A + " d\u00e9g\u00e2t(s) \u00e0 "+ target.getName());
		target.removeHp(A);
		summoner.setAtt(summoner.getAtt()-100);
		return 6;
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
		return "Attaque surprise";
	}

	@Override
	public String getSpellName4() {
		return "Lame Furieuse";
	}

	@Override
	public String getSpellName5() {
		return "Coupure ardente";
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
		return "Vous ignorez entièrement la d\u00e9fense de l'adversaire et vous l'attaquer \u00e0 deux reprises";
	}

	@Override
	public String getSpellDescription4() {
		return "Lorsque vous utilisez cette comp\u00e9tence vous ignorez 50% de la d\u00e9fense de l'adversaire";
	}

	@Override
	public String getSpellDescription5() {
		return "Lors de l'utilisation de cette comp\u00e9tence celle-ci consid\u00e8re 100 d'attaques suppl\u00e9mentaire pour son effet.";
	}
}
