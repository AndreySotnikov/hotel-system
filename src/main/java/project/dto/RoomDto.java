package project.dto;

/**
 * Created by andrey on 02.04.15.
 */
public class RoomDto {
    private int floor;
    private int number;
    private int roomType;
    private int tenantId;

    public RoomDto() {
    }

    public RoomDto(int floor, int number, int roomType, int tenantId) {
        this.floor = floor;
        this.number = number;
        this.roomType = roomType;
        this.tenantId = tenantId;
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

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
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

        RoomDto roomDto = (RoomDto) o;

        if (floor != roomDto.floor) return false;
        if (number != roomDto.number) return false;
        if (roomType != roomDto.roomType) return false;
        if (tenantId != roomDto.tenantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = floor;
        result = 31 * result + number;
        result = 31 * result + roomType;
        result = 31 * result + tenantId;
        return result;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "floor=" + floor +
                ", number=" + number +
                ", roomType=" + roomType +
                ", tenantId=" + tenantId +
                '}';
    }
}
