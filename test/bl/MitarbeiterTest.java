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
public class MitarbeiterTest {
    
    public MitarbeiterTest() {
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
     * Test of getId method, of class Mitarbeiter.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Mitarbeiter instance = null;
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVorname method, of class Mitarbeiter.
     */
    @Test
    public void testGetVorname() {
        System.out.println("getVorname");
        Mitarbeiter instance = null;
        String expResult = "";
        String result = instance.getVorname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVorname method, of class Mitarbeiter.
     */
    @Test
    public void testSetVorname() {
        System.out.println("setVorname");
        String vorname = "";
        Mitarbeiter instance = null;
        instance.setVorname(vorname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNachname method, of class Mitarbeiter.
     */
    @Test
    public void testGetNachname() {
        System.out.println("getNachname");
        Mitarbeiter instance = null;
        String expResult = "";
        String result = instance.getNachname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNachname method, of class Mitarbeiter.
     */
    @Test
    public void testSetNachname() {
        System.out.println("setNachname");
        String nachname = "";
        Mitarbeiter instance = null;
        instance.setNachname(nachname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGebdatum method, of class Mitarbeiter.
     */
    @Test
    public void testGetGebdatum() {
        System.out.println("getGebdatum");
        Mitarbeiter instance = null;
        Date expResult = null;
        Date result = instance.getGebdatum();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGebdatum method, of class Mitarbeiter.
     */
    @Test
    public void testSetGebdatum() {
        System.out.println("setGebdatum");
        Date gebdatum = null;
        Mitarbeiter instance = null;
        instance.setGebdatum(gebdatum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswort method, of class Mitarbeiter.
     */
    @Test
    public void testGetPasswort() {
        System.out.println("getPasswort");
        Mitarbeiter instance = null;
        String expResult = "";
        String result = instance.getPasswort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswort method, of class Mitarbeiter.
     */
    @Test
    public void testSetPasswort() {
        System.out.println("setPasswort");
        String passwort = "";
        Mitarbeiter instance = null;
        instance.setPasswort(passwort);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setid method, of class Mitarbeiter.
     */
    @Test
    public void testSetid() {
        System.out.println("setid");
        int i = 0;
        Mitarbeiter instance = null;
        instance.setid(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
