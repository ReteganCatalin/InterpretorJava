package Model.Type;


import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    public boolean equals(Object another){
        if (another instanceof StringType)
            return true;
        else
            return false;
    }

    public StringType() {

    }

    public Value defaultValue()
    {
        return new StringValue("");
    }

    public String toString()
    {
        return "string";
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }
}
