package org.agileandbeyond.dandd.tddexercise.equipment.armor;

import org.agileandbeyond.dandd.tddexercise.equipment.WearablePosition;

public class Gloves extends Armor{
    
    public Gloves() {
        this.setPosition(WearablePosition.HANDS);
        this.setArmorClassModifier(1);
    }
}
