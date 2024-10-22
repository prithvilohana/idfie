package com.example.idfie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class IDCardController {
    @FXML
    private ImageView profimageSc2;

    @FXML
    private Text idCardName;
    @FXML
    private Label idDept;

    @FXML
    private Label idEmail;

    @FXML
    private Label idFName;

    @FXML
    private Label idNumber;

    @FXML
    private Label idPhone;

    @FXML
    private Label idBatch;

    @FXML
    private Label idCampus;

    public void setIdCardName(String name) {
        idCardName.setText(name);
    }

    public void setIdFName(String fatherName) {
        idFName.setText(fatherName);
    }
    public  void setIdNumber(String IDNumber){
        idNumber.setText(IDNumber);
    }

    public void setIdBatch(String batch) {
        idBatch.setText(batch);
    }

    public void setIdCampus(String campus) {
        idCampus.setText(campus);
    }

    public void setIdDept(String department) {
        idDept.setText(department);
    }

    public void setIdEmail(String email) {
        idEmail.setText(email);
    }

    public void setIdPhone(String phone) {
        idPhone.setText(phone);
    }
    @FXML
    private Button pdfDownload;


    @FXML
    private Pane card;


    @FXML
    void onDownload(ActionEvent event) {
        // Get the bounds of the card pane
        Bounds bounds = card.getBoundsInLocal();

        // Convert the Pane to an image
        WritableImage snapshot = card.snapshot(new SnapshotParameters(), null);

        // Save the image as a temporary file
        File tempFile = new File("temp.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", tempFile);

            // Create a PDF document and set the page size to match the pane size
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(new PDRectangle((float) bounds.getWidth(), (float) bounds.getHeight()));
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(PDImageXObject.createFromFile("temp.png", document), 0, 0, (float) bounds.getWidth(), (float) bounds.getHeight());
            contentStream.close();

            // Save the PDF file using a FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("c:\\"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showSaveDialog(new Stage());

            if (selectedFile != null) {
                document.save(selectedFile);
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Delete the temporary image file
            tempFile.delete();
        }
    }
    

    User user = new User();
    // Method to set the image in Scene 2
    public void setProfileImage(Image image) {
        if (profimageSc2 != null) {
            profimageSc2.setImage(image);
        }
    }
}