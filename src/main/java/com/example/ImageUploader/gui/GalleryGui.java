package com.example.ImageUploader.gui;

import com.example.ImageUploader.service.ImageService;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends Template {

    ImageService imageService;

    @Autowired
    public GalleryGui(ImageService imageService) throws Exception {
        this.imageService = imageService;
        List<String> images = imageService.getPhotos();
        images.forEach(link -> {
            Image image = new Image(link, "photo");
            add(image);
        });
    }
}
