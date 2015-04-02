package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Characteristic;

/**
 * Created by andrey on 01.04.15.
 */
public interface CharacteristicRepository extends CrudRepository<Characteristic,Integer>{
}
