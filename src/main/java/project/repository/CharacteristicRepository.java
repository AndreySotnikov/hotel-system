package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Characteristic;

import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
public interface CharacteristicRepository extends CrudRepository<Characteristic,Integer>{
    @Query("select c from Characteristic c where c.tenantId=:tenantId")
    public List<Characteristic> findAll(@Param("tenantId")int tenantId);

    @Query("select c.characteristicId from Characteristic c where c.tenantId=:tenantId and c.name=:name")
    public int getIdByName(@Param("tenantId")int tenantId, @Param("name") String name);


}
