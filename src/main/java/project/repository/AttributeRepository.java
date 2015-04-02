package project.repository;

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
}
