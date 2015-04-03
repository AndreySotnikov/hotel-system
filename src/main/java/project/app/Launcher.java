package project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import project.entity.*;
import project.entity.other.User_roles;
import project.entity.other.Users;
import project.repository.*;
import project.service.login.EncryptPassword;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andrey on 23.03.15.
 */
public class Launcher {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        EncryptPassword encryptPassword = new EncryptPassword();
        ApplicationContext context = SpringApplication.run(MainConfig.class,args);
        User_rolesRepository ur = context.getBean(User_rolesRepository.class);
        UsersRepository u = context.getBean(UsersRepository.class);
        AttributeRepository ar = context.getBean(AttributeRepository.class);
        CharacteristicRepository cr = context.getBean(CharacteristicRepository.class);

        RoomRepository roomRepository = context.getBean(RoomRepository.class);
        RoomTypeRepository roomTypeRepository = context.getBean(RoomTypeRepository.class);
        TimeTableRepository timeTableRepository = context.getBean(TimeTableRepository.class);
        RoomStateRepository roomStateRepository = context.getBean(RoomStateRepository.class);

        RoomState roomState = new RoomState("занято",1);
        roomStateRepository.save(roomState);
        RoomType roomType = new RoomType(1,"Люкс");
        roomTypeRepository.save(roomType);
        roomTypeRepository.save(new RoomType(1,"Эконом"));
        Room room = new Room(5,30,roomType,1);
        roomRepository.save(room);
        TimeTable timeTable = new TimeTable(room,roomState,5L,10L,1);
        timeTableRepository.save(timeTable);


        Characteristic cr1 = new Characteristic("wi-fi",1);
        Characteristic cr2 = new Characteristic("price",1);

        cr.save(cr1);
        cr.save(cr2);


        Users user = new Users("22",encryptPassword.encrypt("22"),1);
        u.save(user);
        ur.save(new User_roles("ROLE_USER", user));

        user = new Users("333",encryptPassword.encrypt("333"),1);
        u.save(user);
        ur.save(new User_roles("ROLE_USER", user));

        user = new Users("4444",encryptPassword.encrypt("4444"),1);
        u.save(user);
        ur.save(new User_roles("ROLE_USER", user));
    }
}
