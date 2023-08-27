package com.example.projectv1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        primaryStage.setTitle("Analytics");
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller controller = fxmlLoader.getController();

        //SQL Queries
        ArrayList<Integer> orders = controller.getOrdersByDay();
        ArrayList<Integer> revenue = controller.getRevenueByDay();
        ArrayList<Float> percentages = controller.getItemsOrdered();

        // Now you can access the chart references from the controller
        BarChart<String, Number> ordersChart = controller.ordersBarChart;
        BarChart<String, Number> revenueChart = controller.revenueBarChart;

        // Assuming "ordersChart" is the BarChart<String, Number> reference

        // Orders Series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Orders"); // Set the name of the series

        // Add data points to the series
        series.getData().add(new XYChart.Data<>("Monday", orders.get(0)));
        series.getData().add(new XYChart.Data<>("Tuesday", orders.get(1)));
        series.getData().add(new XYChart.Data<>("Wednesday", orders.get(2)));
        series.getData().add(new XYChart.Data<>("Thursday", orders.get(3)));
        series.getData().add(new XYChart.Data<>("Friday", orders.get(4)));
        series.getData().add(new XYChart.Data<>("Saturday", orders.get(5)));
        series.getData().add(new XYChart.Data<>("Sunday", orders.get(6)));

        // Add the series to the BarChart
        ordersChart.getData().add(series);

        // Customize the colors of individual bars
        for (int i = 0; i < series.getData().size(); i++) {
            XYChart.Data<String, Number> data = series.getData().get(i);
            Node bar = data.getNode();

            // Set different colors for different bars
            if (i % 2 == 0) {
                bar.setStyle("-fx-bar-fill: green;");
            } else {
                bar.setStyle("-fx-bar-fill: gold;");
            }
        }

        // After adding the series to the ordersChart
        CategoryAxis xAxis = (CategoryAxis) ordersChart.getXAxis();
        xAxis.setTickMarkVisible(true); // Show tick marks

        // Set custom tick mark positions to align with the center of each bar
        xAxis.setTickLabelGap(10); // This is the gap between the tick labels and the bars
        xAxis.setTickLabelRotation(0); // No rotation for labels

        xAxis.setCategories(FXCollections.observableArrayList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        ));

        ordersChart.setLegendVisible(false);
        xAxis.setAutoRanging(false); // Disable automatic range adjustment




        // Revenue Series
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Revenue"); // Set the name of the series

        // Add data points to the series
        series2.getData().add(new XYChart.Data<>("Monday", revenue.get(0)));
        series2.getData().add(new XYChart.Data<>("Tuesday", revenue.get(1)));
        series2.getData().add(new XYChart.Data<>("Wednesday", revenue.get(2)));
        series2.getData().add(new XYChart.Data<>("Thursday", revenue.get(3)));
        series2.getData().add(new XYChart.Data<>("Friday", revenue.get(4)));
        series2.getData().add(new XYChart.Data<>("Saturday", revenue.get(5)));
        series2.getData().add(new XYChart.Data<>("Sunday", revenue.get(6)));

        // Add the series to the revenueChart
        revenueChart.getData().add(series2);

        // Customize the colors of individual bars
        for (int i = 0; i < series2.getData().size(); i++) {
            XYChart.Data<String, Number> data = series2.getData().get(i);
            Node bar = data.getNode();

            // Set different colors for different bars
            if (i % 2 == 0) {
                bar.setStyle("-fx-bar-fill: purple;");
            } else {
                bar.setStyle("-fx-bar-fill: blue;");
            }
        }

        // After adding the series to the revenueChart
        CategoryAxis xAxis2 = (CategoryAxis) revenueChart.getXAxis();
        xAxis2.setTickMarkVisible(true); // Show tick marks

        // Set custom tick mark positions to align with the center of each bar
        xAxis2.setTickLabelGap(10); // This is the gap between the tick labels and the bars
        xAxis2.setTickLabelRotation(0); // No rotation for labels

        xAxis2.setCategories(FXCollections.observableArrayList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        ));

        revenueChart.setLegendVisible(false);
        xAxis2.setAutoRanging(false); // Disable automatic range adjustment


        //Pie Chart
        PieChart foodChart = controller.pieChart;

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Espresso", percentages.get(0) * 100),
                new PieChart.Data("Americano", percentages.get(1) * 100),
                new PieChart.Data("Macchiato", percentages.get(2) * 100),
                new PieChart.Data("Latte", percentages.get(3) * 100),
                new PieChart.Data("Iced Coffee", percentages.get(4) * 100),
                new PieChart.Data("Sandwich", percentages.get(5) * 100),
                new PieChart.Data("Muffin", percentages.get(6) * 100),
                new PieChart.Data("Donut", percentages.get(7) * 100),
                new PieChart.Data("Bagel", percentages.get(8) * 100),
                new PieChart.Data("Toast", percentages.get(9) * 100)
        );

        foodChart.setData(pieChartData);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
