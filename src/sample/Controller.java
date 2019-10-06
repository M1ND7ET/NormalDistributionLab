package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class Controller {
    @FXML
    private TextField lowerBound;

    @FXML
    private TextField upperBound;

    @FXML
    private TextField numberOf;

    @FXML
    private Button generateButton;

    public Controller(){

    }

    @FXML
    private void initialize() {
        generateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                generateButton.setText("GENERATING....");
            }
        });
    }

    @FXML
    private void action() {
        GaussianAverages ga = new GaussianAverages((new double[]{Double.valueOf(lowerBound.getText()), Double.valueOf(upperBound.getText())}),Integer.valueOf(numberOf.getText()));
        for(Double each : ga.distribute()){
            System.out.println(each);
        }
    }
}