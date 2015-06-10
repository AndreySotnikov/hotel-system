package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.entity.other.User_roles;
import project.entity.other.Users;
import project.repository.UsersRepository;
import project.service.login.EncryptPassword;
import project.service.login.UsersService;
import project.service.login.Users_rolesService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;


/**
 * Created by andrey on 23.03.15.
 */

@org.springframework.stereotype.Controller
@RequestMapping("/")
@Scope("session")
public class Controller {
    int tenantId;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    Users_rolesService users_rolesService;


    @Autowired
    EncryptPassword encryptPassword;

    @RequestMapping(value = "/loginuser", method = RequestMethod.GET)
    public String login2(){
        return "login/login";
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String trans(Principal principal){
        tenantId = usersRepository.getTenantId(principal.getName());
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String login3(ModelMap modelMap) {
        Users userok = usersRepository.getUser(tenantId);
        String user = null;
        if (userok != null)
            user = userok.getUsername();
        if (user != null)
            modelMap.addAttribute("user",user);
        return "home";
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String login4() {
        return "fail";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String login5() {
        return "home";
    }

    @RequestMapping(value = "/authorized", method = RequestMethod.GET)
    public String login6() {
        return "authorized";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(){
        return "login/registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Users user = new Users(username,encryptPassword.encrypt(password),1);
        usersService.add(user);
        users_rolesService.add(new User_roles("ROLE_USER",user));

        return "home";
    }

    @RequestMapping("/administration")
    public String administration(){
        return "redirect:/room/all";
    }
}
