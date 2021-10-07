package ParaScript.strategies;

import ParaScript.Methods;
import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.Player;

import java.util.ArrayList;

public class PickupItems implements Strategy {
    ArrayList<GroundItem> items;

    @Override
    public boolean activate() {
        items = getItems();
        Player myPlayer = Players.getMyPlayer();
        if (Variables.running
                && items.size() > 0
                && (myPlayer.getInteractingCharacter() == null || myPlayer.getInteractingCharacter().getIndex() <= 0)
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
                int currentItemAmount = Inventory.getCount(true);
                item.take();
                Time.sleep(500);
                Time.sleep(() -> item.distanceTo() < 1, 2000);
                Variables.addItemGained(Inventory.getCount(true) - currentItemAmount);
                if (Inventory.isFull()){
                    break;
                }
            }
        } catch (Exception ಠ_ಠ){
            System.out.println("Pickup items error: ¯\\_(ツ)_/¯");
        }
    }

    private ArrayList<GroundItem> getItems(){
        ArrayList<GroundItem> groundItems = new ArrayList<GroundItem>();
        for (GroundItem item : GroundItems.getNearest(getItemIDs())){
            if (item.distanceTo() < 16)
                groundItems.add(item);
        }
        return groundItems;
    }

    private int[] getItemIDs(){
        int[] itemIDs = new int[]{};
        if (Variables.skill_to_train == Skill.ATTACK){
            if (Variables.fighting_bury_bones)
                itemIDs = BuryBones.getBoneIds();
        }
        itemIDs = Methods.combineIntArrays(Variables.fighting_item_ids, itemIDs);
        return itemIDs;
    }
}
