package project.entity;

import javax.persistence.*;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int characteristicId;
    private String name;
    private int tenantId;

    public Characteristic() {
    }

    public Characteristic(String name, int tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characteristic that = (Characteristic) o;

        if (characteristicId != that.characteristicId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = characteristicId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "characteristicId=" + characteristicId +
                ", name='" + name + '\'' +
                ", tenantId=" + tenantId +
                '}';
    }
}
