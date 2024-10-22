package com.example.idfie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.image.ImageView;

import javafx.scene.image.ImageView;
class User{
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    public String dpt;
    public String idNo;
    public String batchNo;
    public String campus;
    public String picture;
}
public class HelloController {

    @FXML
    private TextField id;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField dept;
    @FXML
    private TextField batch;
    @FXML
    private TextField campus;
    @FXML
    public Button IdGenerator;

    @FXML
    private Label noFIle;

    @FXML
    public ImageView profimageSc2;

    @FXML
    public Button generateBtn;
    @FXML
    private ImageView userPicture;

    User user  = new User();

    @FXML
    public void generateBtnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("c:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*.jpg"), new FileChooser.ExtensionFilter("PNG image", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            userPicture.setImage(image);
            userPicture.setFitWidth(250);
            userPicture.setFitHeight(173);
        }
        else {
            noFIle.setText("No file is selected");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void generateID(ActionEvent event) throws IOException {
        user.idNo = id.getText();
        user.firstName = firstName.getText();
        user.lastName = lastName.getText();
        user.phoneNumber = phone.getText();
        user.emailAddress = email.getText();
        user.batchNo = batch.getText();
        user.campus = campus.getText();
        user.picture = generateBtn.getText();
        if (userPicture == null || user.idNo == "" || user.firstName == "" || user.lastName == "" || user.phoneNumber == "" || user.emailAddress == "" ||user.batchNo == "" || user.campus == ""|| user.picture == ""){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialogbox.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Warning");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IDcard.fxml"));
            root = loader.load();
            IDCardController idCardController = loader.getController();
            idCardController.setProfileImage(userPicture.getImage());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            System.out.println(user.firstName +" "+ user.lastName);
            if (root == null) {
                System.out.println("FXML file not found!");
            } else {
                System.out.println("FXML file loaded successfully");
            }
        }
        

    }


    @FXML
    private ImageView profileImage;
}