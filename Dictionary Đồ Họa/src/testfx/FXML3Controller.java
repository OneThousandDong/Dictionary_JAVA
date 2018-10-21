/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML3Controller implements Initializable {

    @FXML
    private TextField word;
    @FXML
    private Label result;
    private DictionaryManagement dictionary = new DictionaryManagement();
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    private void deleteword() throws IOException {
        word.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    if (dictionary.dictionaryLookup(word.getText()) == -1) {
                        result.setText("Not Found!");
                    } else {
                        dictionary.deletefromDictionary(word.getText());
                        result.setText("Success!");
                    }
                }
            }

        });
    }

    private void back() {
        backButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
                {
                    AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.getChildren().setAll(pane);
                }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dictionary.insertFromFile();
        try {
            deleteword();
        } catch (IOException ex) {
            Logger.getLogger(FXML3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        word.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                word.setText("");
            }
        });
        back();
    }

}
