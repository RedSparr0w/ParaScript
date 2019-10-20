package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;

public class Thieving implements Strategy {
    private Npc victim;

    @Override
    public boolean activate() {
        victim = victim(); // set the local Variable
        if (Variables.running
                && victim != null
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()) {
            Variables.setStatus("thieving");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        victim.interact(Npcs.Option.PICKPOCKET);
        Time.sleep(1000);
        //Wait for the Player to finish pickpocketing
        Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 500);
    }

    private Npc victim(){
        int[] npc_to_thieve = Variables.thieving_npc_selected.getIDs();
        for(Npc victim : Npcs.getNearest(npc_to_thieve)){
            if(victim != null){
                return victim;
            }
        }
        return null;
    }
}