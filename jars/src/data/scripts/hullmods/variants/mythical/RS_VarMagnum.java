package data.scripts.hullmods.variants.mythical;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarMagnum extends RS_BaseVariantHullmod {
    private static final float BALLISTIC_ROF_PERCENT = -50f;
    private static final float BALLISTIC_DAMAGE_PERCENT = 40f;
    private static final float BALLISTIC_RANGE_PERCENT = 20f;
    private static final float ENERGY_ROF_PERCENT = -50f;
    private static final float ENERGY_DAMAGE_PERCENT = 40f;
    private static final float ENERGY_RANGE_PERCENT = 20f;
    private static final float SHIELD_EFFICIENCY_PERCENT = 35f;
    private static final float CR_DECAY_PERCENT = 25f;

    public RS_VarMagnum() {
        this.rarity = VARIANT_RARITY.MYTHICAL;
        this.variantDesignation = "Magnum";
        this.flavourText = "Heavy hits";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(BALLISTIC_ROF, BALLISTIC_ROF_PERCENT);
        stats.put(BALLISTIC_DAMAGE, BALLISTIC_DAMAGE_PERCENT);
        stats.put(BALLISTIC_RANGE, BALLISTIC_RANGE_PERCENT);
        stats.put(ENERGY_ROF, ENERGY_ROF_PERCENT);
        stats.put(ENERGY_DAMAGE, ENERGY_DAMAGE_PERCENT);
        stats.put(ENERGY_RANGE, ENERGY_RANGE_PERCENT);
        stats.put(SHIELD_DAMAGE_TAKEN, SHIELD_EFFICIENCY_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
