package Repository;

import Config.DatabaseConfiguration;
import Domain.CalorieIntake.CalorieIntake;
import Domain.NutritionInfo.NutritionInfo;
import Services.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalorieIntakeRepository {

    private final AuditService auditService = new AuditService();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void getAllCalorieIntake() {
        String selectSql = "SELECT * FROM CalorieIntake";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
                String mealType = resultSet.getString("meal_type");
                int foodItemId = resultSet.getInt("food_item_id");
                double grams = resultSet.getDouble("grams");

                // Fetch the NutritionInfo details using foodItemId if necessary
                // For now, we'll just print the foodItemId
                System.out.println("Id: " + id);
                System.out.println("Date Time: " + dateTime.format(formatter));
                System.out.println("Meal Type: " + mealType);
                System.out.println("Food Item ID: " + foodItemId);
                System.out.println("Grams: " + grams);
                System.out.println(); // Blank line between records
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CalorieIntake getCalorieIntakeById(int id) {
        String selectSql = "SELECT * FROM CalorieIntake WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        CalorieIntake calorieIntake = null;

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
                String mealType = resultSet.getString("meal_type");
                int foodItemId = resultSet.getInt("food_item_id");
                double grams = resultSet.getDouble("grams");

                // Fetch the NutritionInfo details using foodItemId
                NutritionInfo foodItem = getNutritionInfoById(foodItemId);

                calorieIntake = new CalorieIntake(dateTime, mealType, foodItem, grams);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calorieIntake;
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

    public void insertCalorieIntake(CalorieIntake calorieIntake) {
        String insertSql = "INSERT INTO CalorieIntake (date_time, meal_type, food_item_id, grams) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, calorieIntake.getDateTime().format(formatter));
            statement.setString(2, calorieIntake.getMealType());
            statement.setInt(3, getFoodItemId(calorieIntake.getFoodItem()));
            statement.setDouble(4, calorieIntake.getGrams());
            statement.executeUpdate();
            auditService.logAction("INSERT", "CalorieIntake", "Food inserted at " + calorieIntake.getDateTime());
            System.out.println("CalorieIntake inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCalorieIntake(int id) {
        String deleteSql = "DELETE FROM CalorieIntake WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            auditService.logAction("DELETE", "CalorieIntake", "Food intake id " + id + "was successfully deleted");
            System.out.println("CalorieIntake deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCalorieIntake(int id, CalorieIntake calorieIntake) {
        String updateSql = "UPDATE CalorieIntake SET date_time = ?, meal_type = ?, food_item_id = ?, grams = ? WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, calorieIntake.getDateTime().format(formatter));
            statement.setString(2, calorieIntake.getMealType());
            statement.setInt(3, getFoodItemId(calorieIntake.getFoodItem()));
            statement.setDouble(4, calorieIntake.getGrams());
            statement.setInt(5, id);
            statement.executeUpdate();
            auditService.logAction("UPDATE", "CalorieIntake", "Food inserted at " + calorieIntake.getDateTime() + "was succesfully updated");
            System.out.println("CalorieIntake updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getFoodItemId(NutritionInfo foodItem) {
        String selectSql = "SELECT id FROM NutritionInfo WHERE name = ? AND calories = ? AND protein = ? AND fats = ? AND carbohydrates = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        int foodItemId = -1;

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setString(1, foodItem.getName());
            statement.setDouble(2, foodItem.getCalories());
            statement.setDouble(3, foodItem.getProtein());
            statement.setDouble(4, foodItem.getFat());
            statement.setDouble(5, foodItem.getCarbohydrates());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                foodItemId = resultSet.getInt("id");
            } else {
                // If food item not found, insert it and get the generated ID
                String insertSql = "INSERT INTO NutritionInfo (name, calories, protein, fats, carbohydrates) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    insertStatement.setString(1, foodItem.getName());
                    insertStatement.setDouble(2, foodItem.getCalories());
                    insertStatement.setDouble(3, foodItem.getProtein());
                    insertStatement.setDouble(4, foodItem.getFat());
                    insertStatement.setDouble(5, foodItem.getCarbohydrates());
                    insertStatement.executeUpdate();

                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        foodItemId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodItemId;
    }


}
