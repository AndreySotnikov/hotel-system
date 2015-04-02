package project.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.other.User_roles;
import project.repository.User_rolesRepository;

/**
 * Created by andrey on 24.03.15.
 */
@Service
public class Users_rolesService {
    @Autowired
    User_rolesRepository user_rolesRepository;

    @Transactional
    public User_roles add(User_roles user_roles){
        return user_rolesRepository.save(user_roles);
    }
}
