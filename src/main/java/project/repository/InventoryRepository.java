package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Inventory;

import java.util.List;

/**
 * Created by Green-L on 14.04.2015.
 */
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    @Query("select i from Inventory i where i.tenantId=:tenantId")
    public List<Inventory> findAll(@Param("tenantId")int tenantId);
}
