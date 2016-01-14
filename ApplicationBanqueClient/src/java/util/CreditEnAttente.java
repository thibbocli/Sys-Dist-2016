/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import ejb.SessionBeanBanqueRemote;
import ejb.SessionBeanClientRemote;
import entites.Client;
import entites.Credits;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Thibault
 */
public class CreditEnAttente 
{
    private Credits credits=null;
    private String login=null;
    
    public CreditEnAttente(TextMessage tm)
    {
        try
        {
            boolean aValider = tm.getBooleanProperty("accorde");
            float montant = tm.getFloatProperty("montant");
            float taux = tm.getFloatProperty("taux");
            int duree = tm.getIntProperty("duree");
            float salaire = tm.getFloatProperty("salaire");
            float chargeDeCredit = tm.getFloatProperty("chargeDeCredit");
            String loginClient = tm.getStringProperty("client");
            Client client = lookupSessionBeanBanqueRemote().GetClientByLogin(loginClient);

            credits = new Credits(montant,taux,duree,salaire,chargeDeCredit,aValider,client);
            login=tm.getStringProperty("loginEmploye");
        } catch (Exception e)
        {
            System.err.println(e);
        }
        
    }
    
    public Credits GetCredit()
    {
        return credits;
    }
    
    public String GetLogin()
    {
        return login;
    }
    
    @Override
    public String toString()
    {
        return "client : " + credits.getRefclient().getNom();
    }

    private SessionBeanBanqueRemote lookupSessionBeanBanqueRemote()
    {
        try
        {
            Context c = new InitialContext();
            return (SessionBeanBanqueRemote) c.lookup("java:comp/env/SessionBeanBanque");
        } catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
