package Repository;

import Config.DatabaseConfiguration;
import Domain.User.DefaultUser;
import Domain.User.PremiumUser;
import Domain.User.User;
import Services.AuditService;

import java.sql.*;

public class UserRepository {

    private final AuditService auditService = new AuditService();

    public void getAllUsers(){
        String selectSql = "select * from Users";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                double height = resultSet.getDouble("height");
                String userType = resultSet.getString("user_type");
                Integer loyaltyPoints = resultSet.getObject("loyalty_points", Integer.class); // Nullable
                Boolean hasAccessToAdvancedFeatures = resultSet.getObject("has_access_to_advanced_features", Boolean.class); // Nullable

                System.out.println("Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Weight: " + weight);
                System.out.println("Height: " + height);
                System.out.println("User Type: " + userType);

                if ("PremiumUser".equals(userType)) {
                    System.out.println("Loyalty Points: " + loyaltyPoints);
                } else if ("DefaultUser".equals(userType)) {
                    System.out.println("Has Access to Advanced Features: " + hasAccessToAdvancedFeatures);
                }

                System.out.println(); // Blank line between users
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String selectSql = "SELECT * FROM Users WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                double height = resultSet.getDouble("height");
                String userType = resultSet.getString("user_type");
                Integer loyaltyPoints = resultSet.getObject("loyalty_points", Integer.class); // Nullable
                Boolean hasAccessToAdvancedFeatures = resultSet.getObject("has_access_to_advanced_features", Boolean.class); // Nullable

                if ("PremiumUser".equals(userType)) {
                    return new PremiumUser(name, age, weight, height, loyaltyPoints);
                } else if ("DefaultUser".equals(userType)) {
                    return new DefaultUser(name, age, weight, height, hasAccessToAdvancedFeatures);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(User user) {
        if (!userExists(user.getName())) {
            String insertSql = "INSERT INTO Users (name, age, weight, height, user_type, loyalty_points, has_access_to_advanced_features) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.setDouble(3, user.getWeight());
                statement.setDouble(4, user.getHeight());

                if (user instanceof PremiumUser) {
                    statement.setString(5, "PremiumUser");
                    statement.setInt(6, ((PremiumUser) user).getLoyaltyPoints());
                    statement.setNull(7, java.sql.Types.BOOLEAN);
                } else if (user instanceof DefaultUser) {
                    statement.setString(5, "DefaultUser");
                    statement.setNull(6, java.sql.Types.INTEGER);
                    statement.setBoolean(7, ((DefaultUser) user).getHasAccessToAdvancedFeatures());
                }
                statement.executeUpdate();
                auditService.logAction("INSERT", "Users", user.getName());
                System.out.println("User inserted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User already exists: " + user.getName());
        }
    }

    private boolean userExists(String name) {
        String selectSql = "SELECT 1 FROM Users WHERE name = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(int id) {
        String deleteSql = "DELETE FROM Users WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            auditService.logAction("DELETE", "Users", "User " + id + " deleted successfully.");
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, User user) {
        String updateSql = "UPDATE Users SET name = ?, age = ?, weight = ?, height = ?, user_type = ?, loyalty_points = ?, has_access_to_advanced_features = ? WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setDouble(3, user.getWeight());
            statement.setDouble(4, user.getHeight());

            if (user instanceof PremiumUser) {
                statement.setString(5, "PremiumUser");
                statement.setInt(6, ((PremiumUser) user).getLoyaltyPoints());
                statement.setNull(7, java.sql.Types.BOOLEAN);
            } else if (user instanceof DefaultUser) {
                DefaultUser defaultUser = (DefaultUser) user;
                if (defaultUser.getHasAccessToAdvancedFeatures()) {
                    // Convert to PremiumUser
                    statement.setString(5, "PremiumUser");
                    statement.setInt(6, 100); // Set loyalty points to 100
                    statement.setNull(7, java.sql.Types.BOOLEAN);
                } else {
                    statement.setString(5, "DefaultUser");
                    statement.setNull(6, java.sql.Types.INTEGER);
                    statement.setBoolean(7, defaultUser.getHasAccessToAdvancedFeatures());
                }
            }
            statement.setInt(8, id);

            statement.executeUpdate();
            auditService.logAction("UPDATE", "Users", user.getName());
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
