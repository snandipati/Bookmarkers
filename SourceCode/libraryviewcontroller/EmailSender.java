/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryviewcontroller;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.*;

/**
 *
 * @author Silpa
 */
/* This class is used to send an email for the Automated Library Assistant System */
public class EmailSender extends Thread{
    /***    Variables Declaration    ***/ 
        String from;
        String password;
        String message;
        String to[];

        /* Constructor of EmailSender */
        public EmailSender(String from, String password, String message, String to[])
                {
           this.from = from;
           this.password = password;
           this.message = message;
           this.to = to;
                }
        public void run(){
            sendMail(from, password, message, to);
        }

        /* A method to send an email */
        public void sendMail (String from, String password, String message, String to[] )
        {

                    String host = "smtp.gmail.com";
                    Properties props = System.getProperties();
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.user", from);
                    props.put("mail.smtp.password", password);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");

                    Session session = Session.getDefaultInstance(props,null);
                    MimeMessage mimeMessage = new MimeMessage(session);
                    try{

                        mimeMessage.setFrom(new InternetAddress(from));
                        InternetAddress[] toAddress = new InternetAddress[to.length];
                        for(int i=0; i<to.length; i++){
                            toAddress[i] = new InternetAddress(to[i]);
                        }
                        for(int i=0; i<toAddress.length; i++){
                            mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
                        }
                        //setting the subject of an email
                        mimeMessage.setSubject("BookMarkers Library eNotify");
                        mimeMessage.setText(message);
                        Transport transport = session.getTransport("smtp");
                        transport.connect(host,from,password);
                        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                        transport.close();
                        //return true;
                    }
                    catch(MessagingException me){
                        me.printStackTrace();   
                    }



            //return false;
        }
}
