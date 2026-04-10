package org.agileandbeyond.dandd.tddexercise.character;

import java.util.HashMap;
import java.util.Map;

import org.agileandbeyond.dandd.tddexercise.Purse;
import org.agileandbeyond.dandd.tddexercise.equipment.WearablePosition;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Armor;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Shield;
import org.agileandbeyond.dandd.tddexercise.equipment.weaponry.Weapon;
import org.agileandbeyond.dandd.tddexercise.exception.CannotUseShieldException;
import org.agileandbeyond.dandd.tddexercise.exception.CannotUseWeaponException;

public class Character {

	public static int CRITICAL_HIT = 20;

	private String name;
	private Alignment alignment;
	private int experiencePoints = 0;
	private int armorClass = 10;
	private int hitPoints = 5;
	private int level = 1;
	private int attackRollLevelBonus;
	private Map<AbilityType, Ability> abilities;
	private Weapon weapon;
	private ArmorSet armorSet;
	private Race race;
	private Purse purse;

	public Character() {
		abilities = new HashMap<AbilityType, Ability>();
		abilities.put(AbilityType.STRENGTH, new Ability(AbilityType.STRENGTH));
		abilities.put(AbilityType.DEXTERITY, new Ability(AbilityType.DEXTERITY));
		abilities.put(AbilityType.CHARISMA, new Ability(AbilityType.CHARISMA));
		abilities.put(AbilityType.CONSTITUTION, new Ability(AbilityType.CONSTITUTION));
		abilities.put(AbilityType.INTELLIGENCE, new Ability(AbilityType.INTELLIGENCE));
		abilities.put(AbilityType.WISDOM, new Ability(AbilityType.WISDOM));
		armorSet = new ArmorSet();
		setPurse(new Purse());
		
		race = new Human();
	}

	public void donArmor(Armor armor) {
		WearablePosition position = armor.getPosition();

		switch (position) {
		case HEAD:
			this.armorSet.setHelmet(armor);
			break;
		case TORSO:
			this.armorSet.setMail(armor);
			break;
		case WAIST:
			this.armorSet.setBelt(armor);
			break;
		case WRISTS:
			this.armorSet.setGauntlets(armor);
			break;
		case FEET:
			this.armorSet.setBoots(armor);
			break;
		case HANDS:
			this.armorSet.setGloves(armor);
			break;
		default:
			break;
		}
		this.armorClass += armor.getArmorClassModifier();
	}

	public void useShield(Shield shield) throws Exception{
		if (this.getWeapon() != null) {
			if (this.getWeapon().isTwoHanded()) {
				throw new CannotUseShieldException();
			}
		}
		this.armorSet.setShield(shield);
		this.armorClass += shield.getArmorClassModifier();
	}

	public boolean isUsingShield() {
		boolean usingShield = false;
		if (this.armorSet.shield != null) {
			usingShield = true;
		}
		return usingShield;
	}

	public boolean isWearingHelmet() {
		boolean wearingHelmet = false;
		if (this.armorSet.helmet != null) {
			wearingHelmet = true;
		}
		return wearingHelmet;
	}

	public boolean isWearingMail() {
		boolean wearingMail = false;
		if (this.armorSet.mail != null) {
			wearingMail = true;
		}
		return wearingMail;
	}

	public boolean isWearingGauntlets() {
		boolean wearingGauntlets = false;
		if (this.armorSet.gauntlets != null) {
			wearingGauntlets = true;
		}
		return wearingGauntlets;
	}

	public boolean isWearingBoots() {
		boolean wearingBoots = false;
		if (this.armorSet.boots != null) {
			wearingBoots = true;
		}
		return wearingBoots;
	}

	public boolean isWearingBelt() {
		boolean wearingBelt = false;
		if (this.armorSet.belt != null) {
			wearingBelt = true;
		}
		return wearingBelt;
	}

	public boolean isWearingGloves() {
		boolean wearingGloves = false;
		if (this.armorSet.gloves != null) {
			wearingGloves = true;
		}
		return wearingGloves;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		if (armorClass < 0) {
			armorClass = 0;
		}

		this.armorClass = armorClass;

	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		if (hitPoints < 0) {
			hitPoints = 0;
		}

		this.hitPoints = hitPoints;
	}

