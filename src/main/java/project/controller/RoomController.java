package project.controller;

import org.h2.util.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import project.entity.Image;
import project.dto.RoomDto;
import project.entity.Room;
import project.repository.UsersRepository;
import project.service.logic.ImageService;
import project.service.logic.RoomService;
import project.service.logic.RoomTypeService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
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

    @Autowired
    ImageService imageService;

    String fileUploadDirectory;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(ModelMap modelMap, Principal principal) {
        if (tenantId==0)
            tenantId=usersRepository.getTenantId(principal.getName());
        modelMap.addAttribute("roomTypeList",roomTypeService.getAll(tenantId));
        modelMap.addAttribute("tenantId", tenantId);
        return "room/add";
    }

    @RequestMapping(value = "{id}/getpic", method = RequestMethod.GET)
    public @ResponseBody List<Image> getPic(@PathVariable("id") int id){
        return imageService.getAll(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("room",roomService.getOne(id));
        modelMap.addAttribute("imageList", imageService.getAll(id));
        modelMap.addAttribute("roomTypeList",roomTypeService.getAll(tenantId));
        modelMap.addAttribute("tenantId", tenantId);
        return "room/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, @ModelAttribute("room") RoomDto room, ModelMap modelMap){
        Room r = roomService.update(room,id);
        List<Image> images = imageService.getUncommited(tenantId);
        for (Image image : images){
            Image i = imageService.getOne(image.getImageId());
            i.setRoom(r);
            imageService.add(i);
        }
        return "redirect:/room/all";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("room") RoomDto room){
        Room r = roomService.add(room);
        List<Image> images = imageService.getUncommited(tenantId);
        for (Image image : images){
            Image i = imageService.getOne(image.getImageId());
            i.setRoom(r);
            imageService.add(i);
        }
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
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
            fileUploadDirectory = workingDir + "/src/main/resources/assets/pictures/";
            String storageDirectory = fileUploadDirectory;
            String contentType = mpf.getContentType();
            byte dataToWrite[] = mpf.getBytes();
            String filePath = workingDir + "/src/main/resources/assets/pictures/" + newFilename;
            FileOutputStream out = new FileOutputStream(filePath);
            out.write(dataToWrite);
            out.close();
            File newFile = new File(storageDirectory + "/" + newFilename);
            try {
                mpf.transferTo(newFile);

                BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 80);
                String thumbnailFilename = newFilenameBase + "-thumbnail.png";
                File thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
                ImageIO.write(thumbnail, "png", thumbnailFile);

//                BufferedImage img = Scalr.resize(ImageIO.read(newFile), 300);
//                File imgFile = new File(storageDirectory + "/" + newFilename);
//                ImageIO.write(img, "png", imgFile);

                Image image = new Image();
                image.setName(mpf.getOriginalFilename());
                image.setThumbnailFilename(thumbnailFilename);
                image.setNewFilename(newFilename);
                image.setContentType(contentType);
                image.setSize(mpf.getSize());
                image.setThumbnailSize(thumbnailFile.length());
                image.setTenantId(tenantId);
                //image = imageDao.create(image);

                Integer maxId = imageService.maxId();

                image.setUrl("/room/picture/"+(maxId+1));
                image.setThumbnailUrl("/room/thumbnail/"+(maxId+1));
                image.setDeleteUrl("/room/delete/"+(maxId+1));
                image.setDeleteType("DELETE");

                imageService.add(image);

                list.add(image);

            } catch(IOException e) {
                //log.error("Could not upload file "+mpf.getOriginalFilename(), e);
            }

        }

        Map<String, Object> files = new HashMap<String, Object>();
        files.put("files", list);
        return files;
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public void picture(HttpServletResponse response, @PathVariable int id) {
        Image image = imageService.getOne(id);
        File imageFile = new File(fileUploadDirectory+"/"+image.getNewFilename());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
            //log.error("Could not show picture "+id, e);
        }
    }
    @RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
    public void thumbnail(HttpServletResponse response, @PathVariable int id) {
        Image image = imageService.getOne(id);
        File imageFile = new File(fileUploadDirectory+"/"+image.getThumbnailFilename());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getThumbnailSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
            //log.error("Could not show thumbnail "+id, e);
        }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody List delete(@PathVariable int id) {
        Image image = imageService.getOne(id);
        File imageFile = new File(fileUploadDirectory+"/"+image.getNewFilename());
        imageFile.delete();
        File thumbnailFile = new File(fileUploadDirectory+"/"+image.getThumbnailFilename());
        thumbnailFile.delete();
        imageService.delete(id);
        List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
        Map<String, Object> success = new HashMap<String,Object>();
        success.put("success", true);
        results.add(success);
        return results;
    }

}
