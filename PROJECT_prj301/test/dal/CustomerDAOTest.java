/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.List;
import model.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CustomerDAOTest {
    CustomerDAO instance;
    public CustomerDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new CustomerDAO();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAll() {
        int expResult = 15;
        List<Customer> result = instance.getAll();
        assertEquals(expResult, result.size());
    }

    @Test
    public void testGetAccount_String_String() {
        String username = "anhptv";
        String password = "12345";
        String expResult = "anhptv";
        Customer result = instance.getAccount(username, password);
        assertEquals(expResult, result.getName());
    }
    
    @Test
    public void testGetAccount_String_String_WrongPass() {
        String username = "anhptv";
        String password = "1234567";
        Customer expResult = null;
        Customer result = instance.getAccount(username, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAccount_String_String_NullUsername() {
        String username = "";
        String password = "1234567";
        Customer expResult = null;
        Customer result = instance.getAccount(username, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAccount_String() {
        String username = "anhptv";
        Customer expResult = null;
        Customer result = instance.getAccount(username);
        assertNotEquals(expResult, result);
    }
        @Test
    public void testGetAccount_NullString() {
        String username = null;
        Customer expResult = null;
        Customer result = instance.getAccount(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreate() {
        Customer a = new Customer("Vanhhhh", "12");
        int expResult = 1;
        int result = instance.create(a);
        assertEquals(expResult, result);
    }
       @Test
    public void testCreateWithEmptyStrings() {
        Customer a = new Customer("", "");
        int expResult = 0;
        int result = instance.create(a);
        assertEquals(expResult, result);
    }
    @Test
    public void testCreateInvalid() {
        System.out.println("create");
        Customer a = new Customer("anhptv", "12");
        int expResult = -1;
        int result = instance.create(a);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate() {
        Customer s = new Customer("anhptv", "12345", "Hải Dương", "TP HD", "P. Tan Binh", "02348449", "anh@fpt.edu.cn", true, "sđcnk.jpg");
        int expResult = 1;
        int result = instance.update(s);
        assertEquals(expResult, result);
    }
    @Test
    public void testUpdateInvalid() {
        Customer s = new Customer("", "");
        int expResult = 0;
        int result = instance.update(s);
        assertEquals(expResult, result);
    }
}
