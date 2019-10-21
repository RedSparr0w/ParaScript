package ParaScript.strategies;

import ParaScript.Methods;
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
        int currentItemAmount = Inventory.getCount(true);
        try {
            for (GroundItem item : items) {
                item.take();
                Time.sleep(500);
                Time.sleep(() -> item.distanceTo() < 1, 2000);
                if (Inventory.isFull()){
                    break;
                }
            }
        } catch (Exception ಠ_ಠ){
            System.out.println("Pickup items error: ¯\\_(ツ)_/¯");
        }
        Variables.addItemGained(Inventory.getCount(true) - currentItemAmount);
    }

    private GroundItem[] getItems(){
        return GroundItems.getNearest(getItemIDs());
    }

    private int[] getItemIDs(){
        int[] itemIDs = new int[]{};
        if (Variables.skill_to_train == Skill.ATTACK){
            if (Variables.fighting_bury_bones)
                itemIDs = new int[]{526}; // TODO: need to add all the other bones
        }
        itemIDs = Methods.combineIntArrays(Variables.fighting_item_ids, itemIDs);
        return itemIDs;
    }
}