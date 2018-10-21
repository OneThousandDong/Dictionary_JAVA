/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import java.awt.AWTEventMulticaster;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig.Anchor;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML2Controller implements Initializable {

    @FXML
    private AnchorPane root = new AnchorPane();
    @FXML
    private DictionaryManagement dictionary = new DictionaryManagement();
    private ListView<String> list=new ListView<>();
    private ObservableList<String> item;
    @FXML
    private Button backButton;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void back()
    {
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

        String s[] = new String[dictionary.sizedict];
        for (int i = 0; i < dictionary.sizedict; i++) {
            s[i] = dictionary.ver1.getList().get(i).getWord_Target() + "        " + dictionary.ver1.getList().get(i).getWord_Explain();
        }
        item = FXCollections.observableArrayList(s);
        list.setItems(item);
        list.setPrefSize(663, 450);
        list.setLayoutY(37);
        root.getChildren().add(list);
        back();
        
    }

}
