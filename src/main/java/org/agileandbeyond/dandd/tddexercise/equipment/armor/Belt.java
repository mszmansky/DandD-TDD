package org.agileandbeyond.dandd.tddexercise.equipment.armor;

import org.agileandbeyond.dandd.tddexercise.equipment.WearablePosition;

public class Belt extends Armor {
    
    public Belt() {
        this.setPosition(WearablePosition.WAIST);
        this.setArmorClassModifier(1);
    }
}
