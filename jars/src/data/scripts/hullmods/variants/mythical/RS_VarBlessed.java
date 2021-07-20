package data.scripts.hullmods.variants.mythical;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarBlessed extends RS_BaseVariantHullmod {
    private static final float BREAK_PROBABILITY_PERCENT = -95f;
    private static final float TIME_MULT_PERCENT = 10f;
    private static final float ENERGY_FLUX_COST_PERCENT = -15f;
    private static final float ENERGY_DAMAGE_PERCENT = 10f;
    private static final float BALLISTIC_FLUX_COST_PERCENT = -15f;
    private static final float BALLISTIC_ROF_PERCENT = 10f;
    private static final float MAINTENANCE_COST_PERCENT = 40f;
    private static final float CR_DECAY_PERCENT = 150f;
    private static final float MALFUNCTION_CHANCE_PERCENT = 100f;
    private static final float MISSILE_ROF_PERCENT = -50f;
    private static final float MISSILE_GUIDANCE_PERCENT = -25f;

    public RS_VarBlessed() {
        this.rarity = VariantRarity.MYTHICAL;
        this.variantDesignation = "Blessed";
        this.flavourText = "Just a tribute";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;
        this.additionalInfo = "Blessed shot chance?";
        this.infoColour = new Color(255, 255, 255, 50);

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(BREAK_PROB, BREAK_PROBABILITY_PERCENT);
        stats.put(TIME_MULT, TIME_MULT_PERCENT);
        stats.put(ENERGY_FLUX_COST, ENERGY_FLUX_COST_PERCENT);
        stats.put(ENERGY_DAMAGE, ENERGY_DAMAGE_PERCENT);
        stats.put(BALLISTIC_FLUX_COST, BALLISTIC_FLUX_COST_PERCENT);
        stats.put(BALLISTIC_ROF, BALLISTIC_ROF_PERCENT);
        stats.put(MAINTENANCE_COST, MAINTENANCE_COST_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);
        stats.put(MALFUNCTION_CHANCE, MALFUNCTION_CHANCE_PERCENT);
        stats.put(MISSILE_ROF, MISSILE_ROF_PERCENT);
        stats.put(MISSILE_GUIDANCE, MISSILE_GUIDANCE_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
