package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Ores;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Mine implements Strategy {
    private SceneObject ore;

    @Override
    public boolean activate() {
        ore = ore(); // set the local Variable
        if (Variables.running
                && ore != null
                && (Variables.getStatus() == "none" || Variables.getStatus() == "mining")
                && Variables.VARROCK_EAST_MINE_ZONE.inTheZone()
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()) {
            Variables.setStatus("mining");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            if (Variables.shouldDropItems()) {
                if (Inventory.getCount(441) >= 1) Inventory.getItem(441).interact(Items.Option.DROP);
            }
            ore.interact(SceneObjects.Option.MINE);
            Time.sleep(1000);
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        } catch (Exception ಠ_ಠ){
            System.out.println("Mining error: ¯\\_(ツ)_/¯");
        }
    }

    private SceneObject ore(){
        int[] ore_to_mine = Variables.mining_ore_selected.getIDs();
        for(SceneObject ore : SceneObjects.getNearest(ore_to_mine)){
            if(Variables.VARROCK_EAST_MINE_ZONE.inTheZoneObject(ore)) {
                return ore;
            }
        }
        return null;
    }
}