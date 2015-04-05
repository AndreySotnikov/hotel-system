package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Characteristic;
import project.repository.CharacteristicRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 01.04.15.
 */
@Service
public class CharacteristicService {
    @Autowired
    CharacteristicRepository cRep;

    public Characteristic getOne(int id){
        return cRep.findOne(id);
    }

    public List<Characteristic> getAll(int tenantId){
        List<Characteristic> characteristics = new ArrayList<Characteristic>();
        for (Characteristic characteristic : cRep.findAll(tenantId))
            characteristics.add(characteristic);
        return characteristics;
    }


    public Characteristic add(Characteristic characteristic){
        return cRep.save(characteristic);
    }

    public void delete(int id){
        cRep.delete(id);
    }

    public Characteristic update(Characteristic characteristic, Integer id){
        Characteristic updChar = cRep.findOne(id);
        updChar.setName(characteristic.getName());
        return cRep.save(updChar);
    }

    public int getIdByName(String name, int tenantId){
        return cRep.getIdByName(tenantId,name);
    }
}
