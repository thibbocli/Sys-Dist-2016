/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entites.Logs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thibault
 */
@MessageDriven(activationConfig =
{
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/BanqueTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BanqueTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"), //doit mettre une majuscule au D !!!!!
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/BanqueTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageBeanClient implements MessageListener
{
    @PersistenceContext(unitName = "EABanque-ejbPU")
    private EntityManager em;
    
    public MessageBeanClient()
    {
    }
    
    @Override
    public void onMessage(Message message)
    {
        Logs l = null;
        try
        {
            TextMessage tm = (TextMessage)message;
            if(!tm.getStringProperty("type").equals("infoEmp"))
            {
                l = new Logs(tm.getText());
                em.persist(l);
            }
        } 
        catch (JMSException e)
        {
            System.err.println("une erreur de réception de message");
        }
        
        
    }

    public void persist(Object object)
    {
        em.persist(object);
    }
    
}
