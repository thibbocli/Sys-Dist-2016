/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entites.Client;
import entites.Credits;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/BanqueTopic2"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/BanqueTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/BanqueTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageBeanBanque implements MessageListener
{
    @PersistenceContext(unitName = "EABanque-ejbPU")
    private EntityManager em;
    
    public MessageBeanBanque()
    {
    }
    
    @Override
    public void onMessage(Message message)
    {
        try
        {
            TextMessage tm = (TextMessage)message;
            if(tm.getStringProperty("type").compareTo("credit")==0 && tm.getBooleanProperty("accorde"))
            {
            
                boolean aValider = tm.getBooleanProperty("accorde");
                float montant = tm.getFloatProperty("montant");
                float taux = tm.getFloatProperty("taux");
                int duree = tm.getIntProperty("duree");
                float salaire = tm.getFloatProperty("salaire");
                float chargeDeCredit = tm.getFloatProperty("chargeDeCredit");
                String login = tm.getStringProperty("client");
                Client client = (Client)em.createNamedQuery("Client.findByLogin").setParameter("login", login).getSingleResult();
            
                Credits credits = new Credits(montant,taux,duree,salaire,chargeDeCredit,aValider,client);
            
                persist(credits);
            }
        } 
        catch (JMSException e)
        {
            System.err.println(e);
        }
        
    }

    public void persist(Object object)
    {
        em.persist(object);
    }
    
}
