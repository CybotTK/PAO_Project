package Repository;

import Config.DatabaseConfiguration;
import Domain.NutritionInfo.NutritionInfo;
import Services.AuditService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class NutritionInfoRepository {

    private final AuditService auditService = new AuditService();

    public void getAllNutritionInfo() {
        String selectSql = "SELECT * FROM NutritionInfo";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double calories = resultSet.getDouble("calories");
                double protein = resultSet.getDouble("protein");
                double carbs = resultSet.getDouble("carbohydrates");
                double fats = resultSet.getDouble("fats");

                System.out.println("Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Calories: " + calories);
                System.out.println("Protein: " + protein);
                System.out.println("Carbs: " + carbs);
                System.out.println("Fats: " + fats);
                System.out.println(); // Blank line between records
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NutritionInfo getNutritionInfoById(int id) {
        String selectSql = "SELECT * FROM NutritionInfo WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        NutritionInfo nutritionInfo = null;

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double calories = resultSet.getDouble("calories");
                double protein = resultSet.getDouble("protein");
                double carbohydrates = resultSet.getDouble("carbohydrates");
                double fats = resultSet.getDouble("fats");
                nutritionInfo = new NutritionInfo(name, calories, protein, fats, carbohydrates);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nutritionInfo;
    }

    public void insertNutritionInfo(NutritionInfo nutritionInfo) {
        String insertSql = "INSERT INTO NutritionInfo (name, calories, protein, carbohydrates, fats) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, nutritionInfo.getName());
            statement.setDouble(2, nutritionInfo.getCalories());
            statement.setDouble(3, nutritionInfo.getProtein());
            statement.setDouble(4, nutritionInfo.getCarbohydrates());
            statement.setDouble(5, nutritionInfo.getFat());
            statement.executeUpdate();
            auditService.logAction("INSERT", "NutritionInfo", nutritionInfo.getName());
            System.out.println("NutritionInfo inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNutritionInfo(int id) {
        String deleteSql = "DELETE FROM NutritionInfo WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            auditService.logAction("DELETE", "NutritionInfo", "Food with id " + id + " was deleted successfully.");
            System.out.println("NutritionInfo deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNutritionInfo(int id, NutritionInfo nutritionInfo) {
        String updateSql = "UPDATE NutritionInfo SET name = ?, calories = ?, protein = ?, carbohydrates = ?, fats = ? WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, nutritionInfo.getName());
            statement.setDouble(2, nutritionInfo.getCalories());
            statement.setDouble(3, nutritionInfo.getProtein());
            statement.setDouble(4, nutritionInfo.getCarbohydrates());
            statement.setDouble(5, nutritionInfo.getFat());
            statement.setInt(6, id);
            statement.executeUpdate();
            auditService.logAction("UPDATE", "NutritionInfo", "Food " + nutritionInfo.getName() + " was updated successfully.");
            System.out.println("NutritionInfo updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
