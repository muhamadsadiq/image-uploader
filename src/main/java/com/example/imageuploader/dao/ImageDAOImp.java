package com.example.imageuploader.dao;

import com.example.imageuploader.db.DBConnection;
import com.example.imageuploader.model.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageDAOImp implements ImageDAO{
    private static Connection connection = DBConnection.getConnection();

    @Override
    public boolean addImage(Image image) {
        System.out.println("Start addImage");

        try{
            String insertStatment = "INSERT INTO image (id, name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertStatment);
            preparedStatement.setString(1,image.getImageId());
            preparedStatement.setString(2,image.getImageName());

            int result = preparedStatement.executeUpdate();
            return result == 1? true: false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteImage(String imageId) {
        System.out.println("start deleteEmployee");

        try{
            String deleteStatment = "DELETE FROM image WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatment);
            preparedStatement.setString(1,imageId);

            int result = preparedStatement.executeUpdate();
            return result == 1? true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Image> getAllImages() {
        System.out.println("start get All Employee");

        try
        {
            String selectStatement = "SELECT * FROM image;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Image> images = new ArrayList<Image>();

            while( resultSet.next() )
            {
                Image image = new Image();

                image.setImageId(resultSet.getString("id"));
                image.setImageName(resultSet.getString("name"));


                images.add(image);
            }

            return images;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Image getImage(String id) {
        System.out.println("Start Select image");

        try
        {
            String selectStatement = "SELECT * FROM image WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Image image = new Image();

            while( resultSet.next() )
            {
                image.setImageId(resultSet.getString("id"));
                image.setImageName(resultSet.getString("name"));

            }

            return image;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

    }

}

