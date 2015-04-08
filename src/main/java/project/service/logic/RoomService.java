package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dto.RoomDto;
import project.entity.Characteristic;
import project.entity.Room;
import project.repository.CharacteristicRepository;
import project.repository.RoomRepository;
import project.repository.RoomTypeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Service
public class RoomService {
    @Autowired
    RoomRepository rRep;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    public Room getOne(int id){
        return rRep.findOne(id);
    }

    public List<Room> getAll(int tenantId){
        List<Room> rooms = new ArrayList<Room>();
        for (Room room : rRep.findAll(tenantId))
            rooms.add(room);
        return rooms;
    }

    @Transactional
    public Room add(Room room){
        return rRep.save(room);
    }

    @Transactional
    public Room add(RoomDto room){
        Room saveRoom = new Room(room.getFloor(),room.getNumber(),roomTypeRepository.findOne(room.getRoomType()),room.getTenantId());
        return rRep.save(saveRoom);
    }

    @Transactional
    public void delete(int id){
        rRep.delete(id);
    }

    @Transactional
    public Room update(Room room, Integer id){
        Room updRoom = rRep.findOne(id);
        updRoom.setFloor(room.getFloor());
        updRoom.setNumber(room.getNumber());
        updRoom.setRoomType(room.getRoomType());
        return rRep.save(updRoom);
    }

    @Transactional
    public Room update(RoomDto room, Integer id){
        Room updRoom = rRep.findOne(id);
        updRoom.setFloor(room.getFloor());
        updRoom.setNumber(room.getNumber());
        updRoom.setRoomType(roomTypeRepository.findOne(room.getRoomType()));
        return rRep.save(updRoom);
    }
}
