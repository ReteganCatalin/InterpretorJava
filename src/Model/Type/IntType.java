package Model.Type;

public class IntType implements  Type {
    public IntType() {
    }

    public boolean equals(Object another){
        if (another instanceof IntType)
            return true;
        else
            return false;
    }
    public String toString()
    {
        return "int";
    }
}
