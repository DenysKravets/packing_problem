package ua.lviv.packing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.lviv.packing.Entity.Case;
import ua.lviv.packing.Entity.Product;
import ua.lviv.packing.Entity.Volume;



class JUnit5Test {

    public static Case _case = Main.mainCase;

    @BeforeEach
    public void setUp() throws Exception {
        
    }

    @Test
    void fitsIntoTest() {
        Product product = new Product(1, 3, 3, 3);
        Volume volume = new Volume(0, 0, 0, 2, 2, 2);
        assertFalse(product.fitsInto(volume));
    }

    @Test
    void intersectsWithTest() {
        Product product = new Product(1, 2, 2, 2);
        product.setGlobal_x(0);
        product.setGlobal_y(0);
        product.setGlobal_z(0);
        Volume volume = new Volume(0, 0, 0, 2, 2, 2);
        assertTrue(product.intersectsWith(volume));

        Product product2 = new Product(1, 2, 2, 2);
        product2.setGlobal_x(1);
        product2.setGlobal_y(1);
        product2.setGlobal_z(1);
        assertTrue(product.intersectsWith(product2));
    }

    //Didn't make it in time to completely cover it with tests
    @Test
    void algorithmTest() {

        // Main main = new Main();

        // Case _case = new Case(1, 20, 20, 20);
        
        // ArrayList<Product> products = new ArrayList<>();
        // products.add(new Product(1, 2, 2, 2));
        // products.add(new Product(1, 2, 2, 2));
        // Orderline orderline = new Orderline(1, products);

        // ArrayList<Product> products2 = new ArrayList<>();
        // products2.add(new Product(2, 4, 4, 4));
        // products2.add(new Product(2, 4, 4, 4));
        // Orderline orderline2 = new Orderline(2, products2);

        // ArrayList<Product> products3 = new ArrayList<>();
        // Product product1 = new Product(3, 6, 6, 6);
        // products3.add(product1);
        // products3.add(new Product(3, 6, 6, 6));
        // products3.add(new Product(3, 6, 6, 6));
        // Orderline orderline3 = new Orderline(3, products3);

        // ArrayList<Orderline> orderlines = new ArrayList<>();
        // orderlines.add(orderline3);
        // orderlines.add(orderline);
        // orderlines.add(orderline2);
        
        // setOrderlines(orderlines);

        // Product product = new Product(1, 6, 6, 6);
        // product.setGlobal_x(0);
        // product.setGlobal_y(0);
        // product.setGlobal_z(0);

        // Volume volume = new Volume(0, 0, 0, 20, 20, 20);
        // product.setObjectVolume(volume);
        // volume.setProduct(product);

        // Main.algorithm(product, _case);

        // assertTrue(getOrderlines().size() != 0);

        // //assertTrue(_case.getProducts().get(0).getSizeX() == product1.getSizeX());
    }

}
