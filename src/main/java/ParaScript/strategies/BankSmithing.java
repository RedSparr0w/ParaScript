package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;

// TODO: this needs fixing up, not currently working AFAIK

public class BankSmithing implements Strategy {

    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "banking items")
                && hasBars() || !hasOres();
    }

    @Override
    public void execute() {
        depositItems();
    }

    public void depositItems() {
        if (Variables.skill_to_train == Skill.SMITHING) {
            // Bank all items from first slot
            Variables.setStatus("banking items");
            // 431, item_id, inventory_position?, 5064, 3
            int bar_id = Variables.smithing_bar_selected.getID();
            if (hasBars())
                Menu.sendAction(431,  bar_id - 1, Inventory.getItem(bar_id).getSlot(), 5064, 3);
            Time.sleep(1000);
            Variables.setStatus("withdrawing items");
            // Withdraw items
            // 1 Item:
            // 632, item_id, bank_slot, 5382, 6
            // 10 Items:
            // 867, item_id, bank_slot, 5382, 4
            // All items:
            // 431, item_id, bank_slot, 5382, 3
            int[] ores = Variables.smithing_bar_selected.getOres();
            for (int i = 0; i < Math.floor((28 - Inventory.getCount()) / ores.length); i++)
                for (int ore : ores)
                    Menu.sendAction(632, (ore - 1), getBankSlot(ore), 5382, 6);
            /*
            if (Variables.smithing_bar_selected == Bars.BRONZE) {
                Menu.sendAction(867, (Bars.BRONZE.getOres()[0] - 1), 0, 5382, 4); // 10
                Menu.sendAction(867, (Bars.BRONZE.getOres()[1] - 1), 1, 5382, 4); // 10
            }
            if (Variables.smithing_bar_selected == Bars.IRON)
                Menu.sendAction(867, 440, 2, 5382, 4);
            if (Variables.smithing_bar_selected == Bars.STEEL) {
                for (int i = 0; i < 8; i++) Menu.sendAction(867, 440, 2, 5382, 4);
                for (int i = 0; i < 8; i++) Menu.sendAction(867, 440, 2, 5382, 4); // 8 iron
            }
            */
            //org.rev317.min.api.methods.Bank.withdraw(441, 0, 100);
            Variables.setStatus("smelting");
            Time.sleep(1000);
            return;
        }
        Menu.clickButton(6224);
        Keyboard.getInstance().sendKeys("::bank", true);
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
            Variables.setStatus("smelting");
        }
    }

    private boolean hasBars(){
        boolean hasBars = Inventory.getItem(Variables.smithing_bar_selected.getID()) != null;
        //int inv_spot = Inventory.getItem(123).getSlot();
        //int bank_spot = Bank.getItem(123).getSlot();
        return hasBars;
    }

    private boolean hasOres(){
        int[] ores = Variables.smithing_bar_selected.getOres();
        for (int ore : ores)
            if (Inventory.getItem(ore) == null)
                return false;
        return true;
    }

    private int getBankSlot(int ore){
        switch(ore){
            case 437: return 0;
            case 439: return 1;
            case 443: return 2;
            case 445: return 3;
            case 441: return 4;
            case 454: return 5;
            case 448: return 6;
            case 450: return 7;
            case 452: return 8;
            default: return -1;
        }
    }
}