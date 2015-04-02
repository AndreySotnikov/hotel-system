package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.RoomState;

/**
 * Created by andrey on 01.04.15.
 */
public interface RoomStateRepository extends CrudRepository<RoomState,Integer> {
}
