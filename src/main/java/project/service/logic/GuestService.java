package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Guest;
import project.repository.GuestRepository;

import java.util.List;

/**
 * Created by andrey on 15.04.15.
 */
@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Transactional
    public Guest getOne(int id){
        return guestRepository.findOne(id);
    }

    @Transactional
    public List<Guest> getAll(int tenantId){
        return guestRepository.findAll(tenantId);
    }

    @Transactional
    public Guest add(Guest guest){
        return guestRepository.save(guest);
    }

    @Transactional
    public void delete(int id){
        guestRepository.delete(id);
    }

    @Transactional
    public Guest update(int id, String fio, String phone, String email){
        Guest guest = guestRepository.findOne(id);
        guest.setEmail(email);
        guest.setFio(fio);
        guest.setPhone(phone);
        return guestRepository.save(guest);
    }
}
