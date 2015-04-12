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
import java.util.Date;
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
        modelMap.addAttribute("stateList", roomStateRepository.findAll());
        modelMap.addAttribute("tenantId",tenantId);
        modelMap.addAttribute("count", 15);
        modelMap.addAttribute("roomList", roomService.getSortedAll(tenantId));
        return "timetable/all";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateForm(@RequestParam("id") Integer roomId,
                             @RequestParam("time") Long time,
                             ModelMap modelMap,
                             Principal principal){
        if (tenantId == 0)
            tenantId = usersRepository.getTenantId(principal.getName());
        TimeTable timeTable = timeTableService.getOne(roomId,time,tenantId);
        if (timeTable==null)
            return "redirect:/timetable/all";
        modelMap.addAttribute("timetableId",timeTable.getTimeTableId());
        modelMap.addAttribute("room", roomService.getOne(timeTable.getRoom().getRoomId()));
        modelMap.addAttribute("stateList", roomStateRepository.findAll());
        modelMap.addAttribute("state", timeTable.getRoomState());
        modelMap.addAttribute("tenantId",tenantId);
        modelMap.addAttribute("tenantId",tenantId);
        Date from_d = new Date(timeTable.getFrom());
        Date to_d = new Date(timeTable.getTo());
        String from = String.valueOf(from_d.getMonth()+1)+"/"+String.valueOf(from_d.getDate())+"/"+String.valueOf(from_d.getYear()+1900);
        String to = String.valueOf(to_d.getMonth()+1)+"/"+String.valueOf(to_d.getDate())+"/"+String.valueOf(to_d.getYear()+1900);
        modelMap.addAttribute("date",from + " - " + to);
        return "timetable/update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(//@PathVariable("id") Integer id,
                         @RequestParam("stateId") Integer stateId,
                         @RequestParam("daterange") String daterange,
                         @RequestParam("tenantId") String tenantId,
                         @RequestParam("timetableId") Integer timetableId){
        String[] splitStr = daterange.split(" - ");
        TimeTable timeTable = timeTableService.getOne(timetableId);
        String[] array_from = splitStr[0].split("/");
        String[] array_to = splitStr[1].split("/");
        Long from = new GregorianCalendar(Integer.parseInt(array_from[2]),Integer.parseInt(array_from[0])-1,Integer.parseInt(array_from[1])).getTimeInMillis();
        Long to = new GregorianCalendar(Integer.parseInt(array_to[2]),Integer.parseInt(array_to[0])-1,Integer.parseInt(array_to[1])).getTimeInMillis();
        //Long from = new GregorianCalendar(Integer.parseInt(splitStr[0].substring(6,10)),Integer.parseInt(splitStr[0].substring(0, 2))-1,Integer.parseInt(splitStr[0].substring(3,5))).getTimeInMillis();
        //Long to = new GregorianCalendar(Integer.parseInt(splitStr[1].substring(6, 10)),Integer.parseInt(splitStr[1].substring(0, 2))-1,Integer.parseInt(splitStr[1].substring(3, 5))).getTimeInMillis();
        timeTable.setFrom(from);
        timeTable.setTo(to);
        timeTable.setRoomState(roomStateRepository.findOne(stateId));
        timeTableService.add(timeTable);
        //timeTableService.add(new TimeTable(roomService.getOne(id),roomStateRepository.findOne(stateId),from,to,Integer.parseInt(tenantId)));
        return "redirect:/timetable/all";
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
        String[] array_from = splitStr[0].split("/");
        String[] array_to = splitStr[1].split("/");
        Long from = new GregorianCalendar(Integer.parseInt(array_from[2]),Integer.parseInt(array_from[0])-1,Integer.parseInt(array_from[1])).getTimeInMillis();
        Long to = new GregorianCalendar(Integer.parseInt(array_to[2]),Integer.parseInt(array_to[0])-1,Integer.parseInt(array_to[1])).getTimeInMillis();
        //Long from = new GregorianCalendar(Integer.parseInt(splitStr[0].substring(6,10)),Integer.parseInt(splitStr[0].substring(0, 2))-1,Integer.parseInt(splitStr[0].substring(3,5))).getTimeInMillis();
        //Long to = new GregorianCalendar(Integer.parseInt(splitStr[1].substring(6, 10)),Integer.parseInt(splitStr[1].substring(0, 2))-1,Integer.parseInt(splitStr[1].substring(3, 5))).getTimeInMillis();
        timeTableService.add(new TimeTable(roomService.getOne(id),roomStateRepository.findOne(stateId),from,to,Integer.parseInt(tenantId)));
        return "redirect:/timetable/all";
    }
}
