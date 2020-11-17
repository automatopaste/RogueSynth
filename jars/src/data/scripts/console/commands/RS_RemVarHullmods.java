package data.scripts.console.commands;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import org.lazywizard.console.BaseCommand;
import org.lazywizard.console.Console;

public class RS_RemVarHullmods implements BaseCommand {
    @Override
    public CommandResult runCommand(String args, CommandContext context) {
        if (context == CommandContext.COMBAT_SIMULATION || context == CommandContext.COMBAT_CAMPAIGN || context == CommandContext.COMBAT_MISSION) {
            Console.showMessage("Command only usable in campaign");
            return CommandResult.ERROR;
        }
        if (args != null && !args.trim().isEmpty()) {
            String[] ids = args.split(" ");
            if (ids.length != 1) {
                Console.showMessage("Syntax error");
                return CommandResult.BAD_SYNTAX;
            }
            String ship = ids[0];

            FleetMemberAPI member = null;
            for (FleetMemberAPI fm : Global.getSector().getPlayerFleet().getMembersWithFightersCopy()) {
                if (fm.getShipName() != null && fm.getShipName().contains(ids[0])) {
                    member = fm;
                    break;
                }
            }
            if (member == null) {
                Console.showMessage("No ship with name " + ship + " found in player fleet");
                return CommandResult.ERROR;
            }

            String removed = "";
            for (String hm : member.getVariant().getPermaMods()) {
                if (hm.startsWith("RS_Var")) {
                    member.getVariant().removePermaMod(hm);
                    removed += hm + " ";
                }
            }
            Console.showMessage("Removed RS_Var hullmods ( " + removed + ")");
            return CommandResult.SUCCESS;
        } else {
            return CommandResult.BAD_SYNTAX;
        }
    }
}
