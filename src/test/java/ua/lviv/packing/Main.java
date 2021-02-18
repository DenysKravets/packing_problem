package ua.lviv.packing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Comparator;

import ua.lviv.packing.Entity.Volume;
import ua.lviv.packing.Util.Orderlines;
import ua.lviv.packing.Entity.Case;
import ua.lviv.packing.Entity.Orderline;
import ua.lviv.packing.Entity.Product;

import static ua.lviv.packing.Util.Orderlines.*;
import static ua.lviv.packing.Util.Control.*;

public class Main {

    public static void drawLine() {
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {

        /*
        //Testing 
        //Setting up test subjects XD
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        products.add(new Product(1, 2, 2, 2));
        
        //I artificially set coordinates so that it itercepts with a volume
        // Product productTest = new Product(1, 1, 1, 1);
        // productTest.setGlobal_z(4);
        // productTest.setGlobal_x(0);
        // productTest.setGlobal_y(0);
        // products.add(productTest);
        Orderline orderline = new Orderline(1, products);

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(new Product(2, 4, 4, 4));
        products2.add(new Product(2, 4, 4, 4));
        Orderline orderline2 = new Orderline(2, products2);

        ArrayList<Product> products3 = new ArrayList<>();
        products3.add(new Product(3, 6, 6, 6));
        products3.add(new Product(3, 6, 6, 6));
        Orderline orderline3 = new Orderline(3, products3);

        ArrayList<Orderline> orderlines = new ArrayList<>();
        orderlines.add(orderline3);
        orderlines.add(orderline);
        orderlines.add(orderline2);
        
        setOrderlines(orderlines);

        //Actual working code
        cases.add(new Case(1, 14, 7, 7));
        cases.add(new Case(1, 14, 7, 7));
        */

        System.out.println("You should add at least one case and one orderline for the program to work!");

        Scanner scanner = new Scanner(System.in);
        boolean start = false;
        while(!start) {
            System.out.println("Enter 1 to add new case;");
            System.out.println("Enter 2 to add new orderline;");
            System.out.println("Enter 0 to start;");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1": {
                    System.out.println("Creating a new case.");
                    System.out.print("Enter id: ");
                    long id = Long.parseLong(scanner.nextLine());
                    System.out.print("Enter sizeX: ");
                    int sizeX = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter sizeY: ");
                    int sizeY = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter sizeZ: ");
                    int sizeZ = Integer.parseInt(scanner.nextLine());
                    cases.add(new Case(id, sizeX, sizeY, sizeZ));
                    break;
                }
                case "2": {
                    System.out.println("Creating a new orderline.");
                    System.out.print("Enter orderline id: ");
                    long orderlineId = Long.parseLong(scanner.nextLine());
                    System.out.print("Enter product id: ");
                    long productId = Long.parseLong(scanner.nextLine());
                    System.out.print("Enter number of products: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter sizeX: ");
                    int sizeX = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter sizeY: ");
                    int sizeY = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter sizeZ: ");
                    int sizeZ = Integer.parseInt(scanner.nextLine());
                    getOrderlines().add(new Orderline(orderlineId, createNProducts(n, productId, sizeX, sizeY, sizeZ)));
                    break;
                }
                case "0":{
                    start = true;
                    break;
                }
                default: {
                    System.out.println("Please enter one of the numbers lsited above.");
                }
            }
        }
        scanner.close();

        //Sort products in orderlines by volume
        Orderlines.sortByVolume();

        //Sort cases by volume
        sortByVolume(cases);

        //Start
        start();

        //Just checking
        //System.out.println(product);

        //Let's see the result
        //drawLine();
        //showAllProducts();
        
        cases.forEach(_case -> {
            drawLine();
            System.out.println(_case);
            _case.getProducts().forEach(System.out::println);
        });

