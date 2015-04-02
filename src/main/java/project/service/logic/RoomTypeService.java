package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Room;
import project.entity.RoomType;
import project.repository.RoomRepository;
import project.repository.RoomTypeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Service
public class RoomTypeService {
    @Autowired
    RoomTypeRepository rTRep;

    public RoomType getOne(int id){
        return rTRep.findOne(id);
    }

    public List<RoomType> getAll(int tenantId){
        List<RoomType> roomTypes = new ArrayList<RoomType>();
        for (RoomType roomType : rTRep.findAll(tenantId))
            roomTypes.add(roomType);
        return roomTypes;
    }

    public RoomType add(RoomType roomType){
        return rTRep.save(roomType);
    }

    public void delete(int id){
        rTRep.delete(id);
    }

    public RoomType update(RoomType roomType, Integer id){
        RoomType updRoomType = rTRep.findOne(id);
        updRoomType.setName(roomType.getName());
        return rTRep.save(updRoomType);
    }
}
