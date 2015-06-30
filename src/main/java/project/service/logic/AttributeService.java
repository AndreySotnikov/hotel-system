package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Attribute;
import project.repository.AttributeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 02.04.15.
 */
@Service
public class AttributeService {
    @Autowired
    AttributeRepository attributeRepository;

    public Attribute getOne(int id){
        return attributeRepository.findOne(id);
    }

    public List<Attribute> getAll(int tenantId){
        List<Attribute> attributes = new ArrayList<Attribute>();
        for (Attribute attribute : attributeRepository.findAll(tenantId))
            attributes.add(attribute);
        return attributes;
    }


    @Transactional
    public Attribute add(Attribute attribute){
        return attributeRepository.save(attribute);
    }

    @Transactional
    public void delete(int id){
        attributeRepository.delete(id);
    }

    @Transactional
    public Attribute update(Attribute attribute, Integer id){
        Attribute updAtr = attributeRepository.findOne(id);
        updAtr.setValue(attribute.getValue());
        return attributeRepository.save(updAtr);
    }

    @Transactional
    public List<Integer> getAttributeList(int roomTypeId){
        return attributeRepository.getAttributeList(roomTypeId);
    }


    @Transactional
    public List<String> getValueList(int roomTypeId){
        return attributeRepository.getValueList(roomTypeId);
    }

    @Transactional
    public Attribute updateAdd(int roomTypeId,int characteristicId, String value, int tenantId){
//        Attribute updAttribute = attributeRepository.findOne(roomTypeId,characteristicId);
//        if (updAttribute==null)
         Attribute   updAttribute = add(new Attribute(roomTypeId,characteristicId,value,tenantId));
//        else {
//            updAttribute.setValue(value);
//            updAttribute = add(updAttribute);
//        }
        return updAttribute;
    }

    @Transactional
    public void deleteRoomType(int roomTypeId){
        attributeRepository.deleteRoomType(roomTypeId);
    }

//    public Integer size(){
//        return attributeRepository.maxId();
//    }
}
