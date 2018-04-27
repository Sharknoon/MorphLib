package asdf.morphlib;

/**
 *
 * @author Josua Frank
 */
public class StandardStructureElements {

    public static final StructureElement FOUR;
    public static final StructureElement EIGHT;
    public static final StructureElement DISC;

    static {
        FOUR = new StructureElement(3, 3, 1, 1);
        FOUR.setElement(0, 1, true);
        FOUR.setElement(1, 0, true);
        FOUR.setElement(1, 2, true);
        FOUR.setElement(2, 1, true);
        FOUR.setElement(1, 1, true);

        EIGHT = new StructureElement(3, 3, 1, 1);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                EIGHT.setElement(x, y, true);
            }
        }

        DISC = new StructureElement(5, 5, 2, 2);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                DISC.setElement(x, y, true);
            }
        }
        DISC.setElement(0, 0, false);
        DISC.setElement(0, 4, false);
        DISC.setElement(4, 0, false);
        DISC.setElement(4, 4, false);

    }

}
