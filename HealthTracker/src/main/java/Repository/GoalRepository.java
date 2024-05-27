package Repository;

import Config.DatabaseConfiguration;
import Domain.Goal.Goal;
import Services.AuditService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GoalRepository {

    private final AuditService auditService = new AuditService();

    public void getAllGoals() {
        String selectSql = "SELECT * FROM Goal";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double targetCalories = resultSet.getDouble("target_calories");
                double targetProtein = resultSet.getDouble("target_protein");
                double targetCarbohydrate = resultSet.getDouble("target_carbohydrates");
                double targetFat = resultSet.getDouble("target_fat");
                double targetWeight = resultSet.getDouble("target_weight");
                int targetSteps = resultSet.getInt("target_steps");

                System.out.println("Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Target Calories: " + targetCalories);
                System.out.println("Target Protein: " + targetProtein);
                System.out.println("Target Carbohydrate: " + targetCarbohydrate);
                System.out.println("Target Fat: " + targetFat);
                System.out.println("Target Weight: " + targetWeight);
                System.out.println("Target Steps: " + targetSteps);
                System.out.println(); // Blank line between records
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Goal getGoalById(int id) {
        String selectSql = "SELECT * FROM Goal WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        Goal goal = null;

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double targetCalories = resultSet.getDouble("target_calories");
                double targetProtein = resultSet.getDouble("target_protein");
                double targetCarbohydrate = resultSet.getDouble("target_carbohydrates");
                double targetFat = resultSet.getDouble("target_fat");
                double targetWeight = resultSet.getDouble("target_weight");
                int targetSteps = resultSet.getInt("target_steps");

                goal = new Goal(name, targetWeight, 0, 0, 0, 0, targetSteps); // Weight, age, height, and activityLevel are placeholders
                goal.setTargetCalories(targetCalories);
                goal.setTargetProtein(targetProtein);
                goal.setTargetCarbohydrate(targetCarbohydrate);
                goal.setTargetFat(targetFat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goal;
    }

    public void insertGoal(Goal goal) {
        String insertSql = "INSERT INTO Goal (name, target_calories, target_proteins, target_carbohydrates, target_fats, target_weight, target_steps) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setString(1, goal.getName());
            statement.setDouble(2, goal.getTargetCalories());
            statement.setDouble(3, goal.getTargetProtein());
            statement.setDouble(4, goal.getTargetCarbohydrate());
            statement.setDouble(5, goal.getTargetFat());
            statement.setDouble(6, goal.getTargetWeight());
            statement.setInt(7, goal.getTargetSteps());
            statement.executeUpdate();
            auditService.logAction("INSERT", "Goal", goal.getName());
            System.out.println("Goal inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGoal(int id) {
        String deleteSql = "DELETE FROM Goal WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            auditService.logAction("DELETE", "Goal", "Goal with id " + id + "was deleted succesfully");
            System.out.println("Goal deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGoal(int id, Goal goal) {
        String updateSql = "UPDATE Goal SET name = ?, target_calories = ?, target_proteins = ?, target_carbohydrates = ?, target_fats = ?, target_weight = ?, target_steps = ? WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, goal.getName());
            statement.setDouble(2, goal.getTargetCalories());
            statement.setDouble(3, goal.getTargetProtein());
            statement.setDouble(4, goal.getTargetCarbohydrate());
            statement.setDouble(5, goal.getTargetFat());
            statement.setDouble(6, goal.getTargetWeight());
            statement.setInt(7, goal.getTargetSteps());
            statement.setInt(8, id);
            statement.executeUpdate();
            auditService.logAction("UPDATE", "Goal", "Goal named " + goal.getName() + "was updated successfully");
            System.out.println("Goal updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
