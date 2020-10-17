package Dao;

import java.sql.*;

import com.constants.core.Constants;

public class MySQL_Connection 
{
	//Method returns sql connection
	public static Connection getMySqlConnection()
	{
		Connection con = null;
		try
		{
			Class.forName(Constants.DRIVER);
			con = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
}