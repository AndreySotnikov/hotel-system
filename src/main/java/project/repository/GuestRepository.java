package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Guest;

import java.util.List;

/**
 * Created by andrey on 15.04.15.
 */
public interface GuestRepository extends CrudRepository<Guest,Integer>{
    @Query("select g from Guest g where g.tenantId=:tenantId")
    public List<Guest> findAll(@Param("tenantId")int tenantId);
}
