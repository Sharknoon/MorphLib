package asdf.morphlib;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Josua Frank
 */
public class BinaryImage {

    public int width = 0;
    public int height = 0;
    public boolean[][] image = new boolean[0][0];

    public BinaryImage() {
    }

    public BinaryImage(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Bild muss mindestens 1x1 Pixel gross sein");
        }
        this.width = width;
        this.height = height;
        this.image = new boolean[width][height];
    }

    public void setPixel(int x, int y, boolean pixel) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Pixel nicht im Bild enthalten");
        }
        image[x][y] = pixel;
    }

    public boolean getPixel(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IllegalArgumentException("Pixel nicht im Bild enthalten");
        }
        return image[x][y];
    }

    public void setImage(boolean[][] image) {
        if (image == null) {
            throw new IllegalArgumentException("Das uebergebene Bild ist null");
        }
        this.image = image;
        this.width = image.length;
        this.height = image[0].length;
    }

    public void print() {
        for (boolean[] row : this.image) {
            for (boolean pixel : row) {
                System.out.print(pixel ? "■" : "□");
            }
            System.out.println();
        }
    }

    @Override
    public BinaryImage clone() throws CloneNotSupportedException {
        super.clone();
        BinaryImage clone = new BinaryImage();
        int length = image.length;
        boolean[][] target = new boolean[length][image[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(image[i], 0, target[i], 0, image[i].length);
        }
        clone.setImage(target);
        return clone;
    }

    public void save(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Pfad ist nicht spezifiziert");
        }
        try {
            Files.deleteIfExists(path);
            List<String> linesToWrite = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            for (boolean[] line : image) {
                builder.setLength(0);
                for (boolean pixel : line) {
                    builder.append(pixel ? 1 : 0);
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

}
