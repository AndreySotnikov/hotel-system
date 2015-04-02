package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Attribute;

/**
 * Created by andrey on 01.04.15.
 */
public interface AttributeRepository extends CrudRepository<Attribute,Integer> {
}
