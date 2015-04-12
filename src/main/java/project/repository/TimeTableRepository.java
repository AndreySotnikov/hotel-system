package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.RoomType;
import project.entity.TimeTable;

import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
public interface TimeTableRepository extends CrudRepository<TimeTable,Integer> {
    @Query("select tt from TimeTable tt where tt.tenantId=:tenantId")
    public List<TimeTable> findAll(@Param("tenantId")int tenantId);

    @Query("select tt from TimeTable tt where tt.tenantId=:tenantId and tt.to>=:time and tt.from<=:time and tt.room.roomId=:roomId")
    public TimeTable findOne(@Param("tenantId") int tenantId, @Param("time") long time, @Param("roomId") int roomId);

//    @Query("select tt from TimeTable tt where tt.room.roomId=:roomId")
//    public TimeTable findOne(@Param("roomId") int roomId);
}
