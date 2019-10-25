package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Player;

public class Fighting implements Strategy {
    private Npc victim;

    @Override
    public boolean activate() {
        victim = victim(); // set the local Variable
        Player myPlayer = Players.getMyPlayer();
        if (Variables.running
                && victim != null
                && !myPlayer.isInCombat()
                && myPlayer.getAnimation() == -1
                && !Inventory.isFull()
                && myPlayer.getHealth() > Variables.fighting_minimum_hitpoints) {
            Variables.setStatus("fighting");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        victim.interact(Npcs.Option.ATTACK);
        Time.sleep(2000);
        // Wait for the Player to finish attacking (max 30 seconds)
        Time.sleep(() -> !Players.getMyPlayer().isInCombat(), 30000);
        Variables.updateExpGained();
    }

    private Npc victim(){
        try {
            int[] npc_to_thieve = Variables.fighting_npc_selected.getIDs();
            for (Npc victim : Npcs.getNearest(npc_to_thieve)) {
                if (victim != null) {
                    return victim;
                }
            }
        } catch (Exception ಠ_ಠ){}
        return null;
    }
}