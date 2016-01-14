/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationbanqueclient;

import ejb.SessionBeanClientRemote;
import entites.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Thibault
 */
public class Main {
    
    @Resource(mappedName = "jms/BanqueTopic")
    private static Topic banqueTopic;
    @Resource(mappedName = "jms/BanqueTopicFactory")
    private static ConnectionFactory banqueTopicFactory;
    //@EJB
    //private static SessionBeanClientRemote sessionBeanClient;
    
    private static Connection connection = null;
    private static Session session = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            connection=banqueTopicFactory.createConnection();
            session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            connection.start();
        } catch (JMSException e)
        {
            System.err.println(e);
        }
        
        GUISelect guis = new GUISelect(banqueTopic, session);
        guis.setVisible(true);
    }
    
}
