package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Trees {
    NORMAL("Normal", new int[]{1276, 1277, 1278, 1279, 1280}, 1512),
    OAK("Oak", new int[]{1281}, 1522),
    WILLOW ("Willow", new int[]{1308, 5551, 5552, 5553}, 1520),
    MAPLE("Maple", new int[]{1307}, 1518),
    YEW("Yew", new int[]{1309}, 1516),
    MAGIC("Magic", new int[]{1306}, 1514);

    private String name;
    private int[] ids;
    private double xp;
    private int item_id;
    public int hash = 0;
    public int x = 0;
    public int y = 0;

    Trees(String name, int[] ids, int item_id) {
        this.name = name;
        this.ids = ids;
        this.item_id = item_id;
    }

    public static String[] toStringArray() {
        List<Trees> enumList = Arrays.asList(Trees.values());
        List<String> locationsArray = new ArrayList<>();
        for (Trees obj : enumList) {
            locationsArray.add(obj.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int[] getIDs() { return this.ids; }

    public int getItemID() { return this.item_id; }

    public void reset(){
        this.hash = 0;
        this.x = 0;
        this.y = 0;
    }
}
