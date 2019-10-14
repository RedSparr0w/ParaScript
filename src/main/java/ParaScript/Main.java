package ParaScript;

import ParaScript.data.Variables;
import ParaScript.strategies.*;
import ParaScript.ui.UI;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "RedSparr0w", category = Category.OTHER, description = "ParaScript", name = "ParaScript", servers = { "2006rebotted" }, version = 1)
public class Main extends Script implements MessageListener, Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private UI ui = new UI();

    @Override
    public boolean onExecute() {
        ui.setVisible(true);
        while (!Variables.running) {
            Time.sleep(300);
        }

        strategies.add(new ScriptState());
        if(Variables.skill_to_train.equalsIgnoreCase("Woodcutting")) {
            strategies.add(new MakeArrowShafts());
            strategies.add(new WoodcutTree());
        }
        if(Variables.skill_to_train.equalsIgnoreCase("Mining")) {
            strategies.add(new Mine());
            strategies.add(new Bank());
            strategies.add(new Walk());
        }
        if(Variables.skill_to_train.equalsIgnoreCase("Bank Runner")) {
            strategies.add(new Bank());
            strategies.add(new Walk());
        }
        strategies.add(new HandleLogin());
        provide(strategies);
        return true;
    }

    @Override
    public void onFinish() {
        ui.dispose();
        System.out.println("Script Stopped");
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;

        Color c2=new Color(0f,.749f,1.0f,.3f );
        g.setColor(c2);
        g.setBackground(c2);
        g.fillRect(355, 232, 160, 20);

        Color c=new Color(.686f,.933f,.933f,.3f );
        g.setColor(c);
        g.setBackground(c);
        g.fillRect(355, 252, 160, 85);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 14));
        g.drawString("AIOWoodcutter", 360, 247);
        g.setFont(new Font("Arial", 1, 11));
        g.drawString("Status: " + Variables.getStatus(), 360, 270);
        g.drawString("Logs(P/H): " + 10 + "(" + 120 + ")", 360, 290);
        g.drawString("EXP(P/H): " + 1000 + "(" + 12685 + ")", 360, 310);
        g.drawString("Runtime: " + "02:22:20", 360, 330);

    }

    public void messageReceived(MessageEvent message) {
        switch (message.getType()) {
            case 0:
                if (message.getMessage().contains("Congratulations, you advanced a woodcutting level.")) {
                    // add in level up to paint
                }
                break;
            case 4:
                if(Variables.skill_to_train.equalsIgnoreCase("Bank Runner")) {
                    if (message.getMessage().startsWith(Variables.slaveMaster.toLowerCase() + " wishes to trade with you")) {
                        // add in level up to paint
                    }
                }
                break;
        }
    }
}