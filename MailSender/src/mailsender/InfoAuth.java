/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Scanner;

/**
 *
 * @author jcarrillo
 */
public class InfoAuth {

    private String user, password, from, to, cc, bcc, subject, body, path;
    private String port;
    private String server;
    private String ttls_true_false;
    private String url;
    
    
    InfoAuth() {
    }


    public InfoAuth(String user,String password, String from, String to, String cc,String bcc,String subject, String body,String path,
                    String port,String server,String ttls_true_false,String url) {
        this.user = user;
        this.password = password;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.path=path;
        this.port=port;
        this.server=server;
        this.ttls_true_false=ttls_true_false;
        this.url=url;
        
    }
    
        public String inputData(){
        Scanner en = new Scanner(System.in);
        String data;
        data=en.next();
        return data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTtls_true_false() {
        return ttls_true_false;
    }

    public void setTtls_true_false(String ttls_true_false) {
        this.ttls_true_false = ttls_true_false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
   
     protected void sender() {

        try {

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", ttls_true_false);
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", port);

            String filename = path;

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user,password);//user,password
                    
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); //FROM
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));       //TO
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(cc));       //CC  
            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(bcc));       //BCC
            message.setSubject(subject);                   //SUBJECT

            //PARA GENERAR ATTACHMENT Y ENVIO DEL BODY
            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            //messageBodyPart.setText("This is message body");
            messageBodyPart.setContent(body, "text/html"); //BODY
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);


            //message.setContent(rInfo[5],"text/html");  //BODY HTML
//            message.setText(rInfo[5]
//                    + "\n\n No spam to my email, please!"); //BODY TEXT

            //Send Message
            Transport.send(message);

            System.out.println("Enviado..!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
