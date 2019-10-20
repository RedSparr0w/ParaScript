package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FightingNpcs {
    CHICKEN("Chicken", new int[]{41}, 3),
    MAN_WOMAN("Man & Woman", new int[]{1, 2, 3, 4, 5, 6}, 0),
    COW("Cow", new int[]{81, 397, 1766, 1767}, 0),
    CUSTOM("Custom (specify IDs)", new int[]{-1}, 0);

    private String name;
    private int[] ids;
    private int hp;

    FightingNpcs(String name, int[] ids, int hp) {
        this.name = name;
        this.ids = ids;
        this.hp = hp;
    }

    public static String[] toStringArray() {
        List<FightingNpcs> enumList = Arrays.asList(FightingNpcs.values());
        List<String> npcsArray = new ArrayList<>();
        for (FightingNpcs npc : enumList) {
            npcsArray.add(npc.name);
        }

        String[] simpleArray = new String[ npcsArray.size() ];
        npcsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int[] getIDs() { return this.ids; }

    public void setIDs(int[] ids) {
        if (this == CUSTOM){
            this.ids = ids;
        }
    }

    public double getHP() { return this.hp; }
}
