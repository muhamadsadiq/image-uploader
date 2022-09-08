package com.example.imageuploader.dao;

import com.example.imageuploader.model.Image;

import java.util.List;

public interface ImageDAO {


    public boolean addImage(Image image);


    public boolean deleteImage(String imageId);

    public List<Image> getAllImages();

    public Image getImage(String id);

}
