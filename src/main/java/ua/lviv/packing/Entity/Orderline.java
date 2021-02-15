package ua.lviv.packing.Entity;

import java.util.ArrayList;

public class Orderline {
    
    private long id;
    private int numberOfProducts;
    private long productId;
    private ArrayList<Product> products;

    public Orderline(long id, ArrayList<Product> products) {
        this.id = id;
        this.products = products;
        this.numberOfProducts = products.size();
        this.productId = products.get(0).getId();
    }

    public Orderline(long id, int numberOfProducts, long productId) {
        this.id = id;
        this.numberOfProducts = numberOfProducts;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + numberOfProducts;
        result = prime * result + (int) (productId ^ (productId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Orderline other = (Orderline) obj;
        if (id != other.id)
            return false;
        if (numberOfProducts != other.numberOfProducts)
            return false;
        if (productId != other.productId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Orderline [id=" + id + ", numberOfProducts=" + numberOfProducts + ", productId=" + productId
                + ", products=" + products + "]";
    }

    

    

    
    
}
