package com.email.core;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.constants.core.Constants;

public class Send_Email 
{
	public static void sendEmailToStudent(String strStudentMailId, String studentName)
	{
		// authentication info

		// smpt is server from which we are fetching the massage
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// create session
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.EMAIL_USERNAME, Constants.EMAIL_PASSWORD);
			}
		});
		// Start our mail message
		MimeMessage message = new MimeMessage(session);
		try
		{
			message.setFrom(new InternetAddress(Constants.SENDER_MAIL_ID));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(strStudentMailId));

			// put subject line here
			message.setSubject("Poor Attendence");

			// if we are sending only message without media or file the use msg.setText
			message.setText("Dear "+studentName+",\r\r\tWe are writing this mail in order to inform you about your short attendance in class, please be present to the class regularly, othewise you will not allow to seat for further examination.\r\rRegards,\rThink Quotient team" );
			
			Transport.send(message);

			System.out.println("email Sent Successfully to " +studentName+" "+strStudentMailId);// to confirm the msg sent successfully or not?
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		} 
	}
}