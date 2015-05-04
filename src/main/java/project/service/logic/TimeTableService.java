package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.TimeTable;
import project.repository.TimeTableRepository;
import project.repository.TimeTableRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Service
public class TimeTableService {
    @Autowired
    TimeTableRepository ttRep;

    public TimeTable getOne(int roomId,long time, int tenantId){
        //return ttRep.findOne(roomId);
        return ttRep.findOne(tenantId,time,roomId);
    }

    public TimeTable getOne(int id){
        return ttRep.findOne(id);
    }

    public List<TimeTable> getAll(int tenantId){
        List<TimeTable> timeTables = new ArrayList<TimeTable>();
        for (TimeTable timeTable : ttRep.findAll(tenantId))
            timeTables.add(timeTable);
        return timeTables;
    }


    public TimeTable add(TimeTable timeTable){
        return ttRep.save(timeTable);
    }

    public void delete(int id){
        ttRep.delete(id);
    }

    public TimeTable update(TimeTable timeTable, Integer id){
        TimeTable updTimeTable = ttRep.findOne(id);
        updTimeTable.setFrom(timeTable.getFrom());
        updTimeTable.setTo(timeTable.getTo());
        updTimeTable.setRoom(timeTable.getRoom());
        updTimeTable.setRoomState(timeTable.getRoomState());
        return ttRep.save(updTimeTable);
    }

    public List<TimeTable> getAllByRoom(int roomId){
        return ttRep.findAllByRoom(roomId);
    }

    public int[] getInventory(int id){
        return ttRep.getInventory(id);
    }
}
