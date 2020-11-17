package data.scripts.console.commands;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;

public class RS_AddVarHullmod implements BaseCommand {
    @Override
    public CommandResult runCommand(String args, CommandContext context) {
        if (context == CommandContext.COMBAT_SIMULATION || context == CommandContext.COMBAT_CAMPAIGN || context == CommandContext.COMBAT_MISSION) {
            Console.showMessage("Command only usable in campaign");
            return CommandResult.ERROR;
        }
        if (args != null && !args.trim().isEmpty()) {
            String[] ids = args.split(" ");

            if (ids.length != 2) {
                Console.showMessage("Syntax error");
                return CommandResult.BAD_SYNTAX;
            }

            if (Global.getSettings().getHullModSpec(ids[0]) == null) {
                Console.showMessage("Incorrect hullmod id specified");
                return CommandResult.ERROR;
            }
            FleetMemberAPI member = null;
            for (FleetMemberAPI fm : Global.getSector().getPlayerFleet().getMembersWithFightersCopy()) {
                if (fm.getShipName() != null && fm.getShipName().contains(ids[1])) {
                    member = fm;
                    break;
                }
            }
            if (member == null) {
                Console.showMessage("No ship with name " + ids[1] + " found in player fleet");
                return CommandResult.ERROR;
            }
            if (member.getVariant().hasHullMod(ids[0])) {
                Console.showMessage("Ship already has hullmod " + ids[0]);
                return CommandResult.ERROR;
            }

            Console.showMessage("Variant added ( " + ids[0] + " )");
            member.getVariant().addPermaMod(ids[0]);
            return CommandResult.SUCCESS;
        } else {
            Console.showMessage("Please specify a hullmod id to add");
            return CommandResult.BAD_SYNTAX;
        }
    }
}
