package com.example.imageuploader.model;

public class Image {

    private String imageName;
    private String imageId;

    public Image() {
    }

    public Image(String imageName, String imageId) {
        this.imageName = imageName;
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
