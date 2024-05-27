package Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupData {

    public void createTable(){

        String[] createTableSql = {
                """
                CREATE TABLE IF NOT EXISTS Users(
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                age INT NOT NULL,
                weight DOUBLE PRECISION NOT NULL,
                height DOUBLE PRECISION NOT NULL,
                user_type VARCHAR(50) NOT NULL,
                loyalty_points INT,
                has_access_to_advanced_features BOOLEAN
                )
                """,
                """
                CREATE TABLE IF NOT EXISTS NutritionInfo(
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                calories DOUBLE PRECISION NOT NULL,
                protein DOUBLE PRECISION NOT NULL,
                carbohydrates DOUBLE PRECISION NOT NULL,
                fats DOUBLE PRECISION NOT NULL
                )
                """,
                """
                CREATE TABLE IF NOT EXISTS CalorieIntake(
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                date_time  TIMESTAMP NOT NULL,
                meal_type VARCHAR(50) NOT NULL,
                food_item_id INT NOT NULL,
                grams DOUBLE PRECISION NOT NULL,
                FOREIGN KEY (food_item_id) REFERENCES NutritionInfo(id)
                )
                """,
                """
                CREATE TABLE IF NOT EXISTS Goal(
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50) NOT NULL,
                target_calories DOUBLE PRECISION NOT NULL,
                target_proteins DOUBLE PRECISION NOT NULL,
                target_carbohydrates DOUBLE PRECISION NOT NULL,
                target_fats DOUBLE PRECISION NOT NULL,
                target_weight DOUBLE PRECISION NOT NULL,
                target_steps INTEGER NOT NULL
                )
                """
        };

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            for (String sql : createTableSql) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
