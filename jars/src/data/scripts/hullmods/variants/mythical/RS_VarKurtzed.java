package data.scripts.hullmods.variants.mythical;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;
import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.MIN_LEVEL_ZERO_FLUX_BOOST;

public class RS_VarKurtzed extends RS_BaseVariantHullmod {
    private static final float BALLISTIC_DAMAGE_PERCENT = -15f;
    private static final float ENERGY_DAMAGE_PERCENT = -15f;
    private static final float MISSILE_DAMAGE_PERCENT = 150f;
    private static final float MISSILE_HEALTH_PERCENT = 100f;
    private static final float BALLISTIC_RANGE_PERCENT = -15f;
    private static final float ENERGY_RANGE_PERCENT = -10f;
    private static final float CR_DECAY_PERCENT = 25f;
    private static final float TOP_SPEED_PERCENT = -35f;

    public RS_VarKurtzed() {
        this.rarity = VARIANT_RARITY.MYTHICAL;
        this.variantDesignation = "Kurtzed";
        this.flavourText = "What if we nuked the";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = true;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(BALLISTIC_DAMAGE, BALLISTIC_DAMAGE_PERCENT);
        stats.put(ENERGY_DAMAGE, ENERGY_DAMAGE_PERCENT);
        stats.put(MISSILE_DAMAGE, MISSILE_DAMAGE_PERCENT);
        stats.put(MISSILE_HEALTH, MISSILE_HEALTH_PERCENT);
        stats.put(BALLISTIC_RANGE, BALLISTIC_RANGE_PERCENT);
        stats.put(ENERGY_RANGE, ENERGY_RANGE_PERCENT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);
        stats.put(TOP_SPEED, TOP_SPEED_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }

    @Override
    protected void addPostPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addImage("graphics/icons/memes/RS_VarKurtzed.png", 401f, 373f, 8f);
    }
}
