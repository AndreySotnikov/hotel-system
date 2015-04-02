package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Room;

import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
public interface RoomRepository extends CrudRepository<Room,Integer> {
    @Query("select r from Room r where r.tenantId=:tenantId")
    public List<Room> findAll(@Param("tenantId")int tenantId);
}
