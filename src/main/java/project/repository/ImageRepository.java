package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Image;

import java.util.List;

/**
 * Created by andrey on 10.05.15.
 */
public interface ImageRepository extends CrudRepository<Image,Integer>{
    @Query("select i from Image i where i.room.roomId=:id")
    public List<Image> findAll(@Param("id")int id);

    @Query(value = "select max(IMAGE_ID) from IMAGE", nativeQuery = true)
    public Integer maxId();

    @Query("select i from Image i where i.tenantId=:tenantId and i.room is null")
    public List<Image> getUncommited(@Param("tenantId")int tenantId);
}
