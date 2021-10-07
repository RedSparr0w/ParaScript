package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

public class Drop implements Strategy {
    private Item[] items;

    @Override
    public boolean activate() {
        items = Inventory.getItems(inventoryItemIDs(Variables.getItemIDs()));
        return Variables.running
                && Game.isLoggedIn()
                && Variables.shouldDropItems()
                && items != null
                && items.length > 0;
    }

    @Override
    public void execute() {
        for(Item item : items){
            if(item != null){
                item.drop();
                Time.sleep(1000);
            }
        }
    }

    // Return the item id + 1 (odd way inventory items are handled)
    public int[] inventoryItemIDs(int[] itemIDs) {
        int[] items = new int[itemIDs.length];
        for(int i = 0; i < itemIDs.length; i++) {
            items[i] = itemIDs[i] + 1;
        }
        return items;
    }
}