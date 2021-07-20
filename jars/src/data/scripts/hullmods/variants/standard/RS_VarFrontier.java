package data.scripts.hullmods.variants.standard;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarFrontier extends RS_BaseVariantHullmod {
    private static final float MAINTENANCE_COST_PERCENT = -10f;
    private static final float FUEL_USE_PERCENT = -20f;
    private static final float FUEL_CAPACITY_PERCENT = -15f;

    public RS_VarFrontier() {
        this.rarity = VariantRarity.STANDARD;
        this.variantDesignation = "Frontier";
        this.flavourText = "There and back";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = false;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(MAINTENANCE_COST, MAINTENANCE_COST_PERCENT);
        stats.put(FUEL_USE, FUEL_USE_PERCENT);
        stats.put(FUEL_CAPACITY, FUEL_CAPACITY_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}