package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.entity.Attribute;
import project.entity.Characteristic;
import project.entity.RoomType;
import project.repository.RoomTypeRepository;
import project.repository.UsersRepository;
import project.service.logic.AttributeService;
import project.service.logic.CharacteristicService;

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

    @Autowired
    AttributeService attributeService;

    @Autowired
    CharacteristicService characteristicService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("roomTypeList", roomTypeRepository.findAll(tenantId));
        return "room-type/all";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addForm(ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("tenantId", tenantId);
        modelMap.addAttribute("characteristicList", characteristicService.getAll(tenantId));
        return "room-type/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestParam("chars") String chars,
                      @RequestParam("values") String values){
        String[] charList = chars.split(",");
        String[] valueList = values.split(",");
        RoomType roomType = new RoomType(Integer.parseInt(valueList[valueList.length-1]),valueList[0]);
        roomType = roomTypeRepository.save(roomType);
        for (int i=0;i<charList.length;i++){
            int id = characteristicService.getIdByName(charList[i],tenantId);
            Attribute a = new Attribute(roomType.getRoomTypeId(),id,valueList[i+1],Integer.parseInt(valueList[valueList.length-1]));
            attributeService.add(a);
        }
        System.err.println(chars);
        System.err.println(values);
        return "redirect:/room-type/all";
    }

    @RequestMapping(value = "addChar", method = RequestMethod.POST)
    public void addCharForm(@RequestParam("data") String data ){
        characteristicService.add(new Characteristic(data,tenantId));

    }
}
