package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;

public class UpdateExperience implements Strategy {

    @Override
    public boolean activate() {
        if (++Variables.update_experience_tick >= 2000) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        Variables.updateExpGained();
        Variables.update_experience_tick = 0;
        Time.sleep(500);
    }
}