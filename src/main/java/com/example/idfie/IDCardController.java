package com.example.idfie;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IDCardController {
    @FXML
    private ImageView profimageSc2;

    // Method to set the image in Scene 2
    public void setProfileImage(Image image) {
        if (profimageSc2 != null) {
            profimageSc2.setImage(image);
        }
    }
}