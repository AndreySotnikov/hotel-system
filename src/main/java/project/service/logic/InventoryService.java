package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Inventory;
import project.repository.InventoryRepository;
import java.util.List;

/**
 * Created by Green-L on 14.04.2015.
 */
public class InventoryService {
    @Autowired
    InventoryRepository irep;

    @Transactional
    public List<Inventory> getAll(int tenantId) {
        return irep.findAll(tenantId);
    }

    @Transactional
    public Inventory getOne(Integer id) {
        return irep.findOne(id);
    }

    @Transactional
    public void delete(int id) {
        irep.delete(id);
    }

    @Transactional
    public Inventory add(Inventory inventory) {
        return irep.save(inventory);
    }

    @Transactional
    public Inventory update(Integer id, String name) {
        Inventory updatingInventory = irep.findOne(id);
        updatingInventory.setName(name);
        return irep.save(updatingInventory);
    }
}
