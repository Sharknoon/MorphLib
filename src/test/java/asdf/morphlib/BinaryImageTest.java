/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asdf.morphlib;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author frank
 */
public class BinaryImageTest {

    public BinaryImageTest() {
    }

    static BinaryImage im;

    @BeforeClass
    public static void setUpClass() {
        im = new BinaryImage(3, 3);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setPixel method, of class BinaryImage.
     */
    @Test
    public void testSetPixel() {
        assertFalse(im.getPixel(0, 0));
        im.setPixel(0, 0, true);
        assertTrue(im.getPixel(0, 0));
        im.setPixel(0, 0, false);
        assertFalse(im.getPixel(0, 0));
    }

    /**
     * Test of setImage method, of class BinaryImage.
     */
    @Test
    public void testSetImage() {
        boolean[][] emptyImage = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
        };
        boolean[][] newImage = {
            {false, true, false},
            {true, true, true},
            {false, true, false}
        };
        im.setImage(newImage);
        assertTrue(im.getPixel(1, 1));
        im.setImage(emptyImage);
        assertFalse(im.getPixel(1, 1));
    }

    /**
     * Test of clone method, of class BinaryImage.
     */
    @Test
    public void testClone() {
        BinaryImage clone = im.clone();
        im.setPixel(1, 1, true);
        assertTrue(im.getPixel(1, 1));
        assertFalse(clone.getPixel(0, 0));
        im.setPixel(1, 1, false);
        assertFalse(im.getPixel(1, 1));
    }

    /**
     * Test of save method, of class BinaryImage.
     */
    @Test
    public void testSave() throws IOException {
        Path path = Files.createTempFile("binaryimage", "asdf");
        im.save(path);
        BinaryImage image = new BinaryImage();
        image.load(path);
        assertEquals(im.toString(), image.toString());
    }


    /**
     * Test of toString method, of class BinaryImage.
     */
    @Test
    public void testToString() {
        String erg = "000\n000\n000\n";
        assertEquals(erg, im.toString());
    }

}
