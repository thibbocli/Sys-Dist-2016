/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationbanqueclient;

import entites.Compte;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import util.CreditEnAttente;

/**
 *
 * @author Thibault
 */
public class GUIAdmin extends javax.swing.JFrame implements MessageListener
{
    private Topic topic = null;
    private Session session = null;
    
    private MessageConsumer consumer = null;
    private MessageProducer producer = null;
    
    private List<String> listValidee = new ArrayList<>();
    private List<CreditEnAttente> ListCredit = new ArrayList<>();
    
    
    
    public GUIAdmin(Topic t,Session s)
    {
        initComponents();
        LBMontant.setText("0");
        topic=t;
        session=s;
        
        try
        {
            consumer=session.createConsumer(topic);
            consumer.setMessageListener(this);
            
            producer=session.createProducer(topic);
        } catch (JMSException e)
        {
            System.err.println(e);
        }
        
        
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

        jScrollPane1 = new javax.swing.JScrollPane();
        LIListeValidee = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LBMontant = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LIListCredit = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LBNom = new javax.swing.JLabel();
        LBMontantCredit = new javax.swing.JLabel();
        LBSalaire = new javax.swing.JLabel();
        LBDuree = new javax.swing.JLabel();
        LBPrenom = new javax.swing.JLabel();
        LBTaux = new javax.swing.JLabel();
        LBChargeCredit = new javax.swing.JLabel();
        BTAccepter = new javax.swing.JButton();
        BTRefuser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LIListeValidee.setEnabled(false);
        jScrollPane1.setViewportView(LIListeValidee);

        jLabel1.setText("demandes validées automatiquement : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Administration");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Montant total des transactions : ");

        LBMontant.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBMontant.setForeground(new java.awt.Color(0, 153, 0));

        jLabel4.setText("Demande en attente de validation : ");

        LIListCredit.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                LIListCreditValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(LIListCredit);

        jLabel5.setText("nom :");

        jLabel6.setText("Prenom :");

        jLabel7.setText("montant :");

        jLabel8.setText("taux :");

        jLabel9.setText("salaire :");

        jLabel10.setText("charge de crédit :");

        jLabel11.setText("durée :");

        BTAccepter.setText("Accepter");
        BTAccepter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BTAccepterActionPerformed(evt);
            }
        });

        BTRefuser.setText("Refuser");
        BTRefuser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BTRefuserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(LBMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LBSalaire, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LBNom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LBMontantCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LBDuree, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(LBTaux, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(LBPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(BTAccepter)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BTRefuser))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(LBChargeCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(jLabel2)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LBMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6)
                                                .addComponent(LBNom, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(LBPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8))
                                            .addComponent(LBTaux, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(LBMontantCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                    .addComponent(LBChargeCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(LBSalaire, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(LBDuree, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BTAccepter)
                                .addComponent(BTRefuser)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LIListCreditValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_LIListCreditValueChanged
    {//GEN-HEADEREND:event_LIListCreditValueChanged
        try
        {
            CreditEnAttente selection = (CreditEnAttente)LIListCredit.getSelectedValue();
            if(selection!=null)
            {
                LBNom.setText(selection.GetCredit().getRefclient().getNom());
                LBPrenom.setText(selection.GetCredit().getRefclient().getPrenom());
                LBMontantCredit.setText(Double.toString(selection.GetCredit().getMontant()));
                LBDuree.setText(Integer.toString(selection.GetCredit().getDuree()));
                LBChargeCredit.setText(Double.toString(selection.GetCredit().getChargecredit()));
                LBTaux.setText(Double.toString(selection.GetCredit().getTaux()));
                LBSalaire.setText(Double.toString(selection.GetCredit().getSalaire()));
            }
            else
            {
                LBNom.setText("");
                LBPrenom.setText("");
                LBMontantCredit.setText("");
                LBDuree.setText("");
                LBChargeCredit.setText("");
                LBTaux.setText("");
                LBSalaire.setText("");
            }
            
            // a finir !!!!!!!!!!!!
        } catch (Exception e)
        {
        }
        
    }//GEN-LAST:event_LIListCreditValueChanged

    private void BTAccepterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BTAccepterActionPerformed
    {//GEN-HEADEREND:event_BTAccepterActionPerformed
        CreditEnAttente selection = (CreditEnAttente)LIListCredit.getSelectedValue();
        if(selection!=null)
        {
            ValidationCredit(selection);
        }
        
    }//GEN-LAST:event_BTAccepterActionPerformed

    private void BTRefuserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BTRefuserActionPerformed
    {//GEN-HEADEREND:event_BTRefuserActionPerformed
        CreditEnAttente selection = (CreditEnAttente)LIListCredit.getSelectedValue();
        if(selection!=null)
            RefusCredit(selection);
    }//GEN-LAST:event_BTRefuserActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTAccepter;
    private javax.swing.JButton BTRefuser;
    private javax.swing.JLabel LBChargeCredit;
    private javax.swing.JLabel LBDuree;
    private javax.swing.JLabel LBMontant;
    private javax.swing.JLabel LBMontantCredit;
    private javax.swing.JLabel LBNom;
    private javax.swing.JLabel LBPrenom;
    private javax.swing.JLabel LBSalaire;
    private javax.swing.JLabel LBTaux;
    private javax.swing.JList LIListCredit;
    private javax.swing.JList LIListeValidee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onMessage(Message message)
    {
        try
        {
            TextMessage tm = (TextMessage)message;
            if(tm.getStringProperty("type").equals("credit"))
            {
                if(tm.getBooleanProperty("accorde"))
                {
                    listValidee.add(tm.getText());
                    refreshListValidee();
                }
                else
                {
                    ListCredit.add(new CreditEnAttente(tm));
                    refreshListCredit();
                }
                return;
            }
            
            if(tm.getStringProperty("type").equals("transfert"))
            {
                addfloatlabel(LBMontant, tm.getFloatProperty("montant"));
                return;
            }
            
            System.err.println("type non reconnu : " + tm.getStringProperty("type"));
            
        } catch (JMSException e)
        {
            System.err.println(e);
        }
    }
    
    //permet d'ajouter un float a un label qui contien un float
    private boolean addfloatlabel(JLabel lab,float toadd)
    {
        try
        {
            float valeur = Float.parseFloat(lab.getText());
            valeur = valeur+toadd;
            lab.setText(Float.toString(valeur));
            return true;
        } 
        catch (Exception e)
        {
            System.err.println("le float du label est illisible");
            return false;
        }
    }
    
    private void refreshListValidee()
    {
        DefaultListModel dlm = new DefaultListModel();
        
        for(String s : listValidee)
            dlm.addElement(s);
        
        LIListeValidee.setModel(dlm);
    }
    
    private void refreshListCredit()
    {
        DefaultListModel dlm = new DefaultListModel();
        
        for(CreditEnAttente c : ListCredit)
            dlm.addElement(c);
        
        LIListCredit.setModel(dlm);
        
        LBNom.setText("");
        LBPrenom.setText("");
        LBMontantCredit.setText("");
        LBDuree.setText("");
        LBChargeCredit.setText("");
        LBTaux.setText("");
        LBSalaire.setText("");
        
    }


    private void ValidationCredit(CreditEnAttente c)
    {
            try
            {
                TextMessage tm = session.createTextMessage();
                
                
                tm.setStringProperty("type", "credit");
                tm.setFloatProperty("montant", new Float(c.GetCredit().getMontant()));
                tm.setBooleanProperty("accorde", true);
                tm.setFloatProperty("taux", new Float(c.GetCredit().getTaux()));
                tm.setIntProperty("duree", c.GetCredit().getDuree());
                tm.setFloatProperty("salaire", new Float(c.GetCredit().getSalaire()));
                tm.setFloatProperty("chargeDeCredit", new Float(c.GetCredit().getChargecredit()));
                tm.setStringProperty("client", c.GetCredit().getRefclient().getLogin());
                tm.setText("Crédit accepter par l'admin pour : "+c.GetCredit().getRefclient().getNom() + " montant : " + c.GetCredit().getMontant());
                producer.send(tm);
                
                //pour etre sur normalement pas besoin
                //tm=session.createTextMessage();
                tm.setStringProperty("type", "InfoEmp");
                tm.setStringProperty("login", c.GetLogin());
                tm.setBooleanProperty("valide", true);
                tm.setStringProperty("nomClient", c.GetCredit().getRefclient().getNom());
                producer.send(tm);
                
                ListCredit.remove(c);
                refreshListCredit();
                
            } catch (JMSException e)
            {
                System.err.println(e);
            }
    }
    
    private void RefusCredit(CreditEnAttente c)
    {
        try
        {
            TextMessage tm=session.createTextMessage();
            tm.setStringProperty("type", "InfoEmp");
            tm.setStringProperty("login", c.GetLogin());
            tm.setBooleanProperty("valide", false);
            tm.setStringProperty("nomClient", c.GetCredit().getRefclient().getNom());
            producer.send(tm);
        } catch (JMSException e)
        {
            System.err.println(e);
        }
        
        ListCredit.remove(c);
        refreshListCredit();
    }
    
    
}
