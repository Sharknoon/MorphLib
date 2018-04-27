package asdf.morphlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Morphologische Operationen fuer Binaerbilder
 * @author Josua Frank, Stefan Schmid
 */
public class BinaryImageOperations extends BinaryImage<BinaryImageOperations> {

    /**
     * Invertiert dieses Bild
     */
    public void invert() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image[x][y] = !image[x][y];
            }
        }
    }

    /**
     * Fuehrt eine Erosion durch
     * @param element Das Strukturelement {@link StandardStructureElements}
     */
    public void erosion(StructureElement element) {
        //Algorithmus funktioniert nicht, die Pixel am Rand werden teilweise 
        //falsch interpretiert
        //invert();
        //element.reflect();
        //dilation(element);
        //invert();
        int[] hotSpot = element.getHotSpot();
        boolean hotSpotElement = element.getElement(hotSpot[0], hotSpot[1]);
        //Eine Liste von Koordinaten aller Elementen des Strukutelementes mit Wert 1,
        //damit nicht bei jedem Durchgang das Strukturelement auf Elemente ueberprueft werden muss
        List<int[]> elements = new ArrayList<>();
        for (int x = 0; x < element.width; x++) {
            for (int y = 0; y < element.height; y++) {
                if (element.structure[x][y]) {
                    elements.add(new int[]{x, y});
                }
            }
        }
        boolean[][] newImage = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Ueberpruefung für jeden Punkt, ob Erosion angewendet wird
                if (hotSpotElement != image[x][y]) {
                    continue;
                }
                boolean counter = true;
                for (int[] el : elements) {
                    //Relative Koordinaten zu x und y
                    int relX = el[0] - hotSpot[0];
                    int relY = el[1] - hotSpot[1];
                    int absX = x + relX;
                    int absY = y + relY;
                    //Wenn einer der Elemente des Strukturelementes ausserhalb des 
                    //Bildes liegt, wird sofort abgebrochen
                    if (absX < 0 || absX >= width || absY < 0 || absY >= height) {
                        counter = false;
                        break;
                    }
                    if (!image[absX][absY]) {
                        counter = false;
                        break;
                    }
                }
                //Nur wenn alle Elemente des Strukturelementes relativ zum Hot 
                //Spot auch im Bild vorkommen, wird der Hot Spot Pixel auf true 
                //gesetzt
                if (counter) {
                    newImage[x][y] = true;
                }
            }
        }
        image = newImage;
    }

    /**
     * Fuehrt eine Dilation durch
     * @param element Das Strukturelement {@link StandardStructureElements}
     */
    public void dilation(StructureElement element) {
        int[] hotSpot = element.getHotSpot();
        boolean hotSpotElement = element.getElement(hotSpot[0], hotSpot[1]);
        //Eine Liste von Koordinaten aller Elementen des Strukutelementes mit Wert 1,
        //damit nicht bei jedem Durchgang das Strukturelement auf Elemente ueberprueft werden muss
        List<int[]> elements = new ArrayList<>();
        for (int x = 0; x < element.width; x++) {
            for (int y = 0; y < element.height; y++) {
                if (element.structure[x][y]) {
                    elements.add(new int[]{x, y});
                }
            }
        }
        boolean[][] newImage = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Ueberpruefung für jeden Punkt, ob Dilation angewendet wird
                if (hotSpotElement != image[x][y]) {
                    continue;
                }
                for (int[] el : elements) {
                    //Relative Koordinaten zu x und y
                    int relX = el[0] - hotSpot[0];
                    int relY = el[1] - hotSpot[1];
                    int absX = x + relX;
                    int absY = y + relY;
                    if (absX < 0 || absX >= width || absY < 0 || absY >= height) {
                        continue;
                    }
                    newImage[absX][absY] = true;
                }
            }
        }
        image = newImage;
    }

    /**
     * Gibt die Kanten aus
     */
    public void outline() {
        BinaryImageOperations clone = clone();
        clone.erosion(StandardStructureElements.FOUR);
        clone.invert();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!(image[x][y] && clone.image[x][y])) {
                    image[x][y] = false;
                }
            }
        }
    }

    /**
     * Fuehrt ein Opening durch
     * @param element Das Strukturelement {@link StandardStructureElements}
     */
    public void opening(StructureElement element) {
        erosion(element);
        dilation(element);
    }

    /**
     * Fuehrt ein Closing durch
     * @param element Das Strukturelement {@link StandardStructureElements}
     */
    public void closing(StructureElement element) {
        dilation(element);
        erosion(element);
    }
    
}
