package com.poly.entity.IStrategyMonster;

import com.poly.entity.*;
import com.poly.map.Map;

public interface IStrategyMonster{
	// L'entier renvoye correspond au CD du spell
	public int spell1(Entity summoner, Entity target);
	public int spell2(Entity summoner, Entity target);
	public int spell3(Entity summoner, Entity target);
	public int spell4(Entity summoner, Entity target);
	public int spell5(Entity summoner, Entity target);
	
	public String getSpellName1();
	public String getSpellName2();
	public String getSpellName3();
	public String getSpellName4();
	public String getSpellName5();

	public String getSpellDescription1();
	public String getSpellDescription2();
	public String getSpellDescription3();
	public String getSpellDescription4();
	public String getSpellDescription5();

	public void death(Map M, int x, int y, Player player);
}