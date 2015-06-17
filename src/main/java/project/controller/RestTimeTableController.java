package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.entity.TimeTable;
import project.repository.UsersRepository;
import project.service.logic.TimeTableService;

import java.security.Principal;
import java.util.List;

/**
 * Created by andrey on 09.04.15.
 */
@RestController
@Scope("session")
@RequestMapping("/rest-time-table")
public class RestTimeTableController {

    @Autowired
    TimeTableService timeTableService;

    @Autowired
    UsersRepository usersRepository;

    int tenantId;

    @RequestMapping("/all")
    public List<TimeTable> all(Principal principal){
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        return timeTableService.getAll(tenantId);
    }

    @RequestMapping("/room/{id}")
    public List<TimeTable> one(@PathVariable("id") int id, Principal principal){
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        List<TimeTable> lst = timeTableService.getAllByRoom(id, tenantId);
        System.err.println(lst);
        return lst;
    }

    @RequestMapping("/one")
    public TimeTable one(@RequestParam("id") Integer roomId, @RequestParam("time") Long time,Principal principal){
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        TimeTable timeTable = timeTableService.getOne(roomId,time,tenantId);
        return timeTable;
    }

    @RequestMapping("inventory")
    public int[] getInventory(@RequestParam("id") int id){

        int[] inv = timeTableService.getInventory(id);
        return inv;
    }
}
