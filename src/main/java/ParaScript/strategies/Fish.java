package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;

public class Fish implements Strategy {
    private Npc fishingSpot;

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
            fishingSpot.interact(Npcs.Option.NET);
            Time.sleep(1000);
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 30000);
        } catch (Exception err){
            System.out.println("Fishing error: ¯\\_(ツ)_/¯");
        }
    }

    private Npc fishingSpot(){
        for(Npc spot : Npcs.getNearest(316)){
            if (spot != null)
                return spot;
        }
        return null;
    }
}