package data.scripts.subsystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import data.scripts.hullmods.RS_BaseVariantHullmod;

public class RS_ActiveArmor extends dl_BaseSubsystem{
    public static final String SUBSYSTEM_ID = "RS_ActiveArmor"; //this should match the id in the csv

    public static final float ARMOR_BONUS_PERCENT = 100f;
    public static final float MAX_SPEED_REDUCTION_PERCENT = 50f;

    public RS_ActiveArmor() {
        super(SUBSYSTEM_ID);
    }

    @Override
    public void apply(MutableShipStatsAPI stats, String id, SubsystemState subsystemState, float effectLevel) {
        stats.getArmorBonus().modifyPercent(id, effectLevel * ARMOR_BONUS_PERCENT);
        stats.getMaxSpeed().modifyPercent(id, effectLevel * -MAX_SPEED_REDUCTION_PERCENT);

        ship.setJitter(this, RS_BaseVariantHullmod.ALPHA_HIGHLIGHT_COLOR, 0.25f * effectLevel, 4, 5f * effectLevel);
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        stats.getArmorBonus().unmodify(id);
        stats.getMaxSpeed().unmodify(id);
    }

    @Override
    public String getStatusString() {
        return null;
    }

    @Override
    public String getInfoString() {
        if (isCooldown()) return "CHARGING ARMOR NODES";
        if (isOff()) return "POLARIZER READY";
        if (isOn()) return "ARMOR POLARIZED";
        return "--";
    }

    @Override
    public String getFlavourString() {
        return "ARMOR STRENGTH BOOST, LOW MAXIMUM SPEED";
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
