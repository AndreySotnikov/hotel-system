package project.dto;

/**
 * Created by andrey on 08.05.15.
 */


/*
url: "http://url.to/file/or/page",
        thumbnail_url: "http://url.to/thumnail.jpg ",
        name: "thumb2.jpg",
        type: "image/jpeg",
        size: 46353,
        delete_url: "http://url.to/delete /file/",
        delete_type: "DELETE"
*/

public class ImageFile {
    private String name;
    private String type;
    private long size;

    public ImageFile() {
    }

    public ImageFile(String name, long size) {
        this.name = name;
        this.type = "image/jpeg";
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageFile imageFile = (ImageFile) o;

        if (size != imageFile.size) return false;
        if (name != null ? !name.equals(imageFile.name) : imageFile.name != null) return false;
        if (type != null ? !type.equals(imageFile.type) : imageFile.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + size;
        return (int)result;
    }

    @Override
    public String toString() {
        return "ImageFile{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
