package ParaScript;

import ParaScript.data.Variables;
import ParaScript.strategies.*;
import ParaScript.ui.UI;
import org.parabot.core.ui.Logger;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "RedSparr0w & Dark98", category = Category.OTHER, description = "2006 AIO Script", name = "2006 AIO", servers = { "2006rebotted" }, version = 1.2)
public class Main extends Script implements MessageListener, Paintable {

    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    private UI ui = new UI();
    public static Timer SCRIPT_TIMER;

    @Override
    public boolean onExecute() {
        ui.setVisible(true);
        while (!Variables.running) {
            Time.sleep(300);
        }

        SCRIPT_TIMER = new Timer();

        Variables.setBaseExp();

        strategies.add(new UpdateExperience());
        strategies.add(new ScriptState());
        if(Variables.skill_to_train == Skill.WOODCUTTING) {
            strategies.add(new MakeArrowShafts());
            strategies.add(new WoodcutTree());
        }
        if(Variables.skill_to_train == Skill.MINING) {
            strategies.add(new Mine());
            strategies.add(new Bank());
            strategies.add(new Walk());
        }
        if(Variables.skill_to_train == Skill.THIEVING) {
            strategies.add(new Thieving());
        }
        if(Variables.skill_to_train == Skill.ATTACK) {
            // Activate auto retaliate
            org.rev317.min.api.methods.Menu.clickButton(150);
            strategies.add(new PickupItems());
            strategies.add(new BuryBones());
            strategies.add(new Fighting());
        }
        if(Variables.skill_to_train == Skill.FISHING) {
            strategies.add(new Fish());
        }
        if(Variables.skill_to_train == null) {
            strategies.add(new Bank());
            strategies.add(new Walk());
            strategies.add(new PickupItems());
        }
        strategies.add(new HandleLogin());
        provide(strategies);

        Keyboard.getInstance().sendKeys("Training " + Variables.skill_to_train.getName() + ". Drop items? " + !Variables.shouldBankItems());

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
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("2006 AIO", 360, 247);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.drawString("Status: " + Variables.getStatus(), 360, 270);
        if (SCRIPT_TIMER == null) return;
        g.drawString("Items(P/H): " + Methods.formatNumber(Variables.items_gained) + "(" + Methods.formatNumber(SCRIPT_TIMER.getPerHour(Variables.items_gained)) + ")", 360, 290);
        g.drawString("EXP(P/H): " + Methods.formatNumber((int) Variables.exp_gained) + "(" + Methods.formatNumber(SCRIPT_TIMER.getPerHour((int) Variables.exp_gained)) + ")", 360, 310);
        g.drawString("Runtime: " + SCRIPT_TIMER.toString(), 360, 330);

    }

    public void messageReceived(MessageEvent message) {
        switch (message.getType()) {
            case 0:
                if (message.getMessage().startsWith("You manage to ") || // woodcutting, mining
                    message.getMessage().startsWith("You catch some") || // fishing
                    message.getMessage().startsWith("You pick the ")) {  // pickpockets
                        Variables.addItemGained(1);
                        Variables.updateExpGained();
                }
                if (message.getMessage().startsWith("Congratulations, you advanced a")) {
                    // add in level up to paint
                }
                break;
            case 4:
                if(Variables.skill_to_train == null) {
                    if (message.getMessage().startsWith(Variables.slaveMaster.toLowerCase() + " wishes to trade with you")) {
                        // accept trade
                        // take items, give items if smithing or similar
                        // goto bank, deposit/withdraw items
                        // go back to user
                    }
                }
                break;
        }
    }
}
