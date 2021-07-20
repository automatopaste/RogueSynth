package data.scripts.hullmods.variants.mythical;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarDivinity extends RS_BaseVariantHullmod {
    private static final float VENT_RATE_PERCENT = 30f;
    private static final float ZERO_FLUX_BOOST_FLAT = 20f;
    private static final float BALLISTIC_DAMAGE_PERCENT = 15f;
    private static final float ENERGY_DAMAGE_PERCENT = 15f;
    private static final float MALFUNCTION_CHANCE_PERCENT = 200f;
    private static final float BALLISTIC_RANGE_PERCENT = -15f;
    private static final float ENERGY_RANGE_PERCENT = -10f;
    private static final float CR_DECAY_PERCENT = 25f;
    private static final float TOP_SPEED_PERCENT = -25f;
    private static final float MIN_LEVEL_ZERO_FLUX_BOOST_FLAT = 0.25f;

    public RS_VarDivinity() {
        this.rarity = VariantRarity.MYTHICAL;
        this.variantDesignation = "Divinity";
        this.flavourText = "Cult classic";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(VENT_RATE, VENT_RATE_PERCENT);
        stats.put(ZERO_FLUX_BOOST, ZERO_FLUX_BOOST_FLAT);
        stats.put(BALLISTIC_DAMAGE, BALLISTIC_DAMAGE_PERCENT);
        stats.put(ENERGY_DAMAGE, ENERGY_DAMAGE_PERCENT);
        stats.put(MALFUNCTION_CHANCE, MALFUNCTION_CHANCE_PERCENT);
        stats.put(BALLISTIC_RANGE, BALLISTIC_RANGE_PERCENT);
        stats.put(ENERGY_RANGE, ENERGY_RANGE_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);
        stats.put(TOP_SPEED, TOP_SPEED_PERCENT);
        stats.put(MIN_LEVEL_ZERO_FLUX_BOOST, MIN_LEVEL_ZERO_FLUX_BOOST_FLAT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
