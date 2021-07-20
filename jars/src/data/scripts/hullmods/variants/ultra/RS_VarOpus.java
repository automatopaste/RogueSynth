package data.scripts.hullmods.variants.ultra;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarOpus extends RS_BaseVariantHullmod {
    private static final float TIME_MULT_PERCENT = 5f;
    private static final float ECCM_CHANCE_PERCENT = 35f;
    private static final float MISSILE_HEALTH_PERCENT = 25f;
    private static final float MISSILE_ROF_PERCENT = 25f;
    private static final float MAINTENANCE_COST_PERCENT = 10f;
    private static final float MOUNT_HEALTH_PERCENT = -25f;
    private static final float EMP_RECEIVED_PERCENT = 40f;

    public RS_VarOpus() {
        this.rarity = VariantRarity.ULTRA;
        this.variantDesignation = "Opus";
        this.flavourText = "\"I live to see you eat that contract\"";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(TIME_MULT, TIME_MULT_PERCENT);
        stats.put(ECCM_CHANCE, ECCM_CHANCE_PERCENT);
        stats.put(MISSILE_HEALTH, MISSILE_HEALTH_PERCENT);
        stats.put(MISSILE_ROF, MISSILE_ROF_PERCENT);
        stats.put(MAINTENANCE_COST, MAINTENANCE_COST_PERCENT);
        stats.put(MOUNT_HEALTH, MOUNT_HEALTH_PERCENT);
        stats.put(EMP_RECEIVED, EMP_RECEIVED_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}
