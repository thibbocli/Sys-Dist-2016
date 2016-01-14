/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entites.Client;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thibault
 */
@DeclareRoles({"admin","employe"})
@Stateless
public class SessionBeanBanque implements SessionBeanBanqueRemote {
    @Resource(mappedName = "jms/BanqueTopic")
    private Topic banqueTopic;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    @PersistenceContext(unitName = "EABanque-ejbPU")
    private EntityManager em;
    
    @Resource SessionContext ctx; 

    @Override
    @RolesAllowed("admin")
    public void LoginAdmin() {
    }

    @Override
    @RolesAllowed("employe")
    public void LoginEmploye() {
    }

    @Override
    @RolesAllowed("employe")
    public int DemandeCredit(float montant, float taux, int duree, float salaire, float chargeDeCredit, Client client)
    {
        try
        {
            if(montant < 250000 && chargeDeCredit < 40*salaire/100)
            {
                String msg = "crédit accepté pour le client: "+client.getNom() +" montant: "+montant+" taux: "+taux+" durée: "+duree;
                //envoi un message et le false est pour dire qu'il est deja validé
                sendCreditToBanqueTopic(msg,true,montant,taux,duree,salaire,chargeDeCredit,client);
                return 1;
            }
            else
            {
                String msg = "crédit en attente pour le client: "+client.getNom() +" montant: "+montant+" taux: "+taux+" durée: "+duree;
                //envoi un message et le false est pour dire qu'il n'est pas validé
                sendCreditToBanqueTopic(msg,false,montant,taux,duree,salaire,chargeDeCredit,client);
                return 0;
            }
        } 
        catch (Exception e)
        {
            System.err.println(e);
            return -1;
        }
    }

    @Override
    public List<Client> GetClients()
    {
        return em.createNamedQuery("Client.findAll").getResultList();
    }

    public void persist(Object object)
    {
        em.persist(object);
    }

    private void sendCreditToBanqueTopic(String messageData,boolean accorde,float montant, float taux, int duree, float salaire, float chargeDeCredit, Client client)
    {
        TextMessage tm = context.createTextMessage();
        try
        {
            //défini le type entre log ou crédit
            tm.setStringProperty("type", "credit");
            
            //ajout du login de l'employé
            tm.setStringProperty("loginEmploye", ctx.getCallerPrincipal().getName());
            
            
            //texte qui sera afficher par le messagebean dans la table log
            tm.setText(messageData);
            
            //données utilisees pour inserer dans table crédit
            tm.setBooleanProperty("accorde", accorde);
            tm.setFloatProperty("montant", montant);
            tm.setFloatProperty("taux", taux);
            tm.setIntProperty("duree", duree);
            tm.setFloatProperty("salaire", salaire);
            tm.setFloatProperty("chargeDeCredit", chargeDeCredit);
            tm.setStringProperty("client", client.getLogin());
            
            context.createProducer().send(banqueTopic, tm);
            
        } catch (JMSException e)
        {
            System.err.println(e);
        }
        
        
        
    }

    @Override
    public Client GetClientByLogin(String loginClient)
    {
        return (Client)em.createNamedQuery("Client.findByLogin").setParameter("login", loginClient).getSingleResult();
    }

    @Override
    public String getEmployeLogin()
    {
        return ctx.getCallerPrincipal().getName();
    }
}
