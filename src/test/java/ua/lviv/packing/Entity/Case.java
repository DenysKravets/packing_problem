package ua.lviv.packing.Entity;

import java.util.ArrayList;

public class Case extends Box {

    ArrayList<Product> products = new ArrayList<>();

    public Case(long id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
    }

    public int getVolume() {
        return getSizeX() * getSizeY() * getSizeZ();
    }

    @Override
    public String toString() {
        return "Case [" + super.toString() + "]";
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    
}
