package data.scripts.hullmods.variants;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

public class RS_VarNull extends RS_BaseVariantHullmod {
    public RS_VarNull() {
        this.rarity = VARIANT_RARITY.NULL;
        this.variantDesignation = "Null Variant";
        this.flavourText = "this variant serves as a placeholder to allow the system to correctly apply variants (this message will be removed for mod release)";
        this.isStubText = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}