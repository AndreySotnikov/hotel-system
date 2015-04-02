package project.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomTypeId;
    private String name;
    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;
    private int tenantId;

    public RoomType() {
    }

    public RoomType(int tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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

        RoomType roomType = (RoomType) o;

        if (roomTypeId != roomType.roomTypeId) return false;
        if (name != null ? !name.equals(roomType.name) : roomType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", tenantId=" + tenantId +
                '}';
    }
}
