package project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import project.entity.*;
import project.entity.other.User_roles;
import project.entity.other.Users;
import project.repository.*;
import project.service.logic.GuestService;
import project.service.login.EncryptPassword;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.GregorianCalendar;

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
        GuestRepository guestRepository = context.getBean(GuestRepository.class);

        RoomRepository roomRepository = context.getBean(RoomRepository.class);
        RoomTypeRepository roomTypeRepository = context.getBean(RoomTypeRepository.class);
        TimeTableRepository timeTableRepository = context.getBean(TimeTableRepository.class);
        RoomStateRepository roomStateRepository = context.getBean(RoomStateRepository.class);
        InventoryRepository inventoryRepository = context.getBean(InventoryRepository.class);


        RoomState roomState1 = new RoomState("Занято",1);
        RoomState roomState3 = new RoomState("Оплачено",1);
        RoomState roomState4 = new RoomState("Бронь",1);

        roomStateRepository.save(roomState1);
        roomStateRepository.save(roomState3);
        roomStateRepository.save(roomState4);

        RoomType roomType = new RoomType(1,"Люкс");
        RoomType roomType1 = new RoomType(1,"Эконом");
        roomTypeRepository.save(roomType);
        roomTypeRepository.save(roomType1);
        Room room = new Room(5,30,roomType,1);

        roomRepository.save(room);
        Room room1 = new Room(1,10,roomType1,1);
        Room room2 = new Room(1,5,roomType1,1);
        Room room3 = new Room(3,8,roomType,1);
        Room room4 = new Room(2,4,roomType,1);
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);

        Guest guest = new Guest("qqqq","123456","qwerty@mail.ru",1);
        guestRepository.save(guest);

        TimeTable timeTable1 = new TimeTable(room,roomState1,new GregorianCalendar(2015,3,8).getTimeInMillis(),new GregorianCalendar(2015,3,22).getTimeInMillis(),1);
        timeTable1.setGuest(guest);
        //TimeTable timeTable2 = new TimeTable(room,roomState1,new GregorianCalendar(2015,3,5).getTimeInMillis(),new GregorianCalendar(2015,3,10).getTimeInMillis(),1);
        TimeTable timeTable3 = new TimeTable(room1,roomState1,new GregorianCalendar(2015,3,5).getTimeInMillis(),new GregorianCalendar(2015,3,9).getTimeInMillis(),1);
        timeTable3.setGuest(guest);
        //TimeTable timeTable4 = new TimeTable(room1,roomState3,new GregorianCalendar(2015,3,8).getTimeInMillis(),new GregorianCalendar(2015,3,18).getTimeInMillis(),1);
        TimeTable timeTable5 = new TimeTable(room2,roomState3,new GregorianCalendar(2015,3,23).getTimeInMillis(),new GregorianCalendar(2015,3,25).getTimeInMillis(),1);
        timeTable5.setGuest(guest);
        //TimeTable timeTable6 = new TimeTable(room2,roomState3,new GregorianCalendar(2015,3,16).getTimeInMillis(),new GregorianCalendar(2015,3,28).getTimeInMillis(),1);
        TimeTable timeTable7 = new TimeTable(room3,roomState4,new GregorianCalendar(2015,3,15).getTimeInMillis(),new GregorianCalendar(2015,3,22).getTimeInMillis(),1);
        timeTable7.setGuest(guest);
        //TimeTable timeTable8 = new TimeTable(room3,roomState4,new GregorianCalendar(2015,0,5).getTimeInMillis(),new GregorianCalendar(2015,1,20).getTimeInMillis(),1);
        TimeTable timeTable9 = new TimeTable(room4,roomState1,new GregorianCalendar(2015,3,15).getTimeInMillis(),new GregorianCalendar(2015,3,15).getTimeInMillis(),1);
        timeTable9.setGuest(guest);
        //TimeTable timeTable10 = new TimeTable(room4,roomState4,new GregorianCalendar(2015,3,14).getTimeInMillis(),new GregorianCalendar(2015,3,16).getTimeInMillis(),1);

        timeTableRepository.save(timeTable1);
//        timeTableRepository.save(timeTable2);
        timeTableRepository.save(timeTable3);
//        timeTableRepository.save(timeTable4);
        timeTableRepository.save(timeTable5);
//        timeTableRepository.save(timeTable6);
        timeTableRepository.save(timeTable7);
//        timeTableRepository.save(timeTable8);
        timeTableRepository.save(timeTable9);
//        timeTableRepository.save(timeTable10);

        inventoryRepository.save(new Inventory("Inv1",1));
        inventoryRepository.save(new Inventory("Inv2",1));
        inventoryRepository.save(new Inventory("Inv3",1));


        Characteristic cr1 = new Characteristic("wi-fi",0);
        Characteristic cr2 = new Characteristic("price",0);
        Characteristic cr3 = new Characteristic("beds",0);
        Characteristic cr4 = new Characteristic("guests",0);

        Attribute at1 = new Attribute(1,1,"yes",1);
        Attribute at2 = new Attribute(1,2,"300",1);

        cr.save(cr1);
        cr.save(cr2);
        cr.save(cr3);
        cr.save(cr4);

        ar.save(at1);
        ar.save(at2);

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
