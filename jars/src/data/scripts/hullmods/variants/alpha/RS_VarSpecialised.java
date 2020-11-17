package data.scripts.hullmods.variants.alpha;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarSpecialised extends RS_BaseVariantHullmod {
    private static final float ECCM_CHANCE_PERCENT = 50f;
    private static final float BALLISTIC_ROF_PERCENT = -15f;
    private static final float BALLISTIC_RANGE_PERCENT = 15f;
    private static final float ENERGY_DAMAGE_PERCENT = 20f;
    private static final float ENERGY_ROF_PERCENT = 15f;
    private static final float ENERGY_FLUX_COST_PERCENT = 10f;
    private static final float BALLISTIC_FLUX_COST_PERCENT = 10f;

    public RS_VarSpecialised() {
        this.rarity = VARIANT_RARITY.ALPHA;
        this.variantDesignation = "Specialised";
        this.flavourText = "A different calibre";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(ECCM_CHANCE, ECCM_CHANCE_PERCENT);
        stats.put(BALLISTIC_ROF, BALLISTIC_ROF_PERCENT);
        stats.put(BALLISTIC_RANGE, BALLISTIC_RANGE_PERCENT);
        stats.put(ENERGY_DAMAGE, ENERGY_DAMAGE_PERCENT);
        stats.put(ENERGY_ROF, ENERGY_ROF_PERCENT);
        stats.put(ENERGY_FLUX_COST, ENERGY_FLUX_COST_PERCENT);
        stats.put(BALLISTIC_FLUX_COST, BALLISTIC_FLUX_COST_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
