package com.example.projectv1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



public class BarChartDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        BarChart.Series<String, Number> series = new BarChart.Series<>();
        series.setName("Series Name");

        DataPoint dataPoint1 = new DataPoint("Category 1", 10.0);
        DataPoint dataPoint2 = new DataPoint("Category 2", 20.0);

        series.getData().add(new XYChart.Data<>(dataPoint1.getCategory(), dataPoint1.getValue()));
        series.getData().add(new XYChart.Data<>(dataPoint2.getCategory(), dataPoint2.getValue()));

        barChart.getData().add(series);

        Scene scene = new Scene(barChart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bar Chart Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
