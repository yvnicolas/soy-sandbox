package com.soysandbox;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 *A service to send Email or SMS to subscribers 
 *
 * @author yves
 *
 */

public class DynMailSMSServiceImpl  {

    private static final Logger logger = LoggerFactory.getLogger(DynMailSMSServiceImpl.class);
    private static final String UTF8 = "UTF-8";

 
    private static final String HOST ="mail.gandi.net";
    private static final String SENDER ="service-message@dynamease.net";
    private static final String PWD = "PEP1N1eres";

  

    private HtmlEmail initEmail() {
    	HtmlEmail email = new HtmlEmail();
        email.setHostName(HOST);
        email.setCharset(UTF8);

        email.setAuthentication(SENDER, PWD);
      
        email.setDebug(false);
        email.setSmtpPort(587);
        return email;
    }

   
 
    
    public void sendMail(String address, String subject, String message) {
        HtmlEmail toSend = initEmail();
        toSend.setSubject(subject);
        try {
            toSend.setHtmlMsg(message);
            toSend.setFrom("smartcontact@dynamease.com");

            toSend.addTo(address);
            toSend.send();
        } catch (EmailException e) {
            logger.error("Exception raised sending email to {} : {}", address, e.getMessage(), e);
        }

    }
  
}
