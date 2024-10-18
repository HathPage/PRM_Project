package com.example.prm_project.adapter;

import android.content.Context;
import android.util.Log;

import com.example.prm_project.ConnectionClass;
import com.example.prm_project.model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodAdapter {
    private ConnectionClass connectionClass;
    private Context context;

    public FoodAdapter(Context context) {
        this.context = context;
        connectionClass = new ConnectionClass();
    }

    // Fetch all food items from the Food table
    public ArrayList<Food> getAllFoodItems() {
        ArrayList<Food> foodList = new ArrayList<>();
        Connection connection = connectionClass.Con();
        if (connection != null) {
            String query = "SELECT * FROM Food WHERE Status = 1"; // Assuming Status = 1 means available
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Food food = new Food();
                    food.setId(rs.getInt("Food_id"));
                    food.setName(rs.getString("Name"));
                    food.setCategory(rs.getString("Category"));
                    food.setPrice(rs.getBigDecimal("Price"));
                    food.setDescription(rs.getString("Description"));
                    food.setStatus(rs.getInt("Status"));
                    foodList.add(food);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return foodList;
    }

    // Insert a new food item into the Food table
    public boolean insertFoodItem(Food food) {
        Connection connection = connectionClass.Con();
        if (connection != null) {
            String query = "INSERT INTO Food (Name, Category, Price, Description, Status) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, food.getName());
                ps.setString(2, food.getCategory());
                ps.setBigDecimal(3, food.getPrice());
                ps.setString(4, food.getDescription());
                ps.setInt(5, food.getStatus());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
                return false;
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Update an existing food item in the Food table
    public boolean updateFoodItem(Food food) {
        Connection connection = connectionClass.Con();
        if (connection != null) {
            String query = "UPDATE Food SET Name = ?, Category = ?, Price = ?, Description = ?, Status = ? WHERE Food_id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, food.getName());
                ps.setString(2, food.getCategory());
                ps.setBigDecimal(3, food.getPrice());
                ps.setString(4, food.getDescription());
                ps.setInt(5, food.getStatus());
                ps.setInt(6, food.getId());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
                return false;
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    // Delete a food item from the Food table
    public boolean deleteFoodItem(int foodId) {
        Connection connection = connectionClass.Con();
        if (connection != null) {
            String query = "DELETE FROM Food WHERE Food_id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, foodId);
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException e) {
                Log.e("SQL Error", e.getMessage());
                return false;
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}