package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Ores;
import ParaScript.data.variables.Trees;
import com.sun.deploy.util.ArrayUtil;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
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
            ore.interact(SceneObjects.Option.MINE);
            Time.sleep(1000);
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 3000);
        } catch (Exception err){
            System.out.println("Mining error: ¯\\_(ツ)_/¯");
        }
    }

    private SceneObject ore(){
        for(SceneObject ore : SceneObjects.getNearest(Ores.COPPER_TIN.getIDs())){
            if(Variables.VARROCK_EAST_MINE_ZONE.inTheZoneObject(ore)) {
                return ore;
            }
        }
        return null;
    }
}