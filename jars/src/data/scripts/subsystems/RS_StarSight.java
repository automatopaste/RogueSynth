package data.scripts.subsystems;

import com.fs.starfarer.api.combat.MutableShipStatsAPI;

public class RS_StarSight extends dl_BaseSubsystem {
    public static final String SUBSYSTEM_ID = "RS_StarSight"; //this should match the id in the csv

    public RS_StarSight() {
        super(SUBSYSTEM_ID);
    }

    @Override
    public void apply(MutableShipStatsAPI mutableShipStatsAPI, String s, SubsystemState subsystemState, float v) {

    }

    @Override
    public void unapply(MutableShipStatsAPI mutableShipStatsAPI, String s) {

    }

    @Override
    public String getStatusString() {
        return null;
    }

    @Override
    public String getInfoString() {
        return null;
    }

    @Override
    public String getFlavourString() {
        return null;
    }

    @Override
    public int getNumGuiBars() {
        return 0;
    }

    @Override
    public void aiInit() {

    }

    @Override
    public void aiUpdate(float v) {

    }
}
