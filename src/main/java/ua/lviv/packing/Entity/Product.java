package ua.lviv.packing.Entity;

public class Product extends Box{

    private boolean placed = false;

    private int global_x;
    private int global_y;
    private int global_z;
    private int volume;
    private Volume objectVolume;

    public Product(long id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
        this.global_x = 999;
        this.global_y = 999;
        this.global_z = 999;
        volume = sizeX * sizeY * sizeZ;
    }

    public boolean fitsInto(Volume volume) {

        if(
            this.getSizeX() <= volume.getSizeX() &&
            this.getSizeY() <= volume.getSizeY() &&
            this.getSizeZ() <= volume.getSizeZ()
        ) {
            return true;
        }

        return false;
    }

    public boolean intersectsWith(Volume volume) {
        
        int product_x1 = global_x;
        int product_x2 = global_x + getSizeX();
        int product_y1 = global_y;
        int product_y2 = global_y + getSizeY();
        int product_z1 = global_z;
        int product_z2 = global_z + getSizeZ();
        
        int volume_x1 = volume.getGlobal_x();
        int volume_x2 = volume.getGlobal_x() + volume.getSizeX();
        int volume_y1 = volume.getGlobal_y();
        int volume_y2 = volume.getGlobal_y() + volume.getSizeY();
        int volume_z1 = volume.getGlobal_z();
        int volume_z2 = volume.getGlobal_z() + volume.getSizeZ();

        boolean condition_x = product_x1 < volume_x2 && volume_x1 < product_x2;
        boolean condition_y = product_y1 < volume_y2 && volume_y1 < product_y2;
        boolean condition_z = product_z1 < volume_z2 && volume_z1 < product_z2;

        if(condition_x && condition_y && condition_z) {
            return true;
        }

        return false;
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

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Product [global_x=" + global_x + ", global_y=" + global_y + ", global_z=" + global_z + ", placed="
                + placed + ", " + super.toString() + "]";
    }

    public Volume getObjectVolume() {
        return objectVolume;
    }

    public void setObjectVolume(Volume objectVolume) {
        this.objectVolume = objectVolume;
    }

    

    

    
	
    
}
