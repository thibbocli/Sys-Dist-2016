/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entites.Client;
import entites.Compte;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Thibault
 */
@Remote
public interface SessionBeanClientRemote {

    Client LoginClient();

    List<Compte> GetComptes(Client c);

    public boolean TransfereDeA(Compte donneur, String IdReceveur, float montant);
    
}
