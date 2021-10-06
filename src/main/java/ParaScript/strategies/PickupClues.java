package ParaScript.strategies;

import ParaScript.Methods;
import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.GroundItem;

import java.util.ArrayList;

public class PickupClues implements Strategy {
    ArrayList<GroundItem> items;

    @Override
    public boolean activate() {
        items = getItems();
        if (Variables.running
                && items.size() > 0
                && !Inventory.isFull()
        ) {
            Variables.setStatus("picking up cluescrolls");
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
                int timeout = Math.min(10000, item.distanceTo() * 1000);
                Time.sleep(() -> item.distanceTo() < 1, timeout);
                if (Inventory.isFull()){
                    break;
                }
            }
        } catch (Exception ಠ_ಠ){
            System.out.println("Pickup clue scrolls error: ¯\\_(ツ)_/¯");
        }
        Variables.addItemGained(Inventory.getCount(true) - currentItemAmount);
    }

    private ArrayList<GroundItem> getItems(){
        ArrayList<GroundItem> groundItems = new ArrayList<GroundItem>();
        for (GroundItem item : GroundItems.getNearest(2677, 2678, 2679)){
            if (item.distanceTo() < 12)
                groundItems.add(item);
        }
        return groundItems;
    }
}
