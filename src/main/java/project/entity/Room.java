package project.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private int roomId;
    private int floor;
    private int number;
    @ManyToOne
    private RoomType roomType;
    @OneToMany(mappedBy = "room")
    private transient List<TimeTable> timeTables;
    private int tenantId;

    public Room() {
    }

    public Room(int floor, int number, RoomType roomType, int tenantId) {
        this.floor = floor;
        this.number = number;
        this.roomType = roomType;
        this.tenantId = tenantId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
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

        Room room = (Room) o;

        if (floor != room.floor) return false;
        if (number != room.number) return false;
        if (roomId != room.roomId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + floor;
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", floor=" + floor +
                ", number=" + number +
                ", roomType=" + roomType +
                ", tenantId=" + tenantId +
                '}';
    }
}
