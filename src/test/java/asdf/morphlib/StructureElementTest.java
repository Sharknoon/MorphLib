/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asdf.morphlib;

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
public class StructureElementTest {

    public StructureElementTest() {
    }

    static StructureElement elem;

    @BeforeClass
    public static void setUpClass() {
        elem = StandardStructureElements.FOUR;
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
     * Test of setElement method, of class StructureElement.
     */
    @Test
    public void testSetElement() {
        assertFalse(elem.getElement(0, 0));
        elem.setElement(0, 0, true);
        assertTrue(elem.getElement(0, 0));
        elem.setElement(0, 0, false);
        assertFalse(elem.getElement(0, 0));
    }

    /**
     * Test of setHotSpot method, of class StructureElement.
     */
    @Test
    public void testSetHotSpot() {
        assertArrayEquals(new int[]{1, 1}, elem.getHotSpot());
        elem.setHotSpot(2, 2);
        assertArrayEquals(new int[]{2, 2}, elem.getHotSpot());
        elem.setHotSpot(1, 1);
        assertArrayEquals(new int[]{1, 1}, elem.getHotSpot());
    }

    /**
     * Test of reflect method, of class StructureElement.
     */
    @Test
    public void testReflect() {
        boolean[][] currentStructure = elem.getStructureElement();
        elem.reflect();
        assertArrayEquals(currentStructure, elem.getStructureElement());
        elem.setElement(0, 0, true);
        elem.reflect();
        assertTrue(elem.getElement(2, 2));
        assertFalse(elem.getElement(0, 0));
        elem.setElement(2, 2, false);
        assertFalse(elem.getElement(2, 2));
    }

}
