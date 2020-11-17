package data.scripts.hullmods.variants.standard;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarHeavy extends RS_BaseVariantHullmod {
    private static final float TURN_ACCELERATION_PERCENT = -50f;
    private static final float MOVEMENT_ACCELERATION_PERCENT = -25f;
    private static final float ARMOUR_FLAT = 120f;
    private static final float TOP_SPEED_PERCENT = 25f;
    private static final float ENGINE_HEALTH_PERCENT = 15f;

    public RS_VarHeavy() {
        this.rarity = VARIANT_RARITY.STANDARD;
        this.variantDesignation = "Heavy";
        this.flavourText = "Hit like a truck";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(TURN_ACC, TURN_ACCELERATION_PERCENT);
        stats.put(LINEAR_ACC, MOVEMENT_ACCELERATION_PERCENT);
        stats.put(ARMOUR, ARMOUR_FLAT);
        stats.put(TOP_SPEED, TOP_SPEED_PERCENT);
        stats.put(ENGINE_HEALTH, ENGINE_HEALTH_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
