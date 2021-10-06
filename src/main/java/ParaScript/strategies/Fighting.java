package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Character;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Player;

public class Fighting implements Strategy {
    private Npc victim;
    private Player myPlayer;

    @Override
    public boolean activate() {
        myPlayer = Players.getMyPlayer();
        if (Variables.running
                && !myPlayer.isInCombat()
                //&& (myPlayer.getInteractingCharacter() == null || myPlayer.getInteractingCharacter().getIndex() <= 0)
                && !Inventory.isFull()
                && (myPlayer.getHealth() <= 0 || myPlayer.getHealth() > Variables.fighting_minimum_hitpoints)) {
            victim = victim(); // set the local Variable
            if (victim != null) {
                Variables.setStatus("fighting");
                return true;
            }
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        victim.interact(Npcs.Option.ATTACK);
        Time.sleep(2000);
        // Wait for the Player to finish attacking (max 3 seconds)
        Time.sleep(() -> !Players.getMyPlayer().isInCombat(), 3000);
        Variables.updateExpGained();
    }

    private Npc victim(){
        try {
            int[] npc_to_thieve = Variables.fighting_npc_selected.getIDs();
            for (Npc victim : Npcs.getNearest(npc_to_thieve)) {
                Character interactingPlayer = victim.getInteractingCharacter();
                if (victim != null && (interactingPlayer == null || interactingPlayer.getIndex() <= 0 || interactingPlayer.getIndex() == myPlayer.getIndex())) {
                    if (!Variables.load_cannon || victim.distanceTo() <= 1)
                        return victim;
                }
            }
        } catch (Exception ಠ_ಠ){}
        return null;
    }
}