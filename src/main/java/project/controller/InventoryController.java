package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.entity.Inventory;
import project.repository.UsersRepository;
import project.service.logic.InventoryService;

import java.security.Principal;

/**
 * Created by Green-L on 14.04.2015.
 */
@org.springframework.stereotype.Controller
//@Scope("session")
@RequestMapping("/inventory")
public class InventoryController {

    private int tenantId;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(ModelMap modelMap,Principal principal){
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("inventoryList", inventoryService.getAll(tenantId));
        return "inventory/all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(ModelMap modelMap) {
        modelMap.addAttribute("tenantId", tenantId);
        return "inventory/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("inventory", inventoryService.getOne(id));
        return "inventory/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @RequestParam ("name") String name){
        inventoryService.update(id, name);
        return "redirect:/inventory/all";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("inventory") Inventory inventory){
        inventoryService.add(inventory);
        return "redirect:/inventory/all";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id){
        inventoryService.delete(id);
        return "redirect:/inventory/all";
    }

}
