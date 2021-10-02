package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;

public class Fish implements Strategy {
    private Npc fishingSpot;
    private int[] items = new int[]{317, 321};

    @Override
    public boolean activate() {
        fishingSpot = fishingSpot(); // set the local Variable
        if (Variables.running
                && fishingSpot != null
                && (Variables.getStatus() == "none" || Variables.getStatus() == "fishing")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1) {
            Variables.setStatus("fishing");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            if (Variables.shouldDropItems()) {
                if (Inventory.getCount(441) >= 1) Inventory.getItem(441).interact(Items.Option.DROP);
            }

            fishingSpot.interact(Variables.fishing_type_selected);
            Time.sleep(1000);

            // Wait for the Player to finish fishing (max 60 seconds)
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 60000);
        } catch (Exception ಠ_ಠ){
            System.out.println("Fishing error: ¯\\_(ツ)_/¯");
        }
    }

    private Npc fishingSpot(){
        try {
            for (Npc spot : Npcs.getNearest(316)) {
                if (spot != null)
                    return spot;
            }
        } catch (Exception ಠ_ಠ){}
        return null;
    }
}