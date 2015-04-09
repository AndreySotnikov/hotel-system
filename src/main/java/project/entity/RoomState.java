package project.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class RoomState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_state_id")
    private int roomStateId;
    private String name;
    @OneToMany(mappedBy = "roomState")
    private transient List<TimeTable> timeTable;
    private int tenantId;

    public RoomState() {
    }

    public RoomState(String name, int tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }

    public int getRoomStateId() {
        return roomStateId;
    }

    public void setRoomStateId(int roomStateId) {
        this.roomStateId = roomStateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeTable> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.timeTable = timeTable;
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

        RoomState roomState = (RoomState) o;

        if (roomStateId != roomState.roomStateId) return false;
        if (name != null ? !name.equals(roomState.name) : roomState.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomStateId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomState{" +
                "roomStateId=" + roomStateId +
                ", name='" + name +
                ", tenantId=" + tenantId +
                '}';
    }
}
