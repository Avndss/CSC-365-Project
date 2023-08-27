package com.example.projectv1;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    public BarChart<String, Number> ordersBarChart;

    @FXML
    public BarChart<String, Number> revenueBarChart;

    @FXML
    public PieChart pieChart;

    private static Connection connect;

    public void initialize() {
        // Initialization code for JavaFX components, if needed
    }

    public ArrayList<Integer> getOrdersByDay() {
        ArrayList<Integer> orders = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/One?user=One&password=One");
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT " +
                            "DAYOFWEEK(receipt_date) AS DayOfWeek, " +
                            "COUNT(*) AS NumberOfEvents " +
                            "FROM Receipt " +
                            "GROUP BY DAYOFWEEK(receipt_date) " +
                            "ORDER BY DayOfWeek;");
            while (rs.next()) {
                int numberOfEvents = rs.getInt("NumberOfEvents");
                orders.add(numberOfEvents);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return orders;
    }

    public ArrayList<Integer> getRevenueByDay() {
        ArrayList<Integer> revenue = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/One?user=One&password=One");
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT " +
                            "DAYOFWEEK(Receipt.receipt_date) AS DayOfWeek, SUM(price) as total " +
                            "FROM Receipt, Food " +
                            "WHERE Receipt.Food = Food.food_name " +
                            "GROUP BY DAYOFWEEK(Receipt.receipt_date) " +
                            "ORDER BY DayOfWeek;");
            while (rs.next()) {
                int totalRevenue = rs.getInt("total");
                revenue.add(totalRevenue);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return revenue;
    }

    public ArrayList<Float> getItemsOrdered() {
        ArrayList<Float> itemsOrdered = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/One?user=One&password=One");
            Statement statement = connect.createStatement();

            ResultSet divisor = statement.executeQuery(
                    "SELECT COUNT(*) as total " +
                            "FROM Food, Receipt " +
                            "WHERE Receipt.food = Food.food_name;"
            );
            divisor.next();

            float dividend = divisor.getFloat("total");


            ResultSet rs = statement.executeQuery(
                    "SELECT Food.food_name, COUNT(*) as total " +
                            "FROM Food, Receipt " +
                            "WHERE Receipt.food = Food.food_name " +
                            "GROUP BY Food.food_name " +
                            "ORDER BY Food.food_name ASC; ");

            while (rs.next()) {
                float totalRevenue = rs.getFloat("total") / dividend;
                itemsOrdered.add(totalRevenue);
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return itemsOrdered;
    }

    private void closeConnection() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