        drawLine();
        System.out.println("Not placed products");
        getOrderlines().forEach(orderline -> orderline.getProducts().forEach(product -> {
            if(!product.isPlaced()) {
                System.out.println(product);
            }
        }));



    }

    //list of all cases
    public static ArrayList<Case> cases = new ArrayList<>();
    //The main case
    //for now only one
    public static Case mainCase = null;
    //products that should place new products 
    //are stored here and are changed every iteration
    public static ArrayList<Product> productsToBeIterated = new ArrayList<>();

    //Sort cases by volume
    public static void sortByVolume(ArrayList<Case> cases) {

        Comparator<Case> byVolume = (case1, case2) -> {
            int volume1 = case1.getVolume();
            int volume2 = case2.getVolume();
            return -1 * Integer.compare(volume1, volume2);
        };
        cases.sort(byVolume);
    }

    public static void start() {

        cases.stream().forEach(innerCase -> {
        //Set initial volume
        Volume volume = new Volume(0, 0, 0, innerCase.getSizeX(), innerCase.getSizeY(), innerCase.getSizeZ());

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
                        innerCase.getProducts().add(product);
                        break outerloop;
                    }
                }
            }
        }
        //One volume can contain only one product
        //So we set it up
        //Edit:
        //and vice versa so we can add volume to
        //product, they now know about each other
        volume.setProduct(product);
        product.setObjectVolume(volume);
        //add product to the array so that it actually
        //places new products
        productsToBeIterated.add(product);

        //Start iteration
        while(productsToBeIterated.size() != 0) {
            ArrayList<Product> innerProductsToBeIterated = (ArrayList<Product>) productsToBeIterated.clone();
            productsToBeIterated.clear();
            innerProductsToBeIterated.forEach(iterateProduct -> {
                algorithm(iterateProduct, innerCase);
            });

        };
        });
    }

    public static boolean algorithm(Product product, Case _case) {

        Volume volume = product.getObjectVolume();

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
                    int sizeZ = _case.getSizeZ() - global_z;

                    newVolume1 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    break;
                }
                case 1: {
                    //This is Y, side volume
                    int global_x = volume.getProduct().getGlobal_x();
                    int global_y = volume.getProduct().getGlobal_y() + volume.getProduct().getSizeY();
                    int global_z = volume.getProduct().getGlobal_z();

                    int sizeX = volume.getProduct().getSizeX();
                    int sizeY = _case.getSizeY() - global_y;
                    int sizeZ = _case.getSizeZ() - global_z;

                    newVolume2 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    break;
                }
                case 2: {
                    //This is X, front volume
                    int global_x = volume.getProduct().getGlobal_x() + volume.getProduct().getSizeX();
                    int global_y = volume.getProduct().getGlobal_y();
                    int global_z = volume.getProduct().getGlobal_z();

                    int sizeX = _case.getSizeX() - global_x;
                    int sizeY = _case.getSizeY() - global_y;
                    int sizeZ = _case.getSizeZ() - global_z;

                    newVolume3 = new Volume(global_x, global_y, global_z, sizeX, sizeY, sizeZ);
                    break;
                }
            }
        }

        //Volume1
        //The z coordinate gets shifted in the size of volume
        ArrayList<Product> interseptingProducts = getOverlapingProducts(newVolume1, _case);
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
        interseptingProducts = getOverlapingProducts(newVolume2, _case);
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
        interseptingProducts = getOverlapingProducts(newVolume2, _case);
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

        //Now that we have created new volumes we need to fill each with
        //a new product, if it fits it should loop if no it should not
        //First work with volume 1 aka z direction
        if(setProductInto(newVolume1, _case)) {
            
        }
        //volume2
        if(setProductInto(newVolume2, _case)) {
            
        }
        //volume3
        if(setProductInto(newVolume3, _case)) {
            
        }

        return true;

    }

    //It is not really a best fit but whatever
    public static boolean setProductInto(Volume volume, Case _case) {

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
                        product.setObjectVolume(volume);
                        product.setGlobal_x(volume.getGlobal_x());
                        product.setGlobal_y(volume.getGlobal_y());
                        product.setGlobal_z(volume.getGlobal_z());
                        //before setting it lets check wether it intersects with products
                        //within this case
                        boolean toPlace = true;
                        if(_case.getProducts().size() != 0) {
                            Iterator<Product> caseProductIterator = _case.getProducts().iterator();
                            while(caseProductIterator.hasNext()) {
                                Product caseProduct = caseProductIterator.next();
                                if(caseProduct.isPlaced())
                                {
                                    if(caseProduct.intersectsWith(product)) {
                                        toPlace = false;
                                        product.setGlobal_x(999);
                                        product.setGlobal_y(999);
                                        product.setGlobal_z(999);
                                    }
                                }
                            }
                        }
                        if(toPlace) {
                            product.setPlaced(true);
                            _case.getProducts().add(product);
                            productsToBeIterated.add(product);
                            productPlaced = true;
                        }
                        break outerloop;
                    }
                }
            }
        }

        return productPlaced;
    }

    //get all products that overlap with a new volume
    public static ArrayList<Product> getOverlapingProducts(Volume volume, Case _case) {
        ArrayList<Product> productsToReturn = new ArrayList<>();
        
        Iterator<Product> productIterator = _case.getProducts().iterator();
        while(productIterator.hasNext()) {
            Product innerProduct = productIterator.next();

            if(innerProduct.intersectsWith(volume)) {
                productsToReturn.add(innerProduct);
            }
        }

        return productsToReturn;
    }

}