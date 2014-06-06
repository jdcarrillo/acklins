/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;

/**
 *
 * @author jcarrillo
 *
 *
 */
public class MailSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        InfoAuth infoData = new InfoAuth();
        GetHTML getHtml = new GetHTML();
        
        infoData.setTtls_true_false("true");
        infoData.setServer("smtp.gmail.com");
        infoData.setPort("587");
        infoData.setPath("/home/jcarrillo/archivo.txt");
        infoData.setUrl("http://www.google.com");
        infoData.setUser("juan.carrillo@acklins.net");
        infoData.setPassword("juancarrillo");
        infoData.setFrom("juan.carrillo@acklins.net");
        infoData.setTo("juan.carrillo@acklins.net");
        infoData.setCc("juan.carrillo@acklins.net,diego.moreano@acklins.net");
        infoData.setBcc("juan.carrillo@acklins.net");
        infoData.setSubject("MENSAJE PRUEBA");
        //infoData.setBody("MENSAJE" + getHtml.getHTML(url));
        if(getHtml.getHTML(infoData.getUrl())==null)
            infoData.setBody("ESTE ES EL BODY DEL MENSAJE...");
        else
            infoData.setBody("MENSAJE \n\n" + getHtml.getHTML(infoData.getUrl()));
            
        infoData.sender();
        

    }
}
