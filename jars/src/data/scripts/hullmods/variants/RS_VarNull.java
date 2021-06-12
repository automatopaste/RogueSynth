package data.scripts.hullmods.variants;

import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RS_VarNull extends RS_BaseVariantHullmod {
    public RS_VarNull() {
        this.rarity = VARIANT_RARITY.NULL;
        this.variantDesignation = "Null Variant";
        //this.flavourText = "this variant serves as a placeholder to allow the system to correctly apply variants (this message will be removed for mod release)";
        this.flavourText = "lol";
        this.isStubText = false;
        this.additionalInfo = "Shots to hull or armour have a chance to remove one D-mod.";
        this.infoColour = new Color(255, 0, 0, 255);

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        variantStats = new RS_VariantManager.VariantStats(stats);
    }
}