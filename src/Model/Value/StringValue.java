package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    String val;
    public StringValue(String v)
    {
        val=v;
    }
    public String getValue()
    {
        return val;
    }
    public String toString()
    {
        return val;
    }
    public Type getType() {
        return new StringType();
    }
    public Value deepCopy()
    {
        return new StringValue(new String(val));
    }
}
