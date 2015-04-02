package project.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.other.Users;
import project.repository.UsersRepository;

/**
* Created by andrey on 23.03.15.
*/
@Service
public class UsersService {
    @Autowired
    UsersRepository accountRepository;

    @Transactional
    public Users add(Users users){
        return accountRepository.save(users);
    }
}
