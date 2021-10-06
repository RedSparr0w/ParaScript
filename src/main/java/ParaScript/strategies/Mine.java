package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Rocks;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;

public class Mine implements Strategy {
    private SceneObject rock;

    @Override
    public boolean activate() {
        rock = rock(); // set the local Variable
        if (Variables.running
                && rock != null
                && (Variables.getStatus() == "none" || Variables.getStatus() == "mining")
                && !Players.getMyPlayer().isInCombat()
                && !Inventory.isFull()
                ) {
            Variables.setStatus("mining");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            if (Variables.mining_rock_selected.hash == 0) {
                Variables.mining_rock_selected.hash = rock.getHash();
                Variables.mining_rock_selected.x = rock.getLocalRegionX();
                Variables.mining_rock_selected.y = rock.getLocalRegionY();
            }
            Rocks myOre = Variables.mining_rock_selected;
            // 502, rock_hash, local_x, local_y, 4
            Menu.sendAction(502, myOre.hash, myOre.x, myOre.y, 4);
            // Wait 1 seconds for the player to reach the rock
            Time.sleep(1000);
            // Sleep until player is mining the rock for a maximum of 2 seconds
            Time.sleep(() -> Players.getMyPlayer().getAnimation() != -1, 2000);
            // Sleep until not mining for a maximum of 10 seconds
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 10000);
        } catch (Exception ಠ_ಠ){
            System.out.println("Mining error: ¯\\_(ツ)_/¯");
        }
    }

    private SceneObject rock(){
        int[] rock_to_mine = Variables.mining_rock_selected.getIDs();
        for(SceneObject rock : SceneObjects.getNearest(rock_to_mine)){
            return rock;
        }
        return null;
    }
}