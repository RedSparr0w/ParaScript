package ParaScript.strategies;

import ParaScript.data.Variables;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;

public class Prayer implements Strategy {
    // TODO: implement ectofuntus method
    // TODO: check if player has ectophial in bank, make use of it
    // TODO: check if agility level high enough, use shortcut

    @Override
    public boolean activate() {
        if (Variables.running
                && Game.isLoggedIn()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "withdrawing bones" || Variables.getStatus() == "closing bank" || Variables.getStatus() == "burying bones")) {
            return true;
        }       
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        Variables.setStatus("withdrawing bones");
        Bank.openBank();
        // TODO: allow player to select which bones they want to make use of
        Bank.withdrawItem(536, -1);
        Time.sleep(500);
        Variables.setStatus("closing bank");
        Bank.closeBank();
        Time.sleep(500);
        Variables.setStatus("burying bones");
        BuryBones.buryBones();
        Variables.setStatus("none");
    }
}
