package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.TilePath;

public class Bank implements Strategy {

    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "banking items")
                && Inventory.isFull();
    }

    @Override
    public void execute() {
        Variables.pathToWalk = new TilePath(Variables.VARROCK_EAST_MINE_PATH_TO_BANK);
        Variables.setStatus("banking items");
        while (Variables.pathToWalk != null && !Variables.pathToWalk.hasReached()) {
            Variables.pathToWalk.traverse();
            Time.sleep(1000, 2000);
        }
        depositItems();
    }
    public void depositItems() {
        Npc banker[] = Npcs.getNearest(494);

        if (banker != null) {
            banker[0].interact(Npcs.Option.BANK);
            Time.sleep(3000);
            if (Game.getOpenInterfaceId() == 5292) {
                //Pick Axes
                org.rev317.min.api.methods.Bank.depositAllExcept(1266, 1352, 1354, 1356, 1358, 1360, 6740);
                //Axes
                //org.rev317.min.api.methods.Bank.depositAllExcept(1350, 1352, 1354, 1356, 1358, 1360, 6740);
                Variables.setStatus("none");
            }
        }
    }
}