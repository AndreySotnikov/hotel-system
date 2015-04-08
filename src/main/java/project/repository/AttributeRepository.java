package project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Attribute;

import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
public interface AttributeRepository extends CrudRepository<Attribute,Integer> {
    @Query("select at from Attribute at where at.tenantId=:tenantId")
    public List<Attribute> findAll(@Param("tenantId")int tenantId);

    @Query("select a.typeCharId.characteristicId from Attribute a where a.typeCharId.roomTypeId=:roomTypeId")
    public List<Integer> getAttributeList(@Param("roomTypeId") int roomTypeId);

    @Query("select a.value from Attribute a where a.typeCharId.roomTypeId=:roomTypeId")
    public List<String> getValueList(@Param("roomTypeId") int roomTypeId);

    @Query("select at from Attribute at where at.typeCharId.roomTypeId=:roomTypeId and at.typeCharId.characteristicId=:characteristicId")
    public Attribute findOne(@Param("roomTypeId") int roomTypeId, @Param("characteristicId") int characteristicId);

    @Modifying
    @Query("delete from Attribute at where at.typeCharId.roomTypeId=:roomTypeId")
    public void deleteRoomType(@Param("roomTypeId") int roomTypeId);

}
