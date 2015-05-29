/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Domi
 */
public class ProjektTest {
    
    public ProjektTest() {
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
     * Test of getProjektid method, of class Projekt.
     */
    @Test
    public void testGetProjektid() {
        System.out.println("getProjektid");
        Projekt instance = null;
        int expResult = 0;
        int result = instance.getProjektid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProjektid method, of class Projekt.
     */
    @Test
    public void testSetProjektid() {
        System.out.println("setProjektid");
        int projektid = 0;
        Projekt instance = null;
        instance.setProjektid(projektid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Projekt.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Projekt instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Projekt.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Projekt instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnfangsdatum method, of class Projekt.
     */
    @Test
    public void testGetAnfangsdatum() {
        System.out.println("getAnfangsdatum");
        Projekt instance = null;
        Date expResult = null;
        Date result = instance.getAnfangsdatum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnfangsdatum method, of class Projekt.
     */
    @Test
    public void testSetAnfangsdatum() {
        System.out.println("setAnfangsdatum");
        Date anfangsdatum = null;
        Projekt instance = null;
        instance.setAnfangsdatum(anfangsdatum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnddatum method, of class Projekt.
     */
    @Test
    public void testGetEnddatum() {
        System.out.println("getEnddatum");
        Projekt instance = null;
        Date expResult = null;
        Date result = instance.getEnddatum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnddatum method, of class Projekt.
     */
    @Test
    public void testSetEnddatum() {
        System.out.println("setEnddatum");
        Date enddatum = null;
        Projekt instance = null;
        instance.setEnddatum(enddatum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
