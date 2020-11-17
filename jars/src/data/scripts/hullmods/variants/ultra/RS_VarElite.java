package data.scripts.hullmods.variants.ultra;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarElite extends RS_BaseVariantHullmod {
    private static final float SENSOR_STRENGTH_PERCENT = 20f;
    private static final float SENSOR_PROFILE_PERCENT = 20f;
    private static final float FLUX_DISSIPATION_PERCENT = 8f;
    private static final float FLUX_CAPACITY_PERCENT = 8f;
    private static final float AUTOFIRE_ACCURACY_PERCENT = 25f;
    private static final float MAINTENANCE_COST_PERCENT = 25f;
    //private static final float TOP_SPEED_PERCENT = 10f;
    private static final float CR_DECAY_PERCENT = 15f;

    public RS_VarElite() {
        this.rarity = VARIANT_RARITY.ULTRA;
        this.variantDesignation = "Elite";
        this.flavourText = "Best of the best";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(SENSOR_STRENGTH, SENSOR_STRENGTH_PERCENT);
        stats.put(SENSOR_PROFILE, SENSOR_PROFILE_PERCENT);
        stats.put(FLUX_DISSIPATION, FLUX_DISSIPATION_PERCENT);
        stats.put(FLUX_CAPACITY, FLUX_CAPACITY_PERCENT);
        stats.put(AUTOFIRE_ACCURACY, AUTOFIRE_ACCURACY_PERCENT);
        stats.put(MAINTENANCE_COST, MAINTENANCE_COST_PERCENT);
        //stats.put(TOP_SPEED, TOP_SPEED_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
