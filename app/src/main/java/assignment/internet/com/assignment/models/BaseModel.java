package assignment.internet.com.assignment.models;

import java.io.Serializable;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class BaseModel implements Serializable{
    String Tag;
    String TypeCode;
    String Value;
    String IsBinaryUnique;

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getIsBinaryUnique() {
        return IsBinaryUnique;
    }

    public void setIsBinaryUnique(String isBinaryUnique) {
        IsBinaryUnique = isBinaryUnique;
    }
}
