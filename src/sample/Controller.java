package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chartAndAverages.fxml"));
            Parent root = loader.load();
            ChartController chartAndResults =  loader.getController();
            chartAndResults.setAxisLabels(new String[]{"Value","Amount"});
            chartAndResults.setLineChartTitle("Division of values in normal distribution");
            XYChart.Series series = new XYChart.Series();
            series.setName("division");
            for (Double each : ga.getEachValueAmount().keySet()){
                series.getData().add(new XYChart.Data(each, ga.getEachValueAmount().get(each)));
            }
            chartAndResults.setLineChartData(series);
            chartAndResults.setResults(ga.distribute());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}