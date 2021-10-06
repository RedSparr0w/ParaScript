package ParaScript.strategies;

import ParaScript.data.Variables;
import ParaScript.data.variables.Trees;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

public class WoodcutTree implements Strategy {
    private SceneObject tree;

    @Override
    public boolean activate() {
        tree = tree(); // set the local Variable
        if (Variables.running
                && tree != null
                && (Variables.getStatus() == "none" || Variables.getStatus() == "woodcutting")
                && !Players.getMyPlayer().isInCombat()
                && Players.getMyPlayer().getAnimation() == -1
                && !Inventory.isFull()
            ) {
            Variables.setStatus("woodcutting");
            return true;
        }
        Variables.setStatus("none");
        return false;
    }

    @Override
    public void execute() {
        try {
            Trees myTree = Variables.woodcutting_tree_selected;
            if (myTree.hash == 0) {
                myTree.hash = tree.getHash();
                myTree.x = tree.getLocalRegionX();
                myTree.y = tree.getLocalRegionY();
            }
            // 502, rock_hash, local_x, local_y, 4
            Menu.sendAction(502, myTree.hash, myTree.x, myTree.y, 3);
            // Wait 1.5 seconds for the player to reach the tree
            Time.sleep(1500);
            // Sleep until player is cutting the tree for a maximum of 2 seconds
            Time.sleep(() -> Players.getMyPlayer().getAnimation() != -1, 2000);
            // Sleep until not woodcutting for a maximum of 10 seconds
            Time.sleep(() -> Players.getMyPlayer().getAnimation() == -1, 10000);
        } catch (Exception err){
            System.out.println("Woodcutting error: ¯\\_(ツ)_/¯");
        }
    }

    private SceneObject tree(){
        int[] tree_to_cut = Variables.woodcutting_tree_selected.getIDs();
        for(SceneObject tree : SceneObjects.getNearest(tree_to_cut)){
            if(tree != null){
                return tree;
            }
        }
        return null;
    }
}