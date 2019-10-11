package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Trees {
    NORMAL("normal", new int[]{1276, 1278}),
    OAK("oak", new int[]{1281}),
    WILLOW ("willow", new int[]{5551, 1308, 5553, 5552}),
    MAPLE("maple", new int[]{1307});

    private String name;
    private int[] trees;

    Trees(String name, int[] trees) {
        this.name = name;
        this.trees = trees;
    }

    public static String[] toStringArray() {
        List<Trees> enumList = Arrays.asList(Trees.values());
        List<String> locationsArray = new ArrayList<>();
        for (Trees tree : enumList) {
            locationsArray.add(tree.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public int[] getIDs() {
        return this.trees;
    }
}
