package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;

// TODO: this needs fixing up, not currently working AFAIK

public class Smelt implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && hasOres()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "smelting")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1) {
            Variables.setStatus("smelting");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            Menu.clickButton(Variables.smithing_bar_selected.getButtonID());
            Time.sleep(2000);
            // Sleep until animation == -1
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        } catch (Exception err){
            System.out.println("Smelting error: ¯\\_(ツ)_/¯");
        }
    }

    private boolean hasOres(){
        int[] ores = Variables.smithing_bar_selected.getOres();
        for (int ore : ores)
            if (Inventory.getItem(ore) == null)
                return false;
        return true;
    }
}