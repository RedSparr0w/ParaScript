package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class ScriptState implements Strategy {

    @Override
    public boolean activate() {
        if (!Variables.running) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        //Wait for the Player to finish pickpocketing
        Time.sleep(() -> !Variables.running, 1000);
    }
}