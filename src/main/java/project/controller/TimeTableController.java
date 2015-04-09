package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.entity.TimeTable;
import project.repository.RoomStateRepository;
import project.repository.TimeTableRepository;
import project.repository.UsersRepository;
import project.service.logic.RoomService;
import project.service.logic.TimeTableService;

import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * Created by andrey on 08.04.15.
 */
@org.springframework.stereotype.Controller
@Scope("session")
@RequestMapping(value = "/timetable")
public class TimeTableController {

    int tenantId;

    @Autowired
    RoomService roomService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TimeTableService timeTableService;

    @Autowired
    RoomStateRepository roomStateRepository;

    @RequestMapping(value = "all")
    public String all(ModelMap modelMap, Principal principal) {
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        System.err.println(timeTableService.getAll(tenantId));
        modelMap.addAttribute("timeTableList", timeTableService.getAll(tenantId));
        modelMap.addAttribute("count", 15);
        modelMap.addAttribute("roomList", roomService.getSortedAll(tenantId));
        return "timetable/all";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addForm(@PathVariable("id") Integer id, ModelMap modelMap, Principal principal){
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("room", roomService.getOne(id));
        modelMap.addAttribute("stateList", roomStateRepository.findAll());
        modelMap.addAttribute("tenantId",tenantId);
        return "timetable/add";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(@PathVariable("id") Integer id,
                      @RequestParam("stateId") Integer stateId,
                      @RequestParam("daterange") String daterange,
                      @RequestParam("tenantId") String tenantId){
        String[] splitStr = daterange.split(" - ");
        Long from = new GregorianCalendar(Integer.parseInt(splitStr[0].substring(6,10)),Integer.parseInt(splitStr[0].substring(0, 2)),Integer.parseInt(splitStr[0].substring(3,5))).getTimeInMillis();
        Long to = new GregorianCalendar(Integer.parseInt(splitStr[1].substring(6, 10)),Integer.parseInt(splitStr[1].substring(0, 2)),Integer.parseInt(splitStr[1].substring(3, 5))).getTimeInMillis();
        timeTableService.add(new TimeTable(roomService.getOne(id),roomStateRepository.findOne(stateId),from,to,Integer.parseInt(tenantId)));
        return "redirect:/timetable/all";
    }
}
