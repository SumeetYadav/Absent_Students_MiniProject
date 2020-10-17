package com.jdbc.core;

import java.sql.*;
import com.email.core.Send_Email;
import com.sms.core.Send_SMS;

import Dao.MySQL_Connection;

public class Absent_Students 
{	
	public static void getAbsentStudentList()
	{
		Connection con = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try
		{
			con = MySQL_Connection.getMySqlConnection();
			statement = con.createStatement();
			resultset = statement.executeQuery("select s.stud_id, s.name, s.contact_no, s.email from student_details s, attendence a\r\n" + 
					"where  s.stud_id = a.stud_id_fk and attendence = 0 and date(date_time) between DATE_SUB(curdate(), interval 15 day) and curdate() \r\n" + 
					"group by stud_id\r\n" + 
					"having count(attendence_id)>5");
			
			while(resultset.next())
			{
				int student_id = resultset.getInt(1);
				String student_name = resultset.getString(2);
				String student_contactNo = resultset.getString(3);
				String student_email =  resultset.getString(4);
				
				Send_SMS.sendSmsToStudent(student_contactNo, student_name);
				
				Send_Email.sendEmailToStudent(student_email, student_name);
				
				System.out.println(student_id + "\t"+ student_name +"\t"+ student_contactNo + "\t" + student_email);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(resultset != null)
			{
				try	
				{
						resultset.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(statement != null)
			{
				try
				{
						statement.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(con != null)
			{
				try
				{
						con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
