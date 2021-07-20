package data.scripts.hullmods.variants.alpha;

import com.fs.starfarer.api.combat.ShipAPI;
import data.scripts.campaign.RS_VariantManager;
import data.scripts.hullmods.RS_BaseVariantHullmod;
import data.scripts.subsystems.RS_Hyperburner;
import data.scripts.util.dl_SubsystemUtils;

import java.util.HashMap;
import java.util.Map;

import static data.scripts.campaign.RS_VariantManager.VariantStats.StatType.*;

public class RS_VarHunterKiller extends RS_BaseVariantHullmod {
    private static final float ZERO_FLUX_BOOST_FLAT = 10f;
    private static final float TURN_ACCELERATION_PERCENT = 25f;
    private static final float MOVEMENT_ACCELERATION_PERCENT = 15f;
    private static final float CR_DECAY_PERCENT = 25f;
    private static final float MOUNT_HEALTH_PERCENT = -50f;

    public RS_VarHunterKiller() {
        this.rarity = VariantRarity.ALPHA;
        this.variantDesignation = "Hunter-Killer";
        this.flavourText = "Vacuum decay";
        this.doJitterUnder = true;
        this.doVentColour = true;
        this.doWeaponGlow = false;
        this.doEngineColourShift = true;

        Map<RS_VariantManager.VariantStats.StatType, Float> stats = new HashMap<>();
        stats.put(TURN_ACC, TURN_ACCELERATION_PERCENT);
        stats.put(LINEAR_ACC, MOVEMENT_ACCELERATION_PERCENT);
        stats.put(ZERO_FLUX_BOOST, ZERO_FLUX_BOOST_FLAT);
        stats.put(CR_DECAY_SPEED, CR_DECAY_PERCENT);
        stats.put(MOUNT_HEALTH, MOUNT_HEALTH_PERCENT);

        variantStats = new RS_VariantManager.VariantStats(stats);
    }

    @Override
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
        super.applyEffectsAfterShipCreation(ship, id);

        dl_SubsystemUtils.queueSubsystemFromHullmodForShip(ship, RS_Hyperburner.class);
    }
}
