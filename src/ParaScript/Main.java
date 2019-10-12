package ParaScript;

import ParaScript.data.Variables;
import ParaScript.strategies.*;
import ParaScript.ui.UI;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;

import java.util.ArrayList;

@ScriptManifest(author = "RedSparr0w", category = Category.OTHER, description = "ParaScript", name = "Script", servers = { "2006rebotted" }, version = 1)
public class Main extends Script{

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    @Override
    public boolean onExecute() {

        UI ui = new UI();
        ui.setVisible(true);
        while (!Variables.running) {
            Time.sleep(300);
        }

        strategies.add(new ScriptState());
        strategies.add(new MakeArrowShafts());
        strategies.add(new WoodcutTree());
        strategies.add(new HandleLogin());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {

    }
}