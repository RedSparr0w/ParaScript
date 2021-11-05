package ParaScript.data.variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Bones {
    BONES("Bones", 526),
    BURNT_BONES("Burnt bones", 528),
    BAT_BONES("Bat bones", 530),
    BIG_BONES("Big bones", 532),
    BABY_DARGON_BONES("Baby dragon bones", 534),
    DRAGON_BONES("Dragon bones", 536);

    private String name;
    private int id;

    Bones(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String[] toStringArray() {
        List<Bones> enumList = Arrays.asList(Bones.values());
        List<String> locationsArray = new ArrayList<>();
        for (Bones obj : enumList) {
            locationsArray.add(obj.name);
        }

        String[] simpleArray = new String[ locationsArray.size() ];
        locationsArray.toArray( simpleArray );
        return(simpleArray);
    }

    public String getName() { return this.name; }

    public int getID() { return this.id; }
}
