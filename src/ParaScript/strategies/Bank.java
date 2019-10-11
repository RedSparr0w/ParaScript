package ParaScript.strategies;

import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Bank implements Strategy {
    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        for (SceneObject i : SceneObjects.getNearest(100)) {
            i.interact(SceneObjects.Option.MINE);
        }
    }
}