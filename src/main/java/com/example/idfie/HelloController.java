package com.example.idfie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.image.ImageView;

public class HelloController {

    public Button IdGenerator;
    @FXML
    private Label noFIle;

    public Button generateBtn;
    @FXML
    private ImageView userPicture;

    @FXML
    public void generateBtnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("c:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*.jpg"), new FileChooser.ExtensionFilter("PNG image", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile != null){
            Image image = new Image(selectedFile.getPath());
            userPicture.setImage(image);
        }
        else {
            noFIle.setText("No file is selected");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void generateID(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("IDcard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("generateID() method called");
        if (root == null) {
            System.out.println("FXML file not found!");
        } else {
            System.out.println("FXML file loaded successfully");
        }
    }


    @FXML
    private ImageView profileImage;
}