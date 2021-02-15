package ua.lviv.packing.Entity;

public abstract class Box {
    
    private long id;
    private int sizeX;
    private int sizeY;
    private int sizeZ;

    public Box(long id, int sizeX, int sizeY, int sizeZ) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + sizeX;
        result = prime * result + sizeY;
        result = prime * result + sizeZ;
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
        Box other = (Box) obj;
        if (id != other.id)
            return false;
        if (sizeX != other.sizeX)
            return false;
        if (sizeY != other.sizeY)
            return false;
        if (sizeZ != other.sizeZ)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + ", sizeX=" + sizeX + ", sizeY=" + sizeY + ", sizeZ=" + sizeZ ;
    }
}
