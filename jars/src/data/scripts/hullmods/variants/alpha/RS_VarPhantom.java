package data.scripts.hullmods.variants.alpha;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarPhantom extends RS_BaseVariantHullmod {
    private static final float SENSOR_RANGE_PERCENT = 25f;
    private static final float SENSOR_PROFILE_PERCENT = -25f;
    private static final float MIN_LEVEL_ZERO_FLUX_BOOST_FLAT = 0.05f;
    private static final float SIGHT_RADIUS_PERCENT = 25f;
    private static final float MAINTENANCE_COST_PERCENT = 25f;
    private static final float SHIELD_TURN_RATE_PERCENT = -20f;

    public RS_VarPhantom() {
        this.rarity = VARIANT_RARITY.ALPHA;
        this.variantDesignation = "Phantom";
        this.flavourText = "Skin of stars";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(SENSOR_STRENGTH, SENSOR_RANGE_PERCENT);
        stats.put(SENSOR_PROFILE, SENSOR_PROFILE_PERCENT);
        stats.put(MIN_LEVEL_ZERO_FLUX_BOOST, MIN_LEVEL_ZERO_FLUX_BOOST_FLAT);
        stats.put(SIGHT_RADIUS, SIGHT_RADIUS_PERCENT);
        stats.put(MAINTENANCE_COST, MAINTENANCE_COST_PERCENT);
        stats.put(SHIELD_TURN_RATE, SHIELD_TURN_RATE_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
