package Model.Value;

import Model.Type.ReferenceType;
import Model.Type.Type;

public class ReferenceValue implements Value {
    int address;
    Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getAddress()
    {
        return address;
    }
    public String toString()
    {
        return "("+Integer.toString(address)+","+locationType.toString()+")";
    }
    public Type getType() {
        return new ReferenceType(locationType);
    }
    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Value deepCopy() {
        return new ReferenceValue(new Integer(address),locationType.deepCopy());
    }


}
