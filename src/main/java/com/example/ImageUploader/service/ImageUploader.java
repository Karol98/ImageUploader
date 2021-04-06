package com.example.ImageUploader.service;



import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import elemental.json.Json;
import org.cloudinary.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;

    public ImageUploader() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwmhubjrv",
                "api_key", "598857122326358",
                "api_secret", "vpL83QpSEnr3Pi3ncqFWH-Al1KY"));
    }


    public String uploadFile(String patch) {
        File file = new File(patch);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
           // todo
        }
        return patch;
    }

    public void getPhotos() throws Exception {
       ApiResponse photos = cloudinary.search().execute();
       JSONObject urls = new JSONObject(photos);

    }

}
