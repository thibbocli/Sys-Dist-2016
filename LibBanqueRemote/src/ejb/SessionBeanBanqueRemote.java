/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entites.Client;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Thibault
 */
@Remote
public interface SessionBeanBanqueRemote {

    void LoginAdmin();

    void LoginEmploye();

    int DemandeCredit(float montant, float taux, int duree, float salaire, float chargeDeCredit, Client client);

    List<Client> GetClients();

    Client GetClientByLogin(String loginClient);

    String getEmployeLogin();
    
}
