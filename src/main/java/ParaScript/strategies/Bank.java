package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.TilePath;
import org.rev317.min.api.wrappers.Item;

public class Bank implements Strategy {

    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && Variables.shouldBankItems()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "banking items")
                && Inventory.isFull();
    }

    @Override
    public void execute() {
        Variables.pathToWalk = new TilePath(Variables.VARROCK_EAST_MINE_PATH_TO_BANK);
        Variables.setStatus("banking items");
        while (Variables.pathToWalk != null && !Variables.pathToWalk.hasReached()) {
            if (!Game.isLoggedIn()) new HandleLogin().execute();
            Variables.pathToWalk.traverse();
            Time.sleep(2500);
        }
        depositItems();
    }

    public void depositItems() {
        Npc banker = Npcs.getClosest(494);
        if (banker != null)
        {
            banker.interact(Npcs.Option.BANK);
        } else {
            Keyboard.getInstance().sendKeys("I can't find the banker!");
        }
        Time.sleep(3000);
        if (Game.getOpenInterfaceId() == 5292) {
            if (Variables.skill_to_train == Skill.MINING)
                org.rev317.min.api.methods.Bank.depositAllExcept(1266, 1268, 1270, 1272, 1274, 1276);
            else if (Variables.skill_to_train == Skill.WOODCUTTING)
                org.rev317.min.api.methods.Bank.depositAllExcept(1350, 1352, 1354, 1356, 1358, 1360, 6740);
            else {
                org.rev317.min.api.methods.Bank.depositAllExcept(1);
                Variables.addItemGained(28);
            }
            Variables.setStatus("none");
        }
    }
}
