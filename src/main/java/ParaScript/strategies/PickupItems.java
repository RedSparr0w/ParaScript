package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Ores;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;

public class PickupItems implements Strategy {

    @Override
    public boolean activate() {
        if (Variables.running
                && (Variables.getStatus() == "none" || Variables.getStatus() == "picking up items")
                && Variables.VARROCK_EAST_MINE_ZONE.inTheZone()
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()) {
            Variables.setStatus("picking up items");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            GroundItem[] items = GroundItems.getNearest(436, 438, 440);
            for (GroundItem item : items) {
                item.take();
                Time.sleep(1500);
                if (Inventory.isFull()){
                    Variables.setStatus("none");
                    return;
                }
            }
        } catch (Exception err){
            System.out.println("Pickup items error: ¯\\_(ツ)_/¯");
        }
    }
}