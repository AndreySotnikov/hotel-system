package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public Attribute add(Attribute attribute){
        return attributeRepository.save(attribute);
    }

    public void delete(int id){
        attributeRepository.delete(id);
    }

    public Attribute update(Attribute attribute, Integer id){
        Attribute updAtrt = attributeRepository.findOne(id);
        updAtrt.setValue(attribute.getValue());
        return attributeRepository.save(updAtrt);
    }
}
