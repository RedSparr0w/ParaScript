package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.Date;

public class LoadCannon implements Strategy {
    private SceneObject cannon;
    private long nextFill = System.currentTimeMillis() + 20000;
    private long lastFilled = System.currentTimeMillis();

    @Override
    public boolean activate() {
        cannon = getCannon();
        if (Variables.running
                && Variables.load_cannon
                && cannon != null
                && nextFill <= System.currentTimeMillis()
        ) {
            Variables.setStatus("filling cannon");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        cannon.interact(SceneObjects.Option.SECOND);
        int Timeout = (int) Math.max(500, Math.min(5e3, cannon.distanceTo() * 500));
        Time.sleep(Timeout);
        cannon.interact(SceneObjects.Option.FIRST);
        nextFill = System.currentTimeMillis() + 20000;
    }

    private SceneObject getCannon(){
        for(SceneObject _cannon : SceneObjects.getNearest(6)){
            if (_cannon.distanceTo() <= 10)
                return _cannon;
        }
        return null;
    }
}
