package data.scripts.console.commands;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import data.scripts.campaign.RS_VariantManager;
import data.scripts.util.RS_Misc;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;

import java.util.HashMap;
import java.util.Map;

public class RS_RandomizeFleetVars implements BaseCommand {
    @Override
    public CommandResult runCommand(String args, CommandContext context) {
        float BASE_CHANCE = RS_VariantManager.BASE_CHANCE;

        if (args != null && !args.contains("all")) {
            FleetMemberAPI member = null;
            for (FleetMemberAPI m : Global.getSector().getPlayerFleet().getMembersWithFightersCopy()) {
                if (m.isFighterWing()) continue;

                if (m.getShipName().contains(args)) {
                    member = m;
                    break;
                }
            }

            if (member == null || args.equals("")) {
                Console.showMessage("Ship not found");
                return CommandResult.ERROR;
            }

            RS_Misc.removeVariants(member);

            String id = RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);

            Console.showMessage(member.getShipName() + " was given " + id);
            return CommandResult.SUCCESS;
        }

        Map<FleetMemberAPI, String> shipVars = new HashMap<>();

        for (FleetMemberAPI member : Global.getSector().getPlayerFleet().getMembersWithFightersCopy()) {
            if (member.isFighterWing()) {
                continue;
            }

            RS_Misc.removeVariants(member);

            String id = RS_Misc.rollVariant(BASE_CHANCE, member.getHullSpec().getHullSize(), member);
            shipVars.put(member, " was given " + id);
        }

        for (FleetMemberAPI member : shipVars.keySet()) {
            Console.showMessage(member.getShipName() + shipVars.get(member));
        }
        return CommandResult.SUCCESS;
    }
}