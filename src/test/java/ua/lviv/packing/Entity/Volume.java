package ua.lviv.packing.Entity;

public class Volume {
    
    private int global_x;
    private int global_y;
    private int global_z;
    private int sizeX;
    private int sizeY;
    private int sizeZ;
    private Product product;

    public Volume(int global_x, int global_y, int global_z, int sizeX, int sizeY, int sizeZ) {
        this.global_x = global_x;
        this.global_y = global_y;
        this.global_z = global_z;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public int getGlobal_x() {
        return global_x;
    }

    public void setGlobal_x(int global_x) {
        this.global_x = global_x;
    }

    public int getGlobal_y() {
        return global_y;
    }

    public void setGlobal_y(int global_y) {
        this.global_y = global_y;
    }

    public int getGlobal_z() {
        return global_z;
    }

    public void setGlobal_z(int global_z) {
        this.global_z = global_z;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ(int sizeZ) {
        this.sizeZ = sizeZ;
    }

    @Override
    public String toString() {
        return "Volume [global_x=" + global_x + ", global_y=" + global_y + ", global_z=" + global_z + ", sizeX=" + sizeX
                + ", sizeY=" + sizeY + ", sizeZ=" + sizeZ + "]";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
}
