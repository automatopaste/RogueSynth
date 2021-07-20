package data.scripts.hullmods.variants.ultra;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarRogue extends RS_BaseVariantHullmod {
    private static final float RECOIL_PER_SHOT_PERCENT = 25f;
    private static final float TURRET_TURN_PERCENT = 35f;
    private static final float SHIELD_DAMAGE_TAKEN_PERCENT = -20f;
    private static final float MISSILE_ROF_PERCENT = -25f;
    private static final float LINEAR_ACC_PERCENT = 15f;
    private static final float TURN_ACC_PERCENT = 25f;
    private static final float CR_DECAY_SPEED_PERCENT = 10f;

    public RS_VarRogue() {
        this.rarity = VariantRarity.ULTRA;
        this.variantDesignation = "Rogue";
        this.flavourText = "Eponymous bait";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(RECOIL_PER_SHOT, RECOIL_PER_SHOT_PERCENT);
        stats.put(TURRET_TURN, TURRET_TURN_PERCENT);
        stats.put(SHIELD_DAMAGE_TAKEN, SHIELD_DAMAGE_TAKEN_PERCENT);
        stats.put(MISSILE_ROF, MISSILE_ROF_PERCENT);
        stats.put(LINEAR_ACC, LINEAR_ACC_PERCENT);
        stats.put(TURN_ACC, TURN_ACC_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_SPEED_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
