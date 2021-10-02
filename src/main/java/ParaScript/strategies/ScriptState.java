package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;

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