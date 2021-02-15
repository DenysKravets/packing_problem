package ua.lviv.packing.Entity;

public class Case extends Box {

    public Case(long id, int sizeX, int sizeY, int sizeZ) {
        super(id, sizeX, sizeY, sizeZ);
    }

    @Override
    public String toString() {
        return "Case [" + super.toString() + "]";
    }
    
    
}
