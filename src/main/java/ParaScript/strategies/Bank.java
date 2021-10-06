package ParaScript.strategies;

import ParaScript.data.Variables;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;

public class Bank implements Strategy {

    @Override
    public boolean activate() {
        return Variables.running
                && Game.isLoggedIn()
                && Variables.shouldBankItems()
                && (Variables.getStatus() == "none" || Variables.getStatus() == "banking items")
                && Inventory.isFull()
                && openBank();
    }

    @Override
    public void execute() {
        bankAll();
    }

    public static boolean isBankOpen() {
        return Game.getOpenInterfaceId() == 5292;
    }

    public static boolean openBank() {
        if (isBankOpen()) {
            return true;
        }
        Npc banker = Npcs.getClosest(494);
        if (banker != null) {
            banker.interact(Npcs.Option.BANK);
            Time.sleep(() -> isBankOpen(), 10000);
            return true;
        } else {
            System.out.println("Unable to find a banker");
            return false;
        }
    }

    public static int getInventorySlot(int item_id){
        Item item = Inventory.getItem(item_id + 1);
        if (item == null) return -1;
        return item.getSlot();
    }

    public static int getBankSlot(int item_id){
        item_id++;
        int slot = 0;
        for (int bank_item_id : Variables.bank_items){
            if (bank_item_id == item_id) return slot;
            slot++;
        }
        return -1;
    }

    public static void bankAll(){
        if (!openBank()) return;
        Variables.setStatus("Banking items");
        try {
            for (int item_id : Variables.getItemIDs()) {
                int inventory_slot = getInventorySlot(item_id);
                if (inventory_slot >= 0) {
                    Menu.sendAction(431, item_id, inventory_slot, 5064, 3);
                    Time.sleep(50);
                }
                Time.sleep(200);
            }
        } catch (Exception err){
            System.out.println("Banking error: ¯\\_(ツ)_/¯");
        }
    }

    public static void withdrawItem(int item_id, int amount){
        if (!openBank()) return;
        int slot = getBankSlot(item_id);
        if (slot < 0) return;
        switch(amount){
            case 1:
                Menu.sendAction(632, item_id, slot, 5382, 6);
                return;
            case 5:
                Menu.sendAction(78, item_id, slot, 5382, 5);
                return;
            case 10:
                Menu.sendAction(867, item_id, slot, 5382, 4);
                return;
            case -1: // all
                Menu.sendAction(431, item_id, slot, 5382, 3);
                return;
            default: // x
                Menu.sendAction(53, item_id, slot, 5382, 2);
                return;
        }
    }

    public static void depositItem(int item_id, int amount){
        if (!openBank()) return;
        int slot = Bank.getInventorySlot(item_id);
        if (slot < 0) return;
        switch(amount){
            case 1:
                Menu.sendAction(632, item_id, slot, 5064, 6);
                return;
            case 5:
                Menu.sendAction(78, item_id, slot, 5064, 5);
                return;
            case 10:
                Menu.sendAction(867, item_id, slot, 5064, 4);
                return;
            case -1: // all
                Menu.sendAction(431, item_id, slot, 5064, 3);
                return;
            default: // x
                Menu.sendAction(53, item_id, slot, 5064, 2);
                return;
        }
    }
}
