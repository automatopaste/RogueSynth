package data.scripts.hullmods.variants.ultra;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarSiege extends RS_BaseVariantHullmod {
    private static final float BALLISTIC_RANGE_PERCENT = 15f;
    private static final float ENERGY_RANGE_PERCENT = 20f;
    private static final float PD_RANGE_PERCENT = 15f;
    private static final float ZERO_FLUX_BOOST_FLAT = -25f;
    private static final float MOVEMENT_ACCELERATION_PERCENT = -25f;
    private static final float MISSILE_HEALTH_PERCENT = -35f;

    public RS_VarSiege() {
        this.rarity = VARIANT_RARITY.ULTRA;
        this.variantDesignation = "Siege";
        this.flavourText = "Hold the line";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(BALLISTIC_RANGE, BALLISTIC_RANGE_PERCENT);
        stats.put(ENERGY_RANGE, ENERGY_RANGE_PERCENT);
        stats.put(PD_RANGE, PD_RANGE_PERCENT);
        stats.put(ZERO_FLUX_BOOST, ZERO_FLUX_BOOST_FLAT);
        stats.put(LINEAR_ACC, MOVEMENT_ACCELERATION_PERCENT);
        stats.put(MISSILE_HEALTH, MISSILE_HEALTH_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
