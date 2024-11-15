package sahu.chopstick_game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@SuppressWarnings({ "deprecation", "unused" })
public class Mails extends SimpleEmail{
	public static Email sendemail;
	
	public static void sahil() throws EmailException{
		sendemail = new SimpleEmail();
        sendemail.setSmtpPort(587);
        sendemail.setAuthenticator(new DefaultAuthenticator("voidmatrix00@gmail.com",
        													"tcdepyxqpymwvwga"));//password and email
        sendemail.setDebug(false);
        sendemail.setHostName("smtp.gmail.com");
        sendemail.setFrom("voidmatrix00@gmail.com");
	}
	
	public static void Activity(String x) throws EmailException{
		sahil();
		sendemail.setSubject("activity");
        sendemail.setMsg("game is being played by "+x); 
        sendemail.addTo("sahillalani1511@gmail.com");
        sendemail.setTLS(true);
        sendemail.send(); //sending email
	}
	
	public static void getFeedBack(String msg,String name) throws EmailException {
		sahil();
        sendemail.setSubject("Feedback");
        sendemail.setMsg(name+" : "+msg); 
        sendemail.addTo("sahillalani1511@gmail.com");
        sendemail.setTLS(true);
        sendemail.send(); //sending email
	}
	
	public static void sendgreet(String g,String unm,String pwd) throws EmailException {
		sahil();
        sendemail.setSubject("VoidMatrix Account Sign Up");
        sendemail.setMsg("Thank You For Signing Up With VoidMatrix\nUsername: "+unm+"\nPassword: "+pwd); //Your Email Address
        sendemail.addTo(g); // Receiver Email Address
        sendemail.setTLS(true);
        sendemail.send(); //sending email
	}

}
