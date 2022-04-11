package com.student.studentservice.domain.FaceModel;


import java.io.Serializable;

/**
 *  用来注明一个 Image
 */
public class ImageInfo implements Serializable {
   private String image;
   private String image_type = "BASE64";

    public ImageInfo() {
    }

    public ImageInfo(String image, String image_type) {
        this.image = image;
        this.image_type = image_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }
}
