package project.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by andrey on 08.04.15.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/timetable")
public class TimeTableController {
    @RequestMapping(value = "all")
    public String all(){
        return "timetable/all";
    }
}
