/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bl.Arbeitsschritt;
import bl.Mitarbeiter;
import bl.Projekt;
import java.util.LinkedList;
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
public class DBAccessTest {
    
    public DBAccessTest() {
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
     * Test of main method, of class DBAccess.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DBAccess.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertMitarbeiter method, of class DBAccess.
     */
    @Test
    public void testInsertMitarbeiter() {
        System.out.println("insertMitarbeiter");
        Mitarbeiter m = null;
        DBAccess instance = null;
        instance.insertMitarbeiter(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMitarbeiter method, of class DBAccess.
     */
    @Test
    public void testGetMitarbeiter() {
        System.out.println("getMitarbeiter");
        String passwort = "";
        String nachname = "";
        DBAccess instance = null;
        int expResult = 0;
        int result = instance.getMitarbeiter(passwort, nachname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMitarbeiter method, of class DBAccess.
     */
    @Test
    public void testCheckMitarbeiter() {
        System.out.println("checkMitarbeiter");
        String nachname = "";
        String passwort = "";
        DBAccess instance = null;
        String expResult = "";
        String result = instance.checkMitarbeiter(nachname, passwort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMitarbeiterfromProjekt method, of class DBAccess.
     */
    @Test
    public void testGetMitarbeiterfromProjekt() throws Exception {
        System.out.println("getMitarbeiterfromProjekt");
        int projektid = 0;
        DBAccess instance = null;
        LinkedList<Mitarbeiter> expResult = null;
        LinkedList<Mitarbeiter> result = instance.getMitarbeiterfromProjekt(projektid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertProjekt method, of class DBAccess.
     */
    @Test
    public void testInsertProjekt() {
        System.out.println("insertProjekt");
        Projekt p = null;
        int gründerid = 0;
        DBAccess instance = null;
        instance.insertProjekt(p, gründerid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertArbeitsschritt method, of class DBAccess.
     */
    @Test
    public void testInsertArbeitsschritt() {
        System.out.println("insertArbeitsschritt");
        Projekt p = null;
        Mitarbeiter m = null;
        Arbeitsschritt a = null;
        DBAccess instance = null;
        instance.insertArbeitsschritt(p, m, a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArbeitsschrittId method, of class DBAccess.
     */
    @Test
    public void testGetArbeitsschrittId() {
        System.out.println("getArbeitsschrittId");
        String bezeichnung = "";
        int projektid = 0;
        DBAccess instance = null;
        int expResult = 0;
        int result = instance.getArbeitsschrittId(bezeichnung, projektid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjektId method, of class DBAccess.
     */
    @Test
    public void testGetProjektId() {
        System.out.println("getProjektId");
        String name = "";
        DBAccess instance = null;
        int expResult = 0;
        int result = instance.getProjektId(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjekte method, of class DBAccess.
     */
    @Test
    public void testGetProjekte() {
        System.out.println("getProjekte");
        int id = 0;
        DBAccess instance = null;
        LinkedList<Projekt> expResult = null;
        LinkedList<Projekt> result = instance.getProjekte(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToDoList method, of class DBAccess.
     */
    @Test
    public void testGetToDoList() {
        System.out.println("getToDoList");
        int id = 0;
        DBAccess instance = null;
        LinkedList<Arbeitsschritt> expResult = null;
        LinkedList<Arbeitsschritt> result = instance.getToDoList(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInWorkList method, of class DBAccess.
     */
    @Test
    public void testGetInWorkList() {
        System.out.println("getInWorkList");
        int id = 0;
        DBAccess instance = null;
        LinkedList<Arbeitsschritt> expResult = null;
        LinkedList<Arbeitsschritt> result = instance.getInWorkList(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinishedList method, of class DBAccess.
     */
    @Test
    public void testGetFinishedList() {
        System.out.println("getFinishedList");
        int id = 0;
        DBAccess instance = null;
        LinkedList<Arbeitsschritt> expResult = null;
        LinkedList<Arbeitsschritt> result = instance.getFinishedList(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateArbeitsschritt method, of class DBAccess.
     */
    @Test
    public void testUpdateArbeitsschritt() {
        System.out.println("updateArbeitsschritt");
        int projektid = 0;
        String sf = "";
        int i = 0;
        DBAccess instance = null;
        boolean expResult = false;
        boolean result = instance.updateArbeitsschritt(projektid, sf, i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMitarbeiter method, of class DBAccess.
     */
    @Test
    public void testGetAllMitarbeiter() {
        System.out.println("getAllMitarbeiter");
        DBAccess instance = null;
        LinkedList<Mitarbeiter> expResult = null;
        LinkedList<Mitarbeiter> result = instance.getAllMitarbeiter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
