/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CartDAOTest extends DBContext{
    CartDAO instance;

    public CartDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        instance = new CartDAO();
    }
    
    @After
    public void tearDown() throws SQLException {
    }

    /**
     * Test of getCart method, of class CartDAO.
     */
    @Test
    public void testGetCartWithExistedUsername() {
        System.out.println("getCart");
        String username = "anhptv";
        
        List<Item> li = new ArrayList<>();
        li.add(new Item(16, 26));
        li.add(new Item(20, 15));
        li.add(new Item(47, 4));
        Cart expResult = new Cart(li);
        
        Cart result = instance.getCart(username);
        assertEquals(expResult.getItems().get(0).getId(), result.getItems().get(0).getId());
        assertEquals(expResult.getItems().get(1).getId(), result.getItems().get(1).getId());
        assertEquals(expResult.getItems().get(2).getId(), result.getItems().get(2).getId());
    }
  @Test
    public void testGetCartWithUnexistedUsername() {
        System.out.println("getCart");
        String username = "anh";
        
        Cart result = instance.getCart(username);
        assertEquals(0, result.getItems().size());
    }

    /**
     * Test of addItem method, of class CartDAO.
     */
    @Test
    public void testAddItemWithWrongID() {
        String username = "anhptv";
        Item t = new Item();
        t.setId(10);
        t.setQuantity(3);
        int result =  instance.addItem(username, t);
        assertEquals(-1, result);
    }
    @Test
    public void testAddItemWithCorrectIDButWrongQuantity() {
       CartDAO cartDAO = new CartDAO();
        String username = "anhptv";
        Item t = new Item();
        t.setId(16);
        t.setQuantity(-1);
        int result =  cartDAO.addItem(username, t);
        assertEquals(-1, result);
    }
      @Test
    public void testAddItemThatDoesNotExistInCart() throws SQLException {
        String username = "meoxinh";
        Item t = new Item();
        t.setId(23);
        t.setQuantity(15);
        int result =  instance.addItem(username, t);
        assertEquals(1, result);
    }
    @Test
     public void testAddItemExistedInCart() {
       CartDAO cartDAO = new CartDAO();
        String username = "anhptv";
        Item t = new Item();
        t.setId(15);
        t.setQuantity(15);
        int result =  cartDAO.addItem(username, t);
        assertEquals(1, result);
    }
    /**
     * Test of update method, of class CartDAO.
     */
    @Test
    public void testUpdateWithNegativeQuantity() {
        System.out.println("update");
        int id = 12;
        String username = "meoxinh";
        int quantity = -1;
        int expResult = -1;
        int result = instance.update(id, username, quantity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testUpdateWithUnexistedID() {
        System.out.println("update");
        int id = 10;
        String username = "meoxinh";
        int quantity = 1;
        int expResult = 0;
        int result = instance.update(id, username, quantity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }    

    @Test
    public void testAddCorrectCase() {
        int id = 21;
        String username = "meoxinh";
        int quantity = 1;
        int expResult = -1;
        int result = instance.add(id, username, quantity);
        assertEquals(expResult, result);
    }
       @Test
    public void testAddWithNegativeQuantity() {
        int id = 20;
        String username = "meoxinh";
        int quantity = -1;
        int expResult = -1;
        int result = instance.add(id, username, quantity);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    @Test
    public void testAddExistedProduct() {
        int id = 12;
        String username = "meoxinh";
        int quantity = 1;
        int result = instance.add(id, username, quantity);
        assertEquals(-1, result);
    }
    /**
     * Test of delete method, of class CartDAO.
     */
    @Test
    public void testDeleteUnexistedItem() {
        System.out.println("delete");
        int id = 13;
        String username = "meoxinh";
        int expResult = -1;
        int result = instance.delete(id, username);
        assertEquals(expResult, result);
    }

        @Test
    public void testDeleteCorrectCase() {
        System.out.println("delete");
        int id = 15;
        String username = "meoxinh";
        int expResult = -1;
        int result = instance.delete(id, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of getQuantityById method, of class CartDAO.
     */
    @Test
    public void testGetQuantityByNullUsername() {
        System.out.println("getQuantityById");
        int id = 11;
        int expResult = 0;
        int result = instance.getQuantityById(id, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetQuantityByInvalidId() {
        System.out.println("getQuantityById");
        int id = 0;
        int expResult = 0;
        int result = instance.getQuantityById(id, "meoxinh");
        assertEquals(expResult, result);
    }
        @Test
    public void testGetQuantityValidCase() {
        System.out.println("getQuantityById");
        int id = 17;
        int expResult = 5;
        int result = instance.getQuantityById(id, "meoxinh");
        assertEquals(expResult, result);
    }
}