/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asdf.morphlib;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
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
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        System.out.println("---------------");
        image.invert();
        image.print();
    }

    /**
     * Test of erosion method, of class BinaryImageOperations.
     */
    @Test
    public void testErosion() {
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        System.out.println("---------------");
        image.erosion(StandardStructureElements.FOUR);
        image.print();
    }

    /**
     * Test of dilation method, of class BinaryImageOperations.
     */
    @Test
    public void testDilation() throws URISyntaxException {
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        //image.print();
        image.dilation(StandardStructureElements.FOUR);
        //image.print();
    }

    /**
     * Test of outline method, of class BinaryImageOperations.
     */
    @Test
    public void testOutline() {
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        System.out.println("---------------");
        image.outline();
        image.print();
    }

    /**
     * Test of opening method, of class BinaryImageOperations.
     */
    @Test
    public void testOpening() {
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        System.out.println("---------------");
        image.opening(StandardStructureElements.FOUR);
        image.print();
    }

    /**
     * Test of closing method, of class BinaryImageOperations.
     */
    @Test
    public void testClosing() {
        BinaryImageOperations image = new BinaryImageOperations();
        image.load(Paths.get(System.getProperty("user.home") + "\\Desktop\\test.txt"));
        image.print();
        System.out.println("---------------");
        image.closing(StandardStructureElements.FOUR);
        image.print();
    }

}
