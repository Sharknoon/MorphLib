package asdf.morphlib;

/**
 * Definiert und verwaltet Strukturelemente
 * @author Josua Frank, Stefan Schmid
 */
public class StructureElement {

    public boolean[][] structure = new boolean[0][0];
    public int width = 0;
    public int height = 0;
    public int hotSpotX = 0;
    public int hotSpotY = 0;

    /**
     * Parameterloser Kontruktor
     */
    public StructureElement() {
    }

    /**
     * Erzeugt ein neues Strukturelement
     * @param width Dessen Breite
     * @param height Dessen Hoehe
     * @param hotSpotX Die X-Koordinate des Hot Spots
     * @param hotSpotY Die Y-Koordinate des Hot Spots
     */
    public StructureElement(int width, int height, int hotSpotX, int hotSpotY) {
        if (width <= 0 || height <= 0 || hotSpotX < 0 || hotSpotX >= width || hotSpotY < 0 || hotSpotY >= height) {
            throw new IllegalArgumentException("Bild muss mindestens 1x1 Pixel gross sein und der Hot Spot muss im Bild liegen");
        }
        this.width = width;
        this.height = height;
        this.structure = new boolean[width][height];
        this.hotSpotX = hotSpotX;
        this.hotSpotY = hotSpotY;
    }

    /**
     * Setzt ein Element in diesem Strukturelement auf den gewuenschten Wert
     * @param x Dessen X-Koordinate
     * @param y Dessen Y-Koordinate
     * @param element Dessen gewuenschter Wert
     */
    public void setElement(int x, int y, boolean element) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Element ausserhalb des Strukturelementes");
        }
        structure[x][y] = element;
    }

    /**
     * Gibt einen gewuenschten Elementwert zurueck
     * @param x Dessen X-Koordinate
     * @param y Dessen Y-Koordinate
     * @return Der gewuenschte Elementwert
     */
    public boolean getElement(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Element ausserhalb des Strukturelements");
        }
        return structure[x][y];
    }

    /**
     * Setzt den Hot Spot
     * @param x Dessen X-Koordinate
     * @param y Dessen Y-Koordinate
     */
    public void setHotSpot(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Hot Spot ausserhalb des Strukturelements");
        }
        hotSpotX = x;
        hotSpotY = y;
    }

    /**
     * Gibt den Hot Spot zurueck
     * @return Der geforderte Hot Spot
     */
    public int[] getHotSpot() {
        return new int[]{hotSpotX, hotSpotY};
    }

    /**
     * Gibt die Matrix des Strukturelementes zurueck
     * @return Dessen Matrix
     */
    public boolean[][] getStructureElement() {
        return structure;
    }

    /**
     * Fuehrt eine Punktspiegelung am Hot Spot durch
     * Obsolet, da Erosion nicht ueber Dilation implementiert wird
     */
    public void reflect() {
        if (width < 1 || height < 1) {
            return;
        }
        boolean[][] newStructure = new boolean[width][height];
        for (int y = 0; y < height; y++) {
            boolean[] line = structure[y];
            for (int x = 0; x < width; x++) {
                boolean element = line[x];
                newStructure[width - 1 - x][height - 1 - y] = element;
            }
        }
        structure = newStructure;
    }

}
