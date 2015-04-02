package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.repository.RoomTypeRepository;
import project.repository.UsersRepository;

import java.security.Principal;

/**
 * Created by andrey on 02.04.15.
 */
@org.springframework.stereotype.Controller
@Scope("session")
@RequestMapping("/room-type")
public class RoomTypeController {

    private int tenantId;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("roomType", roomTypeRepository.findAll(tenantId));
        return "room-type/all";
    }
}
