package sample.database;

import sample.model.User;

import java.sql.*;
import java.time.Period;

public class DatabaseHandler extends Configs
{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void signUpUser(User user)
    {
        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," + Const.USERS_USERNAME + ","
                + Const.USERS_PASSWORD + "," + Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?)";

        try
        {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user)
    {
        ResultSet resultSet = null;

        if (!user.getUsername().equals("") || !user.getPassword().equals(""))
        {
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD + "=?";

            try
            {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());


                resultSet = preparedStatement.executeQuery();
            }
            catch (SQLException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Please enter your correct credentials");
        }
        return resultSet;
    }
}
