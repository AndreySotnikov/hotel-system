package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import project.dto.Image;
import project.dto.ImageFile;
import project.dto.RoomDto;
import project.entity.Room;
import project.repository.UsersRepository;
import project.service.logic.RoomService;
import project.service.logic.RoomTypeService;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


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
        modelMap.addAttribute("roomTypeList",roomTypeService.getAll(tenantId));
        modelMap.addAttribute("tenantId", tenantId);
        return "room/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("room",roomService.getOne(id));
        modelMap.addAttribute("roomTypeList",roomTypeService.getAll(tenantId));
        modelMap.addAttribute("tenantId", tenantId);
        return "room/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @ModelAttribute("room") RoomDto room, ModelMap modelMap){
        roomService.update(room,id);
        return "redirect:/room/all";
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id){
        roomService.delete(id);
        return "redirect:/room/all";
    }

//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    @ResponseBody
//    public ImageFile pic(@RequestParam("files[]") MultipartFile file) throws IOException {
//            String workingDir = System.getProperty("user.dir");
//            String filePath = workingDir + "/src/main/resources/assets/pictures/" + file.getOriginalFilename();
//            byte dataToWrite[] = file.getBytes();
//            FileOutputStream out = new FileOutputStream(filePath);
//            out.write(dataToWrite);
//            out.close();
//        return new ImageFile(file.getOriginalFilename(), file.getSize());
//    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody Map upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        //log.debug("uploadPost called");
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<Image> list = new LinkedList<Image>();

        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            //log.debug("Uploading {}", mpf.getOriginalFilename());

            String newFilenameBase = UUID.randomUUID().toString();
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String workingDir = System.getProperty("user.dir");
            String fileUploadDirectory = workingDir + "/src/main/resources/assets/pictures/";
            String storageDirectory = fileUploadDirectory;
            String contentType = mpf.getContentType();
            byte dataToWrite[] = mpf.getBytes();
            String filePath = workingDir + "/src/main/resources/assets/pictures/" + mpf.getOriginalFilename();
            FileOutputStream out = new FileOutputStream(filePath);
            out.write(dataToWrite);
            out.close();
            File newFile = new File(storageDirectory + "/" + newFilename);
            try {
                mpf.transferTo(newFile);

                //BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
                String thumbnailFilename = newFilenameBase + "-thumbnail.png";
                File thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
                //ImageIO.write(thumbnail, "png", thumbnailFile);

                Image image = new Image();
                image.setName(mpf.getOriginalFilename());
                image.setThumbnailFilename(thumbnailFilename);
                image.setNewFilename(newFilename);
                image.setContentType(contentType);
                image.setSize(mpf.getSize());
                image.setThumbnailSize(thumbnailFile.length());
                //image = imageDao.create(image);

                image.setUrl("/picture/"+image.getId());
                image.setThumbnailUrl("/thumbnail/"+image.getId());
                image.setDeleteUrl("/delete/"+image.getId());
                image.setDeleteType("DELETE");

                list.add(image);

            } catch(IOException e) {
                //log.error("Could not upload file "+mpf.getOriginalFilename(), e);
            }

        }

        Map<String, Object> files = new HashMap<String, Object>();
        files.put("files", list);
        return files;
    }

}
