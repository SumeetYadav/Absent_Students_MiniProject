package com.sms.core;

import com.constants.core.Constants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class Send_SMS 
{
	public static void sendSmsToStudent(String strStudentContactNo, String strStudentName)
	{
		Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);     		//initializing account_sid and auth_token
		
			Message message = Message.creator
			(
				new com.twilio.type.PhoneNumber(strStudentContactNo),             // message to i.e our resigtered number         
				new com.twilio.type.PhoneNumber(Constants.TWILIO_REGISTERED_NO),        // message from i.e twilio number        
				"Dear "+ strStudentName +" You have poor attendence since last 15 days. Please attend regular classes otherwise you will not allowed for further test.") // actual message you want to send on mobile
				.create();                                              // create on message creator                                   
				System.out.println(message.getSid()+" Message sent Successfully to " +strStudentName+" "+strStudentContactNo                     //this shows that message has sent sucessfully
			);
		
	}
}