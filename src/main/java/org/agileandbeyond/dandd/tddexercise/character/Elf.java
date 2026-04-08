package org.agileandbeyond.dandd.tddexercise.character;

public class Elf extends Race {
	public Elf() {
		super();
		
		this.setRaceType(RaceType.ELF);
		this.setDexterityModifier(1);
		this.setConstitutionModifier(-1);
		this.setCriticalRangeBonus(1);
	}
	
	@Override
	public int getArmorClassBonus(Character combatant) {
		int racialBonusArmorClassModifier = 0;
		
		if (combatant.getRace().getRaceType() == RaceType.ORC) {
			racialBonusArmorClassModifier = 2;
		}
		
		return racialBonusArmorClassModifier;
	}
}
