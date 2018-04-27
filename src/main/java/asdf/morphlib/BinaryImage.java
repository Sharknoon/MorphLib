package asdf.morphlib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Diese Klasse erstellt und verwaltet Binaerbilder
 *
 * @author Josua Frank, Stefan Schmid
 * @param <T> Die Subklasse
 */
public class BinaryImage<T extends BinaryImage> {

    public int width = 0;
    public int height = 0;
    public boolean[][] image = new boolean[0][0];

    /**
     * Parameterloser Konstruktor
     */
    public BinaryImage() {
    }

    /**
     * Konstruktor mit n x m
     *
     * @param width Die Breite
     * @param height Die Hoehe
     */
    public BinaryImage(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Bild muss mindestens 1x1 Pixel gross sein");
        }
        this.width = width;
        this.height = height;
        this.image = new boolean[width][height];
    }

    /**
     * Setzt einen Pixel auf einen gewuenschten Wert (0/1)
     *
     * @param x Die X-Koordinate
     * @param y Die Y-Koordinate
     * @param pixel Der gewuenschte Wert
     */
    public void setPixel(int x, int y, boolean pixel) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Pixel nicht im Bild enthalten");
        }
        image[x][y] = pixel;
    }

    /**
     * Gibt den gewuenschten Pixelwert zurueck
     *
     * @param x Die X-Koordinate
     * @param y Die Y-Koordinate
     * @return Der gewuenschte Pixelwert
     */
    public boolean getPixel(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Pixel nicht im Bild enthalten");
        }
        return image[x][y];
    }

    /**
     * Ueberschreibt dieses Binaerbild mit einem neuen Bild
     *
     * @param image Das neue Bild als zwei-dimensionales ([x][y]) boolean-array
     */
    public void setImage(boolean[][] image) {
        if (image == null) {
            throw new IllegalArgumentException("Das uebergebene Bild ist null");
        }
        this.image = image;
        this.width = image.length;
        this.height = image[0].length;
    }

    /**
     * Gibt das Bild aus
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Dupliziert das Bild und gibt es zurueck
     *
     * @return Das duplizierte Bild
     */
    public T clone() {
        try {
            Class<T> class_ = (Class<T>) this.getClass();
            T clone = class_.getDeclaredConstructor().newInstance();

            int length = image.length;
            boolean[][] target = new boolean[length][image[0].length];
            for (int i = 0; i < length; i++) {
                System.arraycopy(image[i], 0, target[i], 0, image[i].length);
            }
            clone.setImage(target);
            return clone;
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | SecurityException
                | IllegalArgumentException
                | InvocationTargetException ex) {
            throw new IllegalArgumentException("Konnte neue Instanz nicht erstellen");
        }
    }

    /**
     * Speichert das Bild
     *
     * @param path Der Pfad an dem das Bild gespeichert werden soll
     */
    public void save(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Pfad ist nicht spezifiziert");
        }
        try {
            Files.deleteIfExists(path);
            List<String> linesToWrite = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            for (int y = 0; y < height; y++) {
                builder.setLength(0);
                for (int x = 0; x < width; x++) {
                    builder.append(image[x][y] ? 1 : 0);
                }
                linesToWrite.add(builder.toString());
            }
            Files.write(path, linesToWrite, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        } catch (IOException
                | SecurityException
                | UnsupportedOperationException ex) {
            throw new IllegalArgumentException("Pfad ist ungueltig: " + ex);
        }
    }

    /**
     * Laedt eine gewuenschte Datei
     * @param path Die gewuenschte Datei
     */
    public void load(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Pfad ist nicht spezifiziert");
        }
        try {
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Die Datei an dem angegebenen Pfad existiert nicht");
            }
            List<String> lines = Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.toList());
            this.height = lines.size();
            boolean firstRun = true;
            String line;
            for (int y = 0; y < lines.size(); y++) {
                line = lines.get(y);
                if (firstRun) {
                    this.width = line.length();
                    this.image = new boolean[width][height];
                    firstRun = false;
                }
                for (int x = 0; x < line.length(); x++) {
                    char char_ = line.charAt(x);
                    image[x][y] = char_ == '1';
                }
            }
        } catch (IOException | IllegalArgumentException | SecurityException e) {
            throw new IllegalArgumentException("Fehler beim einlesen der Datei " + e);
        }
    }

    /**
     * Gibt das Bild als String zurueck
     * @return Das Bild als String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(image[x][y] ? "1" : "0");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
