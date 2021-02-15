package ua.lviv.packing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

import ua.lviv.packing.Entity.Volume;
import ua.lviv.packing.Util.Orderlines;
import ua.lviv.packing.Entity.Case;
import ua.lviv.packing.Entity.Orderline;
import ua.lviv.packing.Entity.Product;

import static ua.lviv.packing.Util.Orderlines.*;

public class Main {

    public static void drawLine() {
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {

        //Testing 
        //Setting up test subjects XD
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        products.add(new Product(1, 1, 1, 1));
        //I artificially set coordinates so that it itercepts with a volume
        // Product productTest = new Product(1, 1, 1, 1);
        // productTest.setGlobal_z(4);
        // productTest.setGlobal_x(0);
        // productTest.setGlobal_y(0);
        // products.add(productTest);
        Orderline orderline = new Orderline(1, products);

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(new Product(2, 2, 2, 2));
        products2.add(new Product(2, 2, 2, 2));
        Orderline orderline2 = new Orderline(2, products2);

        ArrayList<Product> products3 = new ArrayList<>();
        products3.add(new Product(3, 3, 3, 3));
        products3.add(new Product(3, 3, 3, 3));
        Orderline orderline3 = new Orderline(3, products3);

        ArrayList<Orderline> orderlines = new ArrayList<>();
        orderlines.add(orderline3);
        orderlines.add(orderline);
        orderlines.add(orderline2);

        setOrderlines(orderlines);
        
        System.out.println(getOrderlines());
        Orderlines.sortByVolume();
        System.out.println(getOrderlines());
        System.out.println("-------------------------------------------");

        //Actual working code
        mainCase = new Case(1, 7, 3, 3);
        Volume volume = new Volume(0, 0, 0, mainCase.getSizeX(), mainCase.getSizeY(), mainCase.getSizeZ());

        //Set initial product
        Product product = null;
        Iterator<Orderline> iterator = getOrderlines().iterator();
        outerloop:
        while(iterator.hasNext()) {
            Orderline innerOrderline = iterator.next();

            Iterator<Product> productIterator = innerOrderline.getProducts().iterator();
            while(productIterator.hasNext()) {
                Product innerProduct = productIterator.next();

                if(innerProduct.fitsInto(volume)) {
                    if(!innerProduct.isPlaced()) {
                        product = innerProduct;
                        product.setPlaced(true);
                        product.setGlobal_x(0);
                        product.setGlobal_y(0);
                        product.setGlobal_z(0);
                        break outerloop;
                    }
                }
            }
        }
        //One volume can contain only one product
        //So we set it up
        volume.setProduct(product);

        //Just cheking
        System.out.println(product);

        //Diving into recursion
        algorithm(volume);

        drawLine();
        showAllProducts();


    }

    public static Case mainCase = null;

    public static boolean algorithm(Volume volume) {

        //Iterating over 3 directions
        //in each direction I can place only one volume
        //X > Y > Z in terms of volume if case was a cube
        //but iteration goes Z -> Y -> X

        Volume newVolume1 = null;
        Volume newVolume2 = null;
        Volume newVolume3 = null;
        for(int i = 0; i < 3; i++) {
            switch (i) {
                case 0: {
                    //This is Z, upper volume
                    int global_x = volume.getProduct().getGlobal_x();
                    int global_y = volume.getProduct().getGlobal_y();
                    int global_z = volume.getProduct().getGlobal_z() + volume.getProduct().getSizeZ();

                    int sizeX = volume.getProduct().getSizeX();
                    int sizeY = volume.getProduct().getSizeY();
                    int sizeZ = mainCase.getSizeZ() - global_z;

                    newVolume1 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    System.out.println(newVolume1);
                    break;
                }
                case 1: {
                    //This is Y, side volume
                    int global_x = volume.getProduct().getGlobal_x();
                    int global_y = volume.getProduct().getGlobal_y() + volume.getProduct().getSizeY();
                    int global_z = volume.getProduct().getGlobal_z();

                    int sizeX = volume.getProduct().getSizeX();
                    int sizeY = mainCase.getSizeY() - global_y;
                    int sizeZ = mainCase.getSizeZ() - global_z;

                    newVolume2 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    System.out.println(newVolume2);
                    break;
                }
                case 2: {
                    //This is X, front volume
                    int global_x = volume.getProduct().getGlobal_x() + volume.getProduct().getSizeX();
                    int global_y = volume.getProduct().getGlobal_y();
                    int global_z = volume.getProduct().getGlobal_z();

                    int sizeX = mainCase.getSizeX() - global_x;
                    int sizeY = mainCase.getSizeY() - global_y;
                    int sizeZ = mainCase.getSizeZ() - global_z;

                    newVolume3 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    System.out.println(newVolume3);
                    break;
                }
            }
        }

        //Volume1
        //The z coordinate gets shifted in the size of volume
        ArrayList<Product> interseptingProducts = getOverlapingProducts(newVolume1);
        //We need to sort them by z coordinate
        //the one with the lowest z coordiante will set the new volume
        Comparator<Product> byZ = (product1, product2) -> {
            int z1 = product1.getGlobal_z();
            int z2 = product2.getGlobal_z();
            return Integer.compare(z1, z2);
        };
        interseptingProducts.sort(byZ);
        if(interseptingProducts.size() != 0) {
            Product zProduct = interseptingProducts.get(0);
            int newSizeZ = zProduct.getGlobal_z() - (volume.getProduct().getGlobal_z() + volume.getProduct().getSizeZ());
            newVolume1.setSizeZ(newSizeZ);
        }
        
        //Volume2
        //The y coordinate gets shifted in the size of volume
        interseptingProducts = getOverlapingProducts(newVolume2);
        //We need to sort them by y coordinate
        //the one with the lowest y coordiante will set the new volume
        Comparator<Product> byY = (product1, product2) -> {
            int y1 = product1.getGlobal_y();
            int y2 = product2.getGlobal_y();
            return Integer.compare(y1, y2);
        };
        interseptingProducts.sort(byY);
        if(interseptingProducts.size() != 0) {
            Product yProduct = interseptingProducts.get(0);
            int newSizeY = yProduct.getGlobal_y() - (volume.getProduct().getGlobal_y() + volume.getProduct().getSizeY());
            newVolume1.setSizeZ(newSizeY);
        }

        //Volume3
        //The x coordinate gets shifted in the size of volume
        interseptingProducts = getOverlapingProducts(newVolume2);
        //We need to sort them by x coordinate
        //the one with the lowest x coordiante will set the new volume
        Comparator<Product> byX = (product1, product2) -> {
            int x1 = product1.getGlobal_x();
            int x2 = product2.getGlobal_x();
            return Integer.compare(x1, x2);
        };
        interseptingProducts.sort(byX);
        if(interseptingProducts.size() != 0) {
            Product xProduct = interseptingProducts.get(0);
            int newSizeX = xProduct.getGlobal_x() - (volume.getProduct().getGlobal_x() + volume.getProduct().getSizeX());
            newVolume1.setSizeZ(newSizeX);
        }

        drawLine();
        System.out.println(newVolume1);
        System.out.println(newVolume2);
        System.out.println(newVolume3);

        //Now that we have created new volumes we need to fill each with
        //a new product, if it fits it should loop if no it should not
        //First work with volume 1 aka z direction
        if(setProductInto(newVolume3)) {
            algorithm(newVolume3);
        }
        //volume2
        if(setProductInto(newVolume2)) {
            algorithm(newVolume2);
        }
        //volume3
        if(setProductInto(newVolume1)) {
            algorithm(newVolume1);
        }

        //there is no way this is going to work
        //しょうがないな
        //いっしょに切腹しましょうか
        return true;

    }

    //It is not really a best fit but whatever
    public static boolean setProductInto(Volume volume) {

        Product product = null;
        boolean productPlaced = false;
        Iterator<Orderline> iterator = getOrderlines().iterator();
        outerloop:
        while(iterator.hasNext()) {
            Orderline innerOrderline = iterator.next();

            Iterator<Product> productIterator = innerOrderline.getProducts().iterator();
            while(productIterator.hasNext()) {
                Product innerProduct = productIterator.next();

                if(innerProduct.fitsInto(volume)) {
                    if(!innerProduct.isPlaced()) {
                        product = innerProduct;
                        volume.setProduct(product);
                        product.setGlobal_x(volume.getGlobal_x());
                        product.setGlobal_y(volume.getGlobal_y());
                        product.setGlobal_z(volume.getGlobal_z());
                        product.setPlaced(true);
                        productPlaced = true;
                        break outerloop;
                    }
                }
            }
        }

        return productPlaced;
    }

    //get all products that overlap with a new volume
    public static ArrayList<Product> getOverlapingProducts(Volume volume) {
        ArrayList<Product> productsToReturn = new ArrayList<>();
        
        Iterator<Orderline> iterator = getOrderlines().iterator();
        while(iterator.hasNext()) {
            Orderline innerOrderline = iterator.next();

            Iterator<Product> productIterator = innerOrderline.getProducts().iterator();
            while(productIterator.hasNext()) {
                Product innerProduct = productIterator.next();

                if(innerProduct.intersectsWith(volume)) {
                    productsToReturn.add(innerProduct);
                }
            }
        }

        return productsToReturn;
    }

}