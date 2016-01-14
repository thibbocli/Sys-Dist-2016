/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationbanqueclient;

import ejb.SessionBeanBanqueRemote;
import ejb.SessionBeanClientRemote;
import entites.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Thibault
 */
public class GUISelect extends javax.swing.JFrame
{
    private Topic topic=null;
    private Session session=null;
    /**
     * Creates new form GUISelect
     */
    public GUISelect(Topic t,Session s)
    {
        topic=t;
        session=s;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        BTClient = new javax.swing.JButton();
        BTEmploye = new javax.swing.JButton();
        BTAdmin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTClient.setText("Client");
        BTClient.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BTClientActionPerformed(evt);
            }
        });

        BTEmploye.setText("Employé");
        BTEmploye.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BTEmployeActionPerformed(evt);
            }
        });

        BTAdmin.setText("Administrateur");
        BTAdmin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BTAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(BTClient)
                .addGap(54, 54, 54)
                .addComponent(BTEmploye)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(BTAdmin)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTEmploye)
                    .addComponent(BTClient)
                    .addComponent(BTAdmin))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTClientActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BTClientActionPerformed
    {//GEN-HEADEREND:event_BTClientActionPerformed
        Client client = lookupSessionBeanClientRemote().LoginClient();
        
        
        GUIClient guic = new GUIClient(client,topic,session);
        guic.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTClientActionPerformed

    private void BTEmployeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BTEmployeActionPerformed
    {//GEN-HEADEREND:event_BTEmployeActionPerformed
        lookupSessionBeanBanqueRemote().LoginEmploye();
        
        GUIEmploye guie = new GUIEmploye(topic,session);
        guie.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTEmployeActionPerformed

    private void BTAdminActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BTAdminActionPerformed
    {//GEN-HEADEREND:event_BTAdminActionPerformed
        lookupSessionBeanBanqueRemote().LoginAdmin();
        
        GUIAdmin guia = new GUIAdmin(topic, session);
        guia.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTAdminActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTAdmin;
    private javax.swing.JButton BTClient;
    private javax.swing.JButton BTEmploye;
    // End of variables declaration//GEN-END:variables

    private SessionBeanClientRemote lookupSessionBeanClientRemote()
    {
        try
        {
            Context c = new InitialContext();
            return (SessionBeanClientRemote) c.lookup("java:comp/env/SessionBeanClient");
        } catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
