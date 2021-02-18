package ua.lviv.packing.Util;

import java.util.ArrayList;

import ua.lviv.packing.Entity.Product;

public class Control {
    
    public static ArrayList<Product> createNProducts(int n, long id, int sizeX, int sizeY, int sizeZ) {
        
        ArrayList<Product> products = new ArrayList<>();
 
        for(int i = 0; i < n; i++) {
            products.add(new Product(id, sizeX, sizeY, sizeZ));
        }
        
        return products;
    }
}