	public Ability getAbility(AbilityType abilityType) {
		return abilities.get(abilityType);
	}

	protected Map<AbilityType, Ability> getAbilities() {
		return abilities;
	}

	public void setStrength(int score) {
		abilities.get(AbilityType.STRENGTH).setScore(score);
	}

	public void setDexterity(int score) {
		abilities.get(AbilityType.DEXTERITY).setScore(score);
	}

	public void setCharisma(int score) {
		abilities.get(AbilityType.CHARISMA).setScore(score);
	}

	public void setConstitution(int score) {
		abilities.get(AbilityType.CONSTITUTION).setScore(score);
	}

	public void setIntelligence(int score) {
		abilities.get(AbilityType.INTELLIGENCE).setScore(score);
	}

	public void setWisdom(int score) {
		abilities.get(AbilityType.WISDOM).setScore(score);
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public boolean isDead() {
		return hitPoints <= 0;
	}

	public boolean attack(Character combatant, int attackRoll) {
		boolean hit = false;
		
		//Since crits are only calculated based on the initial attack roll, 
		//attackRoll should remain constant
		int modifiedAttackRoll = attackRoll + race.getAttackModifier(combatant);

		if (weapon != null) {
			modifiedAttackRoll = modifiedAttackRoll + weapon.getAttackModifier();
		}

		if (modifiedAttackRoll >= combatantArmorClass(combatant)) {
			hit = true;
			increaseExperiencePoints(10);

			combatant.setHitPoints(combatant.getHitPoints()-calculateDamage(combatant, attackRoll));
		}

		return hit;
	}
	
	private int combatantArmorClass(Character combatant) {
		return combatant.getArmorClass() + combatant.getRace().getArmorClassBonus(this);
	}

	private int calculateDamage(Character combatant, int attackRoll) {
		int damage = 1 + race.getDamageModifier(combatant);

		if (weapon != null) {
			damage = damage + weapon.getDamageModifier();
		}

		if (attackRoll + this.getRace().getCriticalRangeBonus() >= CRITICAL_HIT) {
			damage = damage * 2;
		}

		return damage;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void wieldWeapon(Weapon weapon) throws CannotUseWeaponException {
		if (this.armorSet.shield != null) {
			if (weapon.isTwoHanded()) {
				throw new CannotUseWeaponException();
			}
		}
		this.weapon = weapon;
	}

	protected void increaseHitPoints(int increase) {
		hitPoints+=increase;
	}

	protected void increaseLevel(int increase) {
		level+=increase;

		int increaseHitPointsBy = increase*5 + getAbility(AbilityType.CONSTITUTION).getModifier();
		increaseHitPoints(increaseHitPointsBy);

		this.attackRollLevelBonus = (level/2);
	}

	protected void increaseExperiencePoints(int increase) {
		int currentLevel = this.level;

		experiencePoints+=increase;

		int newLevel = (experiencePoints/1000)+1;
		if (newLevel > currentLevel) {
			int increaseLevelBy = newLevel - level;
			increaseLevel(increaseLevelBy);
		}
	}

	public int getAttackRollLevelBonus() {
		return attackRollLevelBonus;
	}

	private class ArmorSet {
		private Armor mail;
		private Armor helmet;
		private Armor boots;
		private Armor gauntlets;
		private Armor belt;
		private Armor gloves;
		private Shield shield;

		public void setMail(Armor mail) {
			this.mail = mail;
		}
		public void setHelmet(Armor helmet) {
			this.helmet = helmet;
		}
		public void setBoots(Armor boots) {
			this.boots = boots;
		}
		public void setGauntlets(Armor gauntlets) {
			this.gauntlets = gauntlets;
		}
		public void setShield(Shield shield) {
			this.shield = shield;
		}
		public void setBelt(Armor belt) {
			this.belt = belt;
		}
		public void setGloves(Armor gloves){
			this.gloves = gloves;
		}
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Purse getPurse() {
		return purse;
	}

	public void setPurse(Purse purse) {
		this.purse = purse;
	}

}
