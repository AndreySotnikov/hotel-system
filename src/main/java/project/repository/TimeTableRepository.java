package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.TimeTable;

/**
 * Created by andrey on 01.04.15.
 */
public interface TimeTableRepository extends CrudRepository<TimeTable,Integer> {
}
