
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label level;
    @FXML
    private Label starLevel1;
    @FXML
    private Label starLevel2;
    
    @FXML
    private void bisec() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("bisectionPage.fxml"));
        Scene nwScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(nwScene);
        stage.setTitle("Bisection Method");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) level.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void falsePos() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("falsePosPage.fxml"));
        Scene nwScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(nwScene);
        stage.setTitle("False Position Method");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) level.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void secent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("secentPage.fxml"));
        Scene nwScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(nwScene);
        stage.setTitle("Secent Method");
        stage.setResizable(false);
        stage.show();
        stage = (Stage) level.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        RotateTransition rota = new RotateTransition();
        RotateTransition rota2 = new RotateTransition();
        TranslateTransition trans = new TranslateTransition();
        //trans.setDuration(Duration.millis(4000));
        trans.setNode(level);
        rota.setNode(starLevel1);
        rota2.setNode(starLevel2);
        trans.setRate(.04);
        rota.setRate(.5);
        rota2.setRate(.5);
        trans.setToX(-1120);
        rota.setToAngle(360);
        rota2.setToAngle(360);
        rota.setAutoReverse(true);
        rota2.setAutoReverse(true);
        trans.setCycleCount(TranslateTransition.INDEFINITE);
        rota.setCycleCount(RotateTransition.INDEFINITE);
        rota2.setCycleCount(RotateTransition.INDEFINITE);
        trans.play();
        rota.play();
        rota2.play();
    }

}
