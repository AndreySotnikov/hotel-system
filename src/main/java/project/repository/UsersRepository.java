package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import project.entity.Room;
import project.entity.other.Users;

import java.util.List;

/**
* Created by andrey on 23.03.15.
*/
public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Query("select u.userId from Users u where u.username=:user")
    public int getTenantId(@Param("user")String user);

    @Query("select u from Users u where u.userId=:tenantId")
    public Users getUser(@Param("tenantId")int tenantId);
}
