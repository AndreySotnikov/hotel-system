package project.entity.other;

import org.springframework.stereotype.Service;
import project.entity.Characteristic;
import project.entity.RoomType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by andrey on 01.04.15.
 */
@Embeddable
public class TypeChar implements Serializable {
    @Column(name = "room_type_id")
    private int roomTypeId;

    @Column(name = "characteristic_id")
    private int characteristicId;

    public TypeChar() {
    }

    public TypeChar(int roomTypeId, int characteristicId) {
        this.roomTypeId = roomTypeId;
        this.characteristicId = characteristicId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }

    @Override
    public String toString() {
        return "TypeChar{" +
                "roomTypeId=" + roomTypeId +
                ", characteristicId=" + characteristicId +
                '}';
    }
}

