package com.example.ImageUploader.gui;

import com.example.ImageUploader.service.ImageUploader;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("uploadImage")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;

    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;
        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(buttonClickEvent -> {
            String uploadedImage = imageUploader.uploadFile(textField.getValue());
            Html photo = new Html("<img src='"+"file:///"+uploadedImage+"'>");
            Label label = new Label("Udało się!");
            add(label);
            add(photo);
            System.out.println(uploadedImage);

            try {
                imageUploader.getPhotos();
            } catch (Exception e) {
                System.out.println("nie udało się");
            }
        });

        add(textField);
        add(button);
    }
}
