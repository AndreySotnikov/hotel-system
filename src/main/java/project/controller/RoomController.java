package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.dto.RoomDto;
import project.entity.Room;
import project.repository.UsersRepository;
import project.service.logic.RoomService;
import project.service.logic.RoomTypeService;

import javax.annotation.PostConstruct;
import java.security.Principal;


/**
 * Created by andrey on 23.03.15.
 */

@org.springframework.stereotype.Controller
@RequestMapping("/room")
@Scope("session")
public class RoomController {

    int tenantId;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoomTypeService roomTypeService;


    @Autowired
    RoomService roomService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(ModelMap modelMap) {
        modelMap.addAttribute("roomTypeList",roomTypeService.getAll());
        modelMap.addAttribute("tenantId", tenantId);
        return "room/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("room") RoomDto room){
        roomService.add(room);
        return "redirect:/room/all";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("roomList",roomService.getAll(tenantId));
        return "room/all";
    }
}
