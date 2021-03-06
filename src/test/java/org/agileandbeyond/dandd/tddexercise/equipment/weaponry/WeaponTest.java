package org.agileandbeyond.dandd.tddexercise.equipment.weaponry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.agileandbeyond.dandd.tddexercise.character.Character;
import org.agileandbeyond.dandd.tddexercise.equipment.weaponry.Weapon;
import org.agileandbeyond.dandd.tddexercise.exception.CannotUseWeaponException;
import org.junit.Before;
import org.junit.Test;

public class WeaponTest {
	
	private Weapon weapon;
	
	@Before
	public void setup() {
		
	}

	@Test
	public void shouldCreateAWeaponWithAName() {
		weapon = new Dagger();
		
		assertEquals("Dagger", weapon.getName());
	}
	
	@Test
	public void shouldSetTheAttackModifier() {
		weapon = new Dagger();
		
		assertEquals(1, weapon.getAttackModifier());
	}
	
	@Test
	public void shouldSetTheDamageModifier() {
		weapon = new Dagger();
		
		assertEquals(1, weapon.getDamageModifier());
	}
	
	@Test
	public void shouldHitArmorClassOfTwoWhenUsingDagger() throws Exception {
		
		Character blorhoff = new Character();
		blorhoff.wieldWeapon(new Dagger());
		Character monster = new Character();
		monster.setArmorClass(2);
		
		assertTrue(blorhoff.attack(monster, 1));
		
	}
	
	@Test
	public void shouldNotHitArmorClassOfThreeWhenUsingDagger() throws CannotUseWeaponException {
		Character blorhoff = new Character();
		blorhoff.wieldWeapon(new Dagger());
		Character monster = new Character();
		monster.setArmorClass(3);
		
		assertFalse(blorhoff.attack(monster, 1));
		
	}
	
	@Test
	public void shouldIncreaseDamageByOneWhenUsingDagger() throws CannotUseWeaponException {
		Character blorhoff = new Character();
		blorhoff.wieldWeapon(new Dagger());
		Character monster = new Character();
		monster.setArmorClass(2);
		
		blorhoff.attack(monster, 1);
		
		assertEquals(3, monster.getHitPoints());
	}
	
	@Test
	public void shouldIncreaseDamageByTwoWhenUsingDaggerAndCriticalHit() throws Exception {
		Character blorhoff = new Character();
		blorhoff.wieldWeapon(new Dagger());
		Character monster = new Character();
		monster.setArmorClass(2);
		
		blorhoff.attack(monster, Character.CRITICAL_HIT);
		
		assertEquals(1, monster.getHitPoints());
	}
	
	@Test
	public void shouldIncreaseDamageByTenWhenUsingSwordAndCriticalHit() throws Exception {
		Character blorhoff = new Character();
		blorhoff.wieldWeapon(new Sword());
		Character monster = new Character();
		monster.setArmorClass(2);
		monster.setHitPoints(10);
		
		blorhoff.attack(monster, Character.CRITICAL_HIT);
		
		assertTrue(monster.isDead());
	}
	
	@Test
	public void shouldHoldADaggerWithOneHand() {
		Weapon dagger = new Dagger();
		assertFalse(dagger.isTwoHanded());
	}
	
	@Test
	public void shouldHoldALongSwordWithTwoHands() {
		Weapon sword = createLongSword();
		assertTrue(sword.isTwoHanded());
	}
	
	
	private Weapon createLongSword() {
		Weapon weapon = new Weapon();
		
		weapon.setName("Long Sword");
		weapon.setAttackModifier(5);
		weapon.setDamageModifier(5);
		weapon.setTwoHanded(true);
		
		return weapon;
	}
}
