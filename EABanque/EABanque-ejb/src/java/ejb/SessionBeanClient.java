/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entites.Client;
import entites.Compte;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Thibault
 */
@DeclareRoles("client")
@Stateless
public class SessionBeanClient implements SessionBeanClientRemote {
    @PersistenceContext(unitName = "EABanque-ejbPU")
    private EntityManager em;
    @Resource SessionContext ctx; 
    
    //@Resource private UserTransaction uTx;
    
    @Override
    @RolesAllowed("client")
    public Client LoginClient() 
    {
        
        String loginClient = ctx.getCallerPrincipal().getName();
        System.out.println(loginClient);
        Client client = em.find(Client.class, loginClient);
        System.out.println("id du client : " + client.getPrenom());
        return client;
    }

    @Override
    @RolesAllowed("client")
    public List<Compte> GetComptes(Client c)
    {
        return em.createNamedQuery("Compte.findByClient").setParameter("loginClient", c).getResultList();
    }

    @Override
    @RolesAllowed("client")
    public boolean TransfereDeA(Compte donneur, String IdReceveur, float montant)
    {
        try
        {
            Compte receveur = (Compte) em.createNamedQuery("Compte.findByIdcompte").setParameter("idcompte", IdReceveur).getSingleResult();
            
            
            Query q = em.createNamedQuery("Compte.retirersolde");
            q.setParameter("montant", montant);
            q.setParameter("idCompte", donneur.getIdcompte());
            q.executeUpdate();

            q=em.createNamedQuery("Compte.ajoutersolde");
            q.setParameter("montant", montant);
            q.setParameter("idCompte", receveur.getIdcompte());
            q.executeUpdate();

            return true;
        } 
        catch (Exception e)
        {
            System.err.println(e);
            return false;
        }
    }
    
    
   
    
    
}
