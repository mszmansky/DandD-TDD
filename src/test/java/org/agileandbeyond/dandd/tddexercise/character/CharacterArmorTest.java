package org.agileandbeyond.dandd.tddexercise.character;

import static org.junit.Assert.*;

import org.agileandbeyond.dandd.tddexercise.character.Character;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Armor;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Boots;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Gauntlets;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Helmet;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.MailArmor;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Shield;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Belt;
import org.agileandbeyond.dandd.tddexercise.equipment.armor.Gloves;
import org.agileandbeyond.dandd.tddexercise.equipment.weaponry.BroadSword;
import org.agileandbeyond.dandd.tddexercise.equipment.weaponry.Dagger;
import org.agileandbeyond.dandd.tddexercise.equipment.weaponry.Weapon;
import org.agileandbeyond.dandd.tddexercise.exception.CannotUseShieldException;
import org.agileandbeyond.dandd.tddexercise.exception.CannotUseWeaponException;
import org.junit.Before;
import org.junit.Test;

public class CharacterArmorTest {
	
	private Character grog;

	@Before
	public void setup() {
		grog = new Character();
	}
	
	@Test
	public void shouldDonHelmetAndIncreaseArmorClassByOne() {
		int armorClassBefore = grog.getArmorClass();
		Armor helmet = createHelmet();
		grog.donArmor(helmet);
		
		assertEquals(1, Math.abs(armorClassBefore - grog.getArmorClass()));
	}

	@Test
	public void shouldDonBeltAndIncreaseArmorClassByOne(){
		int armorClassBefore = grog.getArmorClass();
		Armor belt = createBelt();
		grog.donArmor(belt);
		
		assertEquals(1, Math.abs(armorClassBefore - grog.getArmorClass()));
	}
	
	@Test
	public void shouldDonBeltAndBeABeltWearer() {
		grog.donArmor(createBelt());
		
		assertTrue(grog.isWearingBelt());
	}

	@Test
	public void shouldNotHaveBeltOutOfTheBox() {
		assertFalse(grog.isWearingBelt());
	}

	@Test
	public void shouldDonHelmetAndBeAHelmetWearer() {
		grog.donArmor(createHelmet());
		
		assertTrue(grog.isWearingHelmet());
	}
	
	@Test
	public void shouldNotHaveAHelmetOutOfTheBox() {
		assertFalse(grog.isWearingHelmet());
	}
	
	@Test
	public void shouldDonChainMailAndBeALittleMoreProtected() {
		grog.donArmor(createChainMail());
		
		assertTrue(grog.isWearingMail());
	}
	
	@Test
	public void shouldNotHaveChainMailOutOfTheBox() {
		assertFalse(grog.isWearingMail());
	}
	
	@Test
	public void shouldDonGauntletsAndHaveSweetArmoredWrists(){
		grog.donArmor(createGauntlets());
		
		assertTrue(grog.isWearingGauntlets());
	}
	
	@Test
	public void shouldNotHaveGauntletsOutOfTheBox(){
		assertFalse(grog.isWearingGauntlets());
	}

	@Test
	public void shouldNotHaveGlovesOutOfTheBox() {
		assertFalse(grog.isWearingGloves());
	}

	@Test
	public void shouldDonGlovesAndHaveProtectedDigits() {
		grog.donArmor(createGloves());

		assertTrue(grog.isWearingGloves());
	}

	@Test
	public void shouldDonGlovesAndIncreaseArmorClassByOne() {
		int armorClassBefore = grog.getArmorClass();
		grog.donArmor(createGloves());
		
		assertEquals(1, Math.abs(armorClassBefore - grog.getArmorClass()));
	}
	
	@Test
	public void shouldDonBootsAndHaveProtectedTreads(){
		grog.donArmor(createBoots());
		
		assertTrue(grog.isWearingBoots());
	}
	
	@Test
	public void shouldNotHaveBootsOutOfTheBox(){
		assertFalse(grog.isWearingBoots());
	}
	
	@Test
	public void shouldUseShieldAndIncreaseArmorClassByFour() throws Exception {
		int armorClassBefore = grog.getArmorClass();
		grog.useShield(createShield());
		
		assertEquals(4, Math.abs(armorClassBefore - grog.getArmorClass()));
	}
	
	@Test
	public void shouldUseShieldAndBlockDeadlyBlows() throws Exception {
		grog.useShield(createShield());
		
		assertTrue(grog.isUsingShield());
	}
	
	@Test
	public void shouldNotHaveShieldOutOfTheBox(){
		assertFalse(grog.isUsingShield());
	}
	
	@Test(expected = CannotUseShieldException.class)
	public void shouldNotBeAbleToUseShieldWhenWieldingTwoHandedBroadSword() throws Exception{
		grog.wieldWeapon(createBroadSword());
		grog.useShield(createShield());
	}
	
	@Test(expected = CannotUseWeaponException.class)
	public void shouldNotBeAbleToWieldTwoHandedBroadSwordWhenUsingShield() throws Exception {
		grog.useShield(createShield());
		grog.wieldWeapon(createBroadSword());
	}
	
	@Test
	public void shouldBeAbleToWieldDaggerWhenUsingShield() throws Exception {
		grog.useShield(createShield());
		Weapon dagger = new Dagger();
		grog.wieldWeapon(dagger);
		
		assertSame(dagger, grog.getWeapon());
	}
	
	private Weapon createBroadSword(){
		Weapon broadSword = new BroadSword();
		return broadSword;
	}
	
	private Shield createShield(){
		Shield shield = new Shield();
		return shield;
	}
	
	private Armor createHelmet() {
		Helmet helmet = new Helmet();
		return helmet;
	}
	
	private Armor createChainMail() {
		MailArmor chainMail = new MailArmor();
		return chainMail;
	}
	
	private Armor createBoots() {
		Boots boots = new Boots();
		return boots;
	}
	
	private Armor createGauntlets() {
		Gauntlets gauntlets = new Gauntlets();
		return gauntlets;
	}

	private Armor createBelt() {
		Belt belt = new Belt();
		return belt;
	}

	private Armor createGloves() {
		Gloves gloves = new Gloves();
		return gloves;
	}
}
