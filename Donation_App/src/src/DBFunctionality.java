package src;

import java.sql.*;

public class DBFunctionality {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/donationapp_user_details";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    //TODO Change the password in MYSQL

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

    public static void addUserToDB(Users newUsers){
        String insertSQL = "INSERT INTO user_details(email, username, password, location, userType, phoneNo) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, newUsers.getEmail());
            preparedStatement.setString(2, newUsers.getUsername());
            preparedStatement.setString(3, newUsers.getPassword());
            preparedStatement.setString(4, newUsers.getLocation());
            preparedStatement.setString(5, newUsers.getUserType());
            preparedStatement.setString(6, newUsers.getPhoneNO());

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

class Users {
    private String email;
    private String username;
    private String password;
    private String location;
    private String userType;
    private String phoneNO;

    public Users(String email, String username, String password, String location, String userType, String phoneNo){
        this.email = email;
        this.username = username;
        this.password = password;
        this.location = location;
        this.userType = userType;
        this.phoneNO = phoneNo;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getUserType() {
        return userType;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }
}
