package data.scripts.campaign;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.AbilityPlugin;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.FleetEncounterContext;
import com.fs.starfarer.api.impl.campaign.FleetInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.util.Misc;
import data.scripts.util.RS_Misc;

import java.util.Map;

public class RS_VariantManager implements CampaignEventListener {
    public static final float BASE_CHANCE = 0.5f;

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {
        if (market == null) return;

        //if (market.getFactionId().equals(Factions.PLAYER)) {
        if (true) {
            SubmarketPlugin storage = Misc.getStorage(market);
            if (storage == null) return;

            for (FleetMemberAPI member : storage.getCargo().getMothballedShips().getMembersListCopy()) {
                boolean hasVar = false;
                for (String id : member.getVariant().getHullMods()) {
                    if (id.startsWith("RS_Var")) {
                        hasVar = true;
                        break;
                    }
                }

                if (!hasVar) {
                    RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);
                }
            }
        }
    }

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {

    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {
        if (market == null) return;

        //if (market.getFactionId().equals(Factions.PLAYER)) {
        if (true) {
            SubmarketPlugin storage = Misc.getStorage(market);
            if (storage == null) return;

            for (FleetMemberAPI member : storage.getCargo().getMothballedShips().getMembersListCopy()) {
                boolean hasVar = false;
                for (String id : member.getVariant().getHullMods()) {
                    if (id.startsWith("RS_Var")) {
                        hasVar = true;
                        break;
                    }
                }

                if (!hasVar) {
                    RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);
                }
            }
        }
    }

    @Override
    public void reportEncounterLootGenerated(FleetEncounterContextPlugin plugin, CargoAPI loot) {
        if (plugin == null || loot == null || loot.getMothballedShips() == null) return;

        for (FleetMemberAPI member : loot.getMothballedShips().getMembersListCopy()) {
            if (member.isFighterWing()) continue;

            boolean hasVar = false;
            for (String id : member.getVariant().getHullMods()) {
                if (id.startsWith("RS_Var")) {
                    hasVar = true;
                    break;
                }
            }

            if (!hasVar) {
                RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);
            }
        }

        Global.getLogger(RS_VariantManager.class).info("Applying variants to loot: " + loot.getMothballedShips().getMembersListCopy().size() + " fleet members");
    }

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {

    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportBattleFinished(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportPlayerEngagement(EngagementResultAPI result) {

    }

    @Override
    public void reportFleetDespawned(CampaignFleetAPI fleet, FleetDespawnReason reason, Object param) {

    }

    @Override
    public void reportFleetSpawned(CampaignFleetAPI fleet) {

    }

    @Override
    public void reportFleetReachedEntity(CampaignFleetAPI fleet, SectorEntityToken entity) {

    }

    @Override
    public void reportFleetJumped(CampaignFleetAPI fleet, SectorEntityToken from, JumpPointAPI.JumpDestination to) {

    }

    @Override
    public void reportShownInteractionDialog(InteractionDialogAPI dialog) {
        if (dialog == null || dialog.getPlugin() == null || !(dialog.getPlugin() instanceof FleetInteractionDialogPluginImpl)) return;

        FleetInteractionDialogPluginImpl plugin = (FleetInteractionDialogPluginImpl) dialog.getPlugin();
        if (!(plugin.getContext() instanceof FleetEncounterContext)) return;

        FleetEncounterContext context = (FleetEncounterContext) plugin.getContext();
        CampaignFleetAPI withAllies = context.getBattle().getNonPlayerCombined();
        if (withAllies == null) return;

        for (FleetMemberAPI member : withAllies.getMembersWithFightersCopy()) {
            if (member.isFighterWing()) continue;

            boolean hasVar = false;
            for (String id : member.getVariant().getHullMods()) {
                if (id.startsWith("RS_Var")) {
                    hasVar = true;
                    break;
                }
            }

            if (!hasVar) {
                RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);
            }
        }

        Global.getLogger(RS_VariantManager.class).info("Applying variants to fleet: " + withAllies.getNameWithFaction());
    }

    @Override
    public void reportPlayerReputationChange(String faction, float delta) {

    }

    @Override
    public void reportPlayerReputationChange(PersonAPI person, float delta) {

    }

    @Override
    public void reportPlayerActivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDeactivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDumpedCargo(CargoAPI cargo) {

    }

    @Override
    public void reportPlayerDidNotTakeCargo(CargoAPI cargo) {

    }

    @Override
    public void reportEconomyTick(int iterIndex) {

    }

    @Override
    public void reportEconomyMonthEnd() {

    }

    public static class VariantStats {
        public enum StatType {
            LINEAR_ACC,
            TURN_ACC,
            TOP_SPEED,
            ZERO_FLUX_BOOST,
            ENGINE_HEALTH,

            FLUX_DISSIPATION,
            FLUX_CAPACITY,
            VENT_RATE,
            MIN_LEVEL_ZERO_FLUX_BOOST,
            ECCM_CHANCE,
            EMP_RECEIVED,
            SIGHT_RADIUS,
            TIME_MULT,

            CR_DECAY_SPEED,
            MALFUNCTION_CHANCE,

            HULL,
            ARMOUR,
            BREAK_PROB,

            MOUNT_HEALTH,
            BALLISTIC_RANGE,
            ENERGY_RANGE,
            PD_RANGE,
            BALLISTIC_AMMO,
            BALLISTIC_DAMAGE,
            BALLISTIC_ROF,
            BALLISTIC_FLUX_COST,
            ENERGY_AMMO,
            ENERGY_DAMAGE,
            ENERGY_ROF,
            ENERGY_FLUX_COST,
            MISSILE_HEALTH,
            MISSILE_GUIDANCE,
            MISSILE_AMMO,
            MISSILE_DAMAGE,
            MISSILE_ROF,
            AUTOFIRE_ACCURACY,
            RECOIL_PER_SHOT,
            TURRET_TURN,

            SHIELD_DAMAGE_TAKEN,
            SHIELD_TURN_RATE,

            SENSOR_STRENGTH,
            SENSOR_PROFILE,
            BURN,
            FUEL_CAPACITY,
            FUEL_USE,
            CARGO_CAPACITY,
            CREW_CAPACITY,
            MIN_CREW,
            MAINTENANCE_COST
        }
        Map<StatType, Float> stats;

        public VariantStats(Map<StatType, Float> stats) {
            this.stats = stats;
        }

        public Map<StatType, Float> getStats() {
            return stats;
        }

        public void setStats(Map<StatType, Float> stats) {
            this.stats = stats;
        }
    }
}