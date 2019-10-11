package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Mine implements Strategy {
    @Override
    public boolean activate() {
        for (SceneObject i : SceneObjects.getNearest(100)) {
            if (Variables.running
                    && i !=null
                    && i.distanceTo() <= 10
                    && !Players.getMyPlayer().isInCombat()
                    && Players.getMyPlayer().getAnimation() == -1
                    && !Inventory.isFull()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        for (SceneObject i : SceneObjects.getNearest(100)) {
            i.interact(SceneObjects.Option.MINE);
        }
    }
}