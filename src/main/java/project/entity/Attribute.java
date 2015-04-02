package project.entity;

import project.entity.other.TypeChar;

import javax.persistence.*;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class Attribute {
    @EmbeddedId
    private TypeChar typeCharId;
    private String value;
    private int tenantId;

    public Attribute() {
    }

    public Attribute(int roomType, int characteristic, String value, int tenantId) {
        this.typeCharId = new TypeChar(roomType, characteristic);
        this.value = value;
        this.tenantId = tenantId;
    }

    public TypeChar getTypeCharId() {
        return typeCharId;
    }

    public void setTypeCharId(TypeChar typeCharId) {
        this.typeCharId = typeCharId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "typeCharId=" + typeCharId +
                ", value='" + value + '\'' +
                '}';
    }
}
