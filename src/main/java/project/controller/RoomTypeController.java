package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
import project.service.logic.RoomTypeService;

import java.security.Principal;
import java.util.List;

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

    @Autowired
    RoomTypeService roomTypeService;

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
        System.err.println(chars);
        System.err.println(values);
        tenantId = Integer.parseInt(valueList[valueList.length-1]);
        RoomType roomType = new RoomType(tenantId,valueList[0]);
        roomType = roomTypeRepository.save(roomType);
        int roomTypeId = roomType.getRoomTypeId();
        for (int i=0;i<charList.length;i++){
            Attribute a = new Attribute(roomTypeId, Integer.parseInt(charList[i]),valueList[i+1],tenantId);
            attributeService.add(a);
        }
        return "redirect:/room-type/all";
    }

    @RequestMapping(value = "addChar", method = RequestMethod.POST)
    public String addCharForm(ModelMap modelMap, @RequestParam("data") String data ){
        Characteristic characteristic =  characteristicService.add(new Characteristic(data, tenantId));
        modelMap.addAttribute("id", characteristic.getCharacteristicId());
        return "part/answer";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("tenantId", tenantId);
        modelMap.addAttribute("characteristicList", characteristicService.getAll(tenantId));
        modelMap.addAttribute("charList", attributeService.getAttributeList(id));
        modelMap.addAttribute("roomType", roomTypeRepository.findOne(id));
        modelMap.addAttribute("valueList", attributeService.getValueList(id));
        return "room-type/update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, @RequestParam("chars") String chars,
                      @RequestParam("values") String values){
        String[] charList = chars.split(",");
        String[] valueList = values.split(",");
        System.err.println(chars);
        System.err.println(values);
        tenantId = Integer.parseInt(valueList[valueList.length-1]);
        RoomType roomType = roomTypeService.update(valueList[0], id);
        int roomTypeId = roomType.getRoomTypeId();
        attributeService.deleteRoomType(roomTypeId);
        for (int i=0;i<charList.length;i++){
            attributeService.updateAdd(roomTypeId, Integer.parseInt(charList[i]), valueList[i + 1], tenantId);
        }
        return "redirect:/room-type/all";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        attributeService.deleteRoomType(id);
        roomTypeService.delete(id);
        return "redirect:/room-type/all";
    }
}
