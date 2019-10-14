package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.TilePath;

public class Walk implements Strategy {

    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "walking to mine")
                && !Inventory.isFull()
                && Variables.VARROCK_EAST_BANK_ZONE.inTheZone();
    }

    @Override
    public void execute() {
        Variables.setStatus("walking to mine");
        Variables.pathToWalk = new TilePath(Variables.VARROCK_EAST_BANK_PATH_TO_MINE);
        //Variables.setBotStatus("walking to " + Variables.getTree().getName());
        while (Variables.pathToWalk != null && !Variables.pathToWalk.hasReached()) {
            Variables.pathToWalk.traverse();
            Time.sleep(1000, 2000);
        }
    }
}