package asdf.morphlib;

/**
 *
 * @author Josua Frank
 */
public class StructureElement {

    public boolean[][] structure = new boolean[0][0];
    public int width = 0;
    public int height = 0;
    public int hotSpotX = 0;
    public int hotSpotY = 0;

    public StructureElement() {
    }

    public StructureElement(int width, int height, int hotSpotX, int hotSpotY) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Bild muss mindestens 1x1 Pixel gross sein");
        }
        this.width = width;
        this.height = height;
        this.structure = new boolean[width][height];
        this.hotSpotX = hotSpotX;
        this.hotSpotY = hotSpotY;
    }

    public void setElement(int x, int y, boolean element) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Element ausserhalb des Strukturelementes");
        }
        structure[x][y] = element;
    }

    public boolean getElement(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Element ausserhalb des Strukturelements");
        }
        return structure[x][y];
    }

    public void setHotSpot(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Hot Spot ausserhalb des Strukturelements");
        }
        hotSpotX = x;
        hotSpotY = y;
    }

    public int[] getHotSpot() {
        return new int[]{hotSpotX, hotSpotY};
    }

    public boolean[][] getStructureElement() {
        return structure;
    }
    
}
