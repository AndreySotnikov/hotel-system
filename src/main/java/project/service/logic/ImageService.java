package project.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Image;
import project.repository.ImageRepository;

import java.util.List;

/**
 * Created by andrey on 10.05.15.
 */

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<Image> getAll(int roomId){
        return imageRepository.findAll(roomId);
    }

    public Image getOne(int id){
        return imageRepository.findOne(id);
    }

    public Image add(Image image){
        return imageRepository.save(image);
    }

    public void delete(int id){
        imageRepository.delete(id);
    }

    public Integer maxId(){
        Integer tmp = imageRepository.maxId();
        return (tmp==null)?0:tmp;
    }

    public Image update(int id, Image image){
        Image old = imageRepository.findOne(id);
        old.setUrl(image.getUrl());
        old.setThumbnailUrl(image.getThumbnailUrl());
        old.setDeleteUrl(image.getDeleteUrl());
        old.setDeleteType(image.getDeleteType());
        return imageRepository.save(old);
    }

    public List<Image> getUncommited(int tenantId){
        return imageRepository.getUncommited(tenantId);
    }
}
