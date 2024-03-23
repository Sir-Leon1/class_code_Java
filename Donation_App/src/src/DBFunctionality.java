package src;

import java.sql.*;

public class DBFunctionality {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/donationapp_user_details";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void initializeDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connection");
            //loadUsersDB(connection);
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void addUserToDB(String email, String username, String password, String location, String usertype, String phoneno){

        String insertSQL = "INSERT INTO user_details(email, username, password, location, userType, phoneNo) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, usertype);
            preparedStatement.setString(6, phoneno);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Success");
            }
            //TODO : implement an if statement to check if the user was added.
            } catch (SQLException e){
            System.out.println("Exception caught");
            e.printStackTrace();
        }
    }
}

