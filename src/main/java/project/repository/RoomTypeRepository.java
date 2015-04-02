package project.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.RoomType;

import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
public interface RoomTypeRepository extends CrudRepository<RoomType,Integer> {
    @Query("select rt from RoomType rt where rt.tenantId=:tenantId")
    public List<RoomType> findAll(@Param("tenantId")int tenantId);
}
