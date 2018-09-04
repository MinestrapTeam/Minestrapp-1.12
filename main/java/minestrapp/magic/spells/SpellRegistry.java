package minestrapp.magic.spells;

import java.util.ArrayList;

public class SpellRegistry {
	
	public static ArrayList<SpellBase> spellsList = new ArrayList<SpellBase>();
	
	public static void init() {
		registerSpell(new SpellTest("test"));
		registerSpell(new SpellTest2("test2"));
	}
	
	public static void registerSpell(SpellBase spellbase) {
		spellsList.add(spellbase);
	}
	
	public static SpellBase getSpellByName(String name) {
		for(SpellBase spell: spellsList) {
			if(spell.name == name) {
				return spell;
			}
		}
		return null;
	}
	
	

}
