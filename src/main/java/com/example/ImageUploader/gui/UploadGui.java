package com.example.ImageUploader.gui;

import com.example.ImageUploader.service.ImageService;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("uploadImage")
public class UploadGui extends Template {

    private ImageService imageService;

    public UploadGui(ImageService imageService) {
        this.imageService = imageService;
        TextField textField = new TextField();
        Button button = new Button("upload");

        button.addClickListener(buttonClickEvent -> {
            String uploadedImage = imageService.uploadFile(textField.getValue());
            Html photo = new Html("<img src='" + "file:///" + uploadedImage + "'>");
            Label label = new Label("Image succesfully uploaded");
            add(label);
            add(photo);
            try {
                imageService.getPhotos();
            } catch (Exception e) {
                System.out.println("Something went wrong :(");
            }
        });

        add(textField);
        add(button);
    }
}
