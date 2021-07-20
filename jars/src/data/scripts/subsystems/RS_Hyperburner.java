package data.scripts.subsystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipCommand;
import data.scripts.hullmods.RS_BaseVariantHullmod;

import java.awt.*;

public class RS_Hyperburner extends dl_BaseSubsystem{
    public static final String SUBSYSTEM_ID = "RS_Hyperburner"; //this should match the id in the csv

    public RS_Hyperburner() {
        super(SUBSYSTEM_ID);
    }

    @Override
    public void apply(MutableShipStatsAPI stats, String id, SubsystemState subsystemState, float effectLevel) {
        stats.getMaxSpeed().modifyPercent(id, 50f * effectLevel);
        stats.getAcceleration().modifyPercent(id, 50f * effectLevel);
        stats.getTurnAcceleration().modifyPercent(id, -75f * effectLevel);
        stats.getMaxTurnRate().modifyPercent(id, 25f * effectLevel); //lol

        ship.giveCommand(ShipCommand.ACCELERATE, null, 0);

        ship.getEngineController().extendFlame(this, 2f * effectLevel, 2f * effectLevel, 3f * effectLevel);
        ship.getEngineController().fadeToOtherColor(this, RS_BaseVariantHullmod.ALPHA_HIGHLIGHT_COLOR, RS_BaseVariantHullmod.ALPHA_COLOR, effectLevel, 0.5f);
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getMaxSpeed().unmodify(id);
        stats.getMaxTurnRate().unmodify(id);
        stats.getTurnAcceleration().unmodify(id);
        stats.getAcceleration().unmodify(id);
        stats.getDeceleration().unmodify(id);
    }

    @Override
    public String getStatusString() {
        return null;
    }

    @Override
    public String getInfoString() {
        if (isCooldown()) return "COOLING AFTERBURNER";
        if (isOff()) return "BURN PRIMED";
        if (isOn()) return "ENGINE INJECTION";
        return "--";
    }

    @Override
    public String getFlavourString() {
        return "extreme speed boost";
    }

    @Override
    public int getNumGuiBars() {
        return 1;
    }

    @Override
    public void aiInit() {

    }

    @Override
    public void aiUpdate(float v) {

    }
}
