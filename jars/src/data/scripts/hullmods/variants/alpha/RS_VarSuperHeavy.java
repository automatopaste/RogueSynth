package data.scripts.hullmods.variants.alpha;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;
import data.scripts.subsystems.RS_ActiveArmor;
import data.scripts.util.dl_SpecLoadingUtils;
import data.scripts.util.dl_SubsystemUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarSuperHeavy extends RS_BaseVariantHullmod {
    private static final float TURN_ACCELERATION_PERCENT = -60f;
    private static final float MOVEMENT_ACCELERATION_PERCENT = -45f;
    private static final float ARMOUR_FLAT = 250f;
    private static final float MOUNT_HEALTH_PERCENT = 35f;
    private static final float ENGINE_HEALTH_PERCENT = 15f;
    private static final float SHIELD_TURN_RATE_PERCENT = -60f;

    public RS_VarSuperHeavy() {
        this.rarity = VariantRarity.ALPHA;
        this.variantDesignation = "Super Heavy";
        this.flavourText = "A different calibre";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(TURN_ACC, TURN_ACCELERATION_PERCENT);
        stats.put(LINEAR_ACC, MOVEMENT_ACCELERATION_PERCENT);
        stats.put(ARMOUR, ARMOUR_FLAT);
        stats.put(MOUNT_HEALTH, MOUNT_HEALTH_PERCENT);
        stats.put(ENGINE_HEALTH, ENGINE_HEALTH_PERCENT);
        stats.put(SHIELD_TURN_RATE, SHIELD_TURN_RATE_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }

    @Override
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        super.applyEffectsAfterShipCreation(ship, id);

        dl_SubsystemUtils.queueSubsystemFromHullmodForShip(ship, RS_ActiveArmor.class);
    }

    @Override
    protected void addSubsystemInfo(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        tooltip.addSectionHeading("SUBSYSTEM", Alignment.MID, 20f);

        String name = dl_SpecLoadingUtils.getSubsystemData(RS_ActiveArmor.SUBSYSTEM_ID).getName();
        LabelAPI t = tooltip.addPara(name, 10f);
        t.setHighlight(name);
        t.setHighlightColor(ALPHA_HIGHLIGHT_COLOR);
        t.setAlignment(Alignment.MID);

        String armor = (int) RS_ActiveArmor.ARMOR_BONUS_PERCENT + "%";
        String speed = (int) RS_ActiveArmor.MAX_SPEED_REDUCTION_PERCENT + "%";
        LabelAPI l = tooltip.addPara(
                "An armor polarizing combat subsystem is integrated into the ship. At full activation the subsystem increases armor by "
                        + armor + " and decreases maximum speed by " + speed,
                10f);
        l.setHighlight(armor, speed);
        l.setHighlightColors(ALPHA_HIGHLIGHT_COLOR, DEBUFF_HIGHLIGHT_COLOUR);
        l.setAlignment(Alignment.MID);
    }

    @Override
    protected boolean hasSubsystem() {
        return true;
    }
}
