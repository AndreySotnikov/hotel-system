package project.entity;

/**
 * Created by Green-L on 14.04.2015.
 */

import javax.persistence.*;

import javax.persistence.*;

/**
 * Created by Green-L on 14.04.2015.
 */
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inventoryId;

    private String name;

    private int tenantId;

    public Inventory() {
    }

    public Inventory(String name, int tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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

        Inventory inventory = (Inventory) o;

        if (inventoryId != inventory.inventoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return inventoryId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", name='" + name + '\'' +
                ", tenantId=" + tenantId +
                '}';
    }
}
