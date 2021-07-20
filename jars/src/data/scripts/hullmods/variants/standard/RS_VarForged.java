package data.scripts.hullmods.variants.standard;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarForged extends RS_BaseVariantHullmod {
    private static final float BREAK_PROB_PERCENT = -35f;
    private static final float HULL_PERCENT = 15f;
    private static final float AUTOFIRE_ACCURACY_PERCENT = -25f;

    public RS_VarForged() {
        this.rarity = VariantRarity.STANDARD;
        this.variantDesignation = "Forged";
        this.flavourText = "Built to last";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = false;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(BREAK_PROB, BREAK_PROB_PERCENT);
        stats.put(HULL, HULL_PERCENT);
        stats.put(AUTOFIRE_ACCURACY, AUTOFIRE_ACCURACY_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
