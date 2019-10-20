package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Npcs {
    MAN_WOMAN("Man & Woman", new int[]{1,2,3,4}, 8); // Man and Woman

    private String name;
    private int[] ids;
    private double xp;

    Npcs(String name, int[] ids, double xp) {
        this.name = name;
        this.ids = ids;
        this.xp = xp;
    }

    public static String[] toStringArray() {
        List<Npcs> enumList = Arrays.asList(Npcs.values());
        List<String> locationsArray = new ArrayList<>();
        for (Npcs npc : enumList) {
            locationsArray.add(npc.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int[] getIDs() { return this.ids; }

    public double getXP() { return this.xp; }
}
