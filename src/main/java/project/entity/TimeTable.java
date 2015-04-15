package project.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by andrey on 01.04.15.
 */
@Entity
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int timeTableId;
    @ManyToOne
    //@JoinColumn(name = "room_id",referencedColumnName = "room_id")
    private Room room;
    @ManyToOne
//    @JoinColumn(name = "room_state_id",referencedColumnName = "room_state_id")
    private RoomState roomState;
    @Column(name = "date_from")
    private Long from;
    @Column(name = "date_to")
    private Long to;
    private int tenantId;
    @ManyToOne
    private Guest guest;

    public TimeTable() {
    }

    public TimeTable(Room room, RoomState roomState, Long from, Long to, int tenantId) {
        this.room = room;
        this.roomState = roomState;
        this.from = from;
        this.to = to;
        this.tenantId = tenantId;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeTable timeTable = (TimeTable) o;

        if (timeTableId != timeTable.timeTableId) return false;
        if (from != null ? !from.equals(timeTable.from) : timeTable.from != null) return false;
        if (room != null ? !room.equals(timeTable.room) : timeTable.room != null) return false;
        if (to != null ? !to.equals(timeTable.to) : timeTable.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeTableId;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "timeTableId=" + timeTableId +
                ", room=" + room +
                ", roomState=" + roomState +
                ", from=" + from +
                ", to=" + to +
                ", tenantId=" + tenantId +
                '}';
    }
}
