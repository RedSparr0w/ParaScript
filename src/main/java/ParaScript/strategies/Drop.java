package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.TilePath;

public class Drop implements Strategy {
    private Item[] items;

    @Override
    public boolean activate() {
        items = Inventory.getItems(Variables.getItemIDs());
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
}