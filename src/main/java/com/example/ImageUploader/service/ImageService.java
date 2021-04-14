package com.example.ImageUploader.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageService {

    private Cloudinary cloudinary;

    public ImageService() {
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

    public List<String> getPhotos() throws Exception {
        ApiResponse results = cloudinary.search().execute();
        List<Map<String, Object>> resources = (List<Map<String, Object>>) results.get("resources");
        Stream<String> stream = resources.stream()
                .map(picture -> (String) picture.get("url"));
        return stream.collect(Collectors.toList());
    }
}
