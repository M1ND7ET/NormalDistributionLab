package sample;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ChartController {
    @FXML
    private LineChart lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextArea mathExpectation;

    @FXML
    private TextArea mode;

    @FXML
    private TextArea median;

    @FXML
    private TextArea arithmeticMean;

    @FXML
    private TextArea harmonicMean;

    @FXML
    private TextArea geometricMean;

    @FXML
    private TextArea rootMeanSquare;

    @FXML
    private TextArea standardDeviation;

    @FXML
    private TextArea dispersion;

    public ChartController(){
    }

    @FXML
    private void initialize(){
    }

    public void setAxisLabels(String[] labels){
        xAxis.setLabel(labels[0]);
        yAxis.setLabel(labels[1]);
    }

    public void setLineChartTitle(String title){
        lineChart.setTitle(title);
    }

    public void setLineChartData(XYChart.Series series){
        lineChart.getData().add(series);
    }

    public void setResults(double[] results){
        NumberFormat formatter = new DecimalFormat("#0.000000");
        mathExpectation.setText(formatter.format(results[0]));
        mode.setText(formatter.format(results[1]));
        median.setText(formatter.format(results[2]));
        arithmeticMean.setText(formatter.format(results[3]));
        harmonicMean.setText(formatter.format(results[4]));
        rootMeanSquare.setText(formatter.format(results[5]));
        geometricMean.setText(formatter.format(results[6]));
        standardDeviation.setText(formatter.format(results[7]));
        dispersion.setText(formatter.format(results[8]));
    }
}