/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Customer;
import model.Item;
import model.Order;
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
public class OrderDAOTest {
            OrderDAO instance;

    public OrderDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new OrderDAO();

    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddOrder() {
         List<Item> li = new ArrayList<>();
        li.add(new Item(20, 15, 38000));
        Cart c = new Cart(li);
        CustomerDAO cusDAO = new CustomerDAO();
        Customer u = cusDAO.getAccount("huhu");
        int result = instance.addOrder(u, c);
        assertEquals(27, result);
    }

    @Test
    public void testGetOrderingWithEmptyUsername() throws Exception {
        String username = "";
        int expResult =0;
        List<Order> result = instance.getOrdering(username);
        assertEquals(expResult, result.size());
    }
    @Test
    public void testGetOrdering() throws Exception {
        String username = "anhptv";
        int expResult =10;
        List<Order> result = instance.getOrdering(username);
        assertEquals(expResult, result.size());
    }
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        int expResult = 16;
        List<Order> result = instance.getAll();
        assertEquals(expResult, result.size());
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("Update");
        int id = 17;
        int statusID = 2;
        int expResult = 1;
        int result = instance.Update(id, statusID);
        assertEquals(expResult, result);
    }

}
