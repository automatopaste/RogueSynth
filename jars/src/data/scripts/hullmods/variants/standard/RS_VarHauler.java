package data.scripts.hullmods.variants.standard;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarHauler extends RS_BaseVariantHullmod {
    private static final float CARGO_CAPACITY_PERCENT = 15f;
    private static final float ENGINE_HEALTH_PERCENT = 35f;
    private static final float BALLISTIC_ROF_PERCENT = -20f;
    private static final float ENERGY_ROF_PERCENT = -20f;

    public RS_VarHauler() {
        this.rarity = VARIANT_RARITY.STANDARD;
        this.variantDesignation = "Hauler";
        this.flavourText = "Fitted for delivery";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = false;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(CARGO_CAPACITY, CARGO_CAPACITY_PERCENT);
        stats.put(ENGINE_HEALTH, ENGINE_HEALTH_PERCENT);
        stats.put(BALLISTIC_ROF, BALLISTIC_ROF_PERCENT);
        stats.put(ENERGY_ROF, ENERGY_ROF_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
