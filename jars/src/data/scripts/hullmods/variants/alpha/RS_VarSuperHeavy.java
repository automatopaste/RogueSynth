package data.scripts.hullmods.variants.alpha;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarSuperHeavy extends RS_BaseVariantHullmod {
    private static final float TURN_ACCELERATION_PERCENT = -50f;
    private static final float MOVEMENT_ACCELERATION_PERCENT = -35f;
    private static final float ARMOUR_FLAT = 250f;
    private static final float MOUNT_HEALTH_PERCENT = 35f;
    private static final float ENGINE_HEALTH_PERCENT = 15f;
    private static final float SHIELD_TURN_RATE_PERCENT = -20f;

    public RS_VarSuperHeavy() {
        this.rarity = VARIANT_RARITY.ALPHA;
        this.variantDesignation = "Super Heavy";
        this.flavourText = "A different calibre";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(TURN_ACC, TURN_ACCELERATION_PERCENT);
        stats.put(LINEAR_ACC, MOVEMENT_ACCELERATION_PERCENT);
        stats.put(ARMOUR, ARMOUR_FLAT);
        stats.put(MOUNT_HEALTH, MOUNT_HEALTH_PERCENT);
        stats.put(ENGINE_HEALTH, ENGINE_HEALTH_PERCENT);
        stats.put(SHIELD_TURN_RATE, SHIELD_TURN_RATE_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
