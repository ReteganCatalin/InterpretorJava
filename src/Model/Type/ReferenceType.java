package Model.Type;

import Model.Value.ReferenceValue;
import Model.Value.Value;

public class ReferenceType implements Type{
    Type referencedType;
    public ReferenceType(Type referencedType)
    {this.referencedType = referencedType;}
    public Type getReferencedType() {return referencedType;}
    public boolean equals(Object another){
        if (another instanceof ReferenceType)
            return referencedType.equals(((ReferenceType) another).referencedType);
        else
            return false;
    }
    public String toString() { return "Reference(" + referencedType.toString()+")";}

    public Value defaultValue()
    { return new ReferenceValue(0,referencedType);}}
