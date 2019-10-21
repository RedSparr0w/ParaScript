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

public class Killer implements Strategy {
    private Npc victim;

    @Override
    public boolean activate() {
        victim = victim(); // set the local Variable
        if (Variables.running
                && victim != null
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        victim.interact(Npcs.Option.ATTACK);
        Time.sleep(1000);
        //Wait for the Player to finish pickpocketing
        Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 500);
    }

    private Npc victim(){
        for(Npc victim : Npcs.getNearest(41)){
            if(victim != null){
                return victim;
            }
        }
        return null;
    }
}