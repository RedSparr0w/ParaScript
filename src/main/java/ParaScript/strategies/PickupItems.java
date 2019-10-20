package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Ores;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

public class PickupItems implements Strategy {
    GroundItem[] items;

    @Override
    public boolean activate() {
        items = getItems();
        if (Variables.running
                && items.length > 0
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
            for (GroundItem item : items) {
                item.take();
                Time.sleep(() -> item.distanceTo() < 1, 2000);
                if (Inventory.isFull()){
                    return;
                }
            }
        } catch (Exception err){
            System.out.println("Pickup items error: ¯\\_(ツ)_/¯");
        }
    }

    private GroundItem[] getItems(){
        int[] itemIDs = new int[]{};
        if (Variables.skill_to_train == Skill.ATTACK){
            if (Variables.fighting_bury_bones)
                itemIDs = new int[]{526};
        }
        return GroundItems.getNearest(itemIDs);
    }
}