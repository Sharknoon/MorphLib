/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asdf.morphlib;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author frank
 */
public class BinaryImageOperationsTest {

    public BinaryImageOperationsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
     * Test of invert method, of class BinaryImageOperations.
     */
    @Test
    public void testInvert() {
        System.out.println("Invert");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        image.invert();
        image.print();
        System.out.println("---------------");
    }

    /**
     * Test of erosion method, of class BinaryImageOperations.
     */
    @Test
    public void testErosion() {
        System.out.println("Erosion");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        StructureElement elem = new StructureElement(3, 3, 1, 1);
        elem.setElement(1, 0, true);
        elem.setElement(1, 1, true);
        elem.setElement(2, 1, true);
        image.erosion(elem);
        image.print();
        System.out.println("---------------");
    }

    /**
     * Test of dilation method, of class BinaryImageOperations.
     */
    @Test
    public void testDilation() {
        System.out.println("Dilation");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        image.dilation(StandardStructureElements.FOUR);
        image.print();
        System.out.println("---------------");
    }

    /**
     * Test of outline method, of class BinaryImageOperations.
     */
    @Test
    public void testOutline() {
        System.out.println("Outline");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        image.outline();
        image.print();
        System.out.println("---------------");
    }

    /**
     * Test of opening method, of class BinaryImageOperations.
     */
    @Test
    public void testOpening() {
        System.out.println("Opening");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        image.opening(StandardStructureElements.DISC);
        image.print();
        System.out.println("---------------");
    }

    /**
     * Test of closing method, of class BinaryImageOperations.
     */
    @Test
    public void testClosing() {
        System.out.println("Closing");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        image.closing(StandardStructureElements.FOUR);
        image.print();
        System.out.println("---------------");
    }

    @Test
    public void testNotSymmetricalStructureElement() {
        System.out.println("Not symmetrical structure element");
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        StructureElement elem = new StructureElement(3, 3, 1, 1);
        elem.setElement(1, 0, true);
        elem.setElement(1, 1, true);
        elem.setElement(2, 1, true);
        image.erosion(elem);
        image.print();
        System.out.println("---------------");
    }

}
