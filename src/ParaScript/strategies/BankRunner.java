package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Ores;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class BankRunner implements Strategy {
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
            ore.interact(SceneObjects.Option.MINE);
            Time.sleep(1000);
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        } catch (Exception err){
            System.out.println("Mining error: ¯\\_(ツ)_/¯");
        }
    }

    private SceneObject ore(){
        int[] ore_to_mine = Ores.COPPER_TIN.getIDs();
        if (Inventory.getCount(437) >= 14)
            ore_to_mine = Ores.TIN.getIDs();
        if (Inventory.getCount(439) >= 14)
            ore_to_mine = Ores.COPPER.getIDs();
        for(SceneObject ore : SceneObjects.getNearest(ore_to_mine)){
            if(Variables.VARROCK_EAST_MINE_ZONE.inTheZoneObject(ore)) {
                return ore;
            }
        }
        return null;
    }
}