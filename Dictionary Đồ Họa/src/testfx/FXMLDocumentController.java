/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.Image;
import java.io.InputStream;
import javafx.scene.image.ImageView;
/**
 *
 * @author Admin
 */
import org.controlsfx.control.textfield.TextFields;

public class FXMLDocumentController implements Initializable {

    public DictionaryManagement dictionary = new DictionaryManagement();
    @FXML
    private AnchorPane root;
    @FXML
    private TextField input;
    @FXML
    private MenuItem show_word;
    @FXML
    private MenuItem add_word;
    @FXML
    private MenuItem delete_word;
    @FXML
    private TextArea textarea;
    @FXML
    private Button speakb;
    Voice v;
    VoiceManager vm;
    @FXML
    private MenuItem help;
    @FXML
    private Button lookup;

    public void help() {
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("FXMLhelp.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.getChildren().setAll(pane);

            }

        });
    }

    public void showAllWord() {
        show_word.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //                Stage primaryStage=new Stage();
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.getChildren().setAll(pane);

            }

        });
    }

    public void deleteWord() {
        delete_word.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("FXML3.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.getChildren().setAll(pane);

            }

        });
    }

    public void addWord() {
        add_word.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("FXML4.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.getChildren().setAll(pane);

            }

        });
    }

    public void speakword() {
        speakb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = input.getText();
                System.setProperty("mbrola.base", "mbrola");
                vm = VoiceManager.getInstance();
                v = vm.getVoice("mbrola_us1");
                v.allocate();
                v.speak(text);
            }
        });
    }

    public void tratu() {
        lookup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (dictionary.dictionaryLookup(input.getText()) == -1) {
                    textarea.setText("Not Found!");
                } else {
                    String line = dictionary.ver1.getList().get(dictionary.dictionaryLookup(input.getText())).getWord_Explain();

                    String[] meanline = line.split("\t");

                    String str = "";

                    for (int i = 0; i < meanline.length; i++) {
                        str = str + meanline[i] + "\n";
                    }
                    textarea.setText(str);
                }
            }
        });
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dictionary.insertFromFile();
        String s[] = new String[dictionary.sizedict];
        for (int i = 0; i < dictionary.sizedict; i++) {
            s[i] = dictionary.ver1.getList().get(i).getWord_Target();
        }
        input.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER) {
                    if (dictionary.dictionaryLookup(input.getText()) == -1) {
                        textarea.setText("Not Found!");
                    } else {
                        String line = dictionary.ver1.getList().get(dictionary.dictionaryLookup(input.getText())).getWord_Explain();

                        String[] meanline = line.split("\t");

                        String str = "";

                        for (int i = 0; i < meanline.length; i++) {
                            str = str + meanline[i] + "\n";
                        }
                        textarea.setText(str);
                    }
                }
            }

        });

        TextFields.bindAutoCompletion(input, s);
        input.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                input.setText("");
            }
        });
        tratu();
        showAllWord();
        deleteWord();
        addWord();
        speakword();
        help();
        
    }

}
