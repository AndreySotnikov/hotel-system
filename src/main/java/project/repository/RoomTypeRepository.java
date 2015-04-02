package project.repository;


import org.springframework.data.repository.CrudRepository;
import project.entity.RoomType;

/**
 * Created by andrey on 01.04.15.
 */
public interface RoomTypeRepository extends CrudRepository<RoomType,Integer> {
}
