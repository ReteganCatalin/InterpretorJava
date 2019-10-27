package Model.Type;

public class BoolType implements Type {
    public boolean equals(Object another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    public BoolType() {
    }

    public String toString()
    {
        return "bool";
    }
}
